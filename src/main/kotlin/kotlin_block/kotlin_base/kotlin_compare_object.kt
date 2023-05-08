package kotlin_block.kotlin_base

fun main() {

    val test0001 = Test0001("Test Text 00").updateValue()
    val test0002 = Test0001.getTextUpdate("garry")

    println(test0001)
    println(test0002)

}

class Test0001(val text: String) {

    companion object {
        val text01: String = "Text Text"

        fun getTextUpdate(updateTxt: String): String {
            return text01 + updateTxt;
        }
    }

    fun updateValue(): String {
        return text + text;
    }



}