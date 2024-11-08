package ru.example;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BaseRegexMatcherTest extends TestCase {
    private final RegexMatcher baseRegexMatcher = new BaseRegexMatcher();

    @Test
    public void testMatchesInvalidRegex() {
        String text = "1234";
        String regex = "\\d{3345sdafsdfqwe}";

        IllegalArgumentException thrown = Assert.assertThrows(IllegalArgumentException.class,
                () -> baseRegexMatcher.matches(regex, text));

        Assert.assertEquals("Невалидный паттерн регулярного выражения: \\d{3345sdafsdfqwe}", thrown.getMessage());
    }

    @Test
    public void testMatchesTextIsMatch() {
        String text = "123";
        String regex = "\\d{3}";

        Assert.assertTrue(baseRegexMatcher.matches(regex, text));
    }

    @Test
    public void testMatchesTextIsNotMatch() {
        String text = "1234";
        String regex = "\\d{3}";

        Assert.assertFalse(baseRegexMatcher.matches(regex, text));
    }

    @Test
    public void testMatchesTextIsNull() {
        String text = null;
        String regex = "\\d{3}";

        NullPointerException thrown = Assert.assertThrows(NullPointerException.class,
                () -> baseRegexMatcher.matches(regex, text));

        Assert.assertEquals("text is marked non-null but is null", thrown.getMessage());
    }

    @Test
    public void testMatchesRegexIsNull() {
        String text = "123";
        String regex = null;

        NullPointerException thrown = Assert.assertThrows(NullPointerException.class,
                () -> baseRegexMatcher.matches(regex, text));

        Assert.assertEquals("regex is marked non-null but is null", thrown.getMessage());
    }

    @Test
    public void testMatchesRegexAndTextIsNull() {
        String text = null;
        String regex = null;

        NullPointerException thrown = Assert.assertThrows(NullPointerException.class,
                () -> baseRegexMatcher.matches(regex, text));

        Assert.assertEquals("regex is marked non-null but is null", thrown.getMessage());
    }
}