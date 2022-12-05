package dame.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pion {
    protected ArrayList<Deplacement> listDeplacement;
    private final Couleur couleur;
    private Coordonnee coord;

    public Pion(Couleur couleur, int ligne, int colonne, List<Deplacement> listDeplacement) {
        this.couleur = couleur;
        this.coord = new Coordonnee(ligne, colonne);
        this.listDeplacement = new ArrayList<>();
        this.listDeplacement.addAll(listDeplacement);
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

    @Override
    public String toString() {
        return couleur.getCouleur();
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
}
