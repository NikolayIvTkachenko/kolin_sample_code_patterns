package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

suspend fun main() {

    val numbers = listOf<Int>(2, 3, 989, 4, 5, 77,88,23, 32,322,1,22)

    val input = Channel<List<Int>>()
    val output = collector()
    val dividers = List(10) {
        divide(input, output)
    }

    GlobalScope.launch {
        for (c in numbers.chunked(5)) {
            input.send(c)
        }
        input.close()
    }

    //show max value from list
    dividers.forEach {
        it.await()
    }

    output.close()

}

fun divide(input: ReceiveChannel<List<Int>>, output: SendChannel<Int>) = GlobalScope.async {
    var max = 0
    for(list in input) {
        for (i in list) {
            if (i > max) {
                max = i
                output.send(max)
            }
        }
    }
}

fun collector() = GlobalScope.actor<Int> {
    var max = 0
    for (i in this) {
        max = Math.max(max, i)
    }
    println(max)
}