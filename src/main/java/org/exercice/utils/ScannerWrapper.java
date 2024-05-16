package org.exercice.utils;

import java.util.Scanner;

/**
 * Classe enveloppant un scanner
 */
public class ScannerWrapper implements IScanner {

    private final Scanner scanner;

    public ScannerWrapper(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
