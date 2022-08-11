package kotlin_block.patterns.construction

fun main() {
    val pcFromWarehouse = PC("Pentium 8", "NTRIX 999", "8 GB")

    println(pcFromWarehouse)

    val powerPC = pcFromWarehouse.copy(graphicsCard = "NKCF 43234", ram ="32 Gb")

    println(powerPC)
}


data class PC(
    var processor : String,
    var graphicsCard : String = "",
    var ram : String
)