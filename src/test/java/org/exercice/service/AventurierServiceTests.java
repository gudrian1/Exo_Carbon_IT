package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Orientation;
import org.exercice.entite.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Classe de tests pour la classe Aventurier
 */
@ExtendWith(MockitoExtension.class)
class AventurierServiceTests {

    @Mock
    private OrientationService orientationService;

    @InjectMocks
    private AventurierService aventurierService;


    @Test
    void whenTournerGauche_shouldWork() {
        Aventurier aventurier = new Aventurier("Lara", 0, 0, Orientation.N, "");
        when(orientationService.gauche(aventurier.getOrientation())).thenReturn(Orientation.O);

        aventurierService.tourner(aventurier, 'G');

        assertEquals(Orientation.O, aventurier.getOrientation());
        verify(orientationService, times(1)).gauche(Orientation.N);
    }

    @Test
    void whenTournerDroit_shouldWork() {
        Aventurier aventurier = new Aventurier("Lara", 0, 0, Orientation.N, "");
        when(orientationService.droite(aventurier.getOrientation())).thenReturn(Orientation.E);

        aventurierService.tourner(aventurier, 'D');

        assertEquals(Orientation.E, aventurier.getOrientation());
        verify(orientationService, times(1)).droite(Orientation.N);
    }

    @Test
    void whenAvancer_shouldAvance() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, Orientation.N, "");
        Carte carte = new Carte(3, 3);
        String[][] carteArray = {
                {".", ".", "."},
                {".", "A", "."},
                {".", ".", "."}
        };
        carte.setCarte(carteArray);

        aventurierService.avancer(aventurier, carte);

        assertEquals(1, aventurier.getX());
        assertEquals(0, aventurier.getY());
        assertEquals("A", carte.getCarte()[0][1]);
        assertEquals(".", carte.getCarte()[1][1]);
    }

    @Test
    void whenAvancerSurMontagne_shouldNotAvance() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, Orientation.N, "");
        Carte carte = new Carte(3, 3);
        String[][] carteArray = {
                {".", "M", "."},
                {".", "A", "."},
                {".", ".", "."}
        };
        carte.setCarte(carteArray);

        aventurierService.avancer(aventurier, carte);

        assertEquals(1, aventurier.getX());
        assertEquals(1, aventurier.getY());
        assertEquals("A", carte.getCarte()[1][1]);
    }

    @Test
    void whenExecuterSequence_shouldExecute() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, Orientation.N, "AADG");
        Carte carte = new Carte(3, 3);
        String[][] carteArray = {
                {".", ".", "."},
                {".", "A", "."},
                {".", ".", "."}
        };
        carte.setCarte(carteArray);

        when(orientationService.droite(any())).thenReturn(Orientation.E);
        when(orientationService.gauche(any())).thenReturn(Orientation.N);

        aventurierService.executerSequence(aventurier, carte);

        assertEquals(1, aventurier.getX());
        assertEquals(0, aventurier.getY());
        assertEquals(Orientation.N, aventurier.getOrientation());
    }

    @Test
    void whenAjouterTresor_shouldGetTresor() {
        Aventurier aventurier = new Aventurier("Lara", 1, 1, Orientation.N, "");
        Carte carte = new Carte(3, 3);
        String[][] carteArray = {
                {".", ".", "."},
                {".", "A", "."},
                {".", ".", "."}
        };
        Map<Point, Integer> tresors = new HashMap<>();
        tresors.put(new Point(1, 0), 2);
        carte.setTresors(tresors);
        carte.setCarte(carteArray);

        aventurierService.avancer(aventurier, carte);

        assertEquals(1, aventurier.getX());
        assertEquals(0, aventurier.getY());
        assertEquals(1, (int) carte.getTresors().get(new Point(1, 0)));
    }
}
