package com.Fay.Services;

import com.Fay.TextComponents.Word;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordService {
    static Word[] getWordsFromStrings(String[] string) {
        Word[] words = new Word[string.length];
        for (int i = 0; i < string.length; i++) {
            words[i] = new Word(string[i]);
        }
        return words;
    }

    static String wordArrayToString(List<Word> words) {
        String[] string = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            string[i] = words.get(i).getData();
        }
        return Arrays.toString(string);
    }

    static boolean equalsWithRegExp(Pattern firstWord, Word secondWord) {
        Matcher m = firstWord.matcher(secondWord.getData());
        return m.matches();
    }
}
