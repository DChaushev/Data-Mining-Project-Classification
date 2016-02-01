package com.uni.sofia.fmi.dm.categorization.utils;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Dimitar
 */
public class TokensHolder implements Serializable {

    private Map<String, Token> tokens;
    private int numberOfClasses;
    private int numberOfInstances;

    private int[] instancesPerClass;

    // keeps how many tokens are there for a category
    // needed to calculate probability for a given token
    private int[] numberOfTokensPerClass;

    public TokensHolder(Map<String, Token> tokens, int[] classes, int numberOfInstances) {
        init(tokens, classes, numberOfInstances);
    }

    public Set<String> getTokensKeysSet() {
        return tokens.keySet();
    }

    public Token getToken(String key) {
        return tokens.get(key);
    }

    /**
     * @param clazz
     * @return the number of words that have occurred for the given category
     */
    public int getNumberOfTokensForClass(int clazz) {
        return numberOfTokensPerClass[clazz];
    }
    
    public int getNumberOfInstancesPerClass(int clazz){
        return this.instancesPerClass[clazz];
    }

    public void setNumberOfTokensForClass(int numberOfTokens, int clazz) {
        this.numberOfTokensPerClass[clazz] = numberOfTokens;
    }

    public void init(Map<String, Token> tokens, int[] classes, int numberOfInstances) {
        this.tokens = tokens;
        this.instancesPerClass = classes;
        this.numberOfClasses = classes.length;
        this.numberOfTokensPerClass = new int[numberOfClasses];
        this.numberOfInstances = numberOfInstances;

        for (Token token : tokens.values()) {
            for (int i = 0; i < numberOfClasses; i++) {
                int occurencesForClass = token.getOccurencesForClass(i);
                numberOfTokensPerClass[i] += occurencesForClass;
            }
        }
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public double getProbabilityForClass(int i) {
        return (double) this.instancesPerClass[i] / this.numberOfInstances;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }
    
    public int getVocabularySize(){
        return tokens.size();
    }

}
