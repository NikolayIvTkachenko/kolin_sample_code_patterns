package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random

suspend fun main() {

    val deferred = CompletableDeferred<String>()

    GlobalScope.launch {
        delay(100)
        if(Random().nextBoolean()){
            deferred.complete("OK")
        } else{
            deferred.completeExceptionally(java.lang.RuntimeException())
        }
    }
    //deferred.cancel()
    println(deferred.await())


}



