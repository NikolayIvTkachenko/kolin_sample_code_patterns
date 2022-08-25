package kotlin_block.kotlin_coroutines_examples

import kotlinx.coroutines.*

fun main() {

//    val job00 = GlobalScope.launch(newSingleThreadContext("Name 001")){
//        println("Starting coroutine in thread ${Thread.currentThread().name}")
//        val answer = doNetworkCall()
//        println(answer)
//    }

    val job01 = GlobalScope.launch(Dispatchers.IO){
        println("Starting coroutine in thread ${Thread.currentThread().name}")
        val answer = doNetworkCall()
        withContext(Dispatchers.Unconfined){
            println("WithContext coroutine in thread ${Thread.currentThread().name}")
            println(answer)
        }

    }


    runBlocking {
        //job00.join()
        job01.join()
    }


}

suspend fun doNetworkCall(): String {
    delay(3000L)
    return "Result of request to API REST"
}