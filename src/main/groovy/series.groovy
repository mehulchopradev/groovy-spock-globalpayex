def fibo(n) {
    if (n < 2) {
        throw new IllegalArgumentException("value of n must be min 2")
    }

    def a = 0
    def b = 1
    def result = []

    result << a << b

    for (def v in 2..<n) {
        def c = a + b
        result << c

        a = b
        b = c
    }

    result
}
