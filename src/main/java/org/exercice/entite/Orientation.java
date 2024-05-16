package org.exercice.entite;

/**
 * Enumération des orientations
 */
public enum Orientation {
    N(0, -1),  E(1, 0), S(0, 1), O(-1, 0);

    public final int dx;
    public final int dy;

    /**
     * Constructeur de l'orientation
     * @param dx La variation de x
     * @param dy La variation de y
     */
    Orientation(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
