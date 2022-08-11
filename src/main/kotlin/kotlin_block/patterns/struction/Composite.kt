package kotlin_block.patterns.struction


fun main() {

    val miller = RiflemanV2()
    val caparzo = RiflemanV2()
    val jackson = RiflemanV2()

    val squad = Squad()

    squad.infantryUnits.add(miller)
    squad.infantryUnits.add(caparzo)
    squad.infantryUnits.add(jackson)

    println(squad.infantryUnits.size)

    val squad002 = Squad(miller, caparzo, jackson)

}

interface CanCountBullets {
    fun bulletsLeft(): Int
}

interface InfantryUnit : CanCountBullets

class RiflemanV2(initialMagazines: Int = 3) : InfantryUnit {

    private val magazines = List<Magazine>(initialMagazines){
        Magazine(5)
    }

    override fun bulletsLeft(): Int {
        return magazines.sumBy { it.bulletsLeft() }
    }

}

class Sniper(initialBullets: Int = 50) : InfantryUnit{

    private val bullets = List(initialBullets) {Bullet()}

    override fun bulletsLeft(): Int {
        return bullets.size
    }

}

class Squad(val infantryUnits: MutableList<InfantryUnit> = mutableListOf()) : CanCountBullets{

    constructor(first: InfantryUnit) : this(mutableListOf()) {
        this.infantryUnits.add(first)
    }

    constructor(first: InfantryUnit, second: InfantryUnit) : this(first) {
        this.infantryUnits.add(second)
    }

    constructor(first: InfantryUnit, second: InfantryUnit, third: InfantryUnit) : this(first, second) {
        this.infantryUnits.add(third)
    }


    constructor(vararg units: InfantryUnit): this(mutableListOf()){
        for (u in units){
            this.infantryUnits.add(u)
        }
    }

    override fun bulletsLeft(): Int {
        TODO("Not yet implemented")
    }
}

class Bullet

class Magazine(capacity: Int): CanCountBullets {
    private val bullets = List(capacity) {Bullet()}
    override fun bulletsLeft(): Int {
        return bullets.size
    }
}


