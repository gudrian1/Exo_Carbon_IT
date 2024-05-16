package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Orientation;
import org.exercice.entite.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Classe de tests pour la classe Carte
 */
@ExtendWith(MockitoExtension.class)
public class CarteServiceTests {

    @Mock
    private CarteService carteService;

    private Carte carte;
    private Aventurier aventurier;

    @BeforeEach
    public void setUp() {
        carte = new Carte(5, 5);
        aventurier = new Aventurier("Indiana Jones", 2, 2, Orientation.N, "AADADAGGA");
    }

    @Test
    public void whenAjouterAventurier_shouldAdd() {
        CarteService carteService = new CarteService();

        carteService.ajouterAventurier(carte, aventurier);

        assertEquals(aventurier, carteService.getAventurier(carte, "Indiana Jones"));
        assertNull(carteService.getAventurier(carte, "Toto"));
    }

    @Test
    void whenAjouterMontagne_shouldAdd() {
        CarteService carteService = new CarteService();
        carteService.ajouterMontagne(carte, 1, 1);
        assertEquals("M", carte.getCarte()[1][1]);
    }

    @Test
    void whenAjouterTresor_shouldAdd() {
        CarteService carteService = new CarteService();
        carteService.ajouterTresor(carte, 2, 2, 3);
        Map<Point, Integer> tresor = carte.getTresors();
        assertEquals("T(3)", carte.getCarte()[2][2]);
        assertEquals(3, tresor.get(new Point(2,2)));
    }

}
