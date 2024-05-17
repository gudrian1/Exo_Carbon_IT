package org.exercice.service;

import org.exercice.entite.Aventurier;
import org.exercice.entite.Carte;
import org.exercice.entite.Orientation;
import org.exercice.utils.IScanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntreeServiceTests {

    @Mock
    private CarteService carteService;

    @Mock
    private IScanner scanner;

    @InjectMocks
    private EntreeService entreeService;

    @Test
    void whenLireFichierEntree_shouldCreerCarte() {
        when(scanner.hasNextLine()).thenReturn(true, false);
        when(scanner.nextLine()).thenReturn("C - 3 - 4");

        Optional<Carte> carte = entreeService.lireFichierEntree("test.txt", scanner);

        assertTrue(carte.isPresent());
        assertEquals(3, carte.get().getLargeur());
        assertEquals(4, carte.get().getHauteur());
    }

    @Test
    void whenLireFichierEntree_shouldAjouterMontagne() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        when(scanner.nextLine()).thenReturn("C - 3 - 4", "M - 1 - 2");

        Optional<Carte> carte = entreeService.lireFichierEntree("test.txt", scanner);

        assertTrue(carte.isPresent());
        verify(carteService, times(1)).ajouterMontagne(carte.get(), 1, 2);
    }

    @Test
    void whenLireFichierEntree_shouldAjouterTresor() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        when(scanner.nextLine()).thenReturn("C - 3 - 4", "T - 1 - 2 - 3");

        Optional<Carte> carte = entreeService.lireFichierEntree("test.txt", scanner);

        assertTrue(carte.isPresent());
        verify(carteService, times(1)).ajouterTresor(carte.get(), 1, 2, 3);
    }

    @Test
    void whenLireFichierEntree_shouldAjouterAventurier() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        when(scanner.nextLine()).thenReturn("C - 3 - 4", "A - Lara - 1 - 1 - N - AADG");

        Optional<Carte> carte = entreeService.lireFichierEntree("test.txt", scanner);

        assertTrue(carte.isPresent());
        ArgumentCaptor<Aventurier> aventurierCaptor = ArgumentCaptor.forClass(Aventurier.class);
        verify(carteService, times(1)).ajouterAventurier(eq(carte.get()), aventurierCaptor.capture());

        Aventurier aventurier = aventurierCaptor.getValue();
        assertEquals("Lara", aventurier.getNom());
        assertEquals(1, aventurier.getX());
        assertEquals(1, aventurier.getY());
        assertEquals(Orientation.N, aventurier.getOrientation());
        assertEquals("AADG", aventurier.getSequence());
    }

    @Test
    void whenLireFichierEntree_shouldIgnorerCommentaires() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        when(scanner.nextLine()).thenReturn("# Ceci est un commentaire", "C - 3 - 4");

        Optional<Carte> carte = entreeService.lireFichierEntree("test.txt", scanner);

        assertTrue(carte.isPresent());
        assertEquals(3, carte.get().getLargeur());
        assertEquals(4, carte.get().getHauteur());
    }

    @Test
    void whenLireFichierEntree_shouldGererException() {
        when(scanner.hasNextLine()).thenReturn(true, true, false);
        when(scanner.nextLine()).thenReturn("C - 3 - 4", "A - Lara - X - Y - NORTH - AADG");

        assertThrows(NumberFormatException.class, () -> {
            entreeService.lireFichierEntree("test.txt", scanner);
        });
    }

    @Test
    void whenLireFichierEntree_shouldNotCreerCarte() {
        when(scanner.hasNextLine()).thenReturn(true, false);
        when(scanner.nextLine()).thenReturn("A - Lara - 1 - 1 - NORTH - AADG");

        Optional<Carte> carte = entreeService.lireFichierEntree("test.txt", scanner);

        assertFalse(carte.isPresent());
    }
}
