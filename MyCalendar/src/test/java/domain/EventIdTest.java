package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class EventIdTest {

    @Test
    void testEventIdValide() {
        UUID uuid = UUID.randomUUID();
        EventId eventId = new EventId(uuid);
        assertEquals(uuid, eventId.valeur());
    }

    @Test
    void testEventIdInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new EventId(null));
    }

    @Test
    void testEventIdUnique() {
        EventId id1 = new EventId(UUID.randomUUID());
        EventId id2 = new EventId(UUID.randomUUID());
        assertNotEquals(id1, id2);
    }
}
