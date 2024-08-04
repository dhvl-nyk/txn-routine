package org.example.dto;

import lombok.Data;

import javax.validation.constraints.*;

import static org.example.constants.TxnConstant.ACC_NO_EMPTY;
import static org.example.constants.TxnConstant.ACC_NO_REQUIRED;

@Data
public class AccountDto {

    @NotNull(message = ACC_NO_REQUIRED)
    @Size(min = 1, message = ACC_NO_EMPTY)
    private String accountNumber;
}

