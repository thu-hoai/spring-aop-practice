package com.example.demo.refactoring.ocp;

import java.math.BigDecimal;

public class OCPViolate {
}


class TransactionManager {
    public void withdraw(Account account, BigDecimal amount) {
        if (account instanceof CreditAccount) {
            account.setBalance(account.getBalance().subtract(addFee(amount, ((CreditAccount) account).getFee())));
        } else {
            account.setBalance(account.getBalance().subtract(amount));
        }
    }

    private BigDecimal addFee(BigDecimal amount, double fee) {
        return amount.multiply(BigDecimal.valueOf(1 + fee));
    }
}

class Account {
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

class CreditAccount extends Account {
    private double fee;

    public double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}