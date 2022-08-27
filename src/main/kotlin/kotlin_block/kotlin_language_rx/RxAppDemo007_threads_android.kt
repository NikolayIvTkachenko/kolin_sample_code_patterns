package kotlin_block.kotlin_language_rx

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger


//https://betterprogramming.pub/threading-in-android-129b8688436a
//https://developer.android.com/reference/android/app/IntentService

//https://github.com/ReactiveX/RxJava

fun main() {

    println("== test 1 ====")
    Flowable.just("Hello world").subscribe(System.out::println)


    println("== test 2 ====")
    var flow = Flowable.range(1, 5)
    flow.map { v -> v * v  }
        .filter { v -> v % 3 == 0 }
        .subscribe{ ans -> println(ans) }


    println("== test 3 ====")
//    Observable.create<Long> { emitter ->
//        while (!emitter.isDisposed()) {
//            val time = System.currentTimeMillis()
//            emitter.onNext(time)
//            if (time % 2 != 0L) {
//                emitter.onError(IllegalStateException("Odd millisecond!"))
//                break
//            }
//        }
//    }.subscribe(
//        { n -> println(n)},
//        {error -> println(error.message) },
//        { println("completed") }
//    )

    println("== test 4 Simple background computation ====")
    Flowable.fromCallable {
        Thread.sleep(1000) //  imitate expensive computation
        "Done"
    }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(
            { x: String? -> println(x) }
        ) { obj: Throwable -> obj.printStackTrace() }

    Thread.sleep(2000)


    println("== test 5 Simple background computation ====")
    val source = Flowable.fromCallable {
        Thread.sleep(1000) //  imitate expensive computation
        "Done"
    }

    val runBackground = source.subscribeOn(Schedulers.io())

    val showForeground = runBackground.observeOn(Schedulers.single())

    showForeground.subscribe(
        { x: String? -> println(x) }
    ) { obj: Throwable -> obj.printStackTrace() }

    Thread.sleep(2000)


    println("== test 6 Schedulers ====")
    /*
    RxJava operators don't work with Threads or ExecutorServices directly but
    with so-called Schedulers that abstract away sources of concurrency behind a
    uniform API. RxJava 3 features several standard schedulers accessible via
    Schedulers utility class.

    Schedulers.computation(): Run computation intensive work on a fixed number
    of dedicated threads in the background. Most asynchronous operators use this
    as their default Scheduler.
    Schedulers.io(): Run I/O-like or blocking operations on a dynamically
    changing set of threads.
    Schedulers.single(): Run work on a single thread
    in a sequential and FIFO manner.
    Schedulers.trampoline(): Run work in a sequential and FIFO manner
    in one of the participating threads, usually for testing purposes.
     */

    //Concurrency within a flow
    println("== test 7 Concurrency within a flow ====")
    Flowable.range(1, 10)
        .observeOn(Schedulers.computation())
        .map { v: Int -> v * v }
        .blockingSubscribe { x: Int? -> println(x) }

    println("== test 8 Parallel processing ====")
    //Processing the numbers 1 to 10 in parallel is a bit more involved:
    Flowable.range(1, 10)
        .flatMap { v: Int ->
            Flowable.just(v)
                .subscribeOn(Schedulers.computation())
                .map { w: Int -> w * w }
        }
        .blockingSubscribe { x: Int? -> println(x) }
    /*
    Practically, parallelism in RxJava means running independent
    flows and merging their results back into a single flow. The operator
    flatMap does this by first mapping each number from 1 to 10 into its own
     individual Flowable, runs them and merges the computed squares.
     */
    /*
    Alternatively, the Flowable.parallel() operator and the
    ParallelFlowable type help achieve the same parallel processing pattern:
     */
    println("== test 9 Parallel processing II====")
    Flowable.range(1, 10)
        .parallel()
        .runOn(Schedulers.computation())
        .map { v: Int -> v * v }
        .sequential()
        .blockingSubscribe { x: Int? -> println(x) }

    println("== test 10 Continuations")
    /*
     Sometimes, when an item has become available,
     one would like to perform some dependent computations on it.
     This is sometimes called continuations and,
     depending on what should happen and what types are involved,
     may involve various operators to accomplish.
     */
    println("== Dependent ====")
    /*
    The most typical scenario is to given
    a value, invoke another service, await and continue with its result:
     */
//    service.apiCall()
//          .flatMap(value -> service.anotherApiCall(value))
//          .flatMap(next -> service.finalCall(next))

    /*
    It is often the case also that later sequences would require values
    from earlier mappings. This can be achieved by moving the outer flatMap
     into the inner parts of the previous flatMap for example:
     */
//    service.apiCall()
//        .flatMap(value ->
//    service.anotherApiCall(value)
//        .flatMap(next -> service.finalCallBoth(value, next))
//    )

    println("Non-dependent")
    /*
    In other scenarios, the result(s) of the first source/dataflow
    is irrelevant and one would like to continue with a quasi independent
    another source. Here, flatMap works as well:
     */

    println("Deferred-dependent")

    /*
    Sometimes, there is an implicit data dependency
    between the previous sequence and the new sequence that,
    for some reason, was not flowing through the "regular channels".
    One would be inclined to write such continuations as follows:
    */

    val count = AtomicInteger()
    Observable.range(1, 10)
        .doOnNext { ignored -> count.incrementAndGet() }
        .ignoreElements()
        .andThen(Single.just(count.get()))
        .subscribe(System.out::println)

    println("Deferred-dependent  II")
    val count2 = AtomicInteger()

    Observable.range(1, 10)
        .doOnNext { ignored: Int? -> count2.incrementAndGet() }
        .ignoreElements()
        .andThen(Single.defer {
            Single.just(
                count2.get()
            )
        })
        .subscribe { x: Int? -> println(x) }


    println("Deferred-dependent  III")
    val count3 = AtomicInteger()

    Observable.range(1, 10)
        .doOnNext { ignored: Int? -> count3.incrementAndGet() }
        .ignoreElements()
        .andThen(Single.fromCallable { count3.get() })
        .subscribe { x: Int? -> println(x) }


}
