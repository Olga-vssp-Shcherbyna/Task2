package com.Fay.Services;

import com.Fay.TextComponents.Document;
import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.Fay.Services.SentenceService.*;
import static com.Fay.Services.WordService.wordArrayToString;
import static com.Fay.TextComponents.PunctuationMark.STOP_MARKS_REGEX;

class DocumentService {

    static Document getTextDocument(String filePath) throws IOException {
        try {
            BufferedReader fr = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            String data = "";
            while ((line = fr.readLine()) != null) {
                data = String.join(" ", data, line).trim();
            }
            if (data.equalsIgnoreCase("")) {
                System.out.println("Empty file");
                return null;
            } else return Document.getInstance(data);
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file path");
        }
        return null;
    }


    static void parseDocument(Document document) {
        String[] sentences = document.getData().split(STOP_MARKS_REGEX);
        document.setSentences(stringsToSentences(sentences));
        for (Sentence s : document.getSentences()) {
            splitSentenceIntoWords(s);
        }
    }

    static void findUniqueWord(Document document) {
        parseDocument(document);
        List<Word> words = new ArrayList<>();
        Sentence firstSentence = getFirstNotEmptySentence(document);
        int k = firstSentenceIndex(document, firstSentence) + 1;
        try {
            for (int i = k; i < document.getSentences().length; i++) {
                assert firstSentence != null;
                if (!compareSentences(firstSentence, document.getSentences()[i]).isEmpty()) {
                    words.addAll(compareSentences(firstSentence, document.getSentences()[i]));
                }
            }
            System.out.printf("Words in first sentence:\n%s\n", wordArrayToString(Arrays.asList(firstSentence.getWords())));
            printUniqueWords(words, firstSentence);
        } catch (NullPointerException e) {
            System.out.println("All sentences are empty");
        }
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

    static Sentence getFirstNotEmptySentence(Document document) {
        Pattern pattern = Pattern.compile("[\\s\\p{Punct}\\d*\\s]*");
        try {
            for (Sentence s : document.getSentences()) {
                Matcher m = pattern.matcher(s.getData());
                if (!(m.matches()))
                    return s;
            }
        } catch (NullPointerException e) {
            System.out.println("Empty document data");
        }
        return null;
    }

    static int firstSentenceIndex(Document document, Sentence firstSentence) {
        return Arrays.asList(document.getSentences()).indexOf(firstSentence);
    }


}

