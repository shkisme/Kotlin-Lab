package chapter02

import chapter02.Color.*
import chapter03.lastChar as last // as로 사용할 이름을 다르게 지정할 수 있음. 확장함수는 충돌 예방을 위해 짧은 이름을 쓰자.
import java.io.BufferedReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.*

// 같은 패키지에 있다면 다른 파일에서 정의한 선언이라도 직접 사용 가능.
// 다른 패키지에 있다면 Import 해야 함.

fun main() { // 클래스 밖에서 함수를 정의할 수 있다.
    println("HELLO") // 자바의 System.out.println()을 감싼 래퍼 제공. 편의 up
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
    // if == 문X, 식O
    // 식 == 값을 만들어 냄.
}

fun cleanMax(a: Int, b: Int): Int = if (a > b) a else b
// 더 간결하게 함수를 작성할 수 있도록 IntelliJ에서 기능 제공

fun moreCleanMax(a: Int, b: Int) = if (a > b) a else b
// 반환값을 생략하여 더 간략하게 표현 가능.
// 식이 본문이라면 컴파일러가 결과 타입을 추론할 수 있음.

fun stringPractice(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
    println("Hello, $name!") // 왠만하면 중괄호{}로 둘러 쌀 것.
    println("Hello, ${if (args.size > 0) args[0] else "Kotlin"}!") // 식도 중괄호로 둘러 싸서 넣을 수 있다.
}

fun person() {
    val borum = Person("asdf", true) // new 키워드 필요 없다.
    println(borum.name)
    println(borum.isMarried) // 필드를 사용해도 getter를 호출해줌.

    borum.isMarried = false // setter를 호출해줌.
}

fun practiceRectangle() {
    val rectangle = Rectangle(41, 42)
    println(rectangle.isSquare)
}

fun getMnemonic(color: Color) =
    when (color) { // when도 값을 만들어내는 식.
        RED, ORANGE -> "Richard" // 발전된 switch문과 동일.
        YELLOW -> "York"
    }
// 코틀린 when에서는 객체(인스턴스)를 허용한다.

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        else -> throw Exception("Dirty!") // 코틀린의 throw는 식이다. 또, new가 필요 없다.
    }

fun mixOptimized(c1: Color, c2: Color) =
    when { // 인자 없이 when을 사용할 수 있다.
        (c1 == RED && c2 == YELLOW) -> ORANGE // 단, 그러려면 각 분기의 조건이 boolean 결과를 계산해야 한다.
        else -> throw Exception("Dirty!")
    }

fun classLayer() {
    println(
        Sum( // 식이 합계라면, 좌항 + 우항을 계산한 다음 두 값을 합한 값을 반환한다.
            Num(1), // 식이 수라면 그 값을 반환한다.
            Num(2)
        )
    )
}

fun eval(e: Expr): Int { // practice smart cast
    if (e is Num) {
        return e.value // IntelliJ는 스마트 캐스트 부분의 배경색을 색칠해줌.
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }
    throw IllegalArgumentException()
}
// 그러나 스마트 캐스트를 사용하려면 그 프로퍼티는 반드시 val이어야 한다.
// 혹은 커스텀 접근자를 사용하지 않아야 함.
// 이유 : 프로퍼티가 var이거나 커스텀 접근자라면 항상 같은 값을 내놓는다고 확신할 수 없기에.

fun asKeyword(e: Expr) {
    val n = e as Num // 원하는 타입으로 명시적으로 캐스팅하고자 한다면 as 키워드 사용.
}

fun evalWithIf(e: Expr): Int =
    if (e is Num) e.value
    else if (e is Sum) eval(e.left) + eval(e.right)
    else throw IllegalArgumentException()

fun evalWithWhen(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        // 블록{}으로 감싸서 더 긴 코드를 짤 수도 있다. 마찬가지로 마지막 식이 블록의 결과가 된다.
        else -> throw IllegalArgumentException()
    }

fun forPractice() {
    for (i in 1..100) {
        println(i)
    }
    for (i in 100 downTo 1 step 2) { // ... 이제 자바 못쓰겠는데?
        // step은 변화 값의 절댓값.
        println(i)
    }
    for (i in 0 until 100) {
        println(i)
    }
}

fun map() {
    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary // binaryReps.put(c, binary)와 동일
    }
    for ((letter, binary) in binaryReps) {
        println("${letter} = ${binary}")
    }
}

fun listPractice() {
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'

fun whenInIn(c: Char) = when (c) {
    in '0'..'9' -> "Its a digit!"
    in 'a'..'z', in 'A'..'Z' -> "Its a letter!"
    else -> "I dont know!"
}

fun anotherWithIn() {
    println("Kotlin" in "Java".."Scala") // 알파벳 순서로 비교
    println("Kotlin" in setOf("Java", "Scala")) // 포함 되는지 판단
}

fun readNumber(reader: BufferedReader): Int? { // 예외 명시할 필요 X
    // 자바에서는 체크 예외 처리를 강제한다.
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) { // 언체크 예외
        return null
    } finally {
        reader.close() // IOException은 체크 예외.
        // 그러나 스트림을 닫는 것을 실패한 경우 클라이언트가 취할 수 있는 의미있는 동작은 없음.
        // 따라서 IOException을 잡아내는 코드는 다소 불필요함.
    }
}

fun tryUseExpression(reader: BufferedReader) {
    val number = try { // If 와 달리 반드시 {}로 감싸야 함.
        Integer.parseInt(reader.readLine()) // try 식의 값.
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}

fun extendFun() {
    val c = "Kotlin".last() // import 해야 함.
}
