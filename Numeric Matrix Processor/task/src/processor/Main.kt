package processor

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
//    testSum(scanner)
    testMulByNum(scanner)
}

fun testMulByNum(scanner: Scanner) {
    val a = Matrix.read(scanner)
    val c = scanner.nextInt()
    System.err.println(c)
    println(a.mul(c))
}

private fun testSum(scanner: Scanner) {
    val a = Matrix.read(scanner)
    val b = Matrix.read(scanner)
    try {
        println(a.add(b))
    } catch (e: IllegalArgumentException) {
        println("ERROR")
    }
}
