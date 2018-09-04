fun main(args: Array<String>) {

}

fun merge(l1: ListNode, l2: ListNode): ListNode {
    var result: ListNode? = null
    val first: ListNode?
    var ptr1: ListNode? = l1
    var ptr2: ListNode? = l2
    first = if (ptr1!!.`val` <= ptr2!!.`val`)
        ptr1
    else ptr2
    while (ptr1 != null && ptr2 != null) {
        if (ptr1.`val` <= ptr2.`val`) {
            result?.let { it.next = ptr1 }
            result = result ?: ptr1
            ptr1 = ptr1.next
        } else {
            result?.let { it.next = ptr2 }
            result = result ?: ptr2
            ptr2 = ptr2.next
        }
    }
    if (ptr1 == null) result?.next = ptr2
    if (ptr2 == null) result?.next = ptr1
    return first

}

inline fun <T> T.isNullOrElse(first: () -> Unit, second: () -> Unit): T {
    if (this == null) first() else second()
    return this as T
}

class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        return when (l1) {
            null -> {
                when (l2) {
                    null -> null
                    else -> l2
                }
            }
            else -> {
                when (l2) {
                    null -> l1
                    else -> merge(l1, l2)
                }
            }
        }
    }
}

class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}