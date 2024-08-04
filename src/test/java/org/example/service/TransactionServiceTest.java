package org.example.service;

import org.example.dto.OperationTypeEnum;
import org.example.dto.TransactionDto;
import org.example.entity.Account;
import org.example.entity.OperationType;
import org.example.entity.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.OperationTypeRepository;
import org.example.repository.TransactionRepository;
import org.example.service.amount.AmountStrategy;
import org.example.service.amount.CreditVoucher;
import org.example.service.amount.PurchaseWithdraw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;
    @Mock private TransactionRepository transactionRepository;
    @Mock private AccountRepository accountRepository;
    @Mock private OperationTypeRepository operationTypeRepository;
    @Mock private CreditVoucher creditVoucher;

    @Spy List<AmountStrategy> amountStrategies = new ArrayList<>();

    @Test
    void createTransaction() {
        when(accountRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(Account.builder().accountId(1L).accountNumber("abc").build()));
        when(operationTypeRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(
                        OperationType.builder().operationTypeId(2L)
                                .description(OperationTypeEnum.CREDIT_VOUCHER.getValue())
                                .build())
                );
        amountStrategies.add(new CreditVoucher());
        amountStrategies.add(new PurchaseWithdraw());

        when(transactionRepository.save(any())).thenReturn(
            Transaction.builder().transactionId(2L).amount(BigDecimal.TEN).build()
        );
        Transaction transaction = transactionService
                .createTransaction(TransactionDto.builder().accountId(3L).operationTypeId(5L).build());
        assertNotNull(transaction);
        assertEquals(transaction.getTransactionId(),2L);
        assertEquals(transaction.getAmount(),BigDecimal.TEN);
    }
}