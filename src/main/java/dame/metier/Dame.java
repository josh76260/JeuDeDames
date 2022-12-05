package dame.metier;

import java.util.List;

public class Dame extends Pion{
    public Dame(Couleur couleur, int ligne, int colonne) {
        super(couleur, ligne, colonne, List.of(Deplacement.BAS, Deplacement.HAUT, Deplacement.GAUCHE, Deplacement.DROITE));
        this.listDeplacement.addAll(List.of(Deplacement.values()));
    }
}
