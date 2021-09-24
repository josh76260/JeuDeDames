package dame.metier;

import java.util.ArrayList;

public class Pion {
    private Couleur couleur;
    private Coordonnee coord;
    protected ArrayList<Deplacement> listDeplacement;

    public Pion(Couleur couleur, int ligne, int colonne) {
        this.couleur = couleur;
        this.coord = new Coordonnee(ligne, colonne);
        this.listDeplacement = new ArrayList<>();
        this.listDeplacement.add(Deplacement.DIAGONAL_AVANT_GAUCHE);
        this.listDeplacement.add(Deplacement.DIAGONAL_AVANT_DROITE);
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

    @Override
    public String toString() {
        return couleur.getCouleur();
    }

    public void setCoord(Coordonnee coord) {
        this.coord = coord; 
    }
}
