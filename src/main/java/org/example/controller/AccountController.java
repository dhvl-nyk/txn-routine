package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Create an account")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDto accountDto) {
        Account account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Retrieve the account information")
    public ResponseEntity<Account> getAccountInfo(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountInfo(accountId));
    }

}
