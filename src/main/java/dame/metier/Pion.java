package dame.metier;

import java.util.ArrayList;
import java.util.List;

public class Pion {
    private Couleur couleur;
    private Coordonnee coord;
    protected ArrayList<Deplacement> listDeplacement;

    public Pion(Couleur couleur, int ligne, int colonne, List<Deplacement> listDeplacement) {
        this.couleur = couleur;
        this.coord = new Coordonnee(ligne, colonne);
        this.listDeplacement = new ArrayList<>();
        this.listDeplacement.addAll(listDeplacement);
    }

    public Coordonnee getCoord() {
        return coord;
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

    public void setCoord(Coordonnee coord) {
        this.coord = coord; 
    }
}
