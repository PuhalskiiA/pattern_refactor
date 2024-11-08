package ru.example;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Класс, определяющий набор скомпилированных Pattern(ов)
 */
public final class PatternRepository {
    //Тут можно было бы создать неизменяемую мапу, например:
    //         Map<String, Pattern> patterns = Map.ofEntries(
    //                Map.entry(\d{3}, Pattern.compile("\\d{3}")),
    //                Map.entry([abc], Pattern.compile("[abc]")),
    //                );
    //но это уже начиная с Java 9, поэтому можно задать ее через static блок
    private static final Map<String, Pattern> patterns = new HashMap<>();

    static {
        patterns.put("\\d{3}", Pattern.compile("\\d{3}"));
        patterns.put("[abc]", Pattern.compile("[abc]"));
        patterns.put("\\d", Pattern.compile("\\d"));
    }

    private PatternRepository() {}

    /**
     * Метод для получения скомпилированного паттерна, на основе регулярного выражения
     *
     * @param regex - регулярное выражение
     * @return Соответствующий regex паттерн
     */
    public static Pattern getCompiledPattern(@NonNull String regex) {
        return patterns.get(regex);
    }
}
