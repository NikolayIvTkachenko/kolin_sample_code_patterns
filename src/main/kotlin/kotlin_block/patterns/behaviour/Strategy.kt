package kotlin_block.patterns.behaviour

fun main() {

    val h = OurHeroV3()
    h.shoot
    h.currentWeapon = Weapons.banana
    h.shoot

}


enum class Direction {
    LEFT, RIGHT
}

abstract class Projectile(private val x: Int, private val y: Int, private val direction: Direction)

class OurHero {
    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173

    fun shoot(): Projectile {
        return object : Projectile(x, y, direction){
            // Draw and animate projectile here

        }
    }
}

class OurHeroV2 {
    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173

    private var currentWeapon : Weapon = Peashooter()

    fun shoot(): Projectile {
        return currentWeapon.shoot(x, y, direction)
    }

    fun equip(weapon: Weapon) {
        currentWeapon = weapon
    }
}

interface Weapon {
    fun shoot(x: Int, y: Int, direction: Direction): Projectile
}

class Peashooter : Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction): Projectile {
        return object  : Projectile(x, y, direction) {
            // Fly straight
        }
    }
}

class Pomegranate : Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction): Projectile {
        return object : Projectile(x, y, direction){
            // Explode when you hit first enemy
        }
    }
}

class Banana : Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction): Projectile {
        return object : Projectile(x, y, direction) {
            // Return when you hit screen border
        }
    }
}


object Weapons {
    //Function we'll be there soon
    val peashooter = fun(x: Int, y: Int, direction: Direction): Projectile {
        // Fly straight
        return object  : Projectile(x, y, direction) {
            // Fly straight
        }
    }

    val banana = fun (x: Int, y:Int, direction: Direction): Projectile {
        //Return when you hit screen border
        return object : Projectile(x, y, direction) {
            // Return when you hit screen border
        }
    }


    val pomegranate = fun(x: Int, y: Int, direction: Direction): Projectile {
        // Explode when you hit first enemy
        return object : Projectile(x, y, direction){
            // Explode when you hit first enemy
        }
    }
}



class OurHeroV3 {

    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173

    var currentWeapon = Weapons.peashooter

    val shoot = fun() {
        currentWeapon(x, y, direction)
    }
}

