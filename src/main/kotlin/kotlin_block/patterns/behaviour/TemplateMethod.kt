package kotlin_block.patterns.behaviour

fun main() {

    val schedule = MondaySchedule()
    schedule.runSchedule()

}

abstract class DayRoutine {

    fun runSchedule() {
        arriveToWork()
        drinkCoffee()
        doBeforeLunch()
        goToLunch()
        doAfterLunch()
        goHome()

    }

    abstract fun arriveToWork()

    abstract fun drinkCoffee()

    abstract fun doBeforeLunch()

    abstract fun goToLunch()

    abstract fun doAfterLunch()

    abstract fun goHome()

}

class MondaySchedule : DayRoutine(){
    override fun arriveToWork() {
        println("arriveToWork")
    }

    override fun drinkCoffee() {
        println("drinkCoffee")
    }

    override fun doBeforeLunch() {
        println("doBeforeLunch")
    }

    override fun goToLunch() {
        println("goToLunch")
    }

    override fun doAfterLunch() {
        println("doAfterLunch")
    }

    override fun goHome() {
        println("goHome")
    }

}