package processor

import java.lang.IllegalArgumentException
import java.util.*

class Matrix(val n: Int, val m: Int) {
    private val matrix = Array(n) { Array(m) { 0 } }

    companion object {
        fun read(scanner: Scanner): Matrix {
            val n = scanner.nextInt()
            val m = scanner.nextInt()
            val result = Matrix(n, m)
            for (i in 0 until n) {
                for (j in 0 until m) {
                    result.matrix[i][j] = scanner.nextInt()
                }
            }
            return result
        }
    }

    fun add(other: Matrix): Matrix {
        if (this.n != other.n || this.m != other.m) {
            throw IllegalArgumentException("Dimensions are not suitable ${this.n}x${this.m} + ${other.n}x${other.m}")
        }
        val result = Matrix(n, m)
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j]
        return result
    }

    override fun toString(): String {
        var result = ""
        for (i in 0 until n) {
            result += matrix[i].joinToString( " ", postfix = System.lineSeparator())
        }
        return result
    }
}