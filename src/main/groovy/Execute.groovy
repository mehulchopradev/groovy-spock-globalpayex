class Execute {

    def static executeAsync(Runnable runnable) {
        println "executeAsync() is about to schedule your runnable job"
        new Thread(runnable).start()
        println "executeAsync() has scheduled your runnable job"
    }
}
