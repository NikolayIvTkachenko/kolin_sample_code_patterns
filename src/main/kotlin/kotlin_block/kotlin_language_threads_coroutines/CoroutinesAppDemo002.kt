package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

val latch = CountDownLatch(10 * 2)

fun main() {

    //1
//    val latch = CountDownLatch(10_000)
//    val c = AtomicInteger()
//
//    val start = System.currentTimeMillis()
//    for(i in 1..10_000){
//        GlobalScope.launch{
//            c.incrementAndGet()
//            delay(100)
//            c.incrementAndGet()
//            latch.countDown()
//        }
//    }
//
//    latch.await(10, TimeUnit.SECONDS)
//    println("Executed ${c.get() / 2} coroutines in ${System.currentTimeMillis() - start} ms  ")


    //2
    for(i in 1..10){
        CoroutineFactory.greedyLongCoroutine(i)
    }

    for (i in 1..10){
        CoroutineFactory.shortCoroutine(i)
    }

    latch.await(10, TimeUnit.SECONDS)

}

object CoroutineFactory {
    fun greedyLongCoroutine(index: Int) = GlobalScope.async {
        var uuid = UUID.randomUUID()
        for(i in 1..100_000){
            val newUuid = UUID.randomUUID()

            if(newUuid < uuid){
                uuid = newUuid
            }

            println("Done greedyLongCoroutine $index")
            latch.countDown()
        }
    }

    fun shortCoroutine(index: Int) = GlobalScope.async {
        println("Done shortCoroutine $index!")
        latch.countDown()
    }

}