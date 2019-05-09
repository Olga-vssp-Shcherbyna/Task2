package com.Fay.TextComponents;

public class Sentence extends Text {
    private Word[] words;

    public Sentence(String data) {
        this.data = data;
    }

    public Word[] getWords() {
        return words;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }
}

