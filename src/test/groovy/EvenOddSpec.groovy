import spock.lang.Shared
import spock.lang.Specification

class EvenOddSpec extends Specification {
    @Shared
    GroovyObject obj

    def setupSpec() {
        Class cls = new GroovyClassLoader(getClass().getClassLoader()).parseClass(new File('src/main/groovy/evenodd.groovy'))
        obj = (GroovyObject) cls.getDeclaredConstructor().newInstance()
    }

    def "test evenOrOdd"() {
        when:
        def result = obj.evenOrOdd n

        then:
        result == r

        where:
        /* n || r
        12 || 'Even'
        9 || 'Odd' */

        // Data pipes
        n << [12,9]
        r << ['Even', 'Odd']
    }
}
