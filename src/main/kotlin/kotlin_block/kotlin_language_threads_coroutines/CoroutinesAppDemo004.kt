package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.*
import java.util.*


suspend fun main() {

    val coroutines = GlobalScope.async {

        withTimeout(500){
            try {
                val time = Random().nextLong(1000)
                println("It will take me $time to do")

                delay(time)
                println("Returning profile")
                "Profile"

            }catch (e: TimeoutCancellationException){
                e.printStackTrace()
            }
        }


    }


    val result = try{
        coroutines.await()
    }catch (e: TimeoutCancellationException){
        "No Profile"
    }
    println(result)

}


