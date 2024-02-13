package chapter03

/**
 * 확장 함수
 * - 어떤 클래스의 멤버 메서드인 것 처럼 호출할 수 있다.
 * - 하지만 그 클래스의 밖에 선언되어 있다.
 * - this는 생략 가능
 * - 인스턴스 내부의 메서드나 프로퍼티를 바로 사용할 수 있다.
 * - 그러나 확장 함수에서는 private, protected 필드를 사용할 수는 없다.
 * - 내부적으로는, 수신 객체를 첫 번째 인자로 받는 정적 메서드다.
 * - 정적 메서드와 같은 특징을 가진다.
 * - 런타임 시점에 호출될 함수가 결정되는 override와는 다르다. 컴파일 시점에 결정된다.
 * - 확장 함수와 기존 클래스 함수의 이름과 시그니처가 동일하다면, 기존 클래스 함수가 호출된다.
 */
fun String.lastChar(): Char = this.get(this.length - 1)
// 함수의 이름 앞에, 그 함수가 확장할 클래스의 이름을 덧붙인다.
// 클래스의 이름을 수신 객체 타입이라 한다. - String
// 확장함수가 호출되는 대상 값을 수신 객체라고 한다. - this (해당 클래스의 인스턴스)

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join( // 문자열 컬렉션에 대해서만 호출 가능한 함수 작성
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)

/**
 * 확장 프로퍼티
 *
 * - 기존 클래스의 인스턴스 객체에 필드를 추가할 방법은 없다.
 * - 일반적인 프로퍼티에서 수신 객체 클래스가 추가된 형태.
 * - 뒷받침하는 필드가 없어서 기본 게터, 세터를 제공할 수 없음. 따라서 최소한의 게터(+세터)는 정의해둬야 함.
 */
var StringBuilder.lastChar: Char
    get() = get(length - 1) // 프로퍼티 게터
    set(value: Char) { // 프로퍼티 세터
        this.setCharAt(length - 1, value)
    }

fun main() {
    println("Kotlin".lastChar())
    // 수신 객체 타입 - String
    // 수신 객체 - "Kotlin"

    val list = arrayListOf(1, 2, 3)
    println(
        list.joinToString(
            separator = "; ",
            prefix = "(",
            postfix = ")"
        )
    )

    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'

    println(
        "12.345-6.A".split(
            "[.\\-]".toRegex() // split은 Regex 타입의 값을 받는다.
        )
    )

    println("12.345-6.A".split(".", "-"))

    val cat = """
        .\    /\
        . )  ( ')
        .(  /  )
        . \(__)|
    """
    println(cat.trimMargin(".")) // trimMargin - 공백 제거
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
}

fun parsePathWithRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex() // """ 안에서는 \ 문자에 대한 처리를 해 줄 필요 없다.

    val matchResult = regex.matchEntire(path)
    if (matchResult != null) { // match에 성공
        val (directory, filename, extension) = matchResult.destructured
    }
}
