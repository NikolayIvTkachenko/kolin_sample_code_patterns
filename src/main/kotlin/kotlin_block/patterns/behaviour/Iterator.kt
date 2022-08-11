package kotlin_block.patterns.behaviour

fun main() {

    //val rangers = Squad("Josh", "Ewan", "Tom")
    //val deltaForce = Squad("Sam","Eric", "William")
    //val blackHawk = Platoon(rangers, deltaForce)

    //for(u in blackHawk){
    //  println(u)
    //}


//    operator fun iterator() = object : Iterator<InfantryUnit>{
//        var i = 0
//
//    }

//    override fun hasNext(): Boolean {
//        return i < infantryUnits.size + 1
//    }


//    override fun next() =
//        when(i){
//            0 -> commander
//            else -> infantryUnits[i - 1]
//        }.alse {i++}


}

interface InfantryUnit

class Sergant: InfantryUnit

class Squad(val infrantUnits: MutableList<InfantryUnit> = mutableListOf()){
    val commander = Sergant()
}

class Lieutenant: InfantryUnit

class Platoon(val squads: MutableList<Squad> = mutableListOf()){
    val commander = Lieutenant()
}
