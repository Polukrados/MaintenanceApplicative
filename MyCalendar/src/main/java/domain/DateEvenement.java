package domain;

import java.time.LocalDate;
import java.util.Objects;

public record DateEvenement(LocalDate valeur) {
    public DateEvenement {
        if (valeur == null) {
            throw new IllegalArgumentException("La date ne peut pas être null.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateEvenement that = (DateEvenement) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public String toString() {
        return valeur.toString();
    }

    public LocalDate getValeur() {
        return valeur;
    }
}
