package com.Fay.Services;

import com.Fay.TextComponents.Document;
import com.Fay.TextComponents.Sentence;
import com.Fay.TextComponents.Word;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.Fay.Services.DocumentService.getFirstNotEmptySentence;
import static com.Fay.Services.DocumentService.printUniqueWords;
import static org.junit.Assert.assertNull;

public class DocumentServiceTest {

    private static Document document;
    private static Document doc;
    private static String filepath;
    private static String filepath2;

    @BeforeClass
    public static void setUpClass() throws IOException {
        System.out.println("-------------setUpClass info-----------------");
        document = DocumentService.getTextDocument("");
        System.out.println("---------------------------------------------\n");
        filepath = ".\\src\\main\\resources\\Test1.txt";
        filepath2 = ".\\src\\main\\resources\\Test2.txt";
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

    @Before
    public void setUp() throws IOException {
        doc = DocumentService.getTextDocument(filepath2);
    }

    @Test
    public void searchForUniqueWordsInIncorrectFile() {
        DocumentService.findUniqueWord(doc);
    }

    @Before
    public void setUpNext() throws IOException {
        System.out.println("-------------setUpNext info------------------");
        doc = DocumentService.getTextDocument(filepath);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void searchForUniqueWordsInEmptyFile() {
        DocumentService.findUniqueWord(doc);
    }

    @After
    public void resetNext() throws IOException {
        doc = DocumentService.getTextDocument(filepath2);
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