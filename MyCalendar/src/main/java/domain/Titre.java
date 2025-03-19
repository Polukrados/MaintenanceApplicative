package domain;

import java.util.Objects;

public record Titre(String valeur) {
    public Titre {
        if (valeur == null || valeur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide ou null.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titre titre = (Titre) o;
        return Objects.equals(valeur, titre.valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
