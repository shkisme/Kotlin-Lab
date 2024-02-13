@file:JvmName("StringFunctions") // JoinKt가 아닌 다른 클래스로 이름을 바꾸고 싶다면.
// 위치는 package 위에 있어야 함.

package chapter03

import java.lang.StringBuilder

@JvmOverloads // java에서 사용할 때 자바 메서드를 생성해 줌.
fun <T> joinToString(
    collection: Collection<T>, // 제네릭
    separator: String = ", ", // 디폴트 파라미터 값. 오버로딩이 많아질 경우를 방지해줌.
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 이 파일을 자바로 변환하고, 자바 컴파일러로 돌려 보면 JoinKt라는 클래스가 생성되고 해당 클래스 안에 static 메서드들이 작성되어 있다.
