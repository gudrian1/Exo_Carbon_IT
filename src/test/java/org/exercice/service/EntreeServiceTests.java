package org.exercice.service;

import org.exercice.entite.Carte;
import org.exercice.utils.IScanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntreeServiceTests {

    @Mock
    private IScanner scannerMock;
    @Mock
    private EntreeService entreeService;

    @Test
    void lireFichierEntreeTest_AvecCommentaires() {
        // Arrange
        when(scannerMock.hasNextLine()).thenReturn(true, true, true, true, true, false); // add one more true
        when(scannerMock.nextLine()).thenReturn(
                "# Ceci est un commentaire",
                "C - 3 - 4",
                "# Autre commentaire",
                "M - 1 - 1"
        );
        String nomFichier = "test.txt";

        // Act
        Optional<Carte> resultat = entreeService.lireFichierEntree(nomFichier, scannerMock);

        // Assert
        assertTrue(resultat.isPresent());
        Carte carte = resultat.get();
        assertNotNull(carte);
        assertEquals(3, carte.getLargeur());
        assertEquals(4, carte.getHauteur());
        verify(scannerMock, times(6)).hasNextLine(); // change to 6 times
        verify(scannerMock, times(5)).nextLine(); // change to 5 times
    }

    @Test
    void lireFichierEntreeTest_CarteCreee() {
        // Arrange
        when(scannerMock.hasNextLine()).thenReturn(true, true, true, true, false); // add one more true
        when(scannerMock.nextLine()).thenReturn(
                "C - 3 - 4",
                "M - 1 - 1",
                "T - 0 - 2 - 2"
        );
        String nomFichier = "test.txt";

        // Act
        Optional<Carte> resultat = entreeService.lireFichierEntree(nomFichier, scannerMock);

        // Assert
        assertTrue(resultat.isPresent());
        Carte carte = resultat.get();
        assertNotNull(carte);
        assertEquals(3, carte.getLargeur());
        assertEquals(4, carte.getHauteur());
        verify(scannerMock, times(5)).hasNextLine(); // change to 5 times
        verify(scannerMock, times(4)).nextLine(); // change to 4 times
    }

    @Test
    void lireFichierEntreeTest_FichierInvalide() {
        // Arrange
        when(scannerMock.hasNextLine()).thenReturn(true, true, false); // add one more true
        when(scannerMock.nextLine()).thenReturn("C - trois - quatre");
        String nomFichier = "test.txt";

        // Act & Assert
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            entreeService.lireFichierEntree(nomFichier, scannerMock);
        });
        assertNotNull(exception);
        verify(scannerMock, times(3)).hasNextLine(); // change to 3 times
        verify(scannerMock, times(2)).nextLine(); // change to 2 times
    }
}
