package com.globalpayex.banking.domain

import com.globalpayex.banking.common.IBankingUtil
import spock.lang.Ignore
import spock.lang.Specification

class AccountSpec extends Specification {

    Account account

    IBankingUtil bankingUtil

    def setup() {
        bankingUtil = Mock()
        // def bankingUtil = Mock(BankingUtil)
        account = new Account(accType: 'Current', accNumber: 'ABC123', balance: 10000, bankingUtil: bankingUtil)
    }

    def "Withdraw"() {
        given:
        bankingUtil = Mock {
            1 * cannotWithdraw(_, 4000)
        }
        account.bankingUtil = bankingUtil

        when:
        def result = account.withdraw 4000

        then:
        // interactions between the main object and the collaborator object
        // 1 * bankingUtil.cannotWithdraw(_, _) // _ --> some argument you dnt care of the value
        // 1 * bankingUtil.cannotWithdraw(account, 4000)
        // 2 * bankingUtil.cannotWithdraw(account, 4000)
        // (1..3) * bankingUtil.cannotWithdraw(account, 4000)
        // (_..3) * bankingUtil.cannotWithdraw(account, 4000)

        result == 6000
        // with closure
        /* with(account) {
            balance == result
            accType == 'Savings'
            accNumber == 'XYZ2342'
        } */

        // verifyAll closure
        verifyAll(account) {
            balance == result
            accType == 'Current'
            accNumber == 'ABC123'
        }

        // verifyAccountAttributes account, result
    }

    // @Ignore
    def "Withdraw method working in low balance scenario"() {
        // Stubbing
        given:
        // only stubbing
        bankingUtil = Stub()
        // a stubbed object can only do stubbing. It cannot verify interactions
        // bankingUtil.cannotWithdraw(account, _) >> true

        // different outputs to be returned by the stubbed method for successive invocations
        // bankingUtil.cannotWithdraw(account, _) >>> [false, true]

        // different outputs to be returned by the stubbed method based on parameter value
        // ensure to give static typing to the parameters declared in the closure
        bankingUtil.cannotWithdraw(account, _) >> {Account acc, BigDecimal withdrawlAmt -> withdrawlAmt == 9000 ? false : true}

        // assign the new Stub object to the main object attribute
        account.bankingUtil = bankingUtil

        // interaction verification + stubbing
        // verifying interactions as well as stubbing that interaction should be done on the same line
        // 1 * bankingUtil.cannotWithdraw(account, 9700) >> true

        when:
        account.withdraw 9000

        then:
        notThrown(IllegalStateException)
        account.balance == 1000


        when:
        account.withdraw 900

        then:
        IllegalStateException e = thrown()
        e.message == 'cannot withdraw as min bal is not being maintained'
    }

    def "Deposit"() {
        when:
        def result = account.deposit 4000

        then:
        result == 14000
        with(account) {
            balance == result
            accType == 'Current'
            accNumber == 'ABC123'
        }
        // verifyAccountAttributes account, result
    }

    // helper functions
    /* void verifyAccountAttributes(account, result) {
        // groovy assert statement is mandatory before groovy expressions in the helper method
        assert account.balance == result
        assert account.accType == 'Current'
        assert account.accNumber == 'ABC123'
    } */
}
