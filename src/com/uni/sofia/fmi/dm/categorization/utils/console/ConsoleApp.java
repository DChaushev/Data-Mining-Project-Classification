package com.uni.sofia.fmi.dm.categorization.utils.console;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by vankata on 31.01.16.
 */
public class ConsoleApp {
    private ChoiceHandler choiceHandler;
    private Scanner input;

    private String choicesString = String.format("1. %s\n2. %s\n3. %s\n4. %s\n", "Classify text", "Probabilities for last classified text", "Size of vocabulary", "exit");

    public ConsoleApp(ChoiceHandler handler)
    {
        this.choiceHandler = handler;
        input = new Scanner(System.in);
    }

    public void startApp()
    {
        while (true)
        {
            System.out.print(choicesString);

            int choice = getChoice();

            choiceHandler.handleChoice(choice);
        }
    }

    private int getChoice()
    {
        return input.nextInt();
    }

}

