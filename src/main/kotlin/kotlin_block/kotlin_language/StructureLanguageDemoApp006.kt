package kotlin_block.kotlin_language

import java.io.BufferedReader
import java.io.FileReader
import java.util.Random
import kotlin.reflect.KClass

fun main() {


    //Let
    println("==LET ===")
//    val sometimesNull = if (Random().nextBoolean()) "not null" else null
    val sometimesNull = "dfg"
    sometimesNull?.let {
        println("It was $it this time")
    }


    //Apply
    println("==APPLY ===")
    val agent = Agent(name = "Bond").apply {
        this.movie = "moon race"
        this.alsoStarring = " Dr.Evil"
    }
    println(agent)

    //Also
    println("==ALSO ===")
    val l = (1..100).toList()

    l.filter { it % 2 == 0 }
        .also {  println(it) }
        .map { it * it }
        .forEach { println(it) }

    //Run
    println("==RUN ===")
    val justAsString = "string string"
    val n = justAsString.run {
        length
    }
    println(justAsString)
    println(n)

    val agentV2 = Agent("Nick").run {
        movie = "Termitaor 10"
        alsoStarring = "Jams WEB"
    }
    println(agentV2.toString())

    //With
    println("==WITH ===")
    with("scope") {
        println(length)
    }

    //Try-With-Resources
    println("==TRY WITH RESOURCES ===")
    //Kotlin
//    val br = BufferedReader(FileReader(""))
//    br.use{
//        println(it.readLine())
//    }

    //Inline functions
    println("==INLINE FUNCTIONS ===")
    //Each time the compiler sees a call to a function marked with inline, it will replace
    //the call with the "concrete" function body
    makeSense {
        "Inlining"
    }

    doesntMakeSense("TestTest")

    //REIFIED
    println("==REIFIED ===")
    printIfSameType(Int::class, 1)
    printIfSameType(Int::class, 2L)

    //===
    printIfSameTypeReified<Int>(3)
    printIfSameTypeReified<Int>(4L)
    printIfSameTypeReified<Int>(5)
    printIfSameTypeReified<Int>(6L)


    //VALIDATION
    println("==VALIDATION ===")
    //val person = Profile(null, "nick@mail.ru")
    val person = Profile("Nick", "nick@mail.ru")
    println(printNameLength(person))

}

//VALIDATION
data class Profile(var name: String?, var email: String)

fun setCapasity(cap: Int){
    require(cap > 0)
}

fun printNameLength(p: Profile): String {
    require(p.name != null)
    return p.name?.length.toString()
}



//REIFIED
//fun <T> printIfSameType(a: Number) {
//    if (a is T) { //<== ERROR, not compile
//        println(a)
//    }
//}
fun <T: Number> printIfSameType(clazz: KClass<T>, a: Number) {
    if (clazz.isInstance(a)) {
        println(a)
    }
}

inline fun <reified T> printIfSameTypeReified(a: Number) {
    if (a is T) {
        println(a)
    }
}

//Not compile
//fun printList(list: List<Int>) {
//    println("This is a lit of Ints")
//    println(list)
//}
//
//fun printList(list: List<Long>){
//    println("This is a lit of Long")
//    println(list)
//}

//Compile
const val int = 1
const val long = 1L
inline fun <reified T : Any> printList(list: List<T>) {
    when{
        int is T -> println("This is a lit of Ints")
        long is T -> println("This is a lit of Long")
        else -> println("This is a list of something else")
    }
}


//INLINE
inline fun doesntMakeSense(something: String) {
    println(something)
}

inline fun makeSense(block: () -> String) {
    println("Before")
    println(block())
    println("After")
}


//APPLY
data class Agent(var name: String) {

    lateinit var movie: String
    lateinit var alsoStarring: String

    override fun toString(): String {
        return "Agent(name='$name', movie='$movie', alsoStarring='$alsoStarring')"
    }
}

//ALSO
fun multiply(a: Int, b: Int): Int = a * b

fun multiplyV2(a: Int, b: Int): Int {
    val c = a * b
    println(c)
    return c
}

fun multiplyV3(a: Int, b: Int): Int = (a * b).also { println(it) }

