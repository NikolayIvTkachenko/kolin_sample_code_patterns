package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {

    val producer = producePages()

    val consumers = List(10){
        consumePages(producer)
    }

    runBlocking {
        consumers.forEach {
            it.await()
        }
    }


}

private fun producePages() = GlobalScope.produce {
    for (i in 1..10_000) {
        for (c in 'a'..'z') {
            send(i to "page$c")
        }
    }
}

private fun consumePages(channel: ReceiveChannel<Pair<Int, String>>) = GlobalScope.async {
    for (p in channel) {
        println(p)
    }
}

