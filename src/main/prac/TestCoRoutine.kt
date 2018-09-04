
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
//    runBlocking {
    //(1)
//        println("Starting blocking...")
//        delay(1000, TimeUnit.MILLISECONDS)
//        println("starting job after 1 sec")
//    runBlocking {
//    val result = async {
//        suspendingFunction()
//    }
    runBlocking {
        print("The result:")
        delay(1000L)
    }
//    }
    println("done")
}

private suspend fun suspendingFunction(): String {
    delay(2000, TimeUnit.MILLISECONDS)
    return "completed after 2 secs"
}
