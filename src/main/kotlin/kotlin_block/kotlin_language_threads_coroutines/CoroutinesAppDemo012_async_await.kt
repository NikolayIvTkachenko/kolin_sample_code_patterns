package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.util.Random

suspend fun main() {

    val name = getName()
    val catchphrases = getCatchphrase()
    val repeats = getRepeats()

    println("${name.await()} says: ${catchphrases.await().repeat(repeats.await())}")

    val character = FavoriteCharacterV2(name.await(), catchphrases.await(), repeats.await())
    with(character){
        println(character.toString())
    }

}


private fun getName() = GlobalScope.async {
    delay(Random().nextLong(100))
    println("Gon name")
    "Nikolay Future"
}

private fun getCatchphrase() = GlobalScope.async {
    delay(Random().nextLong(100))
    println("Got catchphrase")
    "I will build base on mars"
}

private fun getRepeats() = GlobalScope.async {
    delay(Random().nextLong(100))
    println("Got repeats")
    10
}

data class FavoriteCharacterV2(val name: String, val catchphrase: String, val repeats: Int)