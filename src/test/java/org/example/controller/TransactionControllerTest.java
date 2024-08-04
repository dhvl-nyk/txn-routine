package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.TransactionDto;
import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.constants.TxnConstant.AMOUNT_REQUIRED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private TransactionService transactionService;

    @Test
    @DisplayName("Testing transactions endpoint")
    void createAccount() throws Exception {
        Transaction transaction = Transaction.builder().transactionId(1L).amount(BigDecimal.TEN).build();
        when(transactionService.createTransaction(any()))
                .thenReturn(transaction);
        TransactionDto transactionDto = TransactionDto.builder()
                .accountId(1L).operationTypeId(2L).amount(BigDecimal.TEN)
                .build();
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionId").value(transaction.getTransactionId()))
                .andExpect(jsonPath("$.amount").value(BigDecimal.TEN))
                ;
    }

    @Test
    @DisplayName("Testing transactions endpoint failure in case of bad request")
    void createAccountFailure() throws Exception {
        Transaction transaction = Transaction.builder().transactionId(1L).amount(BigDecimal.TEN).build();
        when(transactionService.createTransaction(any()))
                .thenReturn(transaction);
        TransactionDto transactionDto = TransactionDto.builder()
                .accountId(1L).operationTypeId(2L).build();
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Amount is required."))
                .andExpect(jsonPath("$.description").value("Request Failed"))
        ;
    }

}