package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AccountDto;
import org.example.entity.Account;
import org.example.exception.ResourceNotFoundException;
import org.example.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.example.constants.TxnConstant.ACC_NO_NOT_FOUND;
import static org.example.constants.TxnConstant.ACC_NO_REQUIRED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("Testing createAccount")
    void createAccount() throws Exception {
        Account account = Account.builder().accountId(1L).accountNumber("ABC").build();
        when(accountService.createAccount(any()))
                .thenReturn(account);
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("111");
        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.accountNumber").value("ABC"))
        ;
    }

    @Test
    @DisplayName("Testing createAccount endpoint failure in case of bad request")
    void createAccountFailure() throws Exception {
        Account account = Account.builder().accountId(1L).accountNumber("ABC").build();
        when(accountService.createAccount(any()))
                .thenReturn(account);
        AccountDto accountDto = new AccountDto();
        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(ACC_NO_REQUIRED))
        ;
    }


    @Test
    @DisplayName("Testing getAccountInfo")
    void testGetAccountInfo() throws Exception {
        Account account = Account.builder().accountId(1L).accountNumber("ABC").build();
        when(accountService.getAccountInfo(anyLong())).thenReturn(account);
        mockMvc.perform(get("/api/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1L))
                .andExpect(jsonPath("$.accountNumber").value("ABC"))
        ;
    }

    @Test
    @DisplayName("Testing getAccountInfo")
    void testGetAccountInfoById_NotFound() throws Exception {
        List<String> errorList = Collections.singletonList(String.format(ACC_NO_NOT_FOUND, 1L));
        when(accountService.getAccountInfo(1L))
                .thenThrow(
                        new ResourceNotFoundException(errorList)
                );
        mockMvc.perform(get("/api/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorList.get(0)))
        ;
    }
}