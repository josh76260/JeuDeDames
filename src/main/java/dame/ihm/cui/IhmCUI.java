package dame.ihm.cui;

import dame.Controller;
import dame.metier.Couleur;
import dame.metier.Deplacement;
import dame.metier.Joueur;
import dame.metier.Pion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IhmCUI {
    private Controller controller;

    public IhmCUI(Controller controller) {
        this.controller = controller;
    }

    public void afficherPlateau(Pion[][] plateauDeJeu) {
        System.out.print("   ");
        for (int i = 0; i < plateauDeJeu.length; i++) {
            System.out.print("  " + Character.toString('A' + i) + " ");
        }
        System.out.println();
        int i = 1;
        for (Pion[] ligne : plateauDeJeu) {
            System.out.print("   " + "+---".repeat(plateauDeJeu.length));
            System.out.print("+\n" + String.format("%2d", i) + " ");
            Arrays.stream(ligne).toList().forEach((p) -> System.out.print(p != null ? "| " + p + " " : "|   "));
            System.out.print("|\n");
            i++;
        }
        System.out.print("   " + "+---".repeat(plateauDeJeu.length));
        System.out.println("+");
    }

    public Pion selectPion(Pion[][] plateauDeJeu, Joueur joueur) {
        System.out.println(joueur.getCouleur());
        ArrayList<Pion> pionsJouables = new ArrayList<>();
        Deplacement[] deplacement = joueur.getCouleur() == Couleur.NOIR ?
                new Deplacement[]{Deplacement.DIAGONAL_ARRIERE_DROITE, Deplacement.DIAGONAL_ARRIERE_GAUCHE} :
                new Deplacement[]{Deplacement.DIAGONAL_AVANT_DROITE, Deplacement.DIAGONAL_AVANT_GAUCHE};

        for (Pion[] ligne : plateauDeJeu) {
            pionsJouables.addAll(Arrays.stream(ligne)
                    .filter(p -> p != null && p.getCouleur() == joueur.getCouleur())
                    .filter(
                            pion -> (
                                    controller.estDansLePlateau(pion.getCoord().plus(deplacement[0].getCoord())) &&
                                            !controller.estOccupee(pion.getCoord().plus(deplacement[0].getCoord()))
                            ) || (
                                    controller.estDansLePlateau(pion.getCoord().plus(deplacement[1].getCoord())) &&
                                            !controller.estOccupee(pion.getCoord().plus(deplacement[1].getCoord()))
                            )
                    ).toList());
        }
        int j = 1;
        for (Pion pion : pionsJouables) {
            System.out.println((j++) + ". " + (pion.getCoord().getLigne() + 1) + Character.toString('A' + pion.getCoord().getColonne()));
        }
        int choix = getChoix(j);
        return pionsJouables.get(choix - 1);
    }

    public Deplacement getDeplacement(Pion pion, Pion[][] plateauDeJeu) {
        System.out.println("Pion : " + (pion.getCoord().getLigne() + 1) + Character.toString('A' + pion.getCoord().getColonne()));
        int i = 1;
        List<Deplacement> deplacementsDispo = pion.getListDeplacement().stream().
                filter(deplacement -> controller.estDansLePlateau(pion.getCoord().plus(deplacement.getCoord()))).toList();
        for (Deplacement deplacement : deplacementsDispo) {
            System.out.println((i++) + ". " + deplacement);
        }
        int choix = getChoix(i);
        return deplacementsDispo.get(choix - 1);
    }

    private int getChoix(int i) {
        int choix = 0;
        while (choix <= 0 || choix > i - 1) {
            System.out.print("Votre choix (entre 1 et " + (i - 1) + ") : ");
            choix = new Scanner(System.in).nextInt();
        }
        return choix;
    }
}
