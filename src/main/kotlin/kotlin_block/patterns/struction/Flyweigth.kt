package kotlin_block.patterns.struction

fun main() {


}



enum class Direction {
    LEFT,
    RIGHT
}
class TansaniaSnail(){
    val directionFacing = Direction.LEFT

    //val sprites = listOf(java.io.File("snail-left.jpg"), java.io.File("snail-right.jpg"))

    val sprites = List(8) {i ->
        java.io.File(when{
            i == 0 -> "snail-left.jpg"
            i == 1 -> "snail-right.jpg"
            i in 2..4 -> "snail-move-left-${i-1}.jpg"
            else -> "snail-move-right${(4-i)}.jpg"
        })
    }
}

fun TansaniaSnail.getCurrrentSprite() : java.io.File {
    return when (directionFacing) {
        Direction.LEFT -> sprites[0]
        Direction.RIGHT -> sprites[1]
    }
}


