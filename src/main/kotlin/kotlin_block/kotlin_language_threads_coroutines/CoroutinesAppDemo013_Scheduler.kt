package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.*

suspend fun main() {

//    val r1 = GlobalScope.async{
//        for (i in 1..100){
//            println(Thread.currentThread().name)
//            yield()
//        }
//    }
//
//    r1.await()
//
//    val r2 = GlobalScope.async{
//        for (i in 1..100){
//            val parentThread = Thread.currentThread().name
//            GlobalScope.launch {
//                println(Thread.currentThread().name == parentThread)
//            }
//
//            yield()
//        }
//    }
//
//    r2.await()

    val pool = newFixedThreadPoolContext(2, "New Pool")

    val r3 = GlobalScope.async(pool){
        for (i in 1..100){
            println(Thread.currentThread().name)
            yield()
        }
    }

    r3.await()
    pool.close()
}


