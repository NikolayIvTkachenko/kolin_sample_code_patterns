package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.onReceiveOrNull
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.selectUnbiased

suspend fun main() {

    //V1
    println("==V1 ===================")
    val p1 = GlobalScope.produce<String> {
        repeat(10){
            send("A")
        }
    }

    val p2 = GlobalScope.produce<String> {
        repeat(5) {
            send("B")
        }
    }

    runBlocking {
        repeat(15) {

            val result = selectUnbiased<String> {
                p1.onReceive {
                    it ?: throw java.lang.RuntimeException()
                }
                p2.onReceive {
                    it ?: "p2 closed"
                }
            }

            if(result != null) {
                println(result)
            }
        }
    }


    //V2
    println("==V2 ===================")
    var count = 0
    while(count < 15) {
        val result = selectUnbiased<String?> {
            p1.onReceiveCatching {
                it.toString()
            }
            p2.onReceiveCatching {
                it.toString()
            }
        }

        if (result != null) {
            println(result)
            count++
        }
    }

}