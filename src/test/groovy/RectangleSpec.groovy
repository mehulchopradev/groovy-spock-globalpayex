import spock.lang.Shared
import spock.lang.Specification

class RectangleSpec extends Specification {

    @Shared
    GroovyObject obj

    // lifecycle methods
    def setupSpec() {
        // will be called by spock only once at the start before the feature methods start running
        Class cls = new GroovyClassLoader(getClass().getClassLoader()).parseClass(new File('src/main/groovy/rectangle.groovy'))
        obj = (GroovyObject)cls.getDeclaredConstructor().newInstance()
    }

    def cleanupSpec() {
        // will be called by spock only once after all the feature methods have finished running
    }

    def setup() {
        // will be called by the spock framework everytime before executing a feature method
        /* Class cls = new GroovyClassLoader(getClass().getClassLoader()).parseClass(new File('src/main/groovy/rectangle.groovy'))
        obj = (GroovyObject)cls.getDeclaredConstructor().newInstance() */
    }

    def cleanup() {
        // will be called by the spock framework everytime after executing a feature method
    }

    def "test the working of the perimeter of the rectangle function"() {
        given: "initializing the length, breadth test data"
        def length = 8
        def breadth = 3

        // test actions / Stimulus
        when: "calling the perimeter function passing in length and breadth to it"
        def result = obj.perimeterRectangle length, breadth

        // assertions + regular code
        then: "we expect it to return the right perimeter value"
        result == 22

        when: "calling the perimeter function passing in only length"
        result = obj.perimeterRectangle length

        then: "we expect it to return the perimeter value with breadth taking default value of 2"
        result == 20
    }

    def "test the working of the area of the rectangle function"() {
        given: "initializing the length, breadth test data"
        def length = 10
        def breadth = 4

        // test actions
        when: "calling the area function passing in length and breadth to it"
        def result = obj.areaRectangle length, breadth

        // assertions + regular code
        then: "we expect it to return the right area value"
        result == 40
    }
}
