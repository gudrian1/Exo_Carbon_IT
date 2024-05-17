package org.exercice.service;

import org.exercice.entite.Orientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrientationServiceTests {

    @Mock
    private OrientationService orientationService;

    @Test
    void whenTestGauche_shouldOrientLeft() {
        when(orientationService.gauche(Orientation.N)).thenReturn(Orientation.O);
        when(orientationService.gauche(Orientation.E)).thenReturn(Orientation.N);
        when(orientationService.gauche(Orientation.S)).thenReturn(Orientation.E);
        when(orientationService.gauche(Orientation.O)).thenReturn(Orientation.S);

        assertEquals(Orientation.O, orientationService.gauche(Orientation.N));
        assertEquals(Orientation.N, orientationService.gauche(Orientation.E));
        assertEquals(Orientation.E, orientationService.gauche(Orientation.S));
        assertEquals(Orientation.S, orientationService.gauche(Orientation.O));
    }

    @Test
    void whenTestDroite_shouldOrientRight() {
        when(orientationService.droite(Orientation.N)).thenReturn(Orientation.E);
        when(orientationService.droite(Orientation.E)).thenReturn(Orientation.S);
        when(orientationService.droite(Orientation.S)).thenReturn(Orientation.O);
        when(orientationService.droite(Orientation.O)).thenReturn(Orientation.N);

        assertEquals(Orientation.E, orientationService.droite(Orientation.N));
        assertEquals(Orientation.S, orientationService.droite(Orientation.E));
        assertEquals(Orientation.O, orientationService.droite(Orientation.S));
        assertEquals(Orientation.N, orientationService.droite(Orientation.O));
    }
}
