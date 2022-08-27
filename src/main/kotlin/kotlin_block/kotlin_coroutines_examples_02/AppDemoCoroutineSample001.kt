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









