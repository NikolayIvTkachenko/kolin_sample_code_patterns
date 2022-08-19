package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

suspend fun main() {

    val channel = Channel<String>()
    val actor = activeActor(channel)

    val j1 = GlobalScope.launch {
        for (i in 42..53){
            println("j1 = ${i}")
            actor.send(i)
        }
        actor.close()
    }

    val j2 = GlobalScope.launch {
        for (i in channel){
            println(i)
        }
    }

    j1.join()
    j2.join()

}


fun activeActor(out: SendChannel<String>) = GlobalScope.actor<Int> {
    for (i in this){
        out.send(i.toString().reversed())
    }
    out.close()
}