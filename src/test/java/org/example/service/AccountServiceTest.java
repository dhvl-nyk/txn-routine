package org.example.service;

import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;

    @Test
    void createAccount() {
    String accNo= "11234";
    Mockito.when(accountRepository.save(Mockito.any()))
            .thenReturn(Account.builder().accountId(1L).accountNumber(accNo).build());
    AccountDto accountDto = new AccountDto();
    accountDto.setAccountNumber(accNo);
    Account account = accountService.createAccount(accountDto);
    assertNotNull(account);
    assertEquals(account.getAccountId(), 1L);
    assertEquals(account.getAccountNumber(), accNo);
    }

    @Test
    void getAccountInfo() {
        String accNo= "11220";
        Mockito.when(accountRepository.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(Account.builder().accountId(1L).accountNumber("11220").build()));
        Account account = accountService.getAccountInfo(1L);
        assertNotNull(account);
        assertEquals(account.getAccountNumber(), accNo);
    }

    @Test
    void getAccountInfoFailure() {
        Mockito.when(accountRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->accountService.getAccountInfo(1L));
    }

//    @Test
//    void getAccountInfoFailure2() {
//        Mockito.when(accountRepository.findById(Mockito.any()))
//                .thenThrow(new RuntimeException());
//        assertThrows(ResourceNotFoundException.class,
//                ()->accountService.getAccountInfo(1L));
//    }
}