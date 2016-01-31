package com.uni.sofia.fmi.dm.categorization.utils;

import java.util.Arrays;
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
     * By a given category increment by 1 the number of tokens occured for that
     * category
     *
     * @param category - the category for which the number of tokens to be
     * updated
     */
    private void incrementNumberOfTokensForCategory(Categories category) {
        numberOfTokens[category.getCategoryValue()]++;
    }

    /**
     * @param category
     * @return returns the number of instances that we have loaded for the given
     * category
     */
    public int getOccurencesForCategory(Categories category) {
        return this.classOccurrences[category.getCategoryValue()];
    }

    /**
     * Given a token and a category this updates an entry or adds one
     *
     * @param token - token to be updated or added
     * @param category - the category for the token
     */
    public void handleToken(String token, Categories category) {
        incrementNumberOfTokensForCategory(category);

        Token currentToken = tokens.get(token);

        if (currentToken == null) {
            currentToken = new Token();
            tokens.put(token, currentToken);
        }

        currentToken.incrementOccurencesForCategory(category);
    }

    public double getProbabilityForCategory(Categories category) {
        return classOccurrences[category.getCategoryValue()] / numberOfInstances;
    }

    public double getProbabilityForToken(String token, Categories category) {
        Token currentToken = tokens.get(token);

        if (currentToken == null) {
            return Double.MIN_VALUE;
        }

        return currentToken.getProbabilityForCategory(category);
    }

    @Override
    public String toString() {
        return String.format("Total number of instances: %d\nNumber of tokens: %s\nData: %s", numberOfInstances, Arrays.toString(numberOfTokens), tokens);
    }
}
