package dame.metier;

import java.util.List;

public enum Couleur {
    NOIR("N", List.of(Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE)),
    BLANC("B", List.of(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE));

    private final String couleur;
    private final List<Deplacement> deplacementsDefault;

    Couleur(String couleur, List<Deplacement> deplacementsDefault) {
        this.couleur = couleur;
        this.deplacementsDefault = deplacementsDefault;
    }

    public String getCouleur() {
        return couleur;
    }

    public List<Deplacement> getDeplacementsDefault() {
        return deplacementsDefault;
    }
}
