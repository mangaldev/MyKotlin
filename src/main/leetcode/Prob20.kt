import java.util.*

class Prob20 {
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        val correctPairs = mapOf('(' to ')','[' to ']', '{' to '}')
        s.forEach { c: Char ->
            if(c == '(' || c == '[' || c == '{') stack.push(c)
            else if(stack.isEmpty()) return false
            else if(correctPairs[stack.pop()] != c) return false
        }
        return stack.isEmpty()
    }
}

fun main(args: Array<String>) {
    println(Prob20().isValid("()[]{}"))
}