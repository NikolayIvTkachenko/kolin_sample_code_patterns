package kotlin_block.kotlin_coroutines_examples_02

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//AppDemoCoroutineSample001

fun main() {
    //exampleBlocking()
    //exampleBlockingV2()
    //exampleBlockingDispatcher()
    //exampleGlobalLaunch()
    //exampleLaunchScope()
    //exampleLaunchScopeV2()

    //exampleAsyncAwait()
    exampleAsyncAwaitV2()

}

suspend fun printDelayed(message: String) {
    delay(1500)
    println(message)
}

fun exampleBlocking() {
    println("one")
    runBlocking {
        printDelayed("two")
    }
    println("three")
}

fun exampleBlockingV2() = runBlocking{
    println("one")
    printDelayed("two")
    println("three")
}

fun exampleBlockingDispatcher() {
    runBlocking(Dispatchers.Default) {
        println("one - from thread ${Thread.currentThread().name}")
        printDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
}

fun exampleGlobalLaunch() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    val job = GlobalScope.launch{
        printDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
    job.join()
}

fun exampleLaunchScope() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    launch{
        printDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
}

fun exampleLaunchScopeV2() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")

    val customerDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    launch(customerDispatcher){
        printDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")

    (customerDispatcher.executor as ExecutorService).shutdown()

}

fun exampleAsyncAwait() = runBlocking {
    //val deferred1 = async { calculateHardThings(10) }.await()
    val startTime = System.currentTimeMillis()

    val deferred1 = async { calculateHardThings(10) }
    val deferred2 = async { calculateHardThings(20) }
    val deferred3 = async { calculateHardThings(30) }

    val sum = deferred1.await() + deferred2.await() + deferred3.await()

    println("async/await result = $sum")


    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - startTime }")

}

fun exampleAsyncAwaitV2() = runBlocking {
    //val deferred1 = async { calculateHardThings(10) }.await()
    val startTime = System.currentTimeMillis()

    val deferred1 = async { calculateHardThings(10) }.await()
    val deferred2 = async { calculateHardThings(20) }.await()
    val deferred3 = async { calculateHardThings(30) }.await()

    val sum = deferred1 + deferred2+ deferred3

    println("async/await result = $sum")


    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - startTime }")

}




suspend fun calculateHardThings(startNum: Int): Int {
    delay(1000)
    return startNum * 10
}


