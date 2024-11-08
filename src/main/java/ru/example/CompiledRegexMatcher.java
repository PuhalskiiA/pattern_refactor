package ru.example;

import lombok.NonNull;

import java.util.regex.Pattern;

/**
 * Класс, определяющий работу с уже скомпилированными паттернами
 */
public class CompiledRegexMatcher implements RegexMatcher {
    /**
     * Метод проверяет соответствие текста заданному регулярному выражению.
     *
     * Обрабатывать PatternSyntaxException не имеет смысла, так как при определении невалидного регулярного выражения в
     * static блоке (в классе PatternRepository), приложение упадет на этапе компиляции.
     *
     * @param regex - регулярное выражение
     * @param text - текст, проверяемый на соответствие регулярному выражению
     * @return true - текст соответствует регулярному выражению
     *         false - текст не соответствует регулярному выражению
     * @throws IllegalArgumentException - в случае, если паттерн, соответствующий регулярному выражению - не найден
     */
    @Override
    public boolean matches(@NonNull String regex, @NonNull String text) {
        Pattern pattern = PatternRepository.getCompiledPattern(regex);

        if (pattern == null) throw new IllegalArgumentException("Регулярное выражение не найдено");

        return pattern.matcher(text).matches();
    }
}
