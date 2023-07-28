package com.globalpayex.banking.common

import com.globalpayex.banking.domain.Account
import spock.lang.Specification

class BankingUtilSpec extends Specification {

    BankingUtil bankingUtil

    static List accounts = [
        new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000),
        new Account(accNumber: 'XYZ123', accType: 'Current', balance: 8000),
        new Account(accNumber: 'PQR456', accType: 'Pf', balance: 20000)
    ]

    def setup() {
        bankingUtil = new BankingUtil()
    }

    def "test the cannotWithdraw() for various conditions"() {
        when:
        Boolean actualResult = bankingUtil.cannotWithdraw(account, withdrawlAmt)

        then:
        actualResult == expectedResult

        where:
        account     | withdrawlAmt || expectedResult
        accounts[0] | 5000         || false
        accounts[0] | 9500         || true
        accounts[1] | 7200         || false
        accounts[1] | 7900         || true
        accounts[2] | 20000        || false
    }
}
