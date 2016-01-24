package com.uni.sofia.fmi.dm.categorization.utils.parser;

import com.uni.sofia.fmi.dm.categorization.utils.Categories;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by vankata on 24.01.16.
 */
public class TxtToJsonConverter {

    public static void convertTxtToJson(String url, int numberOfEntries)
    {
        convertTxtToJson(new File(url), numberOfEntries);
    }

    /**
     * Given a *.txt file and number of entries it dumps numberOfEntries data into a data.json file
     * @param file the txt file
     * @param numberOfEntries - number of entries that are needed
     */
    public static void convertTxtToJson(File file, int numberOfEntries)
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file))))
        {
            String line;

            int numberOfEntriesParsed = 0;

            int prefixTextLength = ParsingConstants.REVIEW_TEXT.getValue().length();

            int prefixScoreLength = ParsingConstants.REVIEW_SCORE.getValue().length();

            double score = 0.0;

            Categories currentCategory = Categories.NEGATIVE;

            while (input.hasNextLine())
            {
                line = input.nextLine();

                if (line.startsWith(ParsingConstants.REVIEW_SCORE.getValue()))
                {
                    String scoreString = line.substring(prefixScoreLength);

                    score = Double.parseDouble(scoreString);

                    if (score == 3.0)
                    {
                        continue;
                    }
                    else if (score < 3.0)
                    {
                        currentCategory = Categories.NEGATIVE;
                    }
                    else
                    {
                        currentCategory = Categories.POSITIVE;
                    }

                }
                else if (line.startsWith(ParsingConstants.REVIEW_TEXT.getValue()) && (score != 3.0 ))
                {
                    if (numberOfEntriesParsed <= numberOfEntries)
                    {
                        String text = line.substring(prefixTextLength);

                        jsonObject.put(ParsingConstants.CATEGORY.getValue(), currentCategory.toString());
                        jsonObject.put(ParsingConstants.TEXT.getValue(), text);

                        jsonArray.add( new JSONObject(jsonObject));

                        ++numberOfEntriesParsed;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }catch (IOException e)
        {

        }

        dumpIntoJson(jsonArray.toString());
    }

    public static void dumpIntoJson(String contents)
    {
        try(Formatter format = new Formatter(new BufferedWriter(new FileWriter(new File("data.json")))))
        {
            format.format("%s", contents);

        }   catch (IOException ex) {

        }
    }


}
