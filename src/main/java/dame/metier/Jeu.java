package dame.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class Jeu {

    public static final int MIN_PLATEAU = 0, MAX_PLATEAU = 10;
    private HashMap<Coordonnee, Pion> plateauDeJeu;
    private final Joueur[] tabJoueurs;
    private Joueur joueurCourant;

    public Jeu() {
        plateauDeJeu = new HashMap<>();
        for (int j = 0; j < 4; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu.put(new Coordonnee(j, i), new Pion(Couleur.NOIR, j, i,
                        List.of(Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE)));
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                plateauDeJeu.put(new Coordonnee(j, i), new Pion(Couleur.BLANC, j, i,
                        List.of(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE)));
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

    public Jeu(HashMap<Coordonnee, Pion> plateauDeJeu) {
        this.plateauDeJeu = plateauDeJeu;
        tabJoueurs = new Joueur[2];

        Joueur j1 = new Joueur("J1", Couleur.BLANC), j2 = new Joueur("J2", Couleur.NOIR);

        joueurCourant = j1;
        tabJoueurs[0] = j1;
        tabJoueurs[1] = j2;
    }

    public static HashMap<Coordonnee, Pion> loadFromeFile(String filename) throws FileNotFoundException {
        File fr;
        try {
            fr = new File(Objects.requireNonNull(Jeu.class.getClassLoader().getResource(filename)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(fr);
        HashMap<Coordonnee, Pion> plateau = new HashMap<>();

        while (sc.hasNext()) {
            var data = sc.nextLine().split(";");
            Couleur couleur;
            List<Deplacement> listDeplacement;
            Pion pion = null;
            if (Objects.equals(data[1], "B")) {
                couleur = Couleur.BLANC;
                listDeplacement = List.of(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE);
            } else {
                couleur = Couleur.NOIR;
                listDeplacement = List.of(Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE);
            }
            if (Objects.equals(data[0], "P")) {
                pion = new Pion(couleur, Integer.parseInt(data[2]), Integer.parseInt(data[3]), listDeplacement);
            } else if (Objects.equals(data[0], "D")) {
                pion = new Dame(couleur, Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            }

            plateau.put(new Coordonnee(Integer.parseInt(data[2]), Integer.parseInt(data[3])), pion);
        }
        return plateau;
    }

    public HashMap<Coordonnee, Pion> getPlateauDeJeu() {
        return new HashMap<>(plateauDeJeu);
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean estDansLePlateau(Coordonnee coordonnee) {
        return coordonnee.getLigne() >= MIN_PLATEAU && coordonnee.getLigne() < MAX_PLATEAU &&
                coordonnee.getColonne() >= MIN_PLATEAU && coordonnee.getColonne() < MAX_PLATEAU;
    }

    public boolean estOccupee(Coordonnee coordonnee) {
        return plateauDeJeu.get(coordonnee) != null;
    }

    /**
     * Renvoie une liste de déplacement pour lesquels il y a un saut possible
     *
     * @param sauteur le pion qui va sauter
     * @return la liste des déplacements où un saut est possible
     */
    public List<Deplacement> deplacementAvecSaut(Pion sauteur) {
        List<Deplacement> deplacementsAvecSaut = new ArrayList<>();
        for (Deplacement deplacement :
                Arrays.stream(Deplacement.values()).filter(d -> d != Deplacement.SAUT).toList()) {
            Pion temp = sauteur.clone();
            Coordonnee coordonnee = temp.getCoord().plus(deplacement.getCoord());
            if (temp instanceof Dame) {
                while (estDansLePlateau(coordonnee) || (estOccupee(coordonnee) && !estOccupee(coordonnee.plus(deplacement.getCoord())))) {
                    coordonnee = temp.getCoord().plus(deplacement.getCoord());
                }
            }
            if (estDansLePlateau(coordonnee)) {
                if (estOccupee(coordonnee)) {
                    Pion saute = plateauDeJeu.get(coordonnee);
                    coordonnee = coordonnee.plus(deplacement.getCoord());
                    if (estDansLePlateau(coordonnee) && !estOccupee(coordonnee) && saute.getCouleur() != sauteur.getCouleur()) {
                        deplacement.setAlias(Deplacement.SAUT);
                        deplacementsAvecSaut.add(deplacement);
                    }
                }
            }
        }
        return deplacementsAvecSaut;
//        Coordonnee coordonnee = sauteur.getCoord().plus(deplacement.getCoord());
//        if (estDansLePlateau(coordonnee)) {
//            Pion saute = plateauDeJeu[coordonnee.getLigne()][coordonnee.getColonne()];
//            if (estOccupee(coordonnee)) {
//                coordonnee = coordonnee.plus(deplacement.getCoord());
//                if (estDansLePlateau(coordonnee) && saute.getCouleur() != sauteur.getCouleur())
//                    return plateauDeJeu[coordonnee.getLigne()][coordonnee.getColonne()] == null;
//                else return false;
//            } else return false;
//        } else return false;
    }

    /**
     * Fonction qui permet de calculer le nombre max de sauts pour un pion donné
     *
     * @param pion le pion pour lequel on veut savoir le nombre de sauts possible
     * @return le nombre de sauts possible avec le pion
     */
    public int getMaxSauts(Pion pion) {
        var original = getPlateauDeJeu();
        var joueurCourant = getJoueurCourant();
        int toReturn = maxSauts(pion) - 1;
        plateauDeJeu = original;
        this.joueurCourant = joueurCourant;
        return toReturn;
    }

    private int maxSauts(Pion pion) {
        var deplacementAvecSauts = deplacementAvecSaut(pion);
        if (deplacementAvecSauts.isEmpty()) {
            return 1;
        } else {
            Pion temp = pion.clone();
            bougerPion(temp, List.of(deplacementAvecSauts.get(0), deplacementAvecSauts.get(0)));
            return 1 + maxSauts(temp);
        }
    }

    public List<Deplacement> getDeplacementsPossibles(Pion pion) {
        Coordonnee coordonnee = pion.getCoord();
        List<Deplacement> listReturn = new ArrayList<>();
        List<Deplacement> deplacementPossibles = deplacementAvecSaut(pion).isEmpty() ? pion.getCouleur().getDeplacementsDefault() : deplacementAvecSaut(pion);
        for (Deplacement deplacement :
                deplacementPossibles) {
            Coordonnee temp = coordonnee.plus(deplacement.getCoord());
            if (estDansLePlateau(temp) && !estOccupee(temp) ||
                    (!deplacementPossibles.stream().filter(d -> d.getAlias() == Deplacement.SAUT).toList().isEmpty()
                            && estDansLePlateau(temp)
                            && !estOccupee(temp.plus(deplacement.getCoord()))
                            && !estSaute(temp))) {
                listReturn.add(deplacement);
            }
        }

        for (Deplacement deplacement : List.copyOf(listReturn)) {
            if (!deplacementAvecSaut(pion).isEmpty()) {
                listReturn.remove(deplacement);

                Deplacement.SAUT.setAlias(deplacement);
                listReturn.add(Deplacement.SAUT);
            }
        }
        return listReturn;
    }

    public final void bougerPion(Pion pion, List<Deplacement> deplacements) {
        for (Deplacement deplacement :
                deplacements) {
            plateauDeJeu.remove(pion.getCoord());
            Coordonnee coord = pion.getCoord().plus(deplacement.getCoord());
            pion.setCoord(coord);

            plateauDeJeu.put(coord, pion);
        }
        int ligneDame = -1;
        switch (pion.getCouleur()) {
            case BLANC -> ligneDame = 0;
            case NOIR -> ligneDame = 9;
        }

        if (pion.getCoord().getLigne() == ligneDame) {
            Coordonnee coordonnee = pion.getCoord();
            pion = new Dame(pion.getCouleur(), coordonnee.getLigne(), coordonnee.getColonne());
            plateauDeJeu.replace(coordonnee, pion);
        }
        joueurCourant = tabJoueurs[(Arrays.stream(tabJoueurs).toList().indexOf(joueurCourant) + 1) % 2];
    }

    public void setSaute(Coordonnee coordonnee) {
        plateauDeJeu.get(coordonnee).setEstSaute(true);
    }

    public boolean estSaute(Coordonnee coordonnee) {
        return estOccupee(coordonnee) && plateauDeJeu.get(coordonnee).isEstSaute();
    }

    public boolean finDuJeu() {
        return false;
    }
}
