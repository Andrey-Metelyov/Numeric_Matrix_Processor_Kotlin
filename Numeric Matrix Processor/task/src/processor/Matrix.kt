package processor

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

    fun multiply(c: Double): Matrix {
        val result = this.copy()
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[i][j] = this.matrix[i][j] * c
        return result
    }

    fun multiply(other: Matrix): Matrix {
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

    fun transpose(): Matrix {
        if (this.n != this.m) {
            throw IllegalStateException("Dimensions are not suitable ${this.n}x${this.m}")
        }
        val result = this.copy()
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[i][j] = matrix[j][i]
        return result
    }

    fun transposeSide(): Matrix {
        if (this.n != this.m) {
            throw IllegalStateException("Dimensions are not suitable ${this.n}x${this.m}")
        }
        val result = this.copy()
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[n - i - 1][n - j - 1] = matrix[j][i]
        return result
    }

    fun transposeVLine(): Matrix {
        val result = this.copy()
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[i][j] = matrix[i][m - j - 1]
        return result
    }

    fun transposeHLine(): Matrix {
        val result = this.copy()
        for (i in 0 until n)
            for (j in 0 until m)
                result.matrix[i][j] = matrix[n - i - 1][j]
        return result
    }

    fun det(): Double {
        if (n == 1) {
            return matrix[0][0]
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
        }
        var result = 0.0
        for (i in 0 until m) {
            val minor: Matrix = getMinor(0, i)
            result += (if (i % 2 == 0) 1.0 else -1.0) * matrix[0][i] * minor.det()
        }
        return result
    }

    private fun getMinor(row: Int, column: Int): Matrix {
        val result = Matrix(n - 1, m - 1)
        var ii = 0
        var jj: Int
        for (i in 0 until n) {
            if (i == row) {
                continue
            }
            jj = 0
            for (j in 0 until m) {
                if (j == column) {
                    continue
                }
                result.matrix[ii][jj++] = matrix[i][j]
            }
            ii++
        }
        return result
    }

    fun inv(): Matrix {
        val cofactors = this.copy()
        val det = det()
        for (i in 0 until n) {
            for (j in 0 until m) {
                val minor = getMinor(i, j)
                cofactors.matrix[i][j] = (if ((i + j) % 2 == 0) 1.0 else -1.0) * minor.det()
            }
        }
        return cofactors.transpose().multiply(1.0 / det)
    }
}