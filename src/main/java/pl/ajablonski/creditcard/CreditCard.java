package pl.ajablonski.creditcard;

import java.math.BigDecimal;

public class CreditCard {


    private BigDecimal creditLimit;
    private BigDecimal balance;

    public void assignCredit(BigDecimal creditLimit) {
        if (this.creditLimit != null){
            throw new CreditCantBeReassignedException();
        }
        if (creditLimit.compareTo(BigDecimal.valueOf(100)) < 0){
            throw new CreditBelowThresholdException();
        }
        this.creditLimit = creditLimit;
        this.balance = creditLimit;
    }

    public BigDecimal getBalance() {

        return balance;
    }

    public void withdraw(BigDecimal money) {
        if(isBelowBalance(money)){
            throw new TransactionDenyException();
        }

        this.balance = balance.subtract(money);
    }

    private boolean isBelowBalance(BigDecimal money) {
        return balance.subtract(money).compareTo(BigDecimal.valueOf(0)) < 0;
    }
}
