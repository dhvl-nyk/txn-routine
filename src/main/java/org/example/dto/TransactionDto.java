package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static org.example.constants.TxnConstant.*;

@Data
@Builder
public class TransactionDto {

    @JsonProperty("operation_type_id")
    @NotNull(message = OPERATION_ID_REQUIRED)
    private Long operationTypeId;

    @JsonProperty("account_id")
    @NotNull(message = ACC_ID_REQUIRED)
    private Long accountId;

    @NotNull(message = AMOUNT_REQUIRED)
//    @DecimalMin(value = "0.0", message = AMOUNT_MIN)
    private BigDecimal amount;
}
