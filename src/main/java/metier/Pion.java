package metier;

public class Pion {
    private Couleur couleur;
    private Coordonnee coord;

    public Pion(Couleur couleur, int ligne, int colonne) {
        this.couleur = couleur;
        this.coord = new Coordonnee(ligne, colonne);
    }

    @Override
    public String toString() {
        return couleur.getCouleur();
    }
}
