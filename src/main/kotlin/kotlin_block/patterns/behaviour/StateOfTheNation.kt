package kotlin_block.patterns.behaviour

fun main() {



}


class SnailV2 {
    internal var mood: MoodV2 = StillV2(this)

    private var healthPoints = 10

}

interface WhatCanHappenV2 {

    fun seeHero(): MoodV2

    fun getHit(pointsOfDamage: Int): MoodV2

    fun calmAgain(): MoodV2

    fun timePassed(): MoodV2

}


sealed class MoodV2 : WhatCanHappenV2

class StillV2(private val snail: SnailV2) : MoodV2() {
    override fun seeHero(): MoodV2  {
        return snail.mood.run {
            AgressiveV2(snail)
        }
    }

    override fun getHit(pointsOfDamage: Int): MoodV2 {
        return this
    }

    override fun calmAgain(): MoodV2  {
        return this
    }

    override fun timePassed(): MoodV2  {
        return this
    }

}

class AgressiveV2(snail: SnailV2): MoodV2(){
    override fun seeHero(): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun getHit(pointsOfDamage: Int): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun calmAgain(): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun timePassed(): MoodV2 {
        TODO("Not yet implemented")
    }

}

class RetreatingV2(snail: SnailV2) : MoodV2(){
    override fun seeHero(): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun getHit(pointsOfDamage: Int): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun calmAgain(): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun timePassed(): MoodV2 {
        TODO("Not yet implemented")
    }

}

class DeadV2(snail: SnailV2) : MoodV2() {
    override fun seeHero(): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun getHit(pointsOfDamage: Int): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun calmAgain(): MoodV2 {
        TODO("Not yet implemented")
    }

    override fun timePassed(): MoodV2 {
        TODO("Not yet implemented")
    }

}