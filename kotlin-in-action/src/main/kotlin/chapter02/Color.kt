package chapter02

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    ; // Enum에서 ; 는 필수

    fun rgb() = (r * 256 + g) * 256 + b
}
