package kotlin_block.kotlin_language

import java.util.Random
import kotlin.concurrent.thread


fun <T> printAndClear(list: MutableList<T>){
    for (e in list){
        println(e)
        //list.remove(e) // not work
    }
}


fun main() {

    //0
    //printAndClear(mutableListOf("a", "b", "c"))

    //1
//    val pair = "a" to 1
//    val (key, value) = pair
//    println("$key => $value")


//    for(p in mapOf(1 to "Sunday", 2 to "Monday")){
//        println("${p.key} ${p.value}")
//    }

    //2
    val counter = AverageScore()

    thread(isDaemon = true) {
        while (true) counter.gamesPlayed = 0
    }

    for (i in 1..1_000){
        counter.totalScore += Random().nextInt(100)
        counter.gamesPlayed++

        println(counter.average)
    }

}

data class AverageScore(var totalScore: Int = 0, var gamesPlayed: Int = 0){
    val average: Int
        get() = if(gamesPlayed <= 0) 0
                else totalScore / gamesPlayed

}
