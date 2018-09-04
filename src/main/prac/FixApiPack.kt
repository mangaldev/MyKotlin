import java.io.File
import java.lang.Long.max

fun main(args: Array<String>) {
    val file = File("/Users/mdev/Desktop/apipack_programs_updateid.txt")
    assert(file.exists())
    var lastTimestamp: Long = -1
    val reseedingList = mutableListOf<Long>()
    file.readLines().forEach { line ->
        val (programId, updateid, timeStamp) = line.split(",")
        if (timeStamp.toLong() < lastTimestamp) {
            println("programId = ${programId}")
            reseedingList.add(programId.toLong())
        }
        lastTimestamp = max(lastTimestamp,timeStamp.toLong())
    }
}