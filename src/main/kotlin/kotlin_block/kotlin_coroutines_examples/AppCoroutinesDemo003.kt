package kotlin_block.kotlin_coroutines_examples

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {


    val job = GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {
            val result01 = networkCall01()
            println("Result01 is $result01")
            val result02 = networkCall02()
            println("Result02 is $result02")
        }
        println("Request took $time ms.")
    }

    val job02 = GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {
            var result01: String? = null
            var result02: String? = null

            launch { result01 = networkCall01() }
            launch { result02 = networkCall02() }

            println("Result01 is $result01")
            println("Result02 is $result02")
        }
        println("Request took $time ms.")
    }


    val job03 = GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {
            var result01: String? = null
            var result02: String? = null

            val job001 = launch { result01 = networkCall01() }
            val job002 = launch { result02 = networkCall02() }

            job001.join()
            job002.join()

            println("Result01 is $result01")
            println("Result02 is $result02")
        }
        println("Request took $time ms.")
    }

    val job04 = GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {
            var result01 = async { networkCall01() }
            var result02 = async { networkCall02() }

            println("Result01 is ${result01.await()}")
            println("Result02 is ${result02.await()}")
        }
        println("Request took $time ms.")
    }


    runBlocking {
        //job.join()
        //job02.join()
        //job03.join()
        job04.join()
    }

}

suspend fun networkCall01(): String {
    delay(3000L)
    return "Result 0112345"
}

suspend fun networkCall02(): String {
    delay(3000L)
    return "Result 0245637"
}