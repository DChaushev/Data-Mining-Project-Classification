package com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vankata on 30.01.16.
 */
public class WordParser {

    private Pattern wordPattern;

    public WordParser()
    {
        // I think this removes some noise in the data.
        // The - and ' are for words in the case "ala-bala" and "it's"
        // TODO see if this is a good regular expression
        this("(\\w+[-']\\w+)|(\\w+)");
    }

    public WordParser(String regex)
    {
        wordPattern = Pattern.compile(regex);
    }

    public Matcher getMatcherForString(String text)
    {
        return wordPattern.matcher(text);
    }

}
