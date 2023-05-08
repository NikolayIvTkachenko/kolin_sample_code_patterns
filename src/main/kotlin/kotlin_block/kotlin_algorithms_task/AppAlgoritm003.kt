package kotlin_block.kotlin_algorithms_task

fun main() {

    //Sample INPUT: ["eat", "tea", "tan", "ate", "nat", "bat"]
    //Sample OUTPUT: [["ate", "eat", "tea"], ["nat", "tan"], ["bat"]]
    //Group by general characters in words


    println(groupWord(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
}

fun groupWord(words: Array<String>): List<List<String>> {
    val result: MutableList<List<String>> = mutableListOf()
    val map = mutableMapOf<String, MutableList<String>>()

    for(word in words) {
        //['a','b', 'c'].joinToString("-") -> 'a-b-c'
        val sortedWord = word.toCharArray().sorted().joinToString("")
        if(map.containsKey(sortedWord))
            map[sortedWord]?.add(word)
        else
            map[sortedWord] = mutableListOf(word)
    }

    for((key, value) in map) {
        result.add(value)
    }

    return result

}