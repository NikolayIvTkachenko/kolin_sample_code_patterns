package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

suspend fun main() {

    val batman = GlobalScope.actor<String> {

        for(c in this){
            println("Batman is beating some sence into $c")
            delay(100)
        }
    }

    val robin = GlobalScope.actor<String> {
        for(c in this){
            println("Robin is beating some sence into $c")
            delay(250)
        }
    }

    val j = GlobalScope.launch {
        for(c in listOf("Jocker", "Bane", "Penguin", "Ridder", "Killer Croc")){
            val result = select<Pair<String,String>> {
                batman.onSend(c){
                    Pair("Batman", c)
                }

                robin.onSend(c) {
                    Pair("Robun", c)
                }
            }

            delay(90)
            println(result)
        }
    }

    j.join()
}

