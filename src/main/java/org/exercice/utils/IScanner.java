package org.exercice.utils;

/**
 * Interface pour envelopper un scanner pour le mock
 */
public interface IScanner {
    boolean hasNextLine();

    String nextLine();
}
