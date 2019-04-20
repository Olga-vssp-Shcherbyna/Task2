package com.Fay.TextComponents;

import java.io.File;

public class Document extends Text {
    private static Document INSTANCE;
    private File file;
    private Sentence[] sentences;

    public Document(File text) {
        this.file = text;
    }

    public static Document getInstance(File text) {
        if (INSTANCE == null) {
            synchronized (Document.class) {
                if (INSTANCE == null)
                    INSTANCE = new Document(text);
            }
        }
        return INSTANCE;
    }

    public File getFile() {
        return file;
    }

    public Sentence[] getSentences() {
        return sentences;
    }

    public void setSentences(Sentence[] sentences) {
        this.sentences = sentences;
    }
}
