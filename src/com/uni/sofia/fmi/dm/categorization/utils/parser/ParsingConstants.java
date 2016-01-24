package com.uni.sofia.fmi.dm.categorization.utils.parser;

/**
 * Created by vankata on 24.01.16.
 */
public enum ParsingConstants {
    REVIEW_SCORE("review/score: "),
    REVIEW_TEXT("review/text: "),
    CATEGORY("Category"),
    TEXT("Text");

    private String value;

    private ParsingConstants(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
