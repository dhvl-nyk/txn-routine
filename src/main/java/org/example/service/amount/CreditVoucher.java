package org.example.service.amount;

import org.apache.commons.lang3.StringUtils;
import org.example.dto.OperationTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditVoucher extends AmountStrategy {

    @Override
    public Boolean txnType(String txnType) {
        return StringUtils.equalsIgnoreCase(txnType, OperationTypeEnum.CREDIT_VOUCHER.getValue());
    }

    @Override
    public BigDecimal txnValue(BigDecimal amount) {
        return amount;
    }
}
