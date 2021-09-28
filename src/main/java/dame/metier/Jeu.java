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
                plateauDeJeu[j][i] = new Pion(Couleur.NOIR, j, i, List.of(Deplacement.DIAGONAL_ARRIERE_DROITE, Deplacement.DIAGONAL_ARRIERE_GAUCHE));
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu[j][i] = new Pion(Couleur.BLANC, j, i, List.of(Deplacement.DIAGONAL_AVANT_DROITE, Deplacement.DIAGONAL_AVANT_GAUCHE));
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

    public void bougerPion(Pion pion, Deplacement deplacement) {
        System.out.println(deplacement);
        plateauDeJeu[pion.getCoord().getLigne()][pion.getCoord().getColonne()] = null;
        Coordonnee coord = pion.getCoord().plus(deplacement.getCoord());
        pion.setCoord(coord);

        plateauDeJeu[coord.getLigne()][coord.getColonne()] = pion;

        joueurCourant = tabJoueurs[(Arrays.stream(tabJoueurs).toList().indexOf(joueurCourant)+1)%2];
    }
}
