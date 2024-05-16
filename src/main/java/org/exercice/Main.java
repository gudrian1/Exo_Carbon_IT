package org.exercice;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.service.AventurierService;
import org.exercice.service.CarteService;
import org.exercice.service.EntreeService;
import org.exercice.service.OrientationService;
import org.exercice.utils.ScannerWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var carteService = new CarteService();
        var entreeService = new EntreeService(carteService);
        var orientationService = new OrientationService();
        var aventurierService = new AventurierService(orientationService);
        // Définition du nom du fichier à lire
        String nomFichier = "src/main/resources/entree.txt";
        // Appel de la méthode pour lire le fichier d'entrée et construire la carte
        Scanner scanner;
        try {
            scanner = new Scanner(new File(nomFichier));
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier " + nomFichier + " n'a pas été trouvé.");
            return;
        }
        // Appel de la méthode pour lire le fichier d'entrée et construire la carte
        ScannerWrapper scannerWrapper = new ScannerWrapper(scanner);
        Optional<Carte> carteOpt = entreeService.lireFichierEntree(nomFichier, scannerWrapper);
        // Vérification de la présence de la carte dans l'Optional
        if (carteOpt.isPresent()) {
            // Récupération de la carte à partir de l'Optional
            Carte carte = carteOpt.get();
            // Affichage de la carte initiale
            carteService.afficherCarte(carte);

            // Parcours de tous les aventuriers sur la carte
            for (Aventurier aventurier : carte.getAventuriers()) {
                // Exécution de la séquence d'action de l'aventurier
                aventurierService.executerSequence(aventurier, carte);
                // Affichage de la carte après l'exécution de la séquence de l'aventurier
                carteService.afficherCarte(carte);
            }
        }
    }
}