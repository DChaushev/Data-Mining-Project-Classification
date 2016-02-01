package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.Token;
import com.uni.sofia.fmi.dm.categorization.utils.TokensHolder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser.WordParser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dimitar
 */
public class JsonInformationParser implements InformationParser {

    @Override
    public TokensHolder parse(String url) {
        return this.parse(new File(url));
    }

    @Override
    public TokensHolder parse(File file) {

        try {
            JsonFileEntity jsonFileEntity = (JsonFileEntity) ObjectMapperWrapper.readFile(file, JsonFileEntity.class);
            Map<String, Token> tokens = new HashMap<>();

            List<ReviewEntity> reviews = jsonFileEntity.getReviews();

            WordParser wordParser = new WordParser();

            for (ReviewEntity review : reviews) {
                String text = review.getText();
                Categories reviewCategory = review.getCategory();

                Matcher matcher = wordParser.getMatcherForString(text);

                while (matcher.find()) {
                    String token = matcher.group();
                    token = token.toLowerCase();
                    handleToken(token, tokens, reviewCategory.getCategoryValue());
                }
            }

            return new TokensHolder(tokens, new int[]{jsonFileEntity.getPositiveReviews(), jsonFileEntity.getNegativeReviews()}, reviews.size());

        } catch (IOException ex) {
            Logger.getLogger(JsonInformationParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void handleToken(String token, Map<String, Token> tokens, int categoryValue) {
        if (!tokens.containsKey(token)) {
            tokens.put(token, new Token(Categories.values().length));
        }

        tokens.get(token).incrementOccurencesForCategory(categoryValue);
    }

}
