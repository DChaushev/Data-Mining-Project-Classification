package com.uni.sofia.fmi.dm.categorization.utils.console;

import java.util.Scanner;

/**
 * Created by vankata on 31.01.16.
 */
public class ConsoleApp {
    
    private final ChoiceHandler choiceHandler;
    private final Scanner input;
    
    private static final String OPTION_DISPLAY_FORMAT = "%d. %s";
    
    private static final String[] options = new String[]{
        "Classify text",
        "Probabilities for last classified text",
        "Size of vocabulary",
        "Run validation",
        "Show precision",
        "Exit"
    };
    
    public ConsoleApp(ChoiceHandler handler) {
        this.choiceHandler = handler;
        input = new Scanner(System.in);
    }
    
    public void startApp() {
        while (true) {
            printOptions();
            int choice = getChoice();
            choiceHandler.handleChoice(choice);
        }
    }
    
    private int getChoice() {
        String nextLine = input.nextLine();
        
        try {
            int parseInt = Integer.parseInt(nextLine);
            return parseInt;
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    private void printOptions() {
        System.out.println("-----------------------------");
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format(OPTION_DISPLAY_FORMAT, i + 1, options[i]));
        }
        System.out.print("Choose an option: ");
    }
    
}
