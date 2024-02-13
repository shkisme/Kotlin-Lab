package chapter03

fun collection() {
    val set = hashSetOf(1, 7, 33)
    val list = arrayListOf(1, 7, 13)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    // 코틀린은 자바 컬렉션을 활용한다.
    println(list.last())
    println(set.max())

    println(list) // 디폴트로 제공되는 toString

    println(joinToString(list, separator = "; ", prefix = "(", postfix = ")"))
    println(joinToString(list, prefix = "(", postfix = ")")) // 아니 ㅋㅋ
}

var opCount = 0 // 프로퍼티도 파일의 최상위 수준에 놓을 수 있다.
const val UNIX_LINE_SEPARATOR = '\n'
// const 변경자로 public static final 필드로 컴파일 가능함.

fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("opCount = $opCount")
}

fun View.showOff() = println("Im a View!")

fun Button.showOff() = println("Im a Button!")

fun main() {
    val view: View = Button() // 실제 타입은 Button이지만 이 경우 view의 타입이 View이기 때문에, View의 확장 함수가 호출된다.
    view.showOff() // 컴파일 시점에 결정됨.
    // * 확장함수는 첫 번째 인자가 파라미터인 정적 메서드로 컴파일된다. *
    // 따라서 확장 함수를 오버라이드할 수 없다. (런타임 시점에 결정되는 것이 아니기 때문.)
}
