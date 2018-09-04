fun main(vararg args: String) {
    var i:Int? = 6
    var j:Int? = null
    var k:Int? =(i?: 0) + (j?:0)
    println("result = ${k}")
}






