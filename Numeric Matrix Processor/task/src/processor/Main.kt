package processor

fun main() {
    printMenu()
    while (true) {
        when (readLine()!!) {
            "1" -> testSum()
            "2" -> testMulByNum()
            "3" -> testMulByMatrix()
            "4" -> testTransposeMatrix()
            "5" -> testDeterminant()
            "6" -> testInverseMatrix()
            "0" -> break
        }
    }
}

fun testInverseMatrix() {
    val a = Matrix.read()
    println(a.inv())}

fun testDeterminant() {
    val a = Matrix.read()
    println(a.det())
}

fun testTransposeMatrix() {
    printTransposeMenu()
    when (readLine()!!) {
        "1" -> transposeMainDiagonal()
        "2" -> transposeSideDiagonal()
        "3" -> transposeVerticalLine()
        "4" -> transposeHorizontalLine()
        else -> println("error")
    }
}

fun transposeHorizontalLine() {
    val a = Matrix.read()
    println(a.transposeHLine())
}

fun transposeVerticalLine() {
    val a = Matrix.read()
    println(a.transposeVLine())
}

fun transposeSideDiagonal() {
    val a = Matrix.read()
    println(a.transposeSide())
}

fun transposeMainDiagonal() {
    val a = Matrix.read()
    println(a.transpose())
}

fun printTransposeMenu() {
    println("1. Main diagonal\n" +
            "2. Side diagonal\n" +
            "3. Vertical line\n" +
            "4. Horizontal line\n" +
            "Your choice:")
}

fun printMenu() {
    println("1. Add matrices\n" +
            "2. Multiply matrix by a constant\n" +
            "3. Multiply matrices\n" +
            "4. Transpose matrix\n" +
            "5. Calculate a determinant\n" +
            "6. Inverse matrix\n" +
            "0. Exit\n" +
            "Your choice:")
}

fun testMulByMatrix() {
    val a = Matrix.read()
    val b = Matrix.read()
    try {
        println(a.multiply(b))
    } catch (e: IllegalArgumentException) {
        println("ERROR: ${e.message}")
    }
}

fun testMulByNum() {
    val a = Matrix.read()
    val c = readLine()!!.toDouble()
    System.err.println(c)
    println(a.multiply(c))
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
