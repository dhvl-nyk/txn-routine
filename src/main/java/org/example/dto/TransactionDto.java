package org.example.dto;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static org.example.constants.TxnConstant.*;

@Data
@Builder
public class TransactionDto {

    @NotNull(message = OPERATION_ID_REQUIRED)
    private Long operationTypeId;

    @NotNull(message = ACC_ID_REQUIRED)
    private Long accountId;

    @NotNull(message = AMOUNT_REQUIRED)
//    @DecimalMin(value = "0.0", message = AMOUNT_MIN)
    private BigDecimal amount;
}
