package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.OperationTypeEnum;
import org.example.dto.TransactionDto;
import org.example.entity.Account;
import org.example.entity.OperationType;
import org.example.entity.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.OperationTypeRepository;
import org.example.repository.TransactionRepository;
import org.example.service.amount.AmountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        Transaction transaction = Transaction.builder()
                .account(account.get())
                .operationType(operationType.get())
                .amount(determineAmount(transactionDto.getAmount(), operationType.get()))
                .eventDate(LocalDateTime.now())
                .build();
        return transactionRepository.save(transaction);
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
