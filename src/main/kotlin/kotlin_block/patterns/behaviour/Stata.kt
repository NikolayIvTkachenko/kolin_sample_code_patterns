package kotlin_block.patterns.behaviour

fun main() {



}

interface WhatCanHappen {

    fun seeHero()

    fun getHit(pointsOfDamage: Int)

    fun calmAgain()

    fun timePassed()

}


class Snail : WhatCanHappen {

    private var healthPoints = 10

    private var mood : Mood = Still()

    override fun seeHero() {
        mood = when(mood) {
            is Still -> Agressive()
            is Agressive -> mood
            is Retreating -> mood
            is Dead -> mood

        }
    }

    override fun getHit(pointsOfDamage: Int) {

        healthPoints -= pointsOfDamage

        mood = when {
            (healthPoints <= 0) -> Dead()
            mood is Agressive -> Retreating()
            else -> mood
        }

    }

    override fun calmAgain() {
        TODO("Not yet implemented")
    }

    override fun timePassed() {
        mood = when(mood) {
            is Retreating -> Agressive()
            else -> mood
        }
    }


}

sealed class Mood {
    //Some abstract methods here, like draw(), for example
}

class Still : Mood()

class Agressive : Mood()

class Retreating : Mood()

class Dead : Mood()

