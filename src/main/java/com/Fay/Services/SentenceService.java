package com.Fay.Services;

import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.Fay.Services.WordService.equalsWithRegExp;
import static com.Fay.Services.WordService.getWordsFromStrings;
import static com.Fay.TextComponents.PunctuationMark.INTERMEDIATE_MARKS_REGEX;
import static java.util.regex.Pattern.compile;

public class SentenceService {
    static Sentence[] stringsToSentences(String[] string) {
        Sentence[] sentences = new Sentence[string.length];
        for (int i = 0; i < string.length; i++) {
            sentences[i] = new Sentence(string[i]);
        }
        return sentences;
    }

    static void splitSentenceIntoWords(Sentence sentence) {
        String[] words = sentence.getData().split(INTERMEDIATE_MARKS_REGEX);
        sentence.setWords(getWordsFromStrings(words));
    }


    private static boolean findWordInSentence(Sentence sentence, Word word) {
        Pattern pattern = compile(word.getData().toLowerCase());
        int c = 0;
        for (Word w : sentence.getWords()) {
            if (equalsWithRegExp(pattern, w))
                c++;
        }
        return c > 0;
    }

    static List<Word> compareSentences(Sentence sentence, Sentence s) {
        List<Word> words = new ArrayList<>();
        for (Word w : sentence.getWords()) {
            if (findWordInSentence(s, w)) {
                words.add(w);
            }
        }
        return words;
    }
}
