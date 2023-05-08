package kotlin_block.kotlin_base

fun main() {
    //    println(testSimpleFun(3, 5 ))
    //    testDefaultArguments(1)
    //    printEven(2, 3, 4, 5, 6, 7, 8)
    //    printEven(*intArrayOf(1, 2, 3, 4, 5), 6, 7, 8)


    //testWhen(1)
    //testWhen(13)
    //testWhen("hello")

    //testListFunctions()

    testClasses01()

}

fun testClasses01(){
    val child = PersonTest02("Alex", "Tomanson", 8)
    val p1 = PersonTest02("Tom", "Jones", 35, child)

    println(p1.children)

    val rectangle01 = Rectangle(5.0, 7.0)
    println("The perimeter is ${rectangle01.perimeter}")

    val rectangle02 = Rectangle(5.0, 7.0)
    println("The perimeter is ${rectangle02.perimeter}")
    println(rectangle01 == rectangle02) //if data class => true

    println(rectangle01 === rectangle02) //if data class => true
}


private data class PersonTest02(val firstName: String, val lastname: String, var age: Int) {
    var children: MutableList<PersonTest02> = mutableListOf() //ArrayList

    init {
        println("Person is created $firstName")
    }

    constructor(firstName: String, lastname: String, age: Int, child: PersonTest02): this(firstName, lastname, age) {
        children.add(child)
    }

    constructor(): this("", "", -1)
}

data class Rectangle(var height: Double, var length: Double) {
    var perimeter = (height + length) * 2

    var test = 1
        get() = field + 1
        set(value) {
            if (value < 0) println("Negative value")
        }

    fun area() = height * length

}



fun testListFunctions() {

    val aa = listOf(6, 1, 2, 3, 4, 5)

    aa.forEach{ e -> println(e) }
    println(aa.map { e -> e * 2 })
    println(aa.filter { e -> e % 2 == 0 })
    println(aa.reduce{ sum, e -> sum + e }) //1 + 2  = 3, 3 + 3 = 6, 6 + 4 = 10, 10 + 5 = 15
    println(aa.sortedByDescending { it })
    println(aa.sorted() )

}

fun testWhen(input: Any) {
    when(input) {
        1 -> println("One")
        2 -> println("Two")
        in 10..20 -> println("from 10 till 20")
        is String -> println("Incorrect data. Length ${input.length}")
    }
}



fun testSimpleFun(x: Int, y: Int) = x + y

fun testNamedArguments(x: Int, y: Int, z: Int): List<Int> {
    return listOf(x, y, z)
}

fun testDefaultArguments(x: Int = 4, y: Int = 2) {
    println(x + y)
}

fun printEven(vararg numbers: Int){
    numbers.forEach { t -> if (t % 2 == 0) println(t) }
}

