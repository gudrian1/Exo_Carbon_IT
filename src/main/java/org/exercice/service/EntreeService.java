package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Orientation;
import org.exercice.utils.IScanner;

import java.util.Optional;

/**
 * Service de lecture d'un fichier d'entrée
 */
public class EntreeService {

    private final CarteService carteService;

    /**
     * Constructeur
     *
     * @param carteService Le service de gestion de la carte
     */
    public EntreeService(CarteService carteService) {
        this.carteService = carteService;
    }

    /**
     * Lit un fichier d'entrée et crée une carte à partir de son contenu
     *
     * @param nomFichier Le nom du fichier à lire
     * @return La carte créée à partir du fichier
     */
    public Optional<Carte> lireFichierEntree(String nomFichier, IScanner scanner) {
        // Déclaration de la carte
        Carte carte = null;

        try {
            // Parcours de chaque ligne du fichier

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine(); // Lecture de la ligne suivante
                // Si la ligne commence par "#" (commentaire), la sauter
                if (ligne.startsWith("#")) {
                    continue;
                }
                // Séparation des éléments de la ligne
                String[] elements = ligne.trim().split(" - ");

                // Traitement en fonction du premier élément de la ligne
                switch (elements[0]) {

                    case "C": // Création d'une nouvelle carte
                        carte = new Carte(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                        break;

                    case "M": // Ajout d'une montagne à la carte
                        if (carte != null) {
                            carteService.ajouterMontagne(carte, Integer.parseInt(elements[1]), Integer.parseInt(elements[2]));
                        }
                        break;

                    case "T": // Ajout d'un trésor à la carte
                        if (carte != null) {
                            carteService.ajouterTresor(carte, Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                        }
                        break;

                    case "A": // Ajout d'un aventurier à la carte
                        if (carte != null) {
                            Aventurier aventurier = new Aventurier(elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), Orientation.valueOf(elements[4]), elements[5]);
                            carteService.ajouterAventurier(carte, aventurier);
                        }
                        break;
                }
            }

        } catch (NumberFormatException e) {
            throw e;// Si une conversion de nombre échoue
        } catch (Exception e) { // Si une autre erreur survient
            System.err.printf("Une erreur inattendue s'est produite lors de la lecture du fichier %s.%n", nomFichier);
        }
        // Retourne la carte construite, encapsulée dans un Optional
        return Optional.ofNullable(carte);
    }
}
