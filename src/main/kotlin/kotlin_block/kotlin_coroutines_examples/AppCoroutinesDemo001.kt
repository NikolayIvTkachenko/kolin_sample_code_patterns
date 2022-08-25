package kotlin_block.kotlin_coroutines_examples

import kotlinx.coroutines.*

fun main() {

    val job = GlobalScope.launch(Dispatchers.Default) {
        repeat(5) {
            println("Coroutine is working...")
            delay(5000L)
        }
    }

//    runBlocking {
//        job.join()
//        println("Main Thread is continuing...")
//    }

    runBlocking {
        delay(10000L)
        job.cancel()
        println("Cancel...")
        println("Main Thread is continuing...")
    }


}