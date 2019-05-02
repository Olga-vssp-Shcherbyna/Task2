package com.Fay.Services;

import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.Fay.Services.WordService.equalsWithRegExp;
import static com.Fay.Services.WordService.getWordsFromStrings;
import static com.Fay.TextComponents.PunctuationMark.INTERMEDIATE_MARKS_REGEX;

class SentenceService {
    static Sentence[] stringsToSentences(String[] string) {
        Sentence[] sentences = new Sentence[string.length];
        for (int i = 0; i < string.length; i++) {
            sentences[i] = new Sentence(string[i]);
        }
        return sentences;
    }

    static void splitSentenceIntoWords(Sentence sentence) {
        List<String> words = new ArrayList<>(Arrays.asList(sentence.getData().split(INTERMEDIATE_MARKS_REGEX)));
        words.removeIf(str -> str.equalsIgnoreCase(""));
        String[] wordsArray = words.toArray(new String[0]);
        sentence.setWords(getWordsFromStrings(wordsArray));
    }


    public static boolean findWordInSentence(Sentence sentence, Word word) {
        int c = 0;
        for (Word w : sentence.getWords()) {
            if (equalsWithRegExp(word, w))
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
