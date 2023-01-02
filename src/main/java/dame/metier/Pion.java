package dame.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pion implements Cloneable {
    protected final Couleur couleur;
    protected ArrayList<Deplacement> listDeplacement;
    private Coordonnee coord;
    private boolean estSaute;

    public Pion(Couleur couleur, int ligne, int colonne, List<Deplacement> listDeplacement) {
        this.couleur = couleur;
        this.coord = new Coordonnee(ligne, colonne);
        this.listDeplacement = new ArrayList<>();
        this.listDeplacement.addAll(listDeplacement);
        this.estSaute = false;
    }

    public Coordonnee getCoord() {
        return coord;
    }

    public void setCoord(Coordonnee coord) {
        this.coord = coord;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public ArrayList<Deplacement> getListDeplacement() {
        return listDeplacement;
    }

    public void setListDeplacement(ArrayList<Deplacement> listDeplacement) {
        this.listDeplacement = listDeplacement;
    }

    public boolean isEstSaute() {
        return estSaute;
    }

    public void setEstSaute(boolean estSaute) {
        this.estSaute = estSaute;
    }

    @Override
    public String toString() {
        return "P" + couleur.getCouleur();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pion pion)) return false;
        return getCouleur() == pion.getCouleur() && getCoord().equals(pion.getCoord()) && Objects.equals(getListDeplacement(), pion.getListDeplacement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCouleur(), getCoord(), getListDeplacement());
    }

    @Override
    public Pion clone() {
        try {
            return (Pion) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
