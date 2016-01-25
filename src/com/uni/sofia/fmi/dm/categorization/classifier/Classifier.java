package com.uni.sofia.fmi.dm.categorization.classifier;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.Token;
import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;

import java.util.Map;

/**
 *
 * @author Dimitar
 */
public class Classifier {

    private TokensInformationHolder tokensInformationHolder;

    public Classifier(TokensInformationHolder tokensInformationHolder) {
        this.tokensInformationHolder = tokensInformationHolder;
        calculateProbabilities();
    }

    public Categories classify() {
        return null;
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
        double probability = (token.getOccurencesForCategory(category) + 1) / (numberOfWordsForCategory + sizeOfVocabulary);

        token.setProbabilityForCategory(probability, category);
    }
}
