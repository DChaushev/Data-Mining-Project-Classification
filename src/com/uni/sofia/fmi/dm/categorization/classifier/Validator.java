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

    public Validator(Classifier classifier) {
        this.classifier = classifier;
    }

    public ValidationResponse validate(JsonFileEntity testData) {
        List<ReviewEntity> reviews = testData.getReviews();
        int guessedReviews = 0;

        int truePositives = 0;
        int falseNegative = 0;

        int falsePositive = 0;
        int trueNegative = 0;

        for (ReviewEntity review : reviews) {
            Categories category = classifier.classify(review.getText());
            Categories originalCategory = review.getCategory();

            if (originalCategory.equals(category)) {
                guessedReviews++;
            }

            if (category.equals(Categories.POSITIVE)) {
                if (originalCategory.equals(Categories.POSITIVE)) {
                    truePositives++;
                } else {
                    falsePositive++;
                }
            } else {
                if (originalCategory.equals(Categories.POSITIVE)) {
                    falseNegative++;
                } else {
                    trueNegative++;
                }
            }

        }

        // TODO: recheck theese formulaes
        double precision = (double) truePositives / (truePositives + falsePositive) * 100;
        double recall = (double) truePositives / (truePositives + falseNegative) * 100;
        double accuracy = (double) (truePositives + trueNegative) / (truePositives + trueNegative + falsePositive + falseNegative) * 100;
        double fMeasure = (double) precision * recall / (precision + recall) * 100;
        double guessedPercentage = (double) guessedReviews / reviews.size() * 100;

        ValidationResponse response = new ValidationResponse(guessedPercentage, precision, recall, accuracy, fMeasure);

        return response;
    }

}
