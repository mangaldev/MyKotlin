import kotlinx.coroutines.experimental.async
import java.lang.Thread.currentThread
import java.lang.Thread.sleep

fun main(args: Array<String>) {
    println(currentThread())
    async {
        println("asynch started")
        work()
        println("asynch finished")

    }
    sleep(2000)
}

fun work() {
    println(currentThread())
    sleep(1000)
    println("work completed.")
}