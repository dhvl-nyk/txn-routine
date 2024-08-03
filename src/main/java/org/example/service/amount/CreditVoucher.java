package org.example.service.amount;

import org.example.dto.OperationTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditVoucher extends AmountStrategy {

    @Override
    public Boolean txnType(String txnType) {
        return txnType.equalsIgnoreCase("Credit Voucher");
    }

    @Override
    public BigDecimal txnValue(BigDecimal amount) {
        return amount;
    }
}
