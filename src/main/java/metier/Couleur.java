package metier;

public enum Couleur {
    NOIR("Noir"),
    BLANC("Blanc");

    private final String couleur;

    Couleur(String couleur) {
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }
}
