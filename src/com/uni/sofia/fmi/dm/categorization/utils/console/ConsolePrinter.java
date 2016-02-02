package com.uni.sofia.fmi.dm.categorization.utils.console;

/**
 *
 * @author Dimitar
 */
public class ConsolePrinter implements Printer {

    @Override
    public void displayMessage(Object message) {
        System.out.println(message);
    }

    @Override
    public void displayMessagePrefix(Object message) {
        System.out.print(message);
    }

}
