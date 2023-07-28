package com.globalpayex.banking.common

import com.globalpayex.banking.domain.Account

interface IBankingUtil {
    Boolean cannotWithdraw(Account account, BigDecimal withdrawlAmt)
}