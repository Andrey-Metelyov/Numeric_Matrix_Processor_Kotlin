package processor

import java.lang.IllegalArgumentException
import java.util.*

data class Matrix(val n: Int, val m: Int) {
    private val matrix = Array(n) { Array(m) { 0.0 } }

    companion object {
        fun read(): Matrix {
            val scanner = Scanner(System.`in`)
            val n = scanner.nextInt()
            val m = scanner.nextInt()
            val result = Matrix(n, m)
            for (i in 0 until n) {
                for (j in 0 until m) {
                    result.matrix[i][j] = scanner.nextDouble()
                }
            }
            System.err.println(result)
            scanner.close()
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

    fun mul(c: Int): Matrix {
        val result = this.copy()
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[i][j] = this.matrix[i][j] * c
        return result
    }

    fun mul(other: Matrix): Matrix {
        if (this.n != other.m && this.m != other.n) {
            throw IllegalArgumentException("Dimensions are not suitable ${this.n}x${this.m} + ${other.n}x${other.m}")
        }
        val result = Matrix(this.n, other.m)
        for (i in 0 until n) {
            for (j in 0 until other.m) {
                var sum = 0.0
                for (k in 0 until m) {
                    sum += this.matrix[i][k] * other.matrix[k][j]
//                    System.err.println("[$i, $k](${this.matrix[i][k]}) * [$k, $j](${other.matrix[k][j]}) = $sum")
                }
                result.matrix[i][j] = sum
//                System.err.println("[$i, $j] = $sum")
            }
        }
        return result
    }
}