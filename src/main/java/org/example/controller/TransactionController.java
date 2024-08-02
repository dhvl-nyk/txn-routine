package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class TransactionController {
    private final AccountService accountService;

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @GetMapping("/{accountId}")
    public AccountDto getAccountInfo(@PathVariable Long accountId) {
        return accountService.getAccountInfo(accountId);
    }
}
