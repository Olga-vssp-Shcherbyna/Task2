package com.Fay.TextComponents;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Word extends Text {

    public Word(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Word)) return false;
        Word word = (Word) obj;
        Pattern pattern = compile(this.getData().toLowerCase());
        Matcher m = pattern.matcher(word.getData().toLowerCase());
        return m.matches();
    }
}

