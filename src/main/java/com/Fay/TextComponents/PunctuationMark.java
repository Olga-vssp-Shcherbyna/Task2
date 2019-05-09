package com.Fay.TextComponents;

public class PunctuationMark {
    public static final String STOP_MARKS_REGEX = "\\s*[.!?]\\s*";
    public static final String INTERMEDIATE_MARKS_REGEX = "[\\s\\p{Punct}\\d*\\s]+";
}

