package com.Fay.TextComponents;


public class Document extends Text {
    private static Document INSTANCE;
    private Sentence[] sentences;

    private Document(String text) {
        this.data = text;
    }

    public static Document getInstance(String text) {
        if (INSTANCE == null) {
            synchronized (Document.class) {
                if (INSTANCE == null)
                    INSTANCE = new Document(text);
            }
        }
        return INSTANCE;
    }

    public Sentence[] getSentences() {
        return sentences;
    }

    public void setSentences(Sentence[] sentences) {
        this.sentences = sentences;
    }
}
