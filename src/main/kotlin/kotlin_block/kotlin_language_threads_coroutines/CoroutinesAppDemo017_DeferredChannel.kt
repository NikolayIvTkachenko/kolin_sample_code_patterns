package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select
import kotlin.system.measureTimeMillis

suspend fun main() {

    val elements = 10
    val deferredChannel = Channel<Deferred<Int>>(elements)

    GlobalScope.launch {
        repeat(elements) {i ->
            println("$i sent")
            deferredChannel.send(async {
                delay(if (i == 0) 1000 else 10)
                i
            })
        }
    }

    val time = measureTimeMillis {
        repeat(elements) {
            val result = select<Int> {
                deferredChannel.onReceive {
                    select {
                        it.onAwait{it}
                    }
                }
            }
            println(result)
        }
    }

    println("Took ${time} ms")

    val stop = GlobalScope.async {
        delay(600)
        true
    }


    val channel = Channel<Deferred<Int>>(10)

    repeat(10){i ->
        channel.send(GlobalScope.async {
            delay(i * 100L)
            i
        })
    }


    runBlocking {
        for (i in 1..10){
            select<Unit> {
                stop.onAwait {
                    channel.close()
                }
                channel.onReceive {
                    println(it.await())
                }
            }
        }
    }

}