package com.uni.sofia.fmi.dm.categorization.utils;

import java.io.Serializable;

/**
 *
 * @author Dimitar
 */
public class Token implements Serializable {

    private int[] classOccurrences;

    public Token(int numberOfClasses) {
        setClassOccurrences(new int[numberOfClasses]);
    }

    public Token(int[] classOccurrences) {
        setClassOccurrences(classOccurrences);
    }

    public Token(Token token) {
        this(token.getClassOccurrences());
    }

    public void setClassOccurrences(int[] classOccurrences) {
        this.classOccurrences = classOccurrences;
    }

    public int[] getClassOccurrences() {
        int[] tmpClassOccurrences = new int[classOccurrences.length];

        for (int i = 0; i < classOccurrences.length; ++i) {
            tmpClassOccurrences[i] = classOccurrences[i];
        }

        return tmpClassOccurrences;
    }

    public int getOccurencesForClass(int clazz) {
        return this.classOccurrences[clazz];
    }

    public void setOccurencesForCategory(int occurences, Categories category) {
        this.classOccurrences[category.getCategoryValue()] = occurences;
    }

    /**
     * Increment the ocurrences for a given category by 1
     *
     * @param clazz - the category for which the occurences to be incremented
     */
    public void incrementOccurencesForCategory(int clazz) {
        classOccurrences[clazz]++;
    }
}
