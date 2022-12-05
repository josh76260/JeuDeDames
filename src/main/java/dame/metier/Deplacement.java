package dame.metier;

public enum Deplacement {
    BAS(1, 0),
    HAUT(-1, 0),
    DROITE(0, 1),
    GAUCHE(0, -1),
    DIAGONAL_BAS_GAUCHE(BAS.getLigne(), GAUCHE.getColonne()),
    DIAGONAL_BAS_DROITE(BAS.getLigne(), DROITE.getColonne()),
    DIAGONAL_HAUT_GAUCHE(HAUT.getLigne(), GAUCHE.getColonne()),
    DIAGONAL_HAUT_DROITE(HAUT.getLigne(), DROITE.getColonne()),
    SAUT(0, 0);

    private final Coordonnee coord;
    private Deplacement alias;

    Deplacement(int ligne, int colonne) {
        this.coord = new Coordonnee(ligne, colonne);
        this.alias = this;
    }

    public int getLigne() {
        return coord.getLigne();
    }

    public int getColonne() {
        return coord.getColonne();
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
