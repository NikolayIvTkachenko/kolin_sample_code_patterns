package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.yield

fun main() {

    //Incorrect without mutex
    var counter = 0

    val jobs = List(10){
        GlobalScope.launch {
            repeat(1000){
                counter++
                yield()
            }
        }
    }

    runBlocking {
        jobs.forEach {
            it.join()
        }
        println(counter)
    }


    //Correct with mutex
    var counterV2 = 0
    val mutex = Mutex()

    val jobsV2 = List(10){
        GlobalScope.launch {
            repeat(1000){
//                mutex.lock()
//                counterV2++
//                mutex.unlock()
//                yield()

//                try {
//                    mutex.lock()
//                    counterV2++
//                }finally {
//                    mutex.unlock()
//                }
//                yield()

                mutex.withLock {
                    counterV2++
                }
                yield()

            }
        }
    }

    runBlocking {
        jobsV2.forEach {
            it.join()
        }
        println(counterV2)
    }

}

