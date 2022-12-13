package dame;

import dame.ihm.cui.IhmCUI;
import dame.metier.Coordonnee;
import dame.metier.Deplacement;
import dame.metier.Jeu;
import dame.metier.Pion;

public class Controller {

    private final Jeu jeu;
    private final IhmCUI ihmCui;

    public Controller() {
        jeu = new Jeu();
        ihmCui = new IhmCUI(this);

        jouer();
    }

    private void jouer() {
        while (true) {
            ihmCui.afficherPlateau(jeu.getPlateauDeJeu());
            Pion pion = ihmCui.selectPion(jeu.getPlateauDeJeu(), jeu.getJoueurCourant());
            jeu.bougerPion(pion, ihmCui.getDeplacement(pion));
            //Jeu.bougerPion(ihmCui.getPionABouger(), ihmCui.getCoordToMove());
        }
    }

    public boolean estDansLePlateau(Coordonnee coordonnee) {
        return jeu.estDansLePlateau(coordonnee);
    }

    public static void main(String[] args) {
        new Controller();
    }

    public boolean estOccupee(Coordonnee coordonnee) {
        return jeu.estOccupee(coordonnee);
    }

    public boolean sautPossible(Pion sauteur, Deplacement deplacement) {
        return jeu.sautPossible(sauteur, deplacement);
    }
}
