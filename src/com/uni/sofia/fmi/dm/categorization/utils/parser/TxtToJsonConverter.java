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
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {
            String line;

            int numberOfEntriesParsed = 0;

            int prefixTextLength = ParsingConstants.REVIEW_TEXT.getValue().length();

            int prefixScoreLength = ParsingConstants.REVIEW_SCORE.getValue().length();

            double score = 0.0;

            Categories currentCategory = Categories.NEGATIVE;

            while (input.hasNextLine()) {
                line = input.nextLine();

                if (line.startsWith(ParsingConstants.REVIEW_SCORE.getValue())) {
                    String scoreString = line.substring(prefixScoreLength);

                    score = Double.parseDouble(scoreString);

                    if (score < 3.0) {
                        currentCategory = Categories.NEGATIVE;
                    } else if (score > 3.0) {
                        currentCategory = Categories.POSITIVE;
                    }

                } else if (line.startsWith(ParsingConstants.REVIEW_TEXT.getValue()) && (score != 3.0)) {
                    if (numberOfEntriesParsed <= numberOfEntries) {
                        String text = line.substring(prefixTextLength);

                        jsonObject.put(ParsingConstants.CATEGORY.getValue(), currentCategory.toString());
                        jsonObject.put(ParsingConstants.TEXT.getValue(), text);

                        jsonArray.add(new JSONObject(jsonObject));

                        ++numberOfEntriesParsed;
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {

        }

        dumpIntoJson(jsonArray, outputFile);
    }

    public static void dumpIntoJson(JSONArray contents, File outputFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(outputFile, contents);
        } catch (IOException ex) {
            Logger.getLogger(TxtToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
