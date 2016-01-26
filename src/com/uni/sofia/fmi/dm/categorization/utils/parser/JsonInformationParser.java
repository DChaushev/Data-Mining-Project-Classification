package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Dimitar
 */
public class JsonInformationParser implements InformationParser {

    @Override
    public TokensInformationHolder parse(String url) {
        return this.parse(new File(url));
    }

    @Override
    public TokensInformationHolder parse(File file) {
        ObjectMapper mapper = new ObjectMapper();
        TokensInformationHolder resultHolder = new TokensInformationHolder();

        try {
            JsonFileEntity jsonFileEntity = mapper.readValue(file, JsonFileEntity.class);

            resultHolder.setOccurencesForCategory(jsonFileEntity.getPositiveReviews(), Categories.POSITIVE);
            resultHolder.setOccurencesForCategory(jsonFileEntity.getNegativeReviews(), Categories.NEGATIVE);
            resultHolder.setNumberOfInstances(jsonFileEntity.getNegativeReviews() + jsonFileEntity.getPositiveReviews());

            List<ReviewEntity> reviews = jsonFileEntity.getReviews();
            for (ReviewEntity review : reviews) {
                String text = review.getText();
                String[] stringTokens = text.split(" ");

                for (String stringToken : stringTokens) {
                    if (stringToken.length() > 0) {
                        /*
                         TODO: check if token already exists (might need to add a method or two the into TokenInformationHolder class)
                         Add token. Increment needed counters. etc
                         */
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(JsonInformationParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
