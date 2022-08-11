package kotlin_block.kotlin_language

fun main() {
    println("test 00 1")
    val sc01 = SampleClass(6)

    val s1 = "ABC"
    val s2 = String(s1.toCharArray())

    println(s1 == s2) // == equals
    println(s1 === s2) // === reference

    //val s : String = null //Won't compile
    val s : String? = null //Will be compiled

    println(getGreeting())
    println(getInixSocketPolling(true))
    println(getInixSocketPollingV2(true))

    println("I would suggest: ${suggestGift(11)}")

    val gift = suggestGift(100)
    println("I would suggest: $gift")

    //Classses
    val p = Player()

    //Inheritance
    val pd = DungeonMaster()
    pd.sayHello()
    pd.startGame()


    val pdV02 = DungeonMasterV2("Master")
    pdV02.sayHello()
    pdV02.startGame()


    //Loops
    val word = "Word"
    for (i in 0..(word.length-1)){
        println(word[i])
    }

    for(i in 0 until word.length){
        println(word[i])
    }

    for (i in (word.length-1)..0){
        println(word[i])
    }

    for(i in (word.length-1) downTo 0) {
        println(word[i])
    }

    for (c in word) {
        println(c)
    }

    println("")
    var x = 0
    while(x < 10) {
        x++
        print("$x ")
    }

    println("")
    var xx = 5
    do {
        print("$xx ")
        xx--
    } while (xx > 0)

    println("")
    println("Extension functions")

    val str004 : String = "Hello"
    println(str004)
    val str005 = str004.reversed()
    println(str005)


    println()
    println("=== Assign a function to your variable ===")
    val square = fun (x: Int): Long {
        return (x * x).toLong()
    }

    println(square(3))



}


public inline fun String.reversed(): String {
    return (this as CharSequence).reversed().toString()
}


fun getGreeting(): String {
    return "Hello everybody!!!"
}

fun getInixSocketPolling(isBsd : Boolean) : String {
    val value = if (isBsd) {
        "socket001"
    } else {
        "socket002"
    }
    return value
}

fun getInixSocketPollingV2(isBsd : Boolean) = if (isBsd) "socket001" else "socket002"

fun suggestGift(amount : Int) : String {
    return when (amount) {
        in (0..10) -> "a book";
        in (10..100) -> "a guitar"
        else -> if (amount < 0) "no gift" else "anything!"
    }
}

//Classes
class SampleClass(val field: Int)

class Player {
    var name : String = ""

        set(value) {
            field = value.toUpperCase()
        }
}


//Inheritance
abstract class AbstractDungeonMaster {
    abstract val gameName: String

    fun startGame() {
        println("Game $gameName has started!")
    }
}

interface Dragon

interface Greeter {
    fun sayHello() {
        println("Hello")
    }
}

open class DungeonMaster: Greeter, AbstractDungeonMaster() {
    override val gameName: String
        get() = "Dungeon of the Beholder"
}



abstract class AbstractDungeonMasterV02(private val gameName : String) {
    open fun startGame() {
        println("Game $gameName has started!")
    }
}

open class DungeonMasterV02(gameName: String): Greeter, AbstractDungeonMasterV02(gameName)


open class DungeonMasterV2(gameName: String): Greeter, AbstractDungeonMasterV02(gameName)

class EvilDungeonMaster(private val awfulGame: String) : DungeonMasterV02(awfulGame) {
    override fun startGame() {
        println("$awfulGame will be your last!")
    }

    override fun sayHello() {
        println("Hello everybody evils")
    }

}

data class User (val username : String, val password : String)

