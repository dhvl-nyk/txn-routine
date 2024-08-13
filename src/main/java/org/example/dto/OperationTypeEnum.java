package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationTypeEnum {
    NORMAL_PURCHASE("Normal Purchase"),
    WITHDRAWAL("Withdrawal"),
    CREDIT_VOUCHER("Credit Voucher"),
    PURCHASE_WITH_INSTALLMENTS("Purchase with installments");
    private final String value;
}
