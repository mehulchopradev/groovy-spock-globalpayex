package com.globalpayex.banking.common

import com.globalpayex.banking.domain.Account

class BankingUtil implements IBankingUtil {

    static final Integer MIN_BAL_CURRENT = 500
    static final Integer MIN_BAL_SAVINGS = 1000

    Boolean cannotWithdraw(Account account, BigDecimal withdrawlAmt) {
        Integer minBal
        switch (account.accType) {
            case 'Savings': minBal = MIN_BAL_SAVINGS
                break
            case 'Current': minBal = MIN_BAL_CURRENT
                break
            default:
                minBal = 0
        }

        account.balance - withdrawlAmt < minBal
    }
}
