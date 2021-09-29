package dame.metier;

public enum Deplacement {
    DIAGONAL_AVANT_GAUCHE(-1, -1),
    DIAGONAL_AVANT_DROITE(1, -1),
    DIAGONAL_ARRIERE_GAUCHE(-1, 1),
    DIAGONAL_ARRIERE_DROITE(1, 1),
    AVANT(1, 0),
    ARRIERE(-1, 0),
    DROITE(0, 1),
    GAUCHE(0, -1),
    SAUT(0, 0);

    private Coordonnee coord;
    private Deplacement alias;

    Deplacement(int x, int y) {
        this.coord = new Coordonnee(y, x);
        this.alias = this;
    }

    public int getX() {
        return coord.getColonne();
    }

    public int getY() {
        return coord.getLigne();
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
