import com.globalpayex.banking.common.BankingUtil
import com.globalpayex.banking.domain.Account
import spock.lang.Specification
import spock.util.concurrent.AsyncConditions
import spock.util.concurrent.BlockingVariable

class ExecuteSpec extends Specification {
    // synchronous way of testing asychronous code will make this test fail
    /* def "test executeAsync() for asynchronous code"() {
        given:
        BankingUtil bankingUtil = Mock()
        def acc = new Account(accType: 'Savings', accNumber: 'ABC123', balance: 10000, bankingUtil: bankingUtil)
        
        when:
        Execute.executeAsync {
            acc.withdraw(6000)
        }
        
        then:
        acc.balance == 4000
    } */

    // AsyncConditions -- SPOCK Api
    def "test executeAsync() for asynchronous code using AsyncConditions"() {
        given:
        def asyncConditions = new AsyncConditions(1)
        BankingUtil bankingUtil = Mock()
        def acc = new Account(accType: 'Savings', accNumber: 'ABC123', balance: 10000, bankingUtil: bankingUtil)

        when:
        Execute.executeAsync {
            acc.withdraw 6000
            asyncConditions.evaluate {
                assert acc.balance == 4000
            }
        }

        then:
        asyncConditions.await()
    }

    // BlockingVariable -- SPOCK Api
    def "test executeAsync() for asynchronous code using BlockingVariable"() {
        given:
        def actualAccBalance = new BlockingVariable<BigDecimal>()

        BankingUtil bankingUtil = Mock()
        def acc = new Account(accType: 'Savings', accNumber: 'ABC123', balance: 10000, bankingUtil: bankingUtil)

        when:
        Execute.executeAsync {
            acc.withdraw 6000
            actualAccBalance.set acc.balance
        }

        then:
        actualAccBalance.get() == 4000
    }
}
