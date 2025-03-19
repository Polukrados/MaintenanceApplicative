package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DureeEvenementTest {

    @Test
    void testDureeValide() {
        DureeEvenement duree = new DureeEvenement(60);
        assertEquals(60, duree.valeur());
    }

    @Test
    void testDureeInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(0));
        assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(-10));
    }
}
