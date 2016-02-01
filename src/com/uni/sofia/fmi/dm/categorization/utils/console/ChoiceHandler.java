package com.uni.sofia.fmi.dm.categorization.utils.console;

import com.uni.sofia.fmi.dm.categorization.classifier.Classifier;
import com.uni.sofia.fmi.dm.categorization.utils.TokensHolder;
import com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser.WordParser;

import java.util.Scanner;

/**
 * Created by vankata on 31.01.16.
 */
public class ChoiceHandler {

    private TokensHolder tokensHolder;
    private Classifier classifier;
    private String lastClassifiedText;
    private Scanner input;
    private WordParser wordParser;

    public ChoiceHandler(TokensHolder tokensHolder, Classifier classifier) {
        this.tokensHolder = tokensHolder;
        this.classifier = classifier;
        this.lastClassifiedText = null;
        this.input = new Scanner(System.in);
        this.wordParser = new WordParser();
    }

    public void handleChoice(int option) {
        switch (option) {
            case 1: {
                handleClassifyText();
                break;
            }
            case 2: {
                handleProbabilitiesForLastText();
                break;
            }
            case 3: {
                handleSizeOfVocabulary();
                break;
            }
            case 4: {
                handleExit();
                break;
            }
            default: {
                System.out.println("No such option!!!");
            }
        }
    }

    private void handleClassifyText() {
        System.out.println("Enter text to be classified:");

        String textToClassify;

        while (true) {
            textToClassify = input.nextLine();

            if (textToClassify.isEmpty() || (textToClassify.compareToIgnoreCase(" ") == 0)) {
                System.out.println("Not valid input!!!");
                System.out.println("Enter text to be classified:");
            } else {
                break;
            }
        }

        System.out.println(classifier.classify(textToClassify));

        lastClassifiedText = textToClassify;
    }

    private void handleExit() {
        System.exit(0);
    }

    private void handleProbabilitiesForLastText() {
        if (lastClassifiedText == null) {
            System.out.println("There is nothing classified!");
            return;
        }

        classifier.classify(lastClassifiedText, true);
    }

    private void handleSizeOfVocabulary() {
        System.out.println(tokensHolder.getVocabularySize());
    }

}
