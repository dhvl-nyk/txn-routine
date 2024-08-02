package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountDto createAccount(AccountDto accountDto) {
        Account accountDb = Account.builder().build();
        BeanUtils.copyProperties(accountDto, accountDb);
        Account account = accountRepository.save(accountDb);
        accountDto.setAccountId(account.getAccountId());
        return accountDto;
    }

    public AccountDto getAccountInfo(Long id) {
        Account accountDb =  accountRepository.findById(id).get();
        AccountDto accountDto = AccountDto.builder().build();
        BeanUtils.copyProperties(accountDb, accountDto);
        return accountDto;
    }
}
