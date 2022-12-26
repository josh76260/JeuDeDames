package dame.metier;

public enum Deplacement {
    DIAGONAL_BAS_GAUCHE(1, -1),
    DIAGONAL_BAS_DROITE(1, 1),
    DIAGONAL_HAUT_GAUCHE(-1, -1),
    DIAGONAL_HAUT_DROITE(-1, 1),
    SAUT(0, 0);
    private final Coordonnee coord;
    private Deplacement alias;

    Deplacement(int ligne, int colonne) {
        this.coord = new Coordonnee(ligne, colonne);
        this.alias = this;
    }

    public void setAlias(Deplacement alias) {
        this.alias = alias;
    }

    public Deplacement getAlias() {
        return alias;
    }

    public Coordonnee getCoord() {
        return coord;
    }
}
