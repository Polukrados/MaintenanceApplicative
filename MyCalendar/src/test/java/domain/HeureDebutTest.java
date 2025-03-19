package domain;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class HeureDebutTest {

    @Test
    void testHeureValide() {
        LocalTime heure = LocalTime.of(14, 30);
        HeureDebut heureDebut = new HeureDebut(heure);
        assertEquals(heure, heureDebut.valeur());
    }

    @Test
    void testHeureInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new HeureDebut(null));
    }
}
