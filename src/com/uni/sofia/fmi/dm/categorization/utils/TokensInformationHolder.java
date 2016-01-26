package com.uni.sofia.fmi.dm.categorization.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dimitar
 */
public class TokensInformationHolder {

    private Map<String, Token> tokens;

    // keeps how many tokens are there for a category
    // needed to calculate probability for a given token
    private int[] numberOfTokens;

    // keeps how many instances of a given category are there
    // needed to calculate probability of a given category
    private int[] classOccurrences;

    // total number of instances
    private int numberOfInstances;

    public TokensInformationHolder() {
        tokens = new HashMap<>();

        classOccurrences = new int[Categories.values().length];
        numberOfTokens = new int[Categories.values().length];
    }

    public Map<String, Token> getTokens() {
        return tokens;
    }

    /**
     * @param category
     * @return the number of words that have occurred for the given category
     */
    public int getNumberOfTokensForCategory(Categories category) {
        return numberOfTokens[category.getCategoryValue()];
    }

    public void setNumberOfTokensForCategory(int numberOfTokens, Categories category) {
        this.numberOfTokens[category.getCategoryValue()] = numberOfTokens;
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

    public void setOccurencesForCategory(int occurences, Categories category) {
        this.classOccurrences[category.getCategoryValue()] = occurences;
    }

    /**
     * @param category
     * @return returns the number of instances that we have loaded for the given
     * category
     */
    public int getOccurencesForCategory(Categories category) {
        return this.classOccurrences[category.getCategoryValue()];
    }
}
