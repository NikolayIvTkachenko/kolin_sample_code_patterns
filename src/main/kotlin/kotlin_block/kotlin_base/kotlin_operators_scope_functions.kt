package kotlin_block.kotlin_base



fun main() {

    //testMapFilter()
    //testRun01()
    //testRun02()
    testRun03()
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

private data class ServiceNetwork(val name: String) {
    var port: String? = null
    var query: String? = null

    override fun toString(): String {
        return "ServiceNetwork(name='$name', port=$port, query=$query)"
    }


}