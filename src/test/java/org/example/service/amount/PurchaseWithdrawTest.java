package org.example.service.amount;

import org.example.dto.OperationTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PurchaseWithdrawTest {

    @InjectMocks private PurchaseWithdraw purchaseWithdraw;

    @Test
    void txnType() {
        assertTrue(purchaseWithdraw.txnType(OperationTypeEnum.NORMAL_PURCHASE.getValue()));
    }

    @Test
    void txnTypeFalse() {
        assertFalse(purchaseWithdraw.txnType(null));
        assertFalse(purchaseWithdraw.txnType(""));
        assertFalse(purchaseWithdraw.txnType("In valid"));
    }

    @Test
    void txnValue() {
        BigDecimal bigDecimal = purchaseWithdraw.txnValue(BigDecimal.TEN);
        assertEquals(0, bigDecimal.compareTo(BigDecimal.TEN.negate()));
    }
}