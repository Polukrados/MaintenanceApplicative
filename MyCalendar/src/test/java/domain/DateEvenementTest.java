package domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateEvenementTest {

    @Test
    void testDateValide() {
        LocalDate date = LocalDate.of(2025, 3, 20);
        DateEvenement dateEvenement = new DateEvenement(date);
        assertEquals(date, dateEvenement.valeur());
    }

    @Test
    void testDateInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new DateEvenement(null));
    }
}
