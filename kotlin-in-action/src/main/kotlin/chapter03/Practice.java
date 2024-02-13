package chapter03;

import java.util.List;

import static chapter03.KotlinPracticeKt.UNIX_LINE_SEPARATOR;
import static chapter03.StringFunctions.joinToString;

public class Practice {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        joinToString(list, ";");

        KotlinPracticeKt.getOpCount(); // 상수로 선언했지만, getter로 가져와야 한다.
        Character separator = UNIX_LINE_SEPARATOR;
    }
}
