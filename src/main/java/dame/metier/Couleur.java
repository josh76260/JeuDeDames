package dame.metier;

public enum Couleur {
    NOIR("N"),
    BLANC("B");

    private final String couleur;

    Couleur(String couleur) {
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }
}
