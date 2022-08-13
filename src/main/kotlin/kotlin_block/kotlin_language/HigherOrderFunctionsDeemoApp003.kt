package kotlin_block.kotlin_language

fun main() {

    val multiplyFunction = generateMultiply()
    println(multiplyFunction(2, 6))

    mathInvoker(5, 6, multiplyFunction)

    mathInvoker(7, 8){x, y -> x * y}

    val squareAnonymous = fun(x: Int) = x * x
    val squareLambda = {x: Int -> x * x}

    println(squareAnonymous(9))
    println(squareLambda(8))

    println(subtract(60,7))
}


fun generateMultiply(): (Int, Int) -> Int {
    return {x : Int, y: Int -> x * y}
}

fun mathInvoker(x: Int, y: Int, mathFunction: (Int, Int) -> Int) {
    println(mathFunction(x, y))
}

fun subtract(x: Int, y: Int): Int {
    return x - y
}
