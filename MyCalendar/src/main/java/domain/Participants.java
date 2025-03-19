package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Participants(List<String> noms) {
    public Participants(List<String> noms) {
        if (noms == null || noms.isEmpty()) {
            throw new IllegalArgumentException("La liste des participants ne peut pas être vide ou null.");
        }
        this.noms = List.copyOf(noms); // Immutabilité
    }

    @Override
    public List<String> noms() {
        return Collections.unmodifiableList(noms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participants that = (Participants) o;
        return Objects.equals(noms, that.noms);
    }

    @Override
    public String toString() {
        return String.join(", ", noms);
    }
}
