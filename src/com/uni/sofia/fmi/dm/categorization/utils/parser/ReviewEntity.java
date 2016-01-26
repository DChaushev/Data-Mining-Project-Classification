package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;

/**
 *
 * @author Dimitar
 */
public class ReviewEntity {

    private Categories category;
    private String text;

    public ReviewEntity() {
    }

    public ReviewEntity(Categories category, String text) {
        this.category = category;
        this.text = text;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReviewEntity{" + "category=" + category + ", text=" + text + '}';
    }

}
