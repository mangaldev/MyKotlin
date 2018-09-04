class Prob46 {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        _permute(emptyList(), nums.toList(), result)
        return result
    }

    fun _permute(leftList: List<Int>, rightList: List<Int>, result: MutableList<List<Int>>) {
        if (rightList.isEmpty()) {
            result.add(leftList.toMutableList())
        }
        rightList.forEachIndexed { i, _ ->
            _permute(leftList + rightList[i], rightList.filterIndexed { index, _ -> index != i }, result)
        }
    }

}

fun main(args: Array<String>) {
    println(Prob46().permute(arrayOf(1, 2, 3).toIntArray()))
}