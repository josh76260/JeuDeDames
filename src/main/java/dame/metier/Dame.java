package dame.metier;

import java.util.List;

public class Dame extends Pion{
    public Dame(Couleur couleur, int ligne, int colonne) {
        super(couleur, ligne, colonne);
        this.listDeplacement.addAll(List.of(Deplacement.values()));
    }
}
