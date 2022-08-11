package kotlin_block.patterns.struction

import java.util.stream.Collectors
import java.util.stream.Stream

fun main() {

    //combining all those adapters together
    cellPhone(charger(powerOutlet().toEUPlug()).toUsbTypeC())

    val l = listOf("a", "b", "c")
    streamProcessing(l.stream())

}

fun <T> streamProcessing(stream: Stream<T>) {
    // Do something with stream
    stream.map { x -> println(x) }.collect(Collectors.toList())
}

interface UsbTypeC
interface UsbMini

interface EUPlug
interface USPlug

fun powerOutlet() : USPlug {
    return object : USPlug {}
}

fun cellPhone(chargeCable: UsbTypeC){}

fun charger(plug: EUPlug): UsbMini {
    return object : UsbMini {}
}

fun USPlug.toEUPlug() : EUPlug {
    return object : EUPlug {
        //TODO
    }
}

fun UsbMini.toUsbTypeC() : UsbTypeC {
    return object : UsbTypeC {
        //TODO
    }
}