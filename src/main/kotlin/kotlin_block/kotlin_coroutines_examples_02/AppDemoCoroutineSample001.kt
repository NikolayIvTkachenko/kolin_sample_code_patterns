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

    exampleLaunch00()

    //exampleAsyncAwait()
    //exampleAsyncAwaitV2()

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

fun exampleLaunch00() = runBlocking {
    //val deferred1 = async { calculateHardThings(10) }.await()
    val startTime = System.currentTimeMillis()

//    val deferred1 = async { calculateHardThingsV2(10) }
//    val deferred2 = async { calculateHardThingsV2(20) }
//    val deferred3 = async { calculateHardThingsV2(30) }
//    val sum = deferred1.await() + deferred2.await() + deferred3.await()
//    println("async/await result = $sum")
    //Time taken: 3031


//    launch { calculateHardThingsV2(10) }
//    println("first start")
//    launch { calculateHardThingsV2(20) }
//    println("second start")
//    launch { calculateHardThingsV2(30) }
//    println("third start")
//    //Time taken: 8

//    val deferred1 =async { calculateHardThingsV2(10) }
//    println("first start")
//    val deferred2 =async { calculateHardThingsV2(20) }
//    println("second start")
//    val deferred3 =async { calculateHardThingsV2(30) }
//    println("third start")
//    val sum = deferred1.await() + deferred2.await() + deferred3.await()
//    println("async/await result = $sum")
//    //Time taken: 3028

//    launch(Dispatchers.IO) { calculateHardThingsV2(10) }
//    println("first start")
//    launch(Dispatchers.IO) { calculateHardThingsV2(20) }
//    println("second start")
//    launch(Dispatchers.IO) { calculateHardThingsV2(30) }
//    println("third start")
//    //Time taken: 21

//    launch(Dispatchers.Unconfined) { calculateHardThingsV2(10) }
//    println("first start")
//    launch(Dispatchers.Unconfined) { calculateHardThingsV2(20) }
//    println("second start")
//    launch(Dispatchers.Unconfined) { calculateHardThingsV2(30) }
//    println("third start")
//    //Time taken: 13

    launch(Dispatchers.Default) { calculateHardThingsV2(startTime ,10) }
    println("first start")
    launch(Dispatchers.Default) { calculateHardThingsV2(startTime ,20) }
    println("second start")
    launch(Dispatchers.Default) { calculateHardThingsV2(startTime ,30) }
    println("third start")
    //Time taken: 22
    //calculateHardThingsV2, startNum = 10 result = 100
    //calculateHardThingsV2, startNum = 20 result = 200
    //I'm working in thread DefaultDispatcher-worker-2
    //calculateHardThingsV2, startNum = 30 result = 300
    //I'm working in thread DefaultDispatcher-worker-3
    //Coroutine time taken: 3027
    //Coroutine time taken: 3027
    //I'm working in thread DefaultDispatcher-worker-1
    //Coroutine time taken: 3028


//    launch(Dispatchers.Main) { calculateHardThingsV2(10) }
//    println("first start")
//    launch(Dispatchers.Main) { calculateHardThingsV2(20) }
//    println("second start")
//    launch(Dispatchers.Main) { calculateHardThingsV2(30) }
//    println("third start")
    //error
    //Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used

    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - startTime }")

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

suspend fun calculateHardThingsV2(startTime: Long, startNum: Int): Int {
    delay(3000)
    val result = startNum * 10
    println("calculateHardThingsV2, startNum = $startNum result = $result")
    println("I'm working in thread ${Thread.currentThread().name}")
    val endTime = System.currentTimeMillis()
    println("Coroutine time taken: ${endTime - startTime }")
    return result
}


