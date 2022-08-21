package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

suspend fun main() {
    //Not buffered channel
    val channel = Channel<Int>()
    val j = GlobalScope.launch {
        for (i in 1..10) {
            channel.send(i)
            println("Sent $i")
        }
    }
    j.join()


    //Buffered channel
    val channelV2 = Channel<Int>(5)
    val jV2 = GlobalScope.launch {
        for (i in 1..10) {
            channelV2.send(i)
            println("Sent $i")
        }
    }
    jV2.join()

}

