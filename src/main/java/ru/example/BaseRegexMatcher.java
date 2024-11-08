package ru.example;

import lombok.NonNull;

import java.util.regex.PatternSyntaxException;

/**
 * Класс, определяющий работу с вариативными регулярными выражениями
 */
public class BaseRegexMatcher implements RegexMatcher {
    /**
     * Метод проверяет соответствие текста заданному регулярному выражению.
     *
     * В сравнении с изначальным кодом можно выделить следующее:
     * 1. Можно написать метод различными способами, которые будут тождественны с точки зрения исполнения, за исключением
     * пары отличий относительно исходной записи: return Pattern.compile(regex).matcher(text).matches()
     *  1.1. Вместо явных вызовов методов compile и matcher, можно вызвать метод matches на классе Pattern:
     *  Pattern.matches(regex, text), так как он под собой содержит эти методы
     *  1.2. В качестве альтернативы можно использовать метод matches на экземпляре класса String. Он под капотом
     *  содержит вызов Pattern.matches(regex, this), что эквивалентно п. 1.1
     * 2. Добавлена обработка одного unchecked exception: PatternSyntaxException, который возникает при передаче
     * невалидного регулярного выражения
     * 3. Добавлены аннотации @NonNull на параметры метода, так как в случае если одно из значений приходит в качестве
     * null, то вылетает NullPointerException
     *
     * @param regex - регулярное выражение
     * @param text - текст, проверяемый на соответствие регулярному выражению
     * @return true - текст соответствует регулярному выражению
     *         false - текст не соответствует регулярному выражению
     * @throws IllegalArgumentException - в случае невалидного регулярного выражения
     */
    public boolean matches(@NonNull String regex, @NonNull String text) {
        try {
            return text.matches(regex);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Невалидный паттерн регулярного выражения: " + regex);
        }
    }
}
