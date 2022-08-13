package kotlin_block.kotlin_language

import java.util.*
import java.util.stream.Collectors.toList
import kotlin.system.measureTimeMillis

fun main() {

    //1
    Arrays.asList("a", "b", "c") //Initialize list
        .stream() //Convert to stream
        //.map(it -> {}) //Do something functional here
        .map { x -> println(x) }
        .toList() //Convert back to list


    val noParameters = { 1 }

    val oneParameterVeryVeryExplicit = oneParameter({x: Int -> x.toLong()})
    val oneParameterVeryExplicit = oneParameter{x: Int -> x.toLong()}
    val oneParameterExplicit = oneParameter{it.toLong()}

    val letters = listOf("a", "b", "c", "d")
    println(repeatAll(letters))


    //2
    println(repeatSomething(letters){
        it.toUpperCase()
    })

    //3
    val lettersV2 = listOf("a", "B", "c", "D")
    val resultsV2 = mutableListOf<String>()

    resultsV2.addAll(lettersV2.map {
        it.toUpperCase()
    })

    resultsV2.addAll(lettersV2.map {
        it.toLowerCase()
    })

    println(resultsV2)

    //4
    val people = listOf(
        Person("Nick", "Doe", 34),
        Person("Maria", "Smith", 21),
        Person("Michael", "Smith", 23)
    )

    println(people.find {
        it.firstName == "Maria"
    })

    println(people.findLast {
        it.lastName == "Smith"
    })

    //5
    var numbers = (1..5).toList()
    println(numbers.drop(3))

    numbers = (1..5).toList()
    println(numbers.dropLast(3))

    val readings = listOf(-7, -2, -1, -1, 0, 1, 3, 4)
    println(readings.dropWhile {
        it <= 0
    })

    //6
    println(people.sortedBy {
        it.age
    })
    println(people.sortedByDescending {
        it.lastName
    })


    println(people.sortedWith(compareBy({ it.lastName }, {it.age})))


    val numbers03 = (0..10)
    numbers03.map { it * it }
        .filter { it < 20 }
        .sortedDescending()
        .forEach { println(it) }

    numbers03.map { it * it }
        .forEachIndexed { index, value -> print("$index:$value, ") }

    numbers03.map { it * it }
        .filter { it < 20 }
        .sortedDescending()
        .onEach { println(it) }
        .filter { it > 5 }

    println(numbers03.joinToString { "$it" })
    println(numbers03.joinToString(separator = "-") { "$it" })

    println(people.reduce{p1, p2 ->
        Person("Combined", "Age", p1.age + p2.age)
    })
    println(people.reduce{p1, p2 ->
        if (p1.age > p2.age) { p1 } else { p2 }
    })


    val listOfLists = listOf(listOf(1,2), listOf(3,4,5), listOf(6,7,8))
    println(listOfLists)

    val resultList = mutableListOf<Int>()
    for(l in listOfLists) {
        resultList.addAll(l)
    }

    println(listOfLists.flatten())

    println(listOfLists.flatMap {
        it.asReversed()
    })

    println(listOfLists.flatMap {
        it.map { it.toDouble() }
    })

    //7
    println("7 - flatten =============")
    val setOfListsOfSets = setOf(
        listOf(setOf(1, 2), setOf(3, 4, 5), setOf(6, 7, 8)),
        listOf(setOf(9, 10), setOf(11, 12))
    )
    println(setOfListsOfSets)
    println(setOfListsOfSets.flatten())
    println(setOfListsOfSets.flatten().flatten())
    //println(setOfListsOfSets.flatten().flatten().flatten()) //will not compile

    println("7 - slice =============")
    val numbersList03 = (1..10).toList()
    println(numbersList03)
    println(numbersList03.slice(0..4))
    println(numbersList03.subList(0, 3))

    //8
    println("8 -ZIP/UNZIP =============")
    val employeeIds = listOf(6, 9, 14, 34, 45, 67, 89)
    val daysInCompany = listOf(145, 120, 102, 67, 45, 52, 35, 22, 12, 5)
    println(employeeIds.zip(daysInCompany))
    val employeesToDays = employeeIds.zip(daysInCompany)
    println("employeesToDays = " + employeesToDays)
    val(employees, days) = employeesToDays.unzip()
    println("employees = " + employees)
    println("days = " + days)

    //9
    println("9 -ZIP/UNZIP =============")
    //Streams are lazy, collections are not
    println("Streams are lazy, collections are not")
    (1..10).toList().asReversed()
    (1..10).toList().asSequence()

    val numbersV5 = (1..1_000_000).toList()
    println(measureTimeMillis {
        numbersV5.stream().map {
            it * it
        }
    }) //~1

    println(measureTimeMillis {
        numbersV5.map {
            it * it
        }
    })//~68

    println(measureTimeMillis {
        numbersV5.stream().map {
            it * it
        }.toList()
    })// ~27


    //10
    println("10 ==========")
    val seq = generateSequence(1) {it + 1}
    seq.take(100).forEach {
        println(it)
    }

    val finiteSequence = generateSequence(1) {
        if (it < 1000) it + 1 else null
    }

    finiteSequence.forEach {
        println(it)
    }

    (1..10000).asSequence()



}

fun oneParameter(block: (Int) -> Long) {}


fun repeatAll(letters: List<String>): MutableList<String> {
    val repeatedLetters = mutableListOf<String>()

    for(l in letters) {
        repeatedLetters.add(l + l)
    }
    return repeatedLetters
}


fun <T> repeatSomething(input: List<T>, action: (T) -> T): MutableList<T> {
    val result = mutableListOf<T>()

    for(i in input) {
        result.add(action(i))
    }
    return result
}

data class Person(val firstName: String, val lastName: String, val age: Int)

fun <T> List<T>.find(check: (T) -> Boolean): T? {
    for (p in this){
        if(check(p)) {
            return p
        }
    }
    return null
}