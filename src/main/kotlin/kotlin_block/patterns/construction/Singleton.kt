package kotlin_block.patterns.construction

import java.util.concurrent.atomic.AtomicInteger

fun main() {

    for(i in 1..10){
        println(TestSingleton.increment())
    }

}


object TestSingleton{

    init {
        println("Initial data from Db or File for the first time")
    }

    private val counter = AtomicInteger(0)

    fun increment() = counter.incrementAndGet()

}
