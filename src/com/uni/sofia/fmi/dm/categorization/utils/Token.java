package com.uni.sofia.fmi.dm.categorization.utils;

/**
 *
 * @author Dimitar
 */
public class Token {

    private int[] classOccurrences;
    private double[] probabilities;

    public Token()
    {
        setProbabilities(null);
        setClassOccurrences(null);
    }

    public Token(int[] classOccurrences, double[] probabilities) {
        setClassOccurrences(classOccurrences);
        setProbabilities(probabilities);
    }

    public Token (Token token)
    {
        this(token.getClassOccurrences(), token.getProbabilities());
    }

    public int[] getClassOccurrences() {
        int[] tmpClassOccurrences = new int[classOccurrences.length];

        for (int i = 0; i < classOccurrences.length; ++i)
        {
            tmpClassOccurrences[i] = classOccurrences[i];
        }

        return tmpClassOccurrences;
    }

    public void setClassOccurrences(int[] classOccurrences) {
        if (classOccurrences != null)
        {
            this.classOccurrences = new int[classOccurrences.length];

            for (int i = 0; i < classOccurrences.length; ++i)
            {
                this.classOccurrences[i] = classOccurrences[i];
            }
        }
        else
        {
            Categories numCategories = Categories.NUMBER_OF_CATEGORIES;
            this.classOccurrences = new int[numCategories.getCategoryValue()];

            for (int i = 0; i < this.classOccurrences.length; ++i)
            {
                this.classOccurrences[i] = 0;
            }
        }
    }

    public double[] getProbabilities() {
        double[] tmpProbabilities = new double[probabilities.length];

        for (int i = 0; i < probabilities.length; ++i)
        {
            tmpProbabilities[i] = probabilities[i];
        }

        return tmpProbabilities;
    }

    public void setProbabilities(double[] probabilities) {
        if (probabilities != null)
        {
            this.probabilities = new double[probabilities.length];

            for (int i = 0; i < probabilities.length; ++i)
            {
                this.probabilities[i] = probabilities[i];
            }
        }
        else
        {
            Categories numCategories = Categories.NUMBER_OF_CATEGORIES;
            this.probabilities = new double[numCategories.getCategoryValue()];

            for (int i = 0; i < this.probabilities.length; ++i)
            {
                this.probabilities[i] = 0.0;
            }
        }
    }

    public int getOccurencesForCategory(Categories category)
    {
        return this.classOccurrences[category.getCategoryValue()];
    }

    public double getProbabilityForCategory(Categories category)
    {
        return this.probabilities[category.getCategoryValue()];
    }

    public void setOccurencesForCategory(int occurences, Categories category)
    {
        this.classOccurrences[category.getCategoryValue()] = occurences;
    }

    public void setProbabilityForCategory(double probability, Categories category)
    {
        this.probabilities[category.getCategoryValue()] = probability;
    }
}
