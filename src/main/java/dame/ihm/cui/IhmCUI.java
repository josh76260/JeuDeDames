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
        if (!pions.stream().filter(pion -> !controller.deplacementAvecSaut(pion).isEmpty()).toList().isEmpty()) {
            int coupsMax = -1;
            for (Pion pion : pions.stream().filter(pion -> !controller.deplacementAvecSaut(pion).isEmpty()).toList()) {
                var coups = controller.getMaxSauts(pion);
                if (coups >= coupsMax) {
                    coupsMax = coups;
                    temp.add(pion);
                }
            }
            int finalCoupsMax = coupsMax;
            pionsJouables = new ArrayList<>(temp.stream().filter(pion -> finalCoupsMax <= controller.getMaxSauts(pion)).toList());
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
        ArrayList<Deplacement> toReturn = new ArrayList<>(), deplacementsDispo = (ArrayList<Deplacement>) controller.getDeplacementsPossibles(pion);
        Deplacement deplacementChoisi = null;
        int choix;
        do {
            int i = 1;
            if (!deplacementsDispo.isEmpty()) {
                for (Deplacement deplacement : deplacementsDispo) {
                    System.out.println((i++) + ". " + deplacement);
                }
                choix = getChoix(i);
                deplacementChoisi = deplacementsDispo.get(choix - 1);
                if (deplacementChoisi == Deplacement.SAUT) {
                    List<Deplacement> deplacementAFaire = new ArrayList<>(List.of(Deplacement.SAUT.getAlias(), Deplacement.SAUT.getAlias()));
                    Pion temp = pion.clone();
                    Coordonnee depart = pion.getCoord();
                    for (Deplacement depl :
                            deplacementAFaire) {
                        depart = depart.plus(depl.getCoord());
                        if (controller.estOccupee(depart))
                            controller.setSaute(depart);
                    }
                    temp.setCoord(depart);
                    toReturn.addAll(deplacementAFaire);
                    pion = temp.clone();
                } else
                    toReturn.add(deplacementChoisi);
            }
            deplacementsDispo = (ArrayList<Deplacement>) controller.getDeplacementsPossibles(pion);
        } while (!deplacementsDispo.isEmpty() && deplacementChoisi == Deplacement.SAUT && deplacementsDispo.contains(Deplacement.SAUT));
        return toReturn;
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
