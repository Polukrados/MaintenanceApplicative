package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LieuTest {

    @Test
    void testLieuValide() {
        Lieu lieu = new Lieu("Salle de réunion A");
        assertEquals("Salle de réunion A", lieu.valeur());
    }

    @Test
    void testLieuInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new Lieu(""));
        assertThrows(IllegalArgumentException.class, () -> new Lieu(null));
    }
}
