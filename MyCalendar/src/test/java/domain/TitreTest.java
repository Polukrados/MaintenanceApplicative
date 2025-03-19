package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TitreTest {

    @Test
    void testTitreValide() {
        Titre titre = new Titre("Réunion importante");
        assertEquals("Réunion importante", titre.valeur());
    }

    @Test
    void testTitreInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new Titre(""));
        assertThrows(IllegalArgumentException.class, () -> new Titre(null));
    }
}
