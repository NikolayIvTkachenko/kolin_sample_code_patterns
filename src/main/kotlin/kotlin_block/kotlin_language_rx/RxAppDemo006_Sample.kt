package kotlin_block.kotlin_language_rx

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Action
import java.io.IOException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    //https://betterprogramming.pub/rxjava-different-ways-of-creating-observables-7ec3204f1e23
    //Different Ways to Create Observables in RxJava

    println("== RxJava - create() ===")
    /*
    Create an Observable from scratch by means of a function
     */
    val createObserver = Observable.create(ObservableOnSubscribe<String> { emitter ->
        emitter.onNext("Hello World")
        emitter.onComplete()
    })



    println("== RxJava - just() ===")
    /*
    This is one of the easiest and convenient ways to
    create observable. just() constructs a reactive
    type by taking a pre-existing object and emitting
    that specific object to the downstream consumer
     upon subscription. The just operator converts
     an item into an Observable that emits that item.
     */
    val justObservable = Observable.just(4, 5, 6, 8)


    println("== RxJava - defer() ===")
    /*
    defer() does not create the Observable until the observer
    subscribes and creates a fresh Observable for each observer.
    The Defer operator waits until an observer subscribes to it,
    then it generates an Observable, typically with an Observable
    factory function. It does this creation for each subscriber
    — although each subscriber may think it’s subscribing to the same
    Observable, in fact, each subscriber gets its own individual sequence.
     */
    val observableDefer = Observable.defer {
        val time = System.currentTimeMillis()
        Observable.just(time)
    }

    println("== RxJava - empty() ===")
    /*
    empty() creates an Observable that emits no items to but terminates
    normally. This type of source signals completion immediately upon
    subscription. It returns an Observable that emits no items to the
    Observer and immediately invokes its onComplete() method.
     */
    val empty = Observable.empty<String>()

//    empty.subscribe(
//        { v -> println("This should never be printed!") },
//        { error -> println("Or this!") },
//        { println("Done will be printed.") })


    println("== RxJava - never() ===")
    /*
    never() Creates an Observable that emits no items and does
    not terminate. This type of source does not signal any onNext,
    onSuccess, onError or onComplete.
    This type of reactive source is useful for testing or
    disabling certain sources in combinator operators.
     */
    val never = Observable.never<String>()

//    never.subscribe(
//        { v -> println("This should never be printed!") },
//        { error -> println("Or this!") },
//        { println("This neither!") })


    println("== RxJava - error() ===")
    /*
    error() signals an error, either pre-existing or generated
    via a java.util.concurrent.Callable, to the consumer.
     */
    val error = Observable.error<String>(IOException())
//    error.subscribe(
//        { v -> println("This should never be printed!") },
//        { e -> e.printStackTrace() },
//        { println("This neither!") })

    println("== RxJava - error() ===")
    /*
    onErrorResumeNext() instructs an ObservableSource to pass
    control to another ObservableSource, rather than invoking
    Observer.onError(), if it encounters an error in a chain of
    sequence.

    If you pass another ObservableSource resume sequence to an
    ObservableSource’s onErrorResumeNext() method, if the original
    ObservableSource encounters an error, instead of invoking its
    Observer’s onError() method, it will relinquish control
    to resume sequence which will invoke the Observer’s onNext()
    method if it is able to do so. In such a case, the Observer
    may never know that an error has occurred. You can use this to
    prevent errors from propagating or to supply fallback data
    should errors be encountered.
     */
    val observable = Observable.fromCallable {
        if (Math.random() < 0.5) {
            throw IOException()
        }
        throw IllegalArgumentException()
    }

