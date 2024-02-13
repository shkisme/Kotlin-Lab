package chapter02

interface Expr

class Num(val value: Int) : Expr // Expr를 구현

class Sum(val left: Expr, val right: Expr) : Expr // Expr를 구현
// Expr 타입의 인자라면 어떤 것이든 Sum의 파라미터가 될 수 있음.
// Sum(Num(1), Num(2)) -> 이런 식으로 작성 가능.
