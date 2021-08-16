package processor

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val a = Matrix.read(scanner)
    val b = Matrix.read(scanner)
    try {
        println(a.add(b))
    } catch (e: IllegalArgumentException) {
        println("ERROR")
    }
}
