package org.example.service.amount;

import org.example.dto.OperationTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class PurchaseWithdraw extends AmountStrategy {

    private final List<String> txnTypes = Arrays.asList(
            OperationTypeEnum.NORMAL_PURCHASE.getValue(),
            OperationTypeEnum.PURCHASE_WITH_INSTALLMENTS.getValue(),
            OperationTypeEnum.WITHDRAWAL.getValue()
    );
    @Override
    public Boolean txnType(String txnType) {
//        return !txnType.equalsIgnoreCase("Credit Voucher");
        return txnTypes.contains(txnType);
    }

    @Override
    public BigDecimal txnValue(BigDecimal amount) {
        return amount.negate();
    }
}
