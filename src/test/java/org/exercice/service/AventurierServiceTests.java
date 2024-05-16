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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Classe de tests pour la classe Aventurier
 */
@ExtendWith(MockitoExtension.class)
class AventurierServiceTests {

    private Aventurier aventurier;
    private Carte carte;

    @Mock
    private CarteService carteService;
    @Mock
    private AventurierService aventurierService;
    @Mock
    private OrientationService orientationService;

    @BeforeEach
    public void setup() {
        aventurier = new Aventurier("Test", 1, 1, Orientation.N, "AADADAGGA");
        carte = new Carte(3, 3);
        carteService.ajouterTresor(carte, 2, 2, 1);
    }

    @Test
    void whenAvancer_shouldMoveInMap() {
        AventurierService aventurierService = new AventurierService(orientationService);

        aventurierService.avancer(aventurier, carte);

        assertEquals(1, aventurier.getX());
        assertEquals(2, aventurier.getY());
    }

    @Test
    void whenAvancerOnTreasure_shouldPickUpTreasure() {
        aventurier = new Aventurier("Test", 1, 1, Orientation.E, "A");
        carteService.ajouterTresor(carte,2, 1, 1);
        aventurierService.avancer(aventurier, carte);
        assertEquals(2, aventurier.getX());
        assertEquals(1, aventurier.getY());
        assertFalse(carte.getTresors().containsKey(new Point(2, 1)));
    }

    @Test
    void whenAvancerOnMountain_shouldNotMove() {
        aventurier = new Aventurier("Test", 1, 1, Orientation.E, "A");
        carteService.ajouterMontagne(carte,2, 1);
        aventurierService.avancer(aventurier, carte);
        assertEquals(1, aventurier.getX()); // L'aventurier ne doit pas bouger
        assertEquals(1, aventurier.getY());
    }

    @Test
    void whenAvancerOutOfMap_shouldNotMove() {
        aventurier = new Aventurier("Test", 0, 0, Orientation.N, "A");
        aventurierService.avancer(aventurier, carte);
        assertEquals(0, aventurier.getX()); // L'aventurier ne doit pas bouger
        assertEquals(0, aventurier.getY());
    }

    @Test
    void whenTourner_shouldTourner() {
        when(orientationService.droite(Orientation.N)).thenReturn(Orientation.E);

        aventurierService.tourner(aventurier, 'D');

        assertEquals(Orientation.E, aventurier.getOrientation());
    }

    @Test
    void whenExecuterSequence_shouldExecute() {
        aventurierService.executerSequence(aventurier, carte);
        Map<Point, Integer> tresor = carte.getTresors();
        assertEquals(2, aventurier.getX());
        assertEquals(0, aventurier.getY());
        assertTrue(carte.getTresors().containsKey(new Point(2, 2)));
    }
}
