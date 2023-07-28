import spock.lang.Rollup
import spock.lang.Shared
import spock.lang.Specification
import groovy.json.JsonSlurper

class SeriesSpec extends Specification {

    @Shared
    GroovyObject obj

    @Shared
    def inputs

    @Shared
    def outputs

    def setupSpec() {
        Class cls = new GroovyClassLoader(getClass().getClassLoader()).parseClass(new File('src/main/groovy/series.groovy'))
        obj = (GroovyObject) cls.getDeclaredConstructor().newInstance()
        def jsonReader = new JsonSlurper()
        def obj = jsonReader.parse(new File('src/test/groovy/fibo_tests.json'))
        inputs = obj['inputs']
        outputs = obj['outputs']
    }

    // @Rollup -- summarizes up the execution of the various scenarios from the data table in one single row in the test report
    def "test the fibo()"() {
        when:
        def result = obj.fibo n

        then:
        result == r

        // Data tables
        where:
        /* n | a
        8 | _
        3 | _
        2 | _

        __

        b || r
        _ || [0,1,1,2,3,5,8,13]
        _ || [0,1,1]
        _ || [0,1] */

        // Data pipes
        // n << [8,3,2]
        // r << [[0,1,1,2,3,5,8,13], [0,1,1], [0,1]]
        n << inputs
        r << outputs
    }

    def "test the fibo() for n less than 2"() {
        given:
        def n = 1

        when:
        obj.fibo n

        then:
        // expect for an exception
        IllegalArgumentException e = thrown()
        e.message == 'value of n must be min 2'
    }
}
