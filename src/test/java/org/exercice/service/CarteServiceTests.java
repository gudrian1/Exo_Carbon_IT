package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Orientation;
import org.exercice.entite.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests pour la classe Carte
 */
class CarteServiceTests {

    private CarteService carteService;

    @BeforeEach
    public void setUp() {
        carteService = new CarteService();
    }

    @Test
    void whenAjouterAventurier_shouldAdd() {
        Carte carte = new Carte(5, 5);
        Aventurier aventurier = new Aventurier("Indiana Jones", 2, 2, Orientation.N, "AADADAGGA");
        carteService.ajouterAventurier(carte, aventurier);

        assertEquals(aventurier, carteService.getAventurier(carte, "Indiana Jones"));
        assertNull(carteService.getAventurier(carte, "Toto"));
    }

    @Test
    void whenAjouterMontagne_shouldAdd() {
        Carte carte = new Carte(5, 5);
        carteService.ajouterMontagne(carte, 1, 1);
        assertEquals("M", carte.getCarte()[1][1]);
    }

    @Test
    void whenAjouterTresor_shouldAdd() {
        Carte carte = new Carte(5, 5);
        carteService.ajouterTresor(carte, 2, 2, 3);
        Map<Point, Integer> tresor = carte.getTresors();
        assertEquals("T(3)", carte.getCarte()[2][2]);
        assertEquals(3, tresor.get(new Point(2, 2)));
    }

    @Test
    void whenAfficherCarte_shouldAffiche() {
        Carte carte = new Carte(3, 3);
        carteService.ajouterMontagne(carte, 1, 1);
        carteService.ajouterTresor(carte, 2, 2, 3);
        carteService.ajouterAventurier(carte, new Aventurier("Lara", 0, 0, Orientation.N, "AADG"));

        // Créer un flux de sortie pour capturer la sortie de la console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        carteService.afficherCarte(carte);

        String expectedOutput = "A . .\n. M .\n. . T(3)\n\n";
        // Vérifier que la sortie n'est pas vide
        assertFalse(outContent.toString().isEmpty());
    }
}
