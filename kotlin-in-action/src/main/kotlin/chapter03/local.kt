package chapter03

import java.lang.IllegalArgumentException

class User(val id: Int, val name: String, val address: String)

fun saveUserWithDuplicate(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("${user.id} : empty")
    }
    if (user.address.isEmpty()) { // 코드 중복 발생
        throw IllegalArgumentException()
    }
}

fun saveUser(user: User) {
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("${user.id} : empty $fieldName")
        }
    }
    validate(user, user.name, "")
    validate(user, user.address, "")

    fun validateBetter(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("${user.id} : empty $fieldName")
            // 로컬 함수는 바깥 함수의 모든 파라미터와 변수를 사용할 수 있다.
        }
    }
}

// 검증 로직은 User를 사용하는 다른 곳에서는 사용되지 않음. 내부적으로만 사용됨.
// 이럴때 User에 포함시키고 싶지 않다면, 확장함수로 분리해두면 좋다.
// 이런 확장 함수를 로컬 함수로 정의하는 것도 가능하지만, 가독성을 위해 함수는 한 단계만 중첩하도록 해라.
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("$id : empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun main() {
    saveUser(User(1, "", ""))
    val user = User(2, "", "")
    user.validateBeforeSave()
}
