package com.uni.sofia.fmi.dm.categorization.classifier;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import com.uni.sofia.fmi.dm.categorization.utils.parser.JsonFileEntity;
import com.uni.sofia.fmi.dm.categorization.utils.parser.ReviewEntity;
import java.util.List;

/**
 *
 * @author Dimitar
 */
public class Validator {

    private final Classifier classifier;
    private final JsonFileEntity testData;

    public Validator(Classifier classifier, JsonFileEntity testData) {
        this.classifier = classifier;
        this.testData = testData;
    }

    public void validate() {
        List<ReviewEntity> reviews = testData.getReviews();
        int guessedReviews = 0;

        for (ReviewEntity review : reviews) {
            Categories category = classifier.classify(review.getText());
            if (review.getCategory().equals(category)) {
                guessedReviews++;
            }
        }

        System.out.println((double) guessedReviews / reviews.size() * 100 + "%");
    }

}
