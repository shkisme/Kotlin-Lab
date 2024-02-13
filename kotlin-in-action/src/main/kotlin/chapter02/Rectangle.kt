package chapter02

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean // 커스텀 접근자
        get() {
            return height == width
        }
}
