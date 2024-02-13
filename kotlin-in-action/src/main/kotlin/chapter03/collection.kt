package chapter03

fun main(args: Array<String>) {
    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())
    println(strings.max()) // last, max는 모두 확장 함수였던 것이다.

    val list = listOf("args: ", *args) // * - 스프레드 연산자가 배열의 내용을 펼쳐준다.

    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    // 여기서 to는 코틀린 키워드가 아니다.
    // 중위 호출이라는 특별한 방식으로 to라는 일반 메서드를 호출한 것이다.

    1.to("one") // 일반적인 방식으로 to 메서드를 호출.
    1 to "one" // to 메서드를 중위 호출 방식으로 호출.

    val (number, name) = 1 to "one" // 두 변수를 즉시 초기화 할 수 있음. - 구조 분해
}

/**
 * to 함수
 * - 함수를 중위 호출에 사용하고 싶다면, infix 변경자를 함수 선언 앞에 추가하라.
 * - 확장 함수다.
 * - 타입과 관계 없이 임의의 순서쌍을 만들 수 있다. (to의 수신 객체가 제네릭하다.)
 */
infix fun Any.to(other: Any) = Pair(this, other)
