package com.uni.sofia.fmi.dm.categorization.classifier;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.Token;
import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dimitar
 */
public class Classifier {

    private TokensInformationHolder tih;

    public Classifier(TokensInformationHolder tih) {
        this.tih = tih;
        calculateProbabilities();
    }

    public Categories classify() {
        return null;
    }

    private void calculateProbabilities()
    {
        Map<String, Token> tokens = tih.getTokens();

        int sizeOfVocabulary = tokens.size();

        for (Map.Entry<String, Token> entry : tokens.entrySet())
        {
            for(Categories category : Categories.values())
            {
                calculateProbability(entry.getValue(), category, sizeOfVocabulary);
            }
        }
    }

    private void calculateProbability(Token token, Categories category, int sizeOfVocabulary)
    {
        int numberOfWordsForCategory = tih.getNumberOfTokensForCategory(category);

        double probability = (token.getOccurencesForCategory(category) + 1) / (numberOfWordsForCategory + sizeOfVocabulary);

        token.setProbabilityForCategory(probability, category);
    }
}
