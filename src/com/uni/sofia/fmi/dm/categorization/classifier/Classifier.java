package com.uni.sofia.fmi.dm.categorization.classifier;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.Token;
import com.uni.sofia.fmi.dm.categorization.utils.TokensHolder;
import com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser.WordParser;

import java.util.regex.Matcher;

/**
 *
 * @author Dimitar
 */
public class Classifier {

    private TokensHolder tokensHolder;

    public Classifier(TokensHolder tokensHolder) {
        this.tokensHolder = tokensHolder;
    }

    public Categories classify(String text) {
        return classify(text, false);
    }

    public Categories classify(String text, boolean verbose) {
        Matcher matcher = new WordParser().getMatcherForString(text);

        int numberOfClasses = this.tokensHolder.getNumberOfClasses();
        double[] classProbabilities = new double[numberOfClasses];

        for (int i = 0; i < numberOfClasses; i++) {
            classProbabilities[i] = this.tokensHolder.getProbabilityForClass(i);
        }

        while (matcher.find()) {
            String tokenKey = matcher.group();
            tokenKey = tokenKey.toLowerCase();
            Token token = tokensHolder.getToken(tokenKey);

            if (token != null) {
                for (int i = 0; i < numberOfClasses; i++) {
                    if (verbose) {
                        System.out.print("P(" + tokenKey + " | " + i + ") = ");
                    }
                    classProbabilities[i] *= calculateProbability(token, i, verbose);
                }
            }
        }

        int index = 0;
        double currentMax = 0;
        for (int i = 0; i < classProbabilities.length; i++) {
            if (currentMax < classProbabilities[i]) {
                index = i;
                currentMax = classProbabilities[i];
            }
        }

        return Categories.values()[index];
    }

    public TokensHolder getTokensHolder() {
        return tokensHolder;
    }

    public void setTokensHolder(TokensHolder tokensHolder) {
        this.tokensHolder = tokensHolder;
    }

    private double calculateProbability(Token token, int clazz, boolean verbose) {
        int vocabularySize = tokensHolder.getVocabularySize();
        int numberOfTokensForClass = tokensHolder.getNumberOfInstancesPerClass(clazz);
        int numberOfTokenClassOccurances = token.getOccurencesForClass(clazz);

        if (verbose) {
            System.out.print(String.format("(%d + 1) / (%d + %d)", numberOfTokenClassOccurances, vocabularySize, numberOfTokensForClass));
            System.out.println(" = " + (double) (numberOfTokenClassOccurances + 1) / (vocabularySize + numberOfTokensForClass));
        }
        return (double) (numberOfTokenClassOccurances + 1) / (vocabularySize + numberOfTokensForClass);
    }
}
