package com.uni.sofia.fmi.dm.categorization.classifier;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.Token;
import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;
import com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser.WordParser;

import java.util.Map;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.regex.Matcher;

/**
 *
 * @author Dimitar
 */
public class Classifier {

    private final TokensInformationHolder tokensInformationHolder;

    public Classifier(TokensInformationHolder tokensInformationHolder) {
        this.tokensInformationHolder = tokensInformationHolder;
        calculateProbabilities();
    }

    public Categories classify(String text) {
        WordParser wordParser = new WordParser();

        Matcher matcher = wordParser.getMatcherForString(text);

        double probabilities[] = new double[Categories.values().length];

        // lets initialize the probabilities array with the probability that a category can occur
        for (Categories category : Categories.values()) {
            probabilities[category.getCategoryValue()] = tokensInformationHolder.getProbabilityForCategory(category);
        }

        while (matcher.find()) {
            String token = matcher.group();

            for (Categories category : Categories.values()) {
                probabilities[category.getCategoryValue()] *= tokensInformationHolder.getProbabilityForToken(token, category);
            }
        }

        double currentMax = Double.MIN_VALUE;
        Categories classifiedCategory = Categories.POSITIVE;

        for (Categories category : Categories.values()) {
            if (currentMax < probabilities[category.getCategoryValue()]) {
                currentMax = probabilities[category.getCategoryValue()];
                classifiedCategory = category;
            }
        }

        return classifiedCategory;
    }

    private void calculateProbabilities() {
        Map<String, Token> tokens = tokensInformationHolder.getTokens();

        for (Token token : tokens.values()) {
            for (Categories category : Categories.values()) {
                calculateProbability(token, category, tokens.size());
            }
        }
    }

    private void calculateProbability(Token token, Categories category, int sizeOfVocabulary) {
        int numberOfWordsForCategory = tokensInformationHolder.getNumberOfTokensForCategory(category);
        double probability = (token.getOccurencesForCategory(category) + 1) / (double)(numberOfWordsForCategory + sizeOfVocabulary);

        token.setProbabilityForCategory(probability, category);
    }
}
