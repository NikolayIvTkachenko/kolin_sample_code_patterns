package kotlin_block.kotlin_language_rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun main() {

    //Batching
    println("Batching")
//    val latch = CountDownLatch(1)
//    val o = Observable.intervalRange(8L, 15L, 0L, 100L, TimeUnit.MILLISECONDS)
//
//    o.buffer(2).subscribe({
//        println(it)
//    }, {}, {
//        latch.countDown()
//    })
//    latch.await()


    //Throttling
    println("Throttling")
    val oV2 = PublishSubject
        .intervalRange(8L, 15L, 0L, 100L, TimeUnit.MILLISECONDS)
        .publish()

    oV2.throttleFirst(280L, TimeUnit.MILLISECONDS).subscribe{
        println(it)
    }

//    oV2.buffer(280L, TimeUnit.MILLISECONDS).subscribe{
//        println(it)
//    }

    oV2.connect()

    Thread.sleep(100 * 15)




}


