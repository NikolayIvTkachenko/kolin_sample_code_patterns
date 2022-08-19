package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random
import java.util.concurrent.CountDownLatch


fun main() {

    val latch = CountDownLatch(3)

    var name: String? = null
    var catchphrase = ""
    var repeats = 0

    GlobalScope.launch{
        delay(Random().nextLong(100))
        println("Got name")
        name = "Nick Nikolson"

        latch.countDown()
    }

    GlobalScope.launch{
        delay(Random().nextLong(100))
        println("Got catchphrase")

        catchphrase = "I will build base on mars"

        latch.countDown()
    }

    GlobalScope.launch{
        delay(Random().nextLong(100))
        println("Got repeats")

        repeats = 10

        latch.countDown()
    }

    latch.await()

    val ttt = FavoriteCharacter(name!!, catchphrase, repeats)
    println(ttt)

}


data class FavoriteCharacter(val name: String, val catchphrase: String, val repeats: Int)
