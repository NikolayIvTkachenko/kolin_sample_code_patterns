package kotlin_block.kotlin_language_rx

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    testRxKotlin001()

    //testRxKotlin002()
}


fun testRxKotlin001(){
    val count = AtomicInteger()

    Observable.range(1, 10)
        //.doOnNext { println(Thread.currentThread().name) }
        .map {
            println(Thread.currentThread().name)
            it
        }
        .subscribeOn(Schedulers.io())
        .map {
            println(Thread.currentThread().name)
            it
        }
        .subscribeOn(Schedulers.computation())
        .map {
            println(Thread.currentThread().name)
            it
        }
        .map { it -> it + 2 }
        .observeOn(Schedulers.single())
        .subscribe {
            println(it)
            println(Thread.currentThread().name)
        }
}

fun testRxKotlin002() {
    val count = AtomicInteger()
    Observable.range(1, 10)
        .doOnNext { ignored -> count.incrementAndGet() }
        .ignoreElements()
        .andThen(Single.just(count.get()))
        .subscribe(System.out::println)
}