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

    public Account createAccount(AccountDto accountDto) {
        Account accountDb = Account.builder().build();
        BeanUtils.copyProperties(accountDto, accountDb);
        return accountRepository.save(accountDb);
    }

    public Account getAccountInfo(Long id) {
        return   accountRepository.findById(id).get();
//        AccountDto accountDto = AccountDto.builder().build();
//        BeanUtils.copyProperties(accountDb, accountDto);
    }
}
