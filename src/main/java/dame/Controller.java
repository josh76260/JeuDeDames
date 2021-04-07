package dame;

import dame.ihm.cui.IhmCUI;
import dame.metier.Jeu;

public class Controller {

    private final Jeu jeu;
    private final IhmCUI ihmCui;

    public Controller(){
        jeu = new Jeu();
        ihmCui = new IhmCUI(this);

        jouer();
    }

    private void jouer() {
        ihmCui.afficherPlateau(jeu.getPlateauDeJeu());


    }

    public static void main(String[] args) {
        new Controller();
    }
}
