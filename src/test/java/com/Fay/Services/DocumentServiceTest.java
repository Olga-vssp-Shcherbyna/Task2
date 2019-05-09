package com.Fay.Services;

import com.Fay.TextComponents.Document;
import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.Fay.Services.DocumentService.*;
import static org.junit.Assert.assertNull;

public class DocumentServiceTest {

    private static Document document;
    private static Document doc;
    private static String filepath;

    @BeforeClass
    public static void setUpClass() throws IOException {
        System.out.println("-------------setUpClass info-----------------");
        document = DocumentService.getTextDocument("");
        System.out.println("---------------------------------------------\n");
        filepath = ".\\src\\main\\resources\\Test1.txt";
        doc = DocumentService.getTextDocument(".\\src\\main\\resources\\Test2.txt");
    }

    @Test
    public void shouldReturnFilepathWrongMessage() {
        assertNull("Filepath was not wrong", document);
        System.out.println("Filepath was wrong");
    }

    @Test
    public void shouldReturnEmptyFileMessage() throws IOException {
        Document doc = DocumentService.getTextDocument(filepath);
        assertNull(doc);
    }

    @Test(expected = NullPointerException.class)
    public void notExistingFileParsing() {
        DocumentService.parseDocument(document);
    }


    @Test
    public void searchForUniqueWordsInIncorrectFile() {
        findUniqueWord(doc);
    }


    @Test(expected = NullPointerException.class)
    public void searchForUniqueWordsInEmptyFile() throws IOException {
        doc = DocumentService.getTextDocument(filepath);
        findUniqueWord(doc);
    }


    @Test
    public void shouldReturnUniqueSentenceMessage() {
        Sentence firstSentence = new Sentence("I love cats and java");
        SentenceService.splitSentenceIntoWords(firstSentence);
        List<Word> words = new ArrayList<>();
        DocumentService.printUniqueWords(words, firstSentence);
    }

    @Test
    public void shouldReturnListOfUniqueWords() {
        Sentence firstSentence = new Sentence("I love cats and java");
        SentenceService.splitSentenceIntoWords(firstSentence);
        List<Word> words = new ArrayList<>();
        words.add(firstSentence.getWords()[0]);
        words.add(firstSentence.getWords()[2]);
        printUniqueWords(words, firstSentence);
    }

    @Test
    public void shouldReturnNullSentence() {
        Sentence sentence = getFirstNotEmptySentence(doc);
        assertNull(sentence);
    }
}

