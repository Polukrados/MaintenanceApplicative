package domain;

public record DureeEvenement(int valeur) {
    public DureeEvenement {
        if (valeur <= 0) {
            throw new IllegalArgumentException("La durée doit être positive.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DureeEvenement that = (DureeEvenement) o;
        return valeur == that.valeur;
    }

    @Override
    public String toString() {
        return valeur + " minutes";
    }

    public long getValeur() {
        return valeur;
    }
}
