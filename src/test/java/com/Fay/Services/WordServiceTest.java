package com.Fay.Services;

import com.Fay.TextComponents.Word;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class WordServiceTest {
    private static Word w1;
    private static Word w2;

    @BeforeClass
    public static void setUp() {
        w1 = new Word("Java");
        w2 = new Word("String");
    }

    @Test
    public void shouldReturnFalseWhenWordsDifferent() {
        boolean result = w1.equals(w2);
        assertFalse(result);
    }

}

