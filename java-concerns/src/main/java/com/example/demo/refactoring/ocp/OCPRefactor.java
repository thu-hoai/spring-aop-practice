package com.example.demo.refactoring.ocp;

import java.math.BigDecimal;

public class OCPRefactor {
}


class TransactionManager1 {
    public void withdraw(Account1 account, BigDecimal amount) {
        var balance = account.withdraw(amount);
        account.setBalance(balance);
    }

}

class Account1 {
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal withdraw(BigDecimal amount) {
        return this.balance.subtract(amount);
    }
}

class CreditAccount1 extends Account1 {
    private double fee;

    public double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        return getBalance().subtract(addFee(amount, fee));
    }


    private BigDecimal addFee(BigDecimal amount, double fee) {
        return amount.multiply(BigDecimal.valueOf(1 + fee));
    }
}