class Prob3 {

    fun lengthOfLongestSubstring(sentence: String): Int {
        var max: Int = 0
        var currentLength: Int = 0
        var processedLast = -1
        val map = mutableMapOf<Char, Int>()
        sentence.forEachIndexed { index, letter ->
            if (map.containsKey(letter)) {
                val lastSeen = map[letter]!!
                if (lastSeen > processedLast) {
                    currentLength = index - lastSeen
                    processedLast = lastSeen
                } else {
                    currentLength++
                }
            }
            else currentLength++
            map[letter] = index
            max = Math.max(currentLength,max)
        }
        return max
    }
}

fun main(vararg args: String) {
    println(Prob3().lengthOfLongestSubstring("pwwkew"))
}