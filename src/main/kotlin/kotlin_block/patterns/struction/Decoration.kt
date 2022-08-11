package kotlin_block.patterns.struction


fun main() {

    val happy = HappyMap<String, String>()

    happy["one"] = "one"
    happy["two"] = "two"
    happy["two"] = "three"

    val a = listOf("a")
    val b = listOf("b")
    println(a + b)

    val j1 = Json("""{"a": "b"}""")
    val j2 = Json("""{"c": "d"}""")

    println(j1 + j2)

    println("=== HappyMap V2 ===")

    //val happyMapV2 = SadMap(HappyMapV2<String,String>())

    println("=== SadMap ===")
    val sadHappy = SadMapV2(HappyMapV2<String, String>())
    sadHappy["one"] = "one"
    sadHappy["two"] = "two"
    sadHappy["two"] = "three"

    sadHappy["a"] = "b"
    sadHappy.remove("a")


}


data class Json(val j: String)

operator fun Json.plus(j2: Json): Json{
    val jsonFields = this.j.split(":") + j2.j.split(":")
    val s = (jsonFields).joinToString(":")
    return Json("""{$s}""")
}


class HappyMap<K, V> : HashMap<K, V>() {
    override fun put(key: K, value: V): V? {
        return super.put(key, value).apply {
            this?.let {
                println("Yay! $key")
            }
        }
    }
}

class SadMap<K, V>: HashMap<K, V>() {
    override fun remove(key: K): V? {
        println("Okay...")
        return super.remove(key)
    }
}

class HappyMapV2<K, V>(private val map: MutableMap<K, V> = mutableMapOf()) : MutableMap<K, V> by map {
    override fun put(key: K, value: V): V? {
        return map.put(key, value).apply {
            this?.let {
                println("Yay! $key")
            }
        }
    }
}

class SadMapV2<K, V>(private val map: MutableMap<K, V> = mutableMapOf()) : MutableMap<K, V> by map {
    override fun remove(key: K): V? {
        println("Okay...")
        return map.remove(key)
    }
}


