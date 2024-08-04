package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum OperationTypeEnum {
    NORMAL_PURCHASE("Normal Purchase"),
    WITHDRAWAL("Withdrawal"),
    CREDIT_VOUCHER("Credit Voucher"),
    PURCHASE_WITH_INSTALLMENTS("Purchase with installments");
    private final String value;
//    NORMAL_PURCHASE {
//        @Override
//        public BigDecimal txnValue(BigDecimal amount) {
//            return amount;
//        }
//    },
//    WITHDRAWAL {
//        @Override
//        public BigDecimal txnValue(BigDecimal amount) {
//            return amount.negate();
//        }
//    },
//    CREDIT_VOUCHER {
//        @Override
//        public BigDecimal txnValue(BigDecimal amount) {
//            return amount.negate();
//        }
//    },
//    PURCHASE_WITH_INSTALLMENTS {
//        @Override
//        public BigDecimal txnValue(BigDecimal amount) {
//            return amount.negate();
//        }
//    };
//    private OperationTypeEnum(){}
//
//    public abstract BigDecimal txnValue(BigDecimal amount);

}
