package com.uni.sofia.fmi.dm.categorization.utils.parser;

import java.util.List;

/**
 *
 * @author Dimitar
 */
public class JsonFileEntity {

    private int negativeReviews;
    private int positiveReviews;

    private List<ReviewEntity> reviews;

    public JsonFileEntity() {
    }

    public JsonFileEntity(int negativeReviews, int positiveReviews, List<ReviewEntity> reviews) {
        this.negativeReviews = negativeReviews;
        this.positiveReviews = positiveReviews;
        this.reviews = reviews;
    }

    public int getNegativeReviews() {
        return negativeReviews;
    }

    public void setNegativeReviews(int negativeReviews) {
        this.negativeReviews = negativeReviews;
    }

    public int getPositiveReviews() {
        return positiveReviews;
    }

    public void setPositiveReviews(int positiveReviews) {
        this.positiveReviews = positiveReviews;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "JsonFileEntity{" + "negativeReviews=" + negativeReviews + ", positiveReviews=" + positiveReviews + ", reviews=" + reviews + '}';
    }

}
