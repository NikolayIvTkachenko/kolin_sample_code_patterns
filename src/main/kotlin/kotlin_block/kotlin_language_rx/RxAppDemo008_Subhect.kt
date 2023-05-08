package kotlin_block.kotlin_language_rx


import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.*
import java.util.concurrent.TimeUnit


fun main() {

    //testSubject001()
    //testSubject002()
    //testSubject003()
    //testSubject004()
    //testSubject005()
    //testSubject006()
    testSubject007()

}

fun testSubject001(){
    val subject = PublishSubject.create<Int>()
    subject.onNext(1)
    subject.subscribe { x: Int? -> println(x) }
    subject.onNext(2)
    subject.onNext(3)
    subject.onNext(4)
}

fun testSubject002(){
    val s = ReplaySubject.create<Int>()
    s.subscribe { v: Int -> println("Early:$v") }
    s.onNext(0)
    s.onNext(1)
    s.subscribe { v: Int -> println("Late: $v") }
    s.onNext(2)
}

// Фабричный метод ReplaySubject.createWithSize ограничивает размер буфера, а ReplaySubject.createWithTime время, которое объекты будут оставаться в кеше.
fun testSubject003(){
    val s = ReplaySubject.createWithSize<Int>(2)
    s.onNext(0)
    s.onNext(1)
    s.onNext(2)
    s.subscribe { v: Int -> println("Late: $v") }
    s.onNext(3)
}

fun testSubject004() {
    val s: ReplaySubject<Int> = ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS, Schedulers.io() )
    s.onNext(0)
    Thread.sleep(100)
    s.onNext(1)
    Thread.sleep(100)
    s.onNext(2)
    s.subscribe { v: Int -> println("Late: $v") }
    s.onNext(3)
}

//BehaviorSubject хранит только последнее значение. Это то же самое,
// что и ReplaySubject, но с буфером размером 1. Во время создания ему может быть присвоено начальное значение,
// таким образом гарантируя, что данные всегда будут доступны новым подписчикам.
fun testSubject005(){

    val s = BehaviorSubject.create<Int>()
    s.onNext(0)
    s.onNext(1)
    s.onNext(2)
    s.subscribe { v: Int -> println("Late: $v") }
    s.onNext(3)
}

fun testSubject006(){
    val s: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)
    s.subscribe { v: Int? -> println(v) }
    s.onNext(1)
}

//AsyncSubject также хранит последнее значение. Разница в том,
// что он не выдает данных до тех пока не завершится последовательность.
// Его используют, когда нужно выдать единое значение и тут же завершиться.
fun testSubject007(){

    val s = AsyncSubject.create<Int>()
    s.subscribe { v: Int? -> println(v) }
    s.onNext(0)
    s.onNext(1)
    s.onNext(2)
    s.onComplete()
}

//Как мы уже упоминали, существуют принципы, которые могут быть не очевидны в коде.
// Один из важнейших заключается в том, что ни одно событие не будет выдано после того, как последовательность
// завершена (onError или onCompleted). Реализация subject’ уважает эти принципы:
//fun testSubject008(){
//    val s: Subject<Int, Int> = ReplaySubject.create<Any>()
//    s.subscribe { v -> System.out.println(v) }
//    s.onNext(0)
//    s.onCompleted()
//    s.onNext(1)
//    s.onNext(2)
//}