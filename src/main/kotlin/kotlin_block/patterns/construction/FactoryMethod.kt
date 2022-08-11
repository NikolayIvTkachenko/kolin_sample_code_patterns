package kotlin_block.patterns.construction

fun main() {

    val c = catFactory()
    println(c.name)

    println("")
    println("Test factory method")
    val animalTypes = listOf("dog", "dog", "cat", "dog", "cat", "cat")

    for(t in animalTypes) {
        val c = animalFactory(t)
        println("${c.id} - ${c.name}")
    }

    println("")
    println("Test factory method V2")

    val factory = AnimalFactory()
    for (t in animalTypes) {
        val c = factory.createAnimal(t)
        println("${c.id} - ${c.name}")
    }

    println("")
    println("Test factory method V3 -> Factory")
    val animalTypes03 = listOf(
        "dog" to "bulldog",
        "dog" to "beagle",
        "cat" to "persian",
        "dog" to "poodle",
        "cat" to "russian blue",
        "cat" to "siamese"
    )

    val factoryV03 = AnimalfactoryV03()
    for ((type, breed) in animalTypes03) {
        val c = factoryV03.createAnimal(type, breed)
        println(c.name)
    }

    println("")
    println("Static Factory Method in Kotlin")
    println(NumberMaster.valueOf("123"))


    println("")
    println("Companion object")

    val instance02 = TestClassV02.create()

}


private class TestClassV02 private constructor() {
    companion object {
        fun create() : TestClassV02 {
            return TestClassV02()
        }
    }
}

interface Animal {
    val id : Int
    val name : String
}

class Cat(override val id: Int) : Animal {
    override val name = "Cat"
}

fun catFactory() : Cat {
    return Cat(0)
}

class Dog(override val id: Int) :Animal {
    override val name = "Dog"
}



fun animalFactory(animalType: String) : Animal {
    return when(animalType.trim().toLowerCase()){
        "cat" -> Cat(0)
        "dog" -> Dog(0)
        else -> throw RuntimeException("Unknown animal $animalType")
    }
}

class AnimalFactory {
    var counter = 0

    fun createAnimal(animalType: String) : Animal {
        return when(animalType.trim().toLowerCase()){
            "cat" -> Cat(++counter)
            "dog" -> Dog(++counter)
            else -> throw RuntimeException("Unknown animal $animalType")
        }
    }
}

class AnimalfactoryV03 {
    var counter = 0

    private val dogFactory = DogFactory()
    private val catFactory = CatFactory()

    fun createAnimal(animalType: String, animalBreed: String) : Animal{
        return when(animalType.trim().toLowerCase()) {
            "cat" -> catFactory.createCat(animalBreed, ++counter)
            "dog" -> dogFactory.createDog(animalBreed, ++counter)
            else -> throw RuntimeException("Unknow animal $animalType")
        }
    }
}

class  Beagle(override val id: Int) : Animal {
    override val name = "Dog Beagle"
}

class  Bulltog(override val id: Int) : Animal {
    override val name = "Dog Bulltog"
}

class  Poodle(override val id: Int) : Animal {
    override val name = "Dog Poodle"
}

class  Persian(override val id: Int) : Animal {
    override val name = "Dog Persian"
}

class  RussianBlue(override val id: Int) : Animal {
    override val name = "Dog RussianBlue"
}

class  Siamese(override val id: Int) : Animal {
    override val name = "Dog Siamese"
}

class DogFactory {
    fun createDog(bread: String, id: Int) = when(bread.trim().toLowerCase()){
        "beagle" -> Beagle(id)
        "bulldog" -> Bulltog(id)
        "poodle" -> Poodle(id)
        else -> throw RuntimeException("Unknown ")
    }
}

class CatFactory {
    fun createCat(bread: String, id: Int) = when(bread.trim().toLowerCase()){
        "persian" -> Persian(id)
        "russian blue" -> RussianBlue(id)
        "siamese" -> Siamese(id)
        else -> throw RuntimeException("Unknown ")
    }
}


//Static Factory Method in Kotlin
class NumberMaster {
    companion object {
        fun valueOf(hopefullyNumber : String) : Long {
            return hopefullyNumber.toLong()
        }
    }
}