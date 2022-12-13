package dame.ihm.cui;

import dame.Controller;
import dame.metier.*;

import java.util.*;

public class IhmCUI {
    private final Controller controller;

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
                new Deplacement[]{Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE} :
                new Deplacement[]{Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE};

        for (Pion[] ligne : plateauDeJeu) {
            pionsJouables.addAll(Arrays.stream(ligne)
                    .filter(p -> p != null && p.getCouleur() == joueur.getCouleur())
                    .filter(
                            pion -> (controller.estDansLePlateau(pion.getCoord().plus(deplacement[0].getCoord())) &&
                                    (!controller.estOccupee(pion.getCoord().plus(deplacement[0].getCoord())) ||
                                            controller.sautPossible(pion, deplacement[0])))
                                    ||
                                    (controller.estDansLePlateau(pion.getCoord().plus(deplacement[1].getCoord())) &&
                                            (!controller.estOccupee(pion.getCoord().plus(deplacement[1].getCoord())) ||
                                                    controller.sautPossible(pion, deplacement[1])))
                    ).toList());
        }
        int j = 1;
        for (Pion pion : pionsJouables) {
            System.out.println((j++) + ". " + (pion.getCoord().getLigne() + 1) + Character.toString('A' + pion.getCoord().getColonne()));
        }
        int choix = getChoix(j);
        return pionsJouables.get(choix - 1);
    }

    public List<Deplacement> getDeplacement(Pion pion) {
        System.out.println("Pion : " + (pion.getCoord().getLigne() + 1) + Character.toString('A' + pion.getCoord().getColonne()));
        int i = 1;
        ArrayList<Deplacement> deplacementsDispo = new ArrayList<>(pion.getListDeplacement().stream().
                filter(deplacement -> controller.estDansLePlateau(pion.getCoord().plus(deplacement.getCoord())) &&
                        !controller.estOccupee(pion.getCoord().plus(deplacement.getCoord())) ||
                        controller.sautPossible(pion, deplacement)).toList());
        System.out.println(deplacementsDispo);
        if (!deplacementsDispo.isEmpty()) {
            for (Deplacement deplacement : List.copyOf(deplacementsDispo)) {
                if (controller.sautPossible(pion, deplacement)) {
                    deplacementsDispo.remove(deplacement);

                    Deplacement.SAUT.setAlias(deplacement);
                    deplacementsDispo.add(Deplacement.SAUT);
                }
            }
            for (Deplacement deplacement : deplacementsDispo) {
                System.out.println((i++) + ". " + deplacement);
            }
            int choix = getChoix(i);
            if (deplacementsDispo.get(choix - 1) == Deplacement.SAUT) {
                List<Deplacement> toReturn = new ArrayList<>(List.of(Deplacement.SAUT.getAlias(), Deplacement.SAUT.getAlias()));
                Pion temp = pion.clone();
                Coordonnee depart = pion.getCoord();
                for (Deplacement deplacement :
                        toReturn) {
                    depart = depart.plus(deplacement.getCoord());
                }
                temp.setCoord(depart);
                List<Deplacement> toAdd = getDeplacement(temp.clone());
                toReturn.addAll(toAdd);
                return toReturn;
            }
            return List.of(deplacementsDispo.get(choix - 1));
        }
        else return List.of();
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
