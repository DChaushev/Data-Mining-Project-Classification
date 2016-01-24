package com.uni.sofia.fmi.dm.categorization.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dimitar
 */
public class TokensInformationHolder {

    private Map<String, Token> tokens;

    private int[] classOccurrences;
    private int numberOfInstances;

    public TokensInformationHolder()
    {
        tokens = new HashMap<>();

        classOccurrences = new int[Categories.values().length];
    }

    public Map<String, Token> getTokens() {
        return tokens;
    }

    public void setTokens(Map<String, Token> tokens) {
        this.tokens = tokens;
    }

    public int[] getClassOccurrences() {
        return classOccurrences;
    }

    public void setClassOccurrences(int[] classOccurrences) {
        this.classOccurrences = classOccurrences;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    /**
     *
     * @return returns the number of words that have occured for the given category
    */
    public int getOccurencesForCategory(Categories category)
    {
        return this.classOccurrences[category.getCategoryValue()];
    }
}
