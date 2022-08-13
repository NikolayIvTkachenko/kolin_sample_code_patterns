package kotlin_block.patterns.behaviour

fun main() {


}


interface QA {
    fun doesMyCodeWork(): Boolean
}

interface Parrot {
    fun isEating(): Boolean
    fun isSleeping(): Boolean
}

object Kenny : QA, Parrot {
    override fun doesMyCodeWork(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEating(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSleeping(): Boolean {
        TODO("Not yet implemented")
    }

}

class MyMind {
    val qa = Kenny

    fun taskCompleted(){
        if(!qa.isEating() && !qa.isSleeping()) {
            println(qa.doesMyCodeWork())
        }
    }

}