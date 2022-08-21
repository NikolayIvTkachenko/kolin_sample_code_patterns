package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.selects.selectUnbiased
import java.util.concurrent.ConcurrentHashMap

suspend fun main() {

    val repeats = 10_000
    val p1 = producer("A", repeats)
    val p2 = producer("B", repeats)
    val p3 = producer("C", repeats)

    val results = ConcurrentHashMap<String, Int>()

    repeat(repeats) {
        //val result = select<String> {
        val result = selectUnbiased<String> {
            p1.onReceive { it }
            p2.onReceive { it }
            p3.onReceive { it }
        }
        results.compute(result) { k, v ->
            if(v == null) {
                1
            } else {
                v + 1
            }

        }
    }

    println(results)


}

fun producer(name: String, repeats: Int) = GlobalScope.produce {
    repeat(repeats){
        delay(1)
        send(name)
    }
}