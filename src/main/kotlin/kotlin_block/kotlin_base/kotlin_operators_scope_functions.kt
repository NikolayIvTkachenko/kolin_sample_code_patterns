package kotlin_block.kotlin_base

import kotlin.random.Random


fun main() {

    //testMapFilter()
    //testRun01()
    //testRun02()
    //testRun03()
    //testApplay01()

    //testAlso()
    //testTakeIf()
    //testTakeIf02()
    //testTakeIf03()

    //testWith()
    //testWith02()
    //testRun04()
    //testRun05()
    testLet()

}

private fun testMapFilter(){
    val numbers = mutableListOf("one", "two", "three", "four", "five")
    val resultList = numbers.map { it.length }.filter { it > 3 }
    println(resultList)
}


private fun testRun01(){
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumberRegex.findAll("+123 -FFFF !%*& 88 XYZ")) {
        println(match.value)
    }

}

private fun testRun02(){
    val value03 :Int = 5
    value03.run {
        println(this)
    }
}

private fun testRun03(){

    val service = ServiceNetwork("http://rs-enginaring.com").run {
        port = "9090"
        query = "code = 5"
        println(this)
    }
    println(service.toString())
    //ServiceNetwork(name='http://rs-enginaring.com', port=9090, query=code = 5)
    //kotlin.Unit

    val service02 = ServiceNetwork("http://rs-enginaring.com")
    service02.run {
        port = "9090"
        query = "code = 5"
        println(this)
    }
    println(service02.toString())
    //ServiceNetwork(name='http://rs-enginaring.com', port=9090, query=code = 5)
    //ServiceNetwork(name='http://rs-enginaring.com', port=9090, query=code = 5)
}

private fun testRun04(){
    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = numbers.run {
        "The first element is ${first()}," +
                " the last element is ${last()}"
        //"hello"
    }
    println(firstAndLast)
}

private fun testRun05(){
    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = run {
        "hello"
    }
    println(firstAndLast)
}


private data class ServiceNetwork(val name: String) {
    var port: String? = null
    var query: String? = null

    override fun toString(): String {
        return "ServiceNetwork(name='$name', port=$port, query=$query)"
    }
}

private fun testApplay01(){
    val adam01 = PersonTest("Adam").apply {
        age = 32
        city = "London"
    }
    println(adam01)
    //PersonTest(name='Adam', age=32, city=London)

    val adam02 = PersonTest("Adam").apply {
        age = 32
        city = "London"
        println(this)
    }
    println(adam02)
    //PersonTest(name='Adam', age=32, city=London)
    //PersonTest(name='Adam', age=32, city=London)
}

private data class PersonTest(val name: String){
    var age: Int? = null
    var city: String? = null
    override fun toString(): String {
        return "PersonTest(name='$name', age=$age, city=$city)"
    }


}

private fun testAlso(){
    val numbers = mutableListOf("one", "two", "three")
    numbers
        .also { println("The list elements before adding new one: $it") }
        .add("four")
    println(numbers)
}

private fun testTakeIf(){
    //

    val number = Random.nextInt(100)
    println("number: $number")

    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    println("even: $evenOrNull, odd: $oddOrNull")

}

private fun testTakeIf02(){
    val str = "Hello"
    val caps = str.takeIf { it.isNotEmpty() }?.uppercase()
    //val caps = str.takeIf { it.isNotEmpty() }.uppercase() //compilation error
    println(caps)
}

private fun testTakeIf03(){
    val str = "Hello"
    //val caps = str.takeIf { it.isNotEmpty() }?.uppercase()
    val caps = str.takeUnless { it.isNotEmpty() }?.uppercase()
    println(caps)
}

private fun testWith() {
    val numbers = mutableListOf("one", "two", "three")
    with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
}

private fun testWith02(){
    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = with(numbers) {
        "The first element is ${first()}," +
                " the last element is ${last()}"
        //"hello"
    }
    println(firstAndLast)
}

private fun testLet(){
    val str: String? = "Hello"
    val length = str?.let {
        println("let() called on $it")
        it.length
    }
}