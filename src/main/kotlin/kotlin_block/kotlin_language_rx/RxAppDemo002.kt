package kotlin_block.kotlin_language_rx

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlin.concurrent.thread

fun main() {


    //HOT OBSERVABLE
//    val iterator = (1..10).iterator()
//
//    val publisher = Observable.create<Int>{
//        while (iterator.hasNext()) {
//            val nextNumber = iterator.next()
//            it.onNext(nextNumber)
//        }
//        it.onComplete()
//    }
//
//    publisher.subscribeOn(Schedulers.newThread())
//        .subscribe {
//            println("S1: $it")
//            Thread.sleep(10)
//
//        }
//    Thread.sleep(50)
//
//    publisher.subscribeOn(Schedulers.newThread())
//        .subscribe {
//            println("S2: $it")
//            Thread.sleep(10)
//
//        }
//    Thread.sleep(50)


    //MULTICAST
    val iteratorV2 = (1..5).iterator()
    val subject = Observable.create<Int>{
        while(iteratorV2.hasNext()){
            val number = iteratorV2.nextInt()
            println("P: $number")
            it.onNext(number)
            Thread.sleep(10)
        }
        it.onComplete()
    }.observeOn(Schedulers.newThread()).publish()

    thread {
        subject.connect()
    }

    subject.subscribeOn(Schedulers.newThread())
        .subscribe {
            println("S1: $it")
            Thread.sleep(100)

        }

    subject.subscribeOn(Schedulers.newThread())
        .subscribe {
            println("S2: $it")
            Thread.sleep(100)

        }
    Thread.sleep(2000)

}


