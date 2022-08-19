package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.*
import java.util.*

fun main() {

//    for (i in 1..10){
//        CoroutineFactory.longCoroutine(i)
//    }


    //Корутина
    val j = GlobalScope.launch {
        for(i in 1..10_000) {
            if(i % 1000 == 0) {
                println(i)
                yield()
            }
        }
    }

    //Запускаем и отображаем
    runBlocking {
        j.join()
    }


    val cancellable = GlobalScope.launch {
        try{

            for(i in 1..1000) {
                println("Cancellable: $i")
                //computeNthFibonacci(i)
                yield()
            }

        } catch (e: CancellationException) {
            e.printStackTrace()
        }
    }

    val notCancellable = GlobalScope.launch {
        for(i in 1..1000) {
            println("Not cancellable $i")
            //computeNthFibonacci(i)
        }
    }


//    println("Canceling cancelable")
//    cancellable.cancel()
//    println("Canceling not cancellable")
//    notCancellable.cancel()
//
//    runBlocking {
//        cancellable.join()
//        notCancellable.join()
//    }


    val userProfile = GlobalScope.async {
        delay(Random().nextLong(100))
        "Message"
    }

    val userHistory = GlobalScope.async {
        delay(Random().nextLong(100))
        listOf(1, 2, 3)
    }

    runBlocking {
        println("User profile is ${userProfile.await()} and his history is ${userHistory.await()}")
    }

}

fun CoroutineFactory.longCoroutine(index: Int) = GlobalScope.launch {

    var uuid = UUID.randomUUID()
    for(i in 1..100_000) {
        val newUuid = UUID.randomUUID()

        if(newUuid < uuid) {
            uuid = newUuid
        }

        if(i % 100 == 0) {
            yield()
        }
    }

    println("Done longCoroutine $index")
    latch.countDown()

}

