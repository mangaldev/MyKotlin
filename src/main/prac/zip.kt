fun main(args: Array<String>) {
    val numbers = listOf(1, 23, 34, 50)
    val words = listOf<String>("a", "b", "c", "d")

    val list = numbers.zip(words)
    println("list = ${list}")

    val associate = numbers.mapIndexed { index, n -> n to words[index] }
    println("list = ${associate}")
}