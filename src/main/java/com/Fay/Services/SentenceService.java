package com.Fay.Services;

import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(sentence.getWords()).anyMatch(word::equals);
    }

    static List<Word> compareSentences(Sentence sentence, Sentence s) {
        return Arrays.stream(sentence.getWords())
                .filter(word -> findWordInSentence(s, word))
                .collect(Collectors.toList());
    }
}

