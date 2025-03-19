package domain;

import java.util.Objects;
import java.util.UUID;

public record EventId(UUID valeur) {
    public EventId {
        if (valeur == null) {
            throw new IllegalArgumentException("L'EventId ne peut pas être null.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(valeur, eventId.valeur);
    }

    @Override
    public String toString() {
        return valeur.toString();
    }
}
