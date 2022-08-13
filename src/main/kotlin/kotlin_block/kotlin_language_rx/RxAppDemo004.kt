package kotlin_block.kotlin_language_rx

import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import java.util.UUID
import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription
import java.util.concurrent.atomic.AtomicInteger

fun main() {


    val source = Observable.create<String>{
        var startProducing = System.currentTimeMillis()
        for(i in 1..10_000_000){
            it.onNext(UUID.randomUUID().toString())
            if(i % 100_000 == 0){
                println("Produced $i events in ${System.currentTimeMillis() - startProducing} ms")
                startProducing = System.currentTimeMillis()
            }
        }
        //latch.countDown()
    }

    val sourceV2 = Flowable.create<String>({
        var startProducing = System.currentTimeMillis()
        for(i in 1..10_000_000){
            it.onNext(UUID.randomUUID().toString())
            if(i % 100_000 == 0){
                println("Produced $i events in ${System.currentTimeMillis() - startProducing} ms")
                startProducing = System.currentTimeMillis()
            }
        }
        it.onComplete()
        //latch.countDown()
    }, BackpressureStrategy.DROP)


//    source.observeOn(Schedulers.newThread())
//        .subscribe(object : Subscriber<String> {
//
//            lateinit var subscription: Subscription
//            override fun onSubscribe(subscription: Subscription?) {
//
//                subscription?.let {
//                    this.subscription = it
//                } ?: throw RuntimeException()
//
//            }
//
//            override fun onError(throwable: Throwable?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onComplete() {
//                TODO("Not yet implemented")
//            }
//
//            override fun onNext(item: String?) {
//                println(item)
//            }
//
//        })

    val count = AtomicInteger(0)
    var startTime = System.currentTimeMillis()
    val sourceV3 = Flowable.generate<String>{
        it.onNext(UUID.randomUUID().toString())

        if(count.incrementAndGet() == 10_000_000) {
            it.onComplete()
            //latch.countDown()
        }

        if (count.get() % 100_000 == 0) {
            println("Produced ${count.get()} events in ${System.currentTimeMillis() - startTime} ms")
            startTime = System.currentTimeMillis()
        }

    }

    source.subscribe {
        //println(it)
    }


}





