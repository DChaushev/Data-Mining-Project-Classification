package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
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
        // TODO: MITAK check this json shit is it ok?
        JSONArray reviews = new JSONArray();
        JSONObject review = new JSONObject();

        int positiveReviews = 0;
        int negativeReviews = 0;

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {
            String line;

            int numberOfEntriesParsed = 0;

            int prefixTextLength = ParsingConstants.REVIEW_TEXT.toString().length();

            int prefixScoreLength = ParsingConstants.REVIEW_SCORE.toString().length();

            double score = 0.0;

            Categories currentCategory = Categories.NEGATIVE;

            while (input.hasNextLine()) {
                line = input.nextLine();

                if (line.startsWith(ParsingConstants.REVIEW_SCORE.toString())) {
                    String scoreString = line.substring(prefixScoreLength);

                    score = Double.parseDouble(scoreString);

                    if (score < 3.0) {
                        currentCategory = Categories.NEGATIVE;
                        negativeReviews++;
                    } else if (score > 3.0) {
                        currentCategory = Categories.POSITIVE;
                        positiveReviews++;
                    }

                } else if (line.startsWith(ParsingConstants.REVIEW_TEXT.toString()) && (score != 3.0)) {
                    if (numberOfEntriesParsed < numberOfEntries) {
                        String text = line.substring(prefixTextLength);

                        review.put(ParsingConstants.CATEGORY.toString(), currentCategory.toString());
                        review.put(ParsingConstants.TEXT.toString(), text);

                        reviews.add(new JSONObject(review));

                        numberOfEntriesParsed++;
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TxtToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONObject result = new JSONObject();
        result.put(ParsingConstants.POSITIVE_REVIEWS, positiveReviews);
        result.put(ParsingConstants.NEGATIVE_REVIEWS, negativeReviews);
        result.put(ParsingConstants.REVIEWS, reviews);

        dumpIntoJson(result, outputFile);
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
