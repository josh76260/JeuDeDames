package dame.ihm.cui;

import dame.Controller;
import dame.metier.Pion;

public class IhmCUI {
    private Controller controller;

    public IhmCUI(Controller controller) {
        this.controller = controller;
    }

    public void afficherPlateau(Pion[][] plateauDeJeu) {
        for (Pion[] ligne : plateauDeJeu) {
            for (Pion p : ligne) {
                if (p != null) System.out.print("| " + p + " ");
                else System.out.print("|   ");
            }
            System.out.println("|");
        }
    }
}
