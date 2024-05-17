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
     *
     * @param orientationService Le service d'orientation
     */
    public AventurierService(OrientationService orientationService) {
        this.orientationService = orientationService;
    }

    /**
     * Fait tourner l'aventurier
     *
     * @param aventurier L'aventurier à tourner
     * @param direction  La direction dans laquelle tourner l'aventurier
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
     *
     * @param aventurier L'aventurier à avancer
     * @param carte      La carte sur laquelle avancer
     */
    public void avancer(Aventurier aventurier, Carte carte) {
        int newX = aventurier.getX() + aventurier.getOrientation().dx;
        int newY = aventurier.getY() + aventurier.getOrientation().dy;

        if (estSurLaCarte(newX, newY, carte) && !estMontagne(newX, newY, carte)) {
            Point nouvellePosition = new Point(newX, newY);

            // Gère les trésors
            gererTresor(nouvellePosition, carte);

            // Remplace la position précédente de l'aventurier par "." si aucun autre aventurier ne s'y trouve
            remplacerAnciennePlace(aventurier, carte);

            // Met à jour la position de l'aventurier
            mettreAJourPlace(aventurier, newX, newY, carte);
        }
    }

    /**
     * Exécute la séquence d'actions de l'aventurier
     *
     * @param aventurier L'aventurier à exécuter la séquence
     * @param carte      La carte sur laquelle exécuter la séquence
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


    private boolean estSurLaCarte(int x, int y, Carte carte) {
        return x >= 0 && x < carte.getLargeur() && y >= 0 && y < carte.getHauteur();
    }

    private boolean estMontagne(int x, int y, Carte carte) {
        return carte.getCarte()[y][x].equals("M");
    }

    private void gererTresor(Point position, Carte carte) {
        if (carte.getTresors().containsKey(position) && carte.getTresors().get(position) > 0) {
            int nbTresors = carte.getTresors().get(position) - 1;
            carte.getTresors().put(position, nbTresors);
            if (nbTresors == 0) {
                carte.getTresors().remove(position);
            }
        }
    }

    private void remplacerAnciennePlace(Aventurier aventurier, Carte carte) {
        boolean autreAventurier = carte.getAventuriers().stream()
                .anyMatch(autre -> autre != aventurier && autre.getX() == aventurier.getX() && autre.getY() == aventurier.getY());

        if (!autreAventurier) {
            carte.getCarte()[aventurier.getY()][aventurier.getX()] = ".";
        }
    }

    private void mettreAJourPlace(Aventurier aventurier, int newX, int newY, Carte carte) {
        carte.getCarte()[newY][newX] = "A";
        aventurier.setX(newX);
        aventurier.setY(newY);
    }
}
