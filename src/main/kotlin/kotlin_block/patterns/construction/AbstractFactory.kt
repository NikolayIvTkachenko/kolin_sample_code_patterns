package kotlin_block.patterns.construction


fun main() {

    println("Introduction to generics in Kotlin")

    class Cat

    val box = Box<Cat>()

    box.put(Cat())
    val cat = box.get()

    println("")
    println("=== HQ ===")

    val hq = HQV01()
    val barracks01 = hq.buildBarracks()
    val barracks02 = hq.buildBarracks()
    val vehicleFactory1 = hq.buildVehicleFactory()

    val units = listOf(
        barracks01.build(InfantryUnits.RIFLEMEN),
        barracks02.build(InfantryUnits.ROCKET_SOLDIER),
        barracks02.build(InfantryUnits.ROCKET_SOLDIER),
        vehicleFactory1.build(VehicleUnits.TANK),
        vehicleFactory1.build(VehicleUnits.APC),
        vehicleFactory1.build(VehicleUnits.APC)
    )


}

interface Building<in UnitType, out ProducedUnit> where UnitType : Enum<*>, ProducedUnit : Unit {
    fun build(type: UnitType) : ProducedUnit
}

interface Unit

interface Vehicle : Unit

interface Infantry : Unit

class Rifleman : Infantry

class RocketSoldier : Infantry

enum class InfantryUnits {
    RIFLEMEN,
    ROCKET_SOLDIER
}

class APC : Vehicle

class Tank : Vehicle

enum class VehicleUnits {
    APC,
    TANK
}

class Barracks : Building<InfantryUnits, Infantry> {
    override fun build(type: InfantryUnits): Infantry {
        return when(type) {
            InfantryUnits.RIFLEMEN -> Rifleman()
            InfantryUnits.ROCKET_SOLDIER -> RocketSoldier()
        }
    }
}

class VehicleFactory : Building<VehicleUnits, Vehicle> {
    override fun build(type: VehicleUnits): Vehicle {
        return when(type) {
            VehicleUnits.APC -> APC()
            VehicleUnits.TANK -> Tank()
        }
    }
}

class HQV01 {
    val buildings = mutableListOf<Building<*, Unit>>()

    fun buildBarracks(): Barracks {
        val b = Barracks()
        buildings.add(b)
        return b
    }

    fun buildVehicleFactory(): VehicleFactory {
        val vf = VehicleFactory()
        buildings.add(vf)
        return vf
    }

}

class HQV02 {

    val buildings = mutableListOf<Building<*, Unit>>()

    fun buildBarracks(): Building<InfantryUnits, Infantry> {
        val b = object : Building<InfantryUnits, Infantry> {
            override fun build(type: InfantryUnits): Infantry {
                return when (type) {
                    InfantryUnits.ROCKET_SOLDIER -> RocketSoldier()
                    InfantryUnits.RIFLEMEN -> Rifleman()
                }
            }
        }
        buildings.add(b)
        return b
    }

    fun buildVehicleFactory(): Building<VehicleUnits, Vehicle> {
        val vf = object : Building<VehicleUnits, Vehicle> {
            override fun build(type: VehicleUnits): Vehicle {
                return when(type){
                    VehicleUnits.APC -> APC()
                    VehicleUnits.TANK -> Tank()
                }
            }
        }
        buildings.add(vf)
        return vf
    }
}

interface HQ{
    fun buildBarracks(): Building<InfantryUnits, Infantry>
    fun buildVehicleFactory(): Building<VehicleUnits, Vehicle>
}

class CatHQ : HQ {
    override fun buildBarracks(): Building<InfantryUnits, Infantry> {
        TODO("Not yet implemented")
    }

    override fun buildVehicleFactory(): Building<VehicleUnits, Vehicle> {
        TODO("Not yet implemented")
    }

}

class DogHQ : HQ {
    override fun buildBarracks(): Building<InfantryUnits, Infantry> {
        TODO("Not yet implemented")
    }

    override fun buildVehicleFactory(): Building<VehicleUnits, Vehicle> {
        TODO("Not yet implemented")
    }

}


class Box<T> {
    private var inside: T? = null
    fun put (t: T) {
        inside = t
    }

    fun get(): T? = inside
}


