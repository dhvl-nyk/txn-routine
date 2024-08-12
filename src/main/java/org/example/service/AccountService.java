package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static org.example.constants.TxnConstant.ACC_NO_EXISTS;
import static org.example.constants.TxnConstant.ACC_NO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(AccountDto accountDto) {
        Account accountDb = Account.builder()
                .accountNumber(accountDto.getAccountNumber())
                .build();
         try {
            return accountRepository.save(accountDb);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException(ACC_NO_EXISTS, ex);
        }
    }

    public Account getAccountInfo(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (!account.isPresent())
            throw new ResourceNotFoundException(Collections.singletonList(String.format(ACC_NO_NOT_FOUND, accountId)));
        return account.get();
    }
}
