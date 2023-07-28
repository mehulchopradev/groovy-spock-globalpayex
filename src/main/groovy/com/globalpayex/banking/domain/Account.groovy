package com.globalpayex.banking.domain

import com.globalpayex.banking.common.IBankingUtil

class Account {

    String accNumber
    String accType
    BigDecimal balance
    IBankingUtil bankingUtil

    BigDecimal withdraw(BigDecimal amt) {
        if(bankingUtil.cannotWithdraw(this, amt)) {
            throw new IllegalStateException('cannot withdraw as min bal is not being maintained')
        }
        this.balance -= amt
    }

    BigDecimal deposit(BigDecimal amt) {
        this.balance += amt
    }
}
