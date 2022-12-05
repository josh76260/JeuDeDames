package dame.metier;

import java.util.Objects;

public class Coordonnee {
    private int ligne;
    private int colonne;

    public Coordonnee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    @Override
    public String toString() {
        return "ligne=" + ligne + ", colonne=" + colonne;
    }

    public Coordonnee plus(Coordonnee other) {
        return new Coordonnee(this.ligne + other.ligne, this.colonne + other.colonne);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordonnee that)) return false;
        return getLigne() == that.getLigne() && getColonne() == that.getColonne();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLigne(), getColonne());
    }
}
