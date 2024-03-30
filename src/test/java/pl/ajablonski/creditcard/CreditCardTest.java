package pl.ajablonski.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {

    @Test
    void isAllowsAssignCreditCardLimit() {
        //Arrange
        CreditCard card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1500));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1500),
                card.getBalance());

    }

    @Test
    void isDenyCreditLimitBelowThreshold() {
        CreditCard card = new CreditCard();
        try {
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Exception should be thrown");
        } catch (CreditBelowThresholdException e) {
            assert true;
        }
    }

    @Test
    void isDenyCreditLimitBelowThresholdV2() {
        CreditCard card = new CreditCard();

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit((BigDecimal.valueOf(50)))
        );
    }

    @Test
    void cantReassignCreditLimits() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1500));

        assertThrows(
                CreditCantBeReassignedException.class,
                () -> card.assignCredit((BigDecimal.valueOf(2000)))
        );
    }

    @Test
    void itAllowsWithdrawMoney(){
        var card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(2000));
        card.withdraw(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), card.getBalance());
    }

    @Test
    void itDenyTransactionsOverTheBalance(){
        var card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(2000));
        card.withdraw(BigDecimal.valueOf(1500));

        assertThrows(
                TransactionDenyException.class,
                () -> card.withdraw(BigDecimal.valueOf(1000))
        );

    }

}