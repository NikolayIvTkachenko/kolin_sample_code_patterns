package kotlin_block.kotlin_coroutines_examples_02

import kotlinx.coroutines.*

//AppDemoCoroutineSample001

fun main() {
    //exampleBlocking()
    //exampleBlockingV2()
    //exampleBlockingDispatcher()
    exampleGlobalLaunch()


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