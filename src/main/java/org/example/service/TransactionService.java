package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.OperationTypeEnum;
import org.example.dto.TransactionDto;
import org.example.entity.Account;
import org.example.entity.OperationType;
import org.example.entity.Transaction;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.AccountRepository;
import org.example.repository.OperationTypeRepository;
import org.example.repository.TransactionRepository;
import org.example.service.amount.AmountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.constants.TxnConstant.ACC_NO_NOT_FOUND;
import static org.example.constants.TxnConstant.OPERATION_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;
    private final List<AmountStrategy> amountStrategies;
    public Transaction createTransaction(TransactionDto transactionDto) {
        Optional<Account> account = accountRepository.findById(transactionDto.getAccountId());
        Optional<OperationType> operationType = operationTypeRepository
                .findById(transactionDto.getOperationTypeId());
        verify(transactionDto, account, operationType);
//            throw new ResourceNotFoundException(
//                    String.format("Operation Type with ID %s not found", transactionDto.getOperationTypeId())
//            );
        Transaction transaction = Transaction.builder()
                .account(account.get())
                .operationType(operationType.get())
                .amount(determineAmount(transactionDto.getAmount(), operationType.get()))
                .eventDate(LocalDateTime.now())
                .build();
        return transactionRepository.save(transaction);
    }

    private void verify(TransactionDto transactionDto, Optional<Account> account, Optional<OperationType> operationType) {
        List<String> errors = new ArrayList<>();
        if(!account.isPresent()) errors.add(String.format(ACC_NO_NOT_FOUND, transactionDto.getAccountId()));
        if(!operationType.isPresent()) errors.add(String.format(OPERATION_ID_NOT_FOUND, transactionDto.getOperationTypeId()));
        if (errors.size() > 0)
            throw new ResourceNotFoundException(errors);
    }

    private BigDecimal determineAmount(BigDecimal amount, OperationType operationType) {
//        OperationTypeEnum.getInstance().;

        AmountStrategy strategy = amountStrategies.stream()
                .filter(amountStrategy -> amountStrategy.txnType(operationType.getDescription()))
                .findAny()
                .orElseThrow(RuntimeException::new);
        return strategy.txnValue(amount);
    }

}
