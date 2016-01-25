package com.uni.sofia.fmi.dm.categorization.utils.parser;

/**
 * Created by vankata on 24.01.16.
 */
public enum ParsingConstants {

    REVIEW_SCORE("review/score: "),
    REVIEW_TEXT("review/text: "),
    CATEGORY("Category"),
    TEXT("Text"),
    POSITIVE_REVIEWS("positiveReviews"),
    NEGATIVE_REVIEWS("negativeReviews"),
    REVIEWS("reviews");

    private final String value;

    private ParsingConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
