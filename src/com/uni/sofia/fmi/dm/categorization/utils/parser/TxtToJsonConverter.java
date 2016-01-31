package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Created by vankata on 24.01.16.
 */
public class TxtToJsonConverter {

    public static void convertTxtToJson(String url, String outputFileName, int numberOfEntries) {
        convertTxtToJson(new File(url), new File(outputFileName), numberOfEntries);
    }

    /**
     * Given a *.txt file and number of entries it dumps numberOfEntries data
     * into a data.json file
     *
     * @param file the txt file
     * @param numberOfEntries - number of entries that are needed
     * @param outputFile
     */
    public static void convertTxtToJson(File file, File outputFile, int numberOfEntries) {
        int positiveReviews = 0;
        int negativeReviews = 0;
        List<ReviewEntity> reviews = new ArrayList<>();

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {
            String line;

            int numberOfEntriesParsed = 0;
            int prefixTextLength = ParsingConstants.REVIEW_TEXT.getStringValue().length();
            int prefixScoreLength = ParsingConstants.REVIEW_SCORE.getStringValue().length();

            double score = 0.0;

            Categories currentCategory = Categories.NEGATIVE;

            boolean searchPositiveOnly = false;
            boolean searchNegativeOnly = false;
            boolean skipText = false;

            // if some of the categories get 60% of what we want to convert stop handling that category and
            // handle only the other categories
            int maxReviewsForCategory = (int)(0.6d * numberOfEntries);

            while (input.hasNextLine()) {

                if (numberOfEntriesParsed >= numberOfEntries) {
                    break;
                }

                if (positiveReviews >= maxReviewsForCategory)
                {
                    searchNegativeOnly = true;
                }
                else if (negativeReviews >= numberOfEntries)
                {
                    searchPositiveOnly = true;
                }

                line = input.nextLine();

                if (line.startsWith(ParsingConstants.REVIEW_SCORE.getStringValue())) {
                    String scoreString = line.substring(prefixScoreLength);

                    score = Double.parseDouble(scoreString);

                    if (score < 3.0) {
                        if (!searchPositiveOnly)
                        {
                            currentCategory = Categories.NEGATIVE;
                            negativeReviews++;
                            skipText = false;
                        }
                        else
                        {
                            skipText = true;
                        }
                    } else if (score > 3.0) {
                        if (!searchNegativeOnly)
                        {
                            currentCategory = Categories.POSITIVE;
                            positiveReviews++;
                            skipText = false;
                        }
                        else
                        {
                            skipText = true;
                        }
                    }
                    else
                    {
                        skipText = true;
                    }

                } else if (line.startsWith(ParsingConstants.REVIEW_TEXT.getStringValue()) && !skipText) {

                    String text = line.substring(prefixTextLength);

                    reviews.add(new ReviewEntity(currentCategory, text));

                    numberOfEntriesParsed++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TxtToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        dumpIntoJson(new JsonFileEntity(negativeReviews, positiveReviews, reviews), outputFile);
    }

    public static void dumpIntoJson(Object contents, File outputFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(outputFile, contents);
        } catch (IOException ex) {
            Logger.getLogger(TxtToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
