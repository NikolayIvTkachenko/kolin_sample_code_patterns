package kotlin_block.kotlin_language

fun main() {

    val numbers = List(1_000_000) {it}
    //println(sumRec(0, numbers)) //crashed

    println(sumRecV2(0, 0, numbers))
}


fun sumRec(i: Int, numbers: List<Int>): Long {
    return if(i == numbers.size) {
        0
    }else{
        numbers[i] + sumRec(i + 1, numbers)
    }
}

tailrec fun sumRecV2(i: Int, sum: Long, numbers: List<Int>): Long {
    return if(i == numbers.size) {
        return sum
    }else{
       sumRecV2(i+1, numbers[i] + sum, numbers)
    }
}