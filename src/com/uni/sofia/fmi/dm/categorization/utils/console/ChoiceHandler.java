package com.uni.sofia.fmi.dm.categorization.utils.console;

import com.uni.sofia.fmi.dm.categorization.classifier.Classifier;
import com.uni.sofia.fmi.dm.categorization.classifier.ValidationResponse;
import com.uni.sofia.fmi.dm.categorization.classifier.Validator;
import com.uni.sofia.fmi.dm.categorization.utils.TokensHolder;
import com.uni.sofia.fmi.dm.categorization.utils.parser.JsonFileEntity;
import com.uni.sofia.fmi.dm.categorization.utils.parser.ObjectMapperWrapper;
import com.uni.sofia.fmi.dm.categorization.utils.parser.wordParser.WordParser;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vankata on 31.01.16.
 */
public class ChoiceHandler {

    private static final String TEST_DATA_JSON = "stanfordDataTest.json";

    private final TokensHolder tokensHolder;
    private final Classifier classifier;
    private String lastClassifiedText;
    private final Scanner input;
    private final WordParser wordParser;

    private final JsonFileEntity testingSet;
    private final Validator validator;

    public ChoiceHandler(TokensHolder tokensHolder, Classifier classifier) throws IOException {
        this.tokensHolder = tokensHolder;
        this.classifier = classifier;
        this.lastClassifiedText = null;
        this.input = new Scanner(System.in);
        this.wordParser = new WordParser();
        this.testingSet = (JsonFileEntity) ObjectMapperWrapper.readFile(new File(TEST_DATA_JSON), JsonFileEntity.class);
        this.validator = new Validator(classifier);
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
                handleRunValidation();
                break;
            }
            case 5: {
                handleShowPrecision();
                break;
            }
            case 6: {
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

    private void handleShowPrecision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleRunValidation() {
        ValidationResponse response = validator.validate(testingSet);
        System.out.println(response);
    }

}
