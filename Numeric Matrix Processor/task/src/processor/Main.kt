package processor

import java.util.*

fun main() {
    printMenu()
    while (true) {
        when (readLine()!!) {
            "1" -> testSum()
            "2" -> testMulByNum()
            "3" -> testMulByMatrix()
            "0" -> break
        }
    }
}

fun printMenu() {
    println("1. Add matrices\n" +
            "2. Multiply matrix by a constant\n" +
            "3. Multiply matrices\n" +
            "0. Exit")
}

fun testMulByMatrix() {
    val a = Matrix.read()
    val b = Matrix.read()
    try {
        println(a.mul(b))
    } catch (e: IllegalArgumentException) {
        println("ERROR: ${e.message}")
    }
}

fun testMulByNum() {
    val a = Matrix.read()
    val c = readLine()!!.toInt()
    System.err.println(c)
    println(a.mul(c))
}

private fun testSum() {
    val a = Matrix.read()
    val b = Matrix.read()
    try {
        println(a.add(b))
    } catch (e: IllegalArgumentException) {
        println("ERROR: ${e.message}")
    }
}
