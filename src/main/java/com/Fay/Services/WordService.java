package com.Fay.Services;

import com.Fay.TextComponents.Word;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

class WordService {
    static Word[] getWordsFromStrings(String[] string) {
        Word[] words = new Word[string.length];
        for (int i = 0; i < string.length; i++) {
            words[i] = new Word(string[i]);
        }
        return words;
    }

    public static String wordArrayToString(List<Word> words) {
        String[] string = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            string[i] = words.get(i).getData();
        }
        return Arrays.toString(string);
    }

    static boolean equalsWithRegExp(Word firstWord, Word secondWord) {
        Pattern pattern = compile(firstWord.getData().toLowerCase());
        Matcher m = pattern.matcher(secondWord.getData().toLowerCase());
        return m.matches();
    }
}
