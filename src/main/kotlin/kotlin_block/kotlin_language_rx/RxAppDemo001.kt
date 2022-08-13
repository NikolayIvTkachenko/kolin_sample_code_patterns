package kotlin_block.kotlin_language_rx

import io.reactivex.Observable
import java.util.concurrent.TimeUnit


fun main() {

    //0 ==
    println("0 ===============")
    val publisher = Observable.fromArray(3, 4, 5, 6, 7, 8)

    publisher.subscribe {
        println(it)
    }

    //1 ==
    println("1 ===============")
    publisher.filter{
        it > 5
    }.map {
        it + it
    }.subscribe {
        println(it)
    }

    //2 ==
    println("2 - Thread ===============")
//    val publisherV2 = Observable.interval(1, TimeUnit.SECONDS)
//    publisherV2.subscribe {
//        println("P1 $it")
//    }
//
//    publisherV2.subscribe {
//        println("P2 $it")
//    }
//    Thread.sleep(TimeUnit.SECONDS.toMillis(5))
//
//    val subscrption = publisherV2.subscribe {
//        println("P2 $it")
//    }
//    println("Sleeping")
//    Thread.sleep(TimeUnit.SECONDS.toMillis(2))
//    subscrption.dispose()

    //3 ==
    println("3 - Create Observable ===============")
    val o = Observable.create<Int> {
        for (i in 1..10_000){
            it.onNext(i)
        }
        it.onComplete()
    }

//    o.subscribe {
//        println("All went good: $it")
//    }

    o.subscribe({
        println("All went good: $it")
    }, {
        println("There was an error $it")
    }, {
        println("Publisher closed the stream")
    })




}


