package dame;

import dame.ihm.cui.IhmCUI;
import dame.metier.Coordonnee;
import dame.metier.Deplacement;
import dame.metier.Jeu;
import dame.metier.Pion;

import java.io.FileNotFoundException;
import java.util.List;

public class Controller {

    private final Jeu jeu;
    private final IhmCUI ihmCui;

    public Controller() {
        jeu = new Jeu();
        ihmCui = new IhmCUI(this);

        jouer();
    }

    public Controller(String filename) throws FileNotFoundException {
        jeu = new Jeu(Jeu.loadFromeFile(filename));
        ihmCui = new IhmCUI(this);

        jouer();
    }

    public Controller(Jeu jeu){
        this.jeu = jeu;
        ihmCui = new IhmCUI(this);
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

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 0)
            new Controller(args[0]);
        else
            new Controller();
    }

    public boolean estOccupee(Coordonnee coordonnee) {
        return jeu.estOccupee(coordonnee);
    }

    public List<Deplacement> deplacementAvecSaut(Pion sauteur) {
        return jeu.deplacementAvecSaut(sauteur);
    }
    public List<Deplacement> getDeplacementsPossibles(Pion pion) {
        return jeu.getDeplacementsPossibles(pion);
    }

    public int getMaxSauts(Pion pion) {
        return jeu.getMaxSauts(pion);
    }
}
