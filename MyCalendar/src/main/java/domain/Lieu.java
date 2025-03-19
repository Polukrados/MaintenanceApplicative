package domain;

import java.util.Objects;

public record Lieu(String valeur) {
    public Lieu {
        if (valeur == null || valeur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le lieu ne peut pas être vide ou null.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lieu lieu = (Lieu) o;
        return Objects.equals(valeur, lieu.valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
