package org.example.dto;

import java.math.BigDecimal;

public enum OperationTypeEnum {
    NORMAL_PURCHASE,
    WITHDRAWAL,
    CREDIT_VOUCHER,
    PURCHASE_WITH_INSTALLMENTS;
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
