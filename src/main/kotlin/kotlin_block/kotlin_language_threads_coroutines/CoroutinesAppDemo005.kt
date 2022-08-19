package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.*
import java.util.UUID
import java.util.concurrent.CancellationException

suspend fun main() {

    suspend fun produceBeautifulUuid(): String {
        try{
            val uuids = List(1000){
                yield()
                UUID.randomUUID()

            }

            println("Coroutine done")
            return uuids.sorted().first().toString()

        }catch (t: CancellationException){
            println("Got cancelled")
        }
        return ""
    }

    val parentJob = Job()
    List(10){
        GlobalScope.async(parentJob){
            produceBeautifulUuid()
        }
    }

    delay(100)
    parentJob.cancel()
    delay(1000)

}



