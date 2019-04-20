package com.Fay;

import com.Fay.TextComponents.Document;

import java.io.File;

import static com.Fay.Services.DocumentService.findUniqueWord;
import static com.Fay.Services.DocumentService.getTextFromFile;

public class Main {
    public static void main(String[] args) {
        File f = new File(".\\src\\main\\resources\\Test");
        Document document = Document.getInstance(f);
        getTextFromFile(document);
        findUniqueWord(document);
    }
}
