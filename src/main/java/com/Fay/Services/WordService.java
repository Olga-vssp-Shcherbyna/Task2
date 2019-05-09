package com.Fay.Services;

import com.Fay.TextComponents.Word;

import java.util.ArrayList;
import java.util.List;

class WordService {
    static Word[] getWordsFromStrings(String[] string) {
        Word[] words = new Word[string.length];
        for (int i = 0; i < string.length; i++) {
            words[i] = new Word(string[i]);
        }
        return words;
    }

    public static String wordArrayToString(List<Word> words) {
        List<String> string = new ArrayList<>();
        for (Word word : words) {
            string.add(word.getData());
        }
        return string.toString();
    }

}

