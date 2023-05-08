package kotlin_block.kotlin_base

fun main() {

    var value: Int? = null

    //value = 4

    var test = value?.let {
        println(it)
        it * 3
    } ?: 3

    println("test = $test")

    var test2 = value?.let {
        println(it)
        it * 3
    } ?: run {
        var t = 3 * 2
        t
    }

    println("test = $test2")

    var test3 = value?.let {
        println(it)
        it * 3
    } ?: run {
        return
    }

    println("test = $test3")
}