package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select
import java.util.Random
import javax.sound.midi.Receiver

suspend fun main() {

    val publisher: ReceiveChannel<Int> = GlobalScope.produce {
        for(i in 2018 downTo 1970){
            send(i)
            delay(20)
        }

    }

//    publisher.consumeEach {
//        println("Got $it")
//    }

    val firstProducer = GlobalScope.produce<String>{
        delay(Random().nextLong(100))
        send("First")
    }

    val secondProducer = GlobalScope.produce<String> {
        delay(Random().nextLong(100))
        send("Second")
    }

    val winner = select<String> {
        firstProducer.onReceive{
            it.toLowerCase()
        }

        secondProducer.onReceive{
            it.toUpperCase()
        }
    }
    //println(winner)


    val firstProducerV2 = GlobalScope.produce<String>{
        for(c in 'a'..'z'){
            delay(Random().nextLong(100))
            send(c.toString())
        }

    }

    val secondProducerV2 = GlobalScope.produce<String> {
        for(c in 'A'..'Z'){
            delay(Random().nextLong(100))
            send(c.toString())
        }
    }

    val winnerV2 = select<String> {
        firstProducerV2.onReceive{
            it
        }

        secondProducerV2.onReceive{
            it
        }
    }

    //println(winnerV2)
//    for(i in 1..10){
//        println(select<String> {
//            firstProducerV2.onReceive{
//                it
//            }
//
//            secondProducerV2.onReceive{
//                it
//            }
//        })
//    }


    val firstProducerV3 = GlobalScope.produce<String>{
        for(c in 'a'..'z'){
            delay(Random().nextLong(100))
            send(c.toString())
        }
        close()
    }

    val secondProducerV3 = GlobalScope.produce<String> {
        for(c in 'A'..'Z'){
            delay(Random().nextLong(100))
            send(c.toString())
        }
        close()
    }

    while (true){
        val resultV5 = select<String?> {
            firstProducerV3.onReceive{
                it.toString()
            }

            secondProducerV3.onReceiveCatching{
                it.toString()
            }
        }

//        if (resultV5.isNullOrEmpty() == null){
//            break
//        } else {
//            println(resultV5)
//        }
        if (resultV5 == null){
            break
        } else {

            println(resultV5)
        }

    }




}






