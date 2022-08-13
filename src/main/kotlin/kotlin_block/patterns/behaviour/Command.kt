package kotlin_block.patterns.behaviour

import kotlin_block.patterns.struction.Infantry
import kotlin_block.patterns.struction.Legs
import kotlin_block.patterns.struction.Weapon

fun main() {


}

class Soldier(private val weapon: Weapon, private val legs: Legs) : Infantry {
    override fun move(x: Long, y: Long) {
        //Find target
        //Shoot
        weapon.causeDamage()
    }

    override fun attack(x: Long, y: Long) {
        //Compute direction
        //Move at its own pace
        legs.move()
    }
}
