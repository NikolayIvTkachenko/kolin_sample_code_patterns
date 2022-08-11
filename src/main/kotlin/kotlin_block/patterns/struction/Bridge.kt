package kotlin_block.patterns.struction

fun main() {



    val rifleman = Soldier(Rifle(), RegularLegs())
    val grenadier = Soldier(Grenade(), RegularLegs())
    val upgradedGrenadier = Soldier(GrenadePack(), RegularLegs())
    val upgradeRifleman = Soldier(MachineGun(), RegularLegs())
    val lightRifleman = Soldier(Rifle(), AthleticLegs())
    val lightgrenadier = Soldier(Grenade(), AthleticLegs())




}
//Infantry --> Soldier
//Weapon    --> Rifle
//          --> MachineGun
//          --> Grenade
//          --> GrenadePack
//
//Legs      --> RegularLegs
//          --> AthleticLegs
//
//

interface Infantry {
    fun move(x :Long, y: Long)
    fun attack(x: Long, y: Long)
}

//Update interface
//interface Infantry {
//    fun move(x :Long, y: Long)
//    fun attack(x: Long, y: Long)
//    fun shout()
//}


//Infantry --> Rifleman --> Upgrade Rifleman
//                      --> Light Rifleman
//         --> Grenadier --> Upgrade Grenadier
//                       --> Light Grenadier


open class Rifleman : Infantry {
    override fun move(x: Long, y: Long) {
        TODO("Not yet implemented")
    }

    override fun attack(x: Long, y: Long) {
        TODO("Not yet implemented")
    }

}


open class Grenadier : Infantry {
    override fun move(x: Long, y: Long) {
        TODO("Not yet implemented")
    }

    override fun attack(x: Long, y: Long) {
        TODO("Not yet implemented")
    }

}

class UpgradedRifleman : Rifleman() {
    override fun attack(x: Long, y: Long) {

    }
}

class UpgradeGrenadier : Grenadier(){
    override fun attack(x: Long, y: Long) {

    }
}


class LightRifleman : Rifleman() {
    override fun move(x: Long, y: Long) {

    }
}

class LightGrenadier : Grenadier() {
    override fun move(x: Long, y: Long) {

    }
}



typealias PointsOfDamage = Long
typealias Meters = Int

const val GRENADE_DAMAGE : PointsOfDamage = 5L
const val RIFLE_DAMAGE = 3L
const val REGULAR_SPEED : Meters = 1

interface Weapon {
    fun causeDamage(): PointsOfDamage
}

interface Legs {
    fun move(): Meters
}

class Grenade : Weapon {
    override fun causeDamage(): PointsOfDamage = GRENADE_DAMAGE
}

class GrenadePack : Weapon {
    override fun causeDamage(): PointsOfDamage = GRENADE_DAMAGE * 3
}

class Rifle : Weapon {
    override fun causeDamage(): PointsOfDamage = RIFLE_DAMAGE
}

class MachineGun : Weapon {
    override fun causeDamage(): PointsOfDamage = RIFLE_DAMAGE * 2
}

class RegularLegs : Legs {
    override fun move(): Meters = REGULAR_SPEED
}

class AthleticLegs : Legs {
    override fun move(): Meters = REGULAR_SPEED * 2
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