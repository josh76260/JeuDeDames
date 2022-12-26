package dame.ihm.cui;

import dame.Controller;
import dame.metier.*;

import java.util.*;

public class IhmCUI {
    public static final int DIMENSION_PLATEAU = 10;
    private final Controller controller;

    public IhmCUI(Controller controller) {
        this.controller = controller;
    }

    public void afficherPlateau(HashMap<Coordonnee, Pion> plateauDeJeu) {
        Pion[][] plateau = new Pion[DIMENSION_PLATEAU][DIMENSION_PLATEAU];

        System.out.print("   ");
        for (int i = 0; i < DIMENSION_PLATEAU; i++) {
            System.out.print("   " + Character.toString('A' + i) + "   ");
        }
        System.out.println();

        for (int i = 0; i < DIMENSION_PLATEAU; i++) {
            for (int j = 0; j < DIMENSION_PLATEAU; j++) {
                plateau[i][j] = plateauDeJeu.get(new Coordonnee(i, j));
            }
        }

        int i = 1;
        for (Pion[] ligne : plateau) {
            System.out.print("   " + "+------".repeat(DIMENSION_PLATEAU));
            System.out.print("+\n" + String.format("%2d", i) + " ");
            Arrays.stream(ligne).toList().forEach((p) -> System.out.print(p != null ? "|  " + p + "  " : "|      "));
            System.out.print("|\n");
            i++;
        }
        System.out.print("   " + "+------".repeat(DIMENSION_PLATEAU));
        System.out.println("+");
    }

    public Pion selectPion(HashMap<Coordonnee, Pion> plateauDeJeu, Joueur joueur) {
        System.out.println(joueur.getCouleur());

        ArrayList<Pion> pions = new ArrayList<>(plateauDeJeu.values().stream()
                .filter(p -> p != null && p.getCouleur() == joueur.getCouleur())
                .filter(pion -> !controller.getDeplacementsPossibles(pion).isEmpty()).toList());

        ArrayList<Pion> pionsJouables, temp = new ArrayList<>();
        if (!pions.stream().filter(pion -> !controller.getDeplacementsPossibles(pion).isEmpty()).toList().isEmpty()) {
            int coupsMax = -1;
            for (Pion pion : pions.stream().filter(pion -> !controller.getDeplacementsPossibles(pion).isEmpty()).toList()) {
                List<Deplacement> list = controller.getDeplacementsPossibles(pion);
                if (!list.isEmpty()) {
                    if (list.size() >= coupsMax) {
                        coupsMax = list.size();
                        temp.add(pion);
                    }
                }
            }
            int finalCoupsMax = coupsMax;
            pionsJouables = new ArrayList<>(temp.stream().filter(pion -> finalCoupsMax <= controller.getDeplacementsPossibles(pion).size()).toList());
        } else
            pionsJouables = pions;
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
        ArrayList<Deplacement> deplacementsDispo = (ArrayList<Deplacement>) controller.getDeplacementsPossibles(pion);
        if (!deplacementsDispo.isEmpty()) {
            for (Deplacement deplacement : List.copyOf(deplacementsDispo)) {
                if (!controller.deplacementAvecSaut(pion).isEmpty()) {
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
        } else return List.of();
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
