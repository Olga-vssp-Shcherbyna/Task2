package com.Fay.Services;

import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.Fay.Services.SentenceService.*;
import static org.junit.Assert.assertTrue;

public class SentenceServiceTest {
    private static Sentence s1;
    private static Sentence s2;

    @BeforeClass
    public static void setUp() {
        s1 = new Sentence("I am sentence");
        s2 = new Sentence("Me too");
        splitSentenceIntoWords(s1);
        splitSentenceIntoWords(s2);
    }

    @Test
    public void shouldReturnFalseWhenSentencesAreSimilar() {
        List words = compareSentences(s1, s2);
        boolean result = words.isEmpty();
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenWordInSentence() {
        Word word = new Word("I");
        boolean result = findWordInSentence(s1, word);
        assertTrue(result);
    }

}