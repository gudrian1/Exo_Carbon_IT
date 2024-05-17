package org.exercice.entite;

import java.util.Objects;

/**
 * Classe représentant un point
 */
public class Point {

    int x;
    int y;

    /**
     * Constructeur du point
     *
     * @param x La position x du point
     * @param y La position y du point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //============ Méthode surchargé ==============

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
