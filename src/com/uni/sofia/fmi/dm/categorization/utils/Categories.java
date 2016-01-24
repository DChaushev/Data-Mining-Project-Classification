package com.uni.sofia.fmi.dm.categorization.utils;

/**
 *
 * @author Dimitar
 */
public enum Categories {

    POSITIVE(0),
    NEGATIVE(1),
    NUMBER_OF_CATEGORIES(2); // IMPORTANT!!! update this if you add a category

    private final int value;

    private Categories(int value) {
        this.value = value;
    }

    public int getCategoryValue()
    {
        return value;
    }
}
