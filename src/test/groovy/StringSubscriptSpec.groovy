import spock.lang.Specification

class StringSubscriptSpec extends Specification {

    // feature methods
    // test cases
    def "test the working of the subscript operator on strings"() {
        given:
        // fixture / test data
        def str = 'good morning'

        // only assertions
        expect:
        str[0] == 'g'
        str[-2] == 'n'
        str[0..3] == 'good'
    }
}
