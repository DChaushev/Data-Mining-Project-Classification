package com.uni.sofia.fmi.dm.categorization.utils.console;

import com.uni.sofia.fmi.dm.categorization.classifier.Classifier;
import com.uni.sofia.fmi.dm.categorization.utils.TokensInformationHolder;
import com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser.WordParser;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Created by vankata on 31.01.16.
 */
public class ChoiceHandler {

    private TokensInformationHolder tokensHolder;
    private Classifier classifier;
    private String lastClassifiedText;
    private Scanner input;
    private WordParser wordParser;

    public ChoiceHandler(TokensInformationHolder tokensHolder, Classifier classifier) {
        this.tokensHolder = tokensHolder;
        this.classifier = classifier;
        this.lastClassifiedText = null;
        this.input = new Scanner(System.in);
        this.wordParser = new WordParser();
    }

    public void handleChoice(int option)
    {
        switch (option)
        {
            case 1:
            {
                handleClassifyText();
                break;
            }
            case 2:
            {
                handleProbabilitiesForLastText();
                break;
            }
            case 3:
            {
                handleSizeOfVocabulary();
                break;
            }
            case 4:
            {
                handleExit();
                break;
            }
            default:
            {
                System.out.println("No such option!!!");
            }
        }
    }

    private void handleClassifyText()
    {
        System.out.println("Enter text to be classified:");

        String textToClassify;

        while (true)
        {
            textToClassify = input.nextLine();

            if (textToClassify.isEmpty() || ( textToClassify.compareToIgnoreCase(" ") == 0) )
            {
                System.out.println("Not valid input!!!");
                System.out.println("Enter text to be classified:");
            }
            else
            {
                break;
            }
        }

        System.out.println(classifier.classify(textToClassify));

        lastClassifiedText = textToClassify;
    }

    private void handleExit()
    {
        System.exit(0);
    }

    private void handleProbabilitiesForLastText()
    {
        System.out.println("Word\t[Positive probability, Negative probability]");

        Matcher matcher = wordParser.getMatcherForString(lastClassifiedText);

        while (matcher.find())
        {
            String token = matcher.group();
            double[] probabilities = tokensHolder.getProbabilitiesForToken(token);

            if (probabilities == null)
            {
               System.out.println(String.format("%s\t[%f, %f]", token, Double.MIN_VALUE, Double.MIN_VALUE));
            }
            else
            {
                System.out.println(String.format("%s\t%s", token, Arrays.toString(probabilities)));
            }
        }
    }

    private void handleSizeOfVocabulary()
    {
        System.out.println(tokensHolder.getTokens().size());
    }

}
