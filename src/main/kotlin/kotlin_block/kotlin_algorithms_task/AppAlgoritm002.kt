package kotlin_block.kotlin_algorithms_task

fun main() {
    //AAAABBBCCXYZDDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBB -> A4B3C2XYD5E3A6B22

    println(countLetter("AAAABBBCCXYZDDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBB"))
}

fun countLetter(str: String): String {
    var currentLetter = str[0]
    var count = 1
    var result = ""

    for(letter in str.substring(1)) {
        if (currentLetter == letter) {
            count++
        } else {
            if (count == 1)
                result += currentLetter
            else
                result += "$currentLetter$count"
            count = 1
            currentLetter = letter
        }
    }

    //last symbol
    if (count == 1)
        result += currentLetter
    else
        result += "$currentLetter$count"

    return result
}
