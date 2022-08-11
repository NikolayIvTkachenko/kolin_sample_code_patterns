package kotlin_block.patterns.struction

import java.io.File


fun main() {



}


interface GameWorld

fun loadGame(file: File): GameWorld {
    return World001()
}

class World001(): GameWorld