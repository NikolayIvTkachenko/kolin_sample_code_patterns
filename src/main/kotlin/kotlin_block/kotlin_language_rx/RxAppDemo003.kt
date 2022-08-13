package kotlin_block.kotlin_language_rx

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import java.util.concurrent.TimeUnit

fun main() {

    println("0 ====")
//    val connectableSource = Observable.fromIterable((1..3)).publish()
//
//    connectableSource.connect()
//
//    val regularSource = connectableSource.refCount()
//
//    //regularSource.connect()
//
//    val regularSourceV2 = Observable.fromIterable((1..3)).publish().refCount()
//
//    val stillRegular = Observable.fromIterable((1..3)).share()
//
//    //subject = Observable + Observer
//    val dataSource = Observable.fromIterable((1..3)).publish()
//    val multicast = PublishSubject.create<Int>()
//
//    dataSource.subscribe(multicast)
//
//    multicast.subscribe{
//        println("S1 $it")
//    }
//    println("S1 subscribed")
//
//    multicast.subscribe{
//        println("S2 $it")
//    }
//    println("S2 subscribed")
//
//    dataSource.connect()
//    Thread.sleep(1000)


    //ReplaySubject
    println("ReplaySubject =====")
    val list = (6..34).toList()
    val iterator = list.iterator()
    val o = Observable
        .intervalRange(0, list.size.toLong(), 0, 10, TimeUnit.MILLISECONDS)
        .map {
            iterator.next()
        }.publish()

    //val subject = PublishSubject.create<Int>()
    //val subject = ReplaySubject.create<Int>()
    //val subject = ReplaySubject.createWithSize<Int>(2)
    //val subject = BehaviorSubject.create<Int>()
    //val subject = AsyncSubject.create<Int>()

    val subject = ReplaySubject.createWithSize<Int>(2).toSerialized()



    o.subscribe(subject)
    o.connect()
    Thread.sleep(20)


    println("S1 subscribers")
    subject.subscribe {
        println("S1 $it")
    }
    println("S1 subscribed")

    Thread.sleep(10)

    println("S2 subscribes")
    subject.subscribe{
        println("S2 $it")
    }
    println("S2 subscribed")

    Thread.sleep(1000)







}