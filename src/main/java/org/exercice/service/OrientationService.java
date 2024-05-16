package org.exercice.service;

import org.exercice.entite.Orientation;

public class OrientationService {

    /**
     * Retourne l'orientation à gauche de l'orientation actuelle
     * @param orientation L'orientation actuelle
     * @return L'orientation à gauche
     */
    public Orientation gauche(Orientation orientation) {
        return Orientation.values()[(orientation.ordinal() + 3) % 4];
    }

    /**
     * Retourne l'orientation à droite de l'orientation actuelle
     * @param orientation L'orientation actuelle
     * @return L'orientation à droite
     */
    public Orientation droite(Orientation orientation) {
        return Orientation.values()[(orientation.ordinal() + 1) % 4];
    }
}
