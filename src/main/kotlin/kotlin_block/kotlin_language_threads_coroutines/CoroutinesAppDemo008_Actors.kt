package kotlin_block.kotlin_language_threads_coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

suspend fun main() {

    val test = GlobalScope.actor<Task> {
        while (!isClosedForReceive) {
            println(receive().description.repeat(10))
        }
    }

    val testV2 = GlobalScope.actor<Task> {
        var next = receiveCatching().getOrNull()

        while (next != null) {
            println(next.description.toUpperCase())
            next = receiveCatching().getOrNull()
        }
    }

    val testV3 = GlobalScope.actor<Task> {
        for (t in channel){
            println(t.description)
        }

        println("Done test V3 everything")
    }


    //actorTest(test)
    //actorTest(testV2)
    //actorTest(testV3)


    val reportActor = GlobalScope.actor<ReportRequest> (capacity = 100){
        for(req in this){
           //generateReport(req
        }
    }

    reportActor.send(ReportRequest("Annual report", LocalDate.of(2017,1,1), LocalDate.of(2017,12,31)))


}


data class Task (val description: String)

fun actorTest(actor: SendChannel<Task>){
    runBlocking {
        for(i in 'a'..'z'){
            actor.send(Task(i.toString()))
        }
        actor.close()
    }
}

data class ReportRequest(val name: String, val from: LocalDate, val to: LocalDate)



