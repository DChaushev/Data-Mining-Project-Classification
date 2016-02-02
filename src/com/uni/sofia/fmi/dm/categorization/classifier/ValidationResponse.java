package com.uni.sofia.fmi.dm.categorization.classifier;

/**
 *
 * @author Dimitar
 */
public class ValidationResponse {

    public double guessedPercentage;
    public double precision;
    public double recall;
    public double accuracy;
    public double fMeasure;

    public ValidationResponse(double guessedPercentage, double precision, double recall, double accuracy, double fMeasure) {
        this.guessedPercentage = guessedPercentage;
        this.precision = precision;
        this.recall = recall;
        this.accuracy = accuracy;
        this.fMeasure = fMeasure;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Guessed percentage: ").append(guessedPercentage).append(System.lineSeparator());
        result.append("Precision: ").append(precision).append(System.lineSeparator());
        result.append("Recall: ").append(recall).append(System.lineSeparator());
        result.append("Accuracy: ").append(accuracy).append(System.lineSeparator());
        result.append("F-measure: ").append(fMeasure).append(System.lineSeparator());

        return result.toString();
    }

}
