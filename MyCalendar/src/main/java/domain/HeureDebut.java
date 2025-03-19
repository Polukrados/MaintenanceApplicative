package domain;

import java.time.LocalTime;
import java.util.Objects;

public record HeureDebut(LocalTime valeur) {
    public HeureDebut {
        if (valeur == null) {
            throw new IllegalArgumentException("L'heure de début ne peut pas être null.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeureDebut that = (HeureDebut) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public String toString() {
        return valeur.toString();
    }

    public LocalTime getValeur() {
        return valeur;
    }
}
