package domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {

    @Test
    void testParticipantsValide() {
        List<String> noms = Arrays.asList("Alice", "Bob", "Charlie");
        Participants participants = new Participants(noms);
        assertEquals(noms, participants.noms());
    }

    @Test
    void testParticipantsInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new Participants(null));
        assertThrows(IllegalArgumentException.class, () -> new Participants(List.of()));
    }
}
