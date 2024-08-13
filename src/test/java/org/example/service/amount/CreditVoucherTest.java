package org.example.service.amount;

import org.apache.commons.lang3.StringUtils;
import org.example.dto.OperationTypeEnum;
import org.example.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditVoucherTest {

    @InjectMocks
    private CreditVoucher creditVoucher;

    @Test
    void txnType() {
        assertTrue(creditVoucher.txnType(OperationTypeEnum.CREDIT_VOUCHER.getValue()));
    }

    @Test
    void txnTypeFalse() {
        assertFalse(creditVoucher.txnType(null));
        assertFalse(creditVoucher.txnType(""));
        assertFalse(creditVoucher.txnType("In valid"));
    }

    @Test
    void txnValue() {
        BigDecimal bigDecimal = creditVoucher.txnValue(BigDecimal.TEN);
        assertEquals(0, bigDecimal.compareTo(BigDecimal.TEN));
    }
}