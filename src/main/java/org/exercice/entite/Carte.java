package org.exercice.entite;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant une carte
 */
@Getter
@Setter
public class Carte {

    public int largeur;
    public int hauteur;
    public String[][] carte;
    public Map<Point, Integer> tresors;
    public ArrayList<Aventurier> aventuriers;

    /**
     * Constructeur de la carte
     * @param largeur La largeur de la carte
     * @param hauteur La hauteur de la carte
     */
    public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        carte = new String[hauteur][largeur];
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                carte[y][x] = ".";
            }
        }
        tresors = new HashMap<>();
        aventuriers = new ArrayList<>();
    }
}
