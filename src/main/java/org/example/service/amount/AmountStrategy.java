package org.example.service.amount;

import java.math.BigDecimal;

public abstract class AmountStrategy {

    public abstract Boolean txnType(String txnType);

    public abstract BigDecimal txnValue(BigDecimal amount);
}
