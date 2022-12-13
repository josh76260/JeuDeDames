package dame.metier;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jeu {
    private Pion[][] plateauDeJeu;
    private Joueur[] tabJoueurs;
    private Joueur joueurCourant;

    public Jeu() {
        plateauDeJeu = new Pion[10][10];
        for (int j = 0; j < 4; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu[j][i] = new Pion(Couleur.NOIR, j, i, List.of(Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE));
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu[j][i] = new Pion(Couleur.BLANC, j, i, List.of(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE));
            }
        }
        tabJoueurs = new Joueur[2];

        Joueur j1 = new Joueur("J1", Couleur.values()[new Random().nextInt(2)]), j2;

        if (j1.getCouleur() == Couleur.NOIR)
            joueurCourant = j2 = new Joueur("J2", Couleur.BLANC);
        else {
            j2 = new Joueur("J2", Couleur.NOIR);
            joueurCourant = j1;
        }
        tabJoueurs[0] = j1;
        tabJoueurs[1] = j2;

    }

    public Pion[][] getPlateauDeJeu() {
        return plateauDeJeu;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean estDansLePlateau(Coordonnee coordonnee) {
        return coordonnee.getLigne() >= 0 && coordonnee.getLigne() < 10 &&
                coordonnee.getColonne() >= 0 && coordonnee.getColonne() < 10;
    }

    public boolean estOccupee(Coordonnee coordonnee) {
        return plateauDeJeu[coordonnee.getLigne()][coordonnee.getColonne()] != null;
    }

    public boolean sautPossible(Pion sauteur, Deplacement deplacement) {
        Coordonnee coordonnee = sauteur.getCoord().plus(deplacement.getCoord());
        if (estDansLePlateau(coordonnee)) {
            Pion saute = plateauDeJeu[coordonnee.getLigne()][coordonnee.getColonne()];
            if (estOccupee(coordonnee)) {
                coordonnee = coordonnee.plus(deplacement.getCoord());
                if (estDansLePlateau(coordonnee) && saute.getCouleur() != sauteur.getCouleur())
                    return plateauDeJeu[coordonnee.getLigne()][coordonnee.getColonne()] == null;
                else return false;
            } else return false;
        } else return false;
    }

    public void bougerPion(Pion pion, List<Deplacement> deplacements) {
        for (Deplacement deplacement :
                deplacements) {
            plateauDeJeu[pion.getCoord().getLigne()][pion.getCoord().getColonne()] = null;
            Coordonnee coord = pion.getCoord().plus(deplacement.getCoord());
            pion.setCoord(coord);

            plateauDeJeu[coord.getLigne()][coord.getColonne()] = pion;
        }


        joueurCourant = tabJoueurs[(Arrays.stream(tabJoueurs).toList().indexOf(joueurCourant) + 1) % 2];
    }
}
