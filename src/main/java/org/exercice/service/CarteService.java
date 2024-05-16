package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Point;

public class CarteService {

    /**
     * Ajoute une montagne à la carte
     * @param carte La carte sur laquelle ajouter la montagne
     * @param x La position x de la montagne
     * @param y La position y de la montagne
     */
    public void ajouterMontagne(Carte carte, int x, int y) {
        carte.getCarte()[y][x] = "M";
    }

    /**
     * Ajoute un trésor à la carte
     * @param carte La carte sur laquelle ajouter le trésor
     * @param x La position x du trésor
     * @param y La position y du trésor
     * @param nbTresors Le nombre de trésors
     */
    public void ajouterTresor(Carte carte, int x, int y, int nbTresors) {
        carte.getCarte()[y][x] = "T(" + nbTresors + ")";
        carte.getTresors().put(new Point(x, y), nbTresors);
    }

    /**
     * Ajoute un aventurier à la carte
     * @param carte La carte sur laquelle ajouter l'aventurier
     * @param aventurier L'aventurier à ajouter
     */
    public void ajouterAventurier(Carte carte, Aventurier aventurier) {
        carte.getAventuriers().add(aventurier);
        carte.getCarte()[aventurier.getY()][aventurier.getX()] = "A";
    }

    /**
     * Affiche la carte
     * @param carte La carte à afficher
     */
    public void afficherCarte(Carte carte) {
        for (String[] ligne : carte.getCarte()) {
            System.out.println(String.join(" ", ligne));
        }
        System.out.println();
    }

    /**
     * Récupère un aventurier par son nom
     * @param carte La carte sur laquelle chercher l'aventurier
     * @param nomAventurier Le nom de l'aventurier
     * @return L'aventurier ou null s'il n'existe pas
     */
    public Aventurier getAventurier(Carte carte, String nomAventurier) {
        for (Aventurier aventurier : carte.getAventuriers()) {
            if (aventurier.getNom().equals(nomAventurier)) {
                return aventurier;
            }
        }
        return null;
    }
}
