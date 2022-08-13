package kotlin_block.kotlin_language_threads_coroutines

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {

    val t1 = thread {
        for (i in 1..100){
            println("T1: $i")
        }
    }

    val t2 = thread {
        for (i in 1..100) {
            println("T2: $i")
        }
    }

    val t3 = thread {
        for (i in 1..100_000) {
            println("T3: $i")
        }
    }


//    t1.isDaemon = true
//    t1.start()
//
//    t2.start()


//    var counter = 0
//    val latch = CountDownLatch(100_000)
//    for (i in 1..100_000){
//        thread {
//            counter++
//            latch.countDown()
//        }
//    }
//    latch.await()
//    println("Counter $counter")

//    var counterV2 = 0
//    val latchV2 = CountDownLatch(100_000)
//    for (i in 1..100_000){
//        thread {
//            synchronized(latchV2){
//                counterV2++
//                latchV2.countDown()
//            }
//        }
//    }
//    latchV2.await()
//    println("Counter $counterV2")

//    val counterV3 = AtomicInteger()
//    try{
//        for (i in 0..10_000) {
//            thread {
//                counterV3.incrementAndGet()
//                Thread.sleep(100)
//            }
//        }
//    } catch (oome: OutOfMemoryError) {
//        println("Spawned ${counterV3} threads before crashing")
//        System.exit(-42)
//    }

    val pool = Executors.newFixedThreadPool(100)
    val counterV4 = AtomicInteger(0)

    val start = System.currentTimeMillis()
    for(i in 1..10_000) {
        pool.submit {
            counterV4.incrementAndGet()
            Thread.sleep(100)
            counterV4.incrementAndGet()
        }
    }

    pool.awaitTermination(20, TimeUnit.SECONDS)
    pool.shutdown()

    println("Took me ${System.currentTimeMillis() - start} millis to complete ${counterV4.get() / 2} tasks")

}