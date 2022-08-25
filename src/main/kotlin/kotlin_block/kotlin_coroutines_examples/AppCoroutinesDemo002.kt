package kotlin_block.kotlin_coroutines_examples

import kotlinx.coroutines.*

fun main() {

    var isActive = true

    val job = GlobalScope.launch(Dispatchers.Default) {
        println("Coroutine is working...")
        println("Starting running calculation...")

        for(i in 30..40) {
            if(isActive){
                println("Result for i = $i: ${fib(i)}")
            }
        }

        println("Ending running calculation...")
    }

    val job02 = GlobalScope.launch(Dispatchers.Default) {
        println("Coroutine is working...")
        println("Starting running calculation...")

        withTimeout(3000L) {
            for(i in 30..40) {
                if(isActive){
                    println("Result for i = $i: ${fib(i)}")
                }
            }
        }
        println("Ending running calculation...")
    }

    runBlocking {
        delay(10000L)
        job02.join()
        println("Main Thread is continuing...")
    }


}

fun fib(n: Int): Long {
    return if (n == 0) 0
    else if(n == 1) 1
    else fib(n - 1) + fib(n - 2)
}