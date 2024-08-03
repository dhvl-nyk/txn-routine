package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.TransactionDto;
import org.example.entity.Account;
import org.example.entity.OperationType;
import org.example.entity.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.OperationTypeRepository;
import org.example.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;
    public Transaction createTransaction(TransactionDto transactionDto) {
        Optional<Account> account = accountRepository.findById(transactionDto.getAccountId());
        Optional<OperationType> operationType = operationTypeRepository
                .findById(transactionDto.getOperationTypeId());

        Transaction transaction = Transaction.builder()
                .account(account.get())
                .operationType(operationType.get())
                .amount(transactionDto.getAmount())
                .eventDate(LocalDateTime.now())
                .build();

        return transactionRepository.save(transaction);
    }

}