//    val result = observable.onErrorResumeNext { error ->
//        if (error is IllegalArgumentException) {
//            return@onErrorResumeNext Observable.empty()
//        }
//        Observable.error(error)
//    }
//    for (i in 0..9) {
//        result.subscribe(
//            { v -> println("This should never be printed!") },
//            { error -> error.printStackTrace() },
//            { println("Done") })
//    }


    println("== RxJava - range() ===")
    /*
        range() creates an Observable that emits a particular range
        of sequential integers. The Range operator emits a range of
        sequential integers in order, where you select the start
        of the range and its length. It generates a sequence of
        values for each individual consumer. The range() method
        generates Integers, the rangeLong() generates Longs.


     */
    val greeting = "Hello World!"

    val indexes = Observable.range(0, greeting.length)

    val characters = indexes
        .map { index -> greeting[index] }

    characters.subscribe({ character -> println(character) }, { error -> error.printStackTrace() },
        { println() })


    println("== RxJava - interval() ===")
    /*
    interval() creates an Observable that emits a sequence of
    integers spaced by a given time interval. The Interval
    operator returns an Observable that emits an infinite
    sequence of ascending integers, with a constant interval
    of time of your choosing between emissions.
     */
    val clock = Observable.interval(1, TimeUnit.SECONDS)

    clock.subscribe { time ->
        if (time!! % 2 == 0L) {
            println("Tick")
        } else {
            println("Tock")
        }
    }
    //Thread.sleep(10000)

    println("== RxJava - timer() ===")
    /*
        timer() creates an Observable that emits a particular item after a given delay that we specify.
     */

//    val eggTimer = Observable.timer(5, TimeUnit.MINUTES)
//    eggTimer.blockingSubscribe { v -> println("Egg is ready!") }


    println("== RxJava - from ===")
    /*
    from is used to convert various other objects and data
    types into Observables. When we work with Observables,
    it can be more convenient if all the data you mean to work
    with can be represented as Observables, rather than as
    a mixture of Observables and other types. This allows you
    to use a single set of operators to govern the entire lifespan
    of the data stream.
     */



    println("== RxJava - fromIterable() ===")
    /*
    fromIterable() signals the items from a java.lang.Iterable
    source (such as Lists, Sets or Collections or custom Iterables)
    and then completes the sequence. Converts an Iterable sequence
     into an ObservableSource that emits the items in the sequence.
     */
    val numbers = ArrayList<Int>()
    numbers.add(1)
    numbers.add(2)
    numbers.add(3)
    numbers.add(4)
    val fromObservable = Observable.fromIterable(numbers)
    fromObservable.subscribe {
        n -> println(n)
    }


    println("== RxJava - fromArray() ===")
    /*
    fromArray() converts an Array into an
    ObservableSource that emits the items in the Array.
     */
    val observablefromArray = Observable.fromArray(numbers)

    observablefromArray.subscribe(
        { item -> println(item) },
        { error -> error.printStackTrace() },
        { println("Done") })


    println("== RxJava - fromCallable() ===")
    /*
    When a consumer subscribes, the given java.util.concurrent.Callable
    is invoked and its returned value (or thrown exception) is relayed to that consumer.
     In other words, it returns an Observable that,
     when an observer subscribes to it, invokes a function
     you specify and then emits the value returned from that
     function. This allows you to defer
     the execution of the function you specify until an
     observer subscribes to the ObservableSource. That’s to say,
     it makes the function “lazy.”

     */

    val callable = Callable {
        println("Hello World!")
        return@Callable ("Hello World!")
    }
    val observableCallable = Observable.fromCallable(callable)

    observableCallable.subscribe(
        { item -> println(item) },
        { error -> error.printStackTrace() },
        { println("Done") })


    println("== RxJava - fromAction() ===")
    /*
    fromAction() returns a Completable instance that runs
    the given Action for each subscriber and emits either
    an unchecked exception or simply completes.
     */
    val action = Action{ println("Hello World! Mario!!") }

    val completable = Completable.fromAction(action)

    completable.subscribe(
        { println("Done") },
        { error -> error.printStackTrace() }
    )

    println("== RxJava - fromRunnable() ===")
    /*
        fromRunnable() returns a Completable instance
        that subscribes to the given Observable,
        ignores all values and emits only the terminal event.
     */
    val runnable = { println("Hello Terminator, hey Arnold!") }
    val completable1 = Completable.fromRunnable(runnable)
    completable1.subscribe(
        { println("Done") },
        { error -> error.printStackTrace() }
    )

    println("== RxJava - fromFuture() ===")
    /*
    fromFuture() converts a java.util.concurrent.
    Future into an ObservableSource.
    We can convert any object that supports the Future
    interface into an ObservableSource that emits the
    return value of the Future.get() method of that object,
    by passing the object into the from() method.
     */

    val executor = Executors.newSingleThreadScheduledExecutor()

    val future = executor.schedule({ "Hello world!" }, 1, TimeUnit.SECONDS)

    val observableFuture = Observable.fromFuture<Any>(future)

    observableFuture.subscribe(
        { item -> println(item) },
        { error -> error.printStackTrace() },
        { println("Done") })

}