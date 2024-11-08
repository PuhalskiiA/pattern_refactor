package ru.example;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CompiledRegexMatcherTest extends TestCase {
    private final RegexMatcher compileRegexMatcher = new CompiledRegexMatcher();

    @Test
    public void testGetCompiledPatternIsMatch() {
        String text = "123";
        String regex = "\\d{3}";

        Assert.assertTrue(compileRegexMatcher.matches(regex, text));
    }

    @Test
    public void testGetCompiledPatternIsNotMatch() {
        String text = "1234";
        String regex = "\\d{3}";

        Assert.assertFalse(compileRegexMatcher.matches(regex, text));
    }

    @Test
    public void testGetCompiledPatternNotFound() {
        String text = "Obviously";
        String regex = "[abx]";

        IllegalArgumentException thrown = Assert.assertThrows(IllegalArgumentException.class,
                () -> compileRegexMatcher.matches(regex, text));

        Assert.assertEquals("Регулярное выражение не найдено",
                thrown.getMessage());
    }
}