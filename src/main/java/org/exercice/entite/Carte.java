package org.exercice.entite;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant une carte
 */
@Getter
@Setter
public class Carte {

    private int largeur;
    private int hauteur;
    private String[][] carte;
    private Map<Point, Integer> tresors = new HashMap<>();
    private List<Aventurier> aventuriers = new ArrayList<>();

    /**
     * Constructeur de la carte
     *
     * @param largeur La largeur de la carte
     * @param hauteur La hauteur de la carte
     */
    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        initCarte();
    }

    /**
     * Initialise la carte avec des points vides
     */
    private void initCarte() {
        this.carte = new String[this.hauteur][this.largeur];
        for (int y = 0; y < this.hauteur; y++) {
            for (int x = 0; x < this.largeur; x++) {
                this.carte[y][x] = ".";
            }
        }
    }
}