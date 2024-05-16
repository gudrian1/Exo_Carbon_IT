package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Point;

/**
 * Classe de service de l'aventurier
 */
public class AventurierService {

    private final OrientationService orientationService;

    /**
     * Constructeur
     * @param orientationService Le service d'orientation
     */
    public AventurierService(OrientationService orientationService) {
        this.orientationService = orientationService;
    }

    /**
     * Fait tourner l'aventurier
     * @param aventurier L'aventurier à tourner
     * @param direction La direction dans laquelle tourner l'aventurier
     */
    public void tourner(Aventurier aventurier, char direction) {
        if (direction == 'G') {
            aventurier.setOrientation(orientationService.gauche(aventurier.getOrientation()));
        } else if (direction == 'D') {
            aventurier.setOrientation(orientationService.droite(aventurier.getOrientation()));
        }
    }

    /**
     * Fait avancer l'aventurier
     * @param aventurier L'aventurier à avancer
     * @param carte La carte sur laquelle avancer
     */
    public void avancer(Aventurier aventurier, Carte carte) {
        int newX = aventurier.getX() + aventurier.getOrientation().dx;
        int newY = aventurier.getY() + aventurier.getOrientation().dy;
        if (newX >= 0 && newX < carte.largeur && newY >= 0 && newY < carte.hauteur && !carte.carte[newY][newX].equals("M")) {
            Point key = new Point(newX, newY);
            if (carte.tresors.containsKey(key) && carte.tresors.get(key) > 0) {
                int nbTresors = carte.tresors.get(key) - 1;
                carte.tresors.put(key, nbTresors);
                if (nbTresors == 0) {
                    carte.tresors.remove(key);
                }
            }
            // Vérifie si un autre aventurier se trouve à la position précédente
            boolean autreAventurier = false;
            for (Aventurier autre : carte.getAventuriers()) {
                if (autre != aventurier && autre.getX() == aventurier.getX() && autre.getY() == aventurier.getY()) {
                    autreAventurier = true;
                    break;
                }
            }
            // Si aucun autre aventurier ne se trouve à la position précédente, la remplacer par "."
            if (!autreAventurier) {
                carte.carte[aventurier.getY()][aventurier.getX()] = ".";
            }
            // Met à jour la position de l'aventurier sur la carte
            carte.carte[newY][newX] = "A";
            aventurier.setX(newX);
            aventurier.setY(newY);
        }
    }

    /**
     * Exécute la séquence d'actions de l'aventurier
     * @param aventurier L'aventurier à exécuter la séquence
     * @param carte La carte sur laquelle exécuter la séquence
     */
    public void executerSequence(Aventurier aventurier, Carte carte) {
        for (char action : aventurier.getSequence().toCharArray()) {
            if (action == 'A') {
                avancer(aventurier, carte);
            } else {
                tourner(aventurier, action);
            }
        }
    }
}
