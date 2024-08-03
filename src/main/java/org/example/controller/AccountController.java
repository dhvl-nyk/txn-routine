package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto) {
        Account account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountInfo(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountInfo(accountId));
    }

}
