package domain;

import java.time.LocalDate;
import java.util.Objects;

public final class DateEvenement {
    private final LocalDate valeur;

    public DateEvenement(LocalDate valeur) {
        if (valeur == null) {
            throw new IllegalArgumentException("La date ne peut pas être null.");
        }
        this.valeur = valeur;
    }

    public LocalDate getValeur() {
        return valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateEvenement that = (DateEvenement) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return valeur.toString();
    }
}
