package dame.metier;

import java.util.Random;

public class Jeu {
    private Pion[][] plateauDeJeu;
    private Joueur[] tabJoueurs;
    private Joueur joueurCourant;

    public Jeu() {
        plateauDeJeu = new Pion[10][10];
        plateauDeJeu = new Pion[10][10];
        for (int j = 0; j < 4; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu[j][i] = new Pion(Couleur.NOIR, i, j);
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu[j][i] = new Pion(Couleur.BLANC, i, j);
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
}
