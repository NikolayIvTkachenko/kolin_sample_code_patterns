package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random

fun main() {

    val p1p2 = Channel<Int>()
    val p2p1 = Channel<Int>()

    val player1 = player("Pilot 1", p2p1, p1p2)
    val player2 = player("Pilot 2", p1p2, p2p1)

    runBlocking {
        p2p1.send(0)
        delay(2000)
    }

}

fun player(name: String, input: Channel<Int>, output: Channel<Int>) = GlobalScope.launch {

    for(m in input){
        val d = Random().nextLong(100)
        println("$name got $m, ${if (d > m) "won" else "lost"}")

        delay(d)

        output.send(d.toInt())
    }

}