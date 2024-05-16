package org.exercice.entite;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant un aventurier
 */
@Getter
@Setter
public class Aventurier {

    private String nom;
    private int x;
    private int y;
    private Orientation orientation;
    private String sequence;

    /**
     * Constructeur de l'aventurier
     * @param nom Le nom de l'aventurier
     * @param x La position x de l'aventurier
     * @param y La position y de l'aventurier
     * @param orientation L'orientation de l'aventurier
     * @param sequence La séquence d'actions de l'aventurier
     */
    public Aventurier(String nom, int x, int y, Orientation orientation, String sequence) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.sequence = sequence;
    }
}
