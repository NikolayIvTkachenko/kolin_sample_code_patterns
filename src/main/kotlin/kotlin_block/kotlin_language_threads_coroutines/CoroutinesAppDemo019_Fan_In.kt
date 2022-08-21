package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random

fun main() {

    val collector = Channel<String>()

    techBunch(collector)
    theFerge(collector)

    runBlocking {
        collector.consumeEach {
            println("${it}")
        }
    }


}

private fun techBunch(collector: Channel<String>) = GlobalScope.launch {
    repeat(10) {
        delay(Random().nextLong(1000))
        collector.send("Tech Bunch")
    }
}

private fun theFerge(collector: Channel<String>) = GlobalScope.launch {
    repeat(10) {
        delay(Random().nextLong(1000))
        collector.send("the Ferge")
    }
}