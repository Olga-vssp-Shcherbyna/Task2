package com.Fay.Services;

import com.Fay.TextComponents.Document;
import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.Fay.Services.SentenceService.*;
import static com.Fay.Services.WordService.wordArrayToString;
import static com.Fay.TextComponents.PunctuationMark.STOP_MARKS_REGEX;

public class DocumentService {

    public static void getTextFromFile(Document document) {
        try {
            BufferedReader fr = new BufferedReader(new FileReader(document.getFile()));
            String line;
            String data = "";
            while ((line = fr.readLine()) != null) {
                data = String.join(" ", data, line).trim();
            }
            document.setData(data);
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file path");
        } catch (IOException e) {
            System.out.println("Empty file");
        }
    }

    private static void parseDocument(Document document) {
        String[] sentences = document.getData().split(STOP_MARKS_REGEX);
        document.setSentences(stringsToSentences(sentences));
        for (Sentence s : document.getSentences()) {
            splitSentenceIntoWords(s);
        }
    }

    public static void findUniqueWord(Document document) {
        parseDocument(document);
        List<Word> words = new ArrayList<>();
        Sentence firstSentence = document.getSentences()[0];
        for (int i = 1; i < document.getSentences().length; i++) {
            if (!compareSentences(firstSentence, document.getSentences()[i]).isEmpty()) {
                words.addAll(compareSentences(firstSentence, document.getSentences()[i]));
            }
        }
        System.out.printf("Words in first sentence:\n%s\n", wordArrayToString(Arrays.asList(firstSentence.getWords())));
        printUniqueWords(words, firstSentence);
    }

    static void printUniqueWords(List<Word> words, Sentence firstSentence) {
        List<Word> unique = new ArrayList<>(Arrays.asList(firstSentence.getWords()));
        if (words.isEmpty()) {
            System.out.printf("All words in sentence \"%s\" are unique\n", firstSentence.getData().trim());
            return;
        }
        for (Word w : firstSentence.getWords()) {
            if (words.contains(w)) {
                unique.remove(w);
            }
        }
        System.out.printf("Unique words in the first sentence \"%s\":\n%s\n", firstSentence.getData().trim(), wordArrayToString(unique));
    }
}

