package com.uni.sofia.fmi.dm.categorization.utils;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Dimitar
 */
public class Token implements Serializable{

    private int[] classOccurrences;
    private double[] probabilities;

    public Token() {
        this(null, null);
    }

    public Token(int[] classOccurrences, double[] probabilities) {
        setClassOccurrences(classOccurrences);
        setProbabilities(probabilities);
    }

    public Token(Token token) {
        this(token.getClassOccurrences(), token.getProbabilities());
    }

    public int[] getClassOccurrences() {
        int[] tmpClassOccurrences = new int[classOccurrences.length];

        for (int i = 0; i < classOccurrences.length; ++i) {
            tmpClassOccurrences[i] = classOccurrences[i];
        }

        return tmpClassOccurrences;
    }

    public void setClassOccurrences(int[] classOccurrences) {
        if (classOccurrences != null) {
            this.classOccurrences = new int[classOccurrences.length];
            System.arraycopy(classOccurrences, 0, this.classOccurrences, 0, classOccurrences.length);
        } else {
            this.classOccurrences = new int[Categories.values().length];

            for (int i = 0; i < this.classOccurrences.length; ++i) {
                this.classOccurrences[i] = 0;
            }
        }
    }

    public double[] getProbabilities() {
        double[] tmpProbabilities = new double[probabilities.length];
        System.arraycopy(probabilities, 0, tmpProbabilities, 0, probabilities.length);
        return tmpProbabilities;
    }

    public void setProbabilities(double[] probabilities) {
        if (probabilities != null) {
            this.probabilities = new double[probabilities.length];
            System.arraycopy(probabilities, 0, this.probabilities, 0, probabilities.length);
        } else {
            this.probabilities = new double[Categories.values().length];

            for (int i = 0; i < this.probabilities.length; i++) {
                this.probabilities[i] = 0.0;
            }
        }
    }

    public int getOccurencesForCategory(Categories category) {
        return this.classOccurrences[category.getCategoryValue()];
    }

    public double getProbabilityForCategory(Categories category) {
        return this.probabilities[category.getCategoryValue()];
    }

    public void setOccurencesForCategory(int occurences, Categories category) {
        this.classOccurrences[category.getCategoryValue()] = occurences;
    }

    public void setProbabilityForCategory(double probability, Categories category) {
        this.probabilities[category.getCategoryValue()] = probability;
    }

    /**
     * Increment the ocurrences for a given category by 1
     *
     * @param category - the category for which the occurences to be incremented
     */
    public void incrementOccurencesForCategory(Categories category) {
        classOccurrences[category.getCategoryValue()]++;
    }

    @Override
    public String toString() {
        return String.format("Class occurences: %s\nProbabilities: %s\n", Arrays.toString(classOccurrences), Arrays.toString(probabilities));
    }
}
