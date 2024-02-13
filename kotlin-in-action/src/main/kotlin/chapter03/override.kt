package chapter03

open class View {
    open fun click() = println("View Clicked")
}

class Button : View() {
    override fun click() = println("Button Clicked")
}
