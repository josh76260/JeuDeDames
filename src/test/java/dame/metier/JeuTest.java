package dame.metier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Jeu Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>dec. 5, 2022</pre>
 */
public class JeuTest {

    Jeu jeu;

    @Before
    public void before() {
        jeu = new Jeu();
    }

    @After
    public void after() {
    }

    /**
     * Method: getPlateauDeJeu()
     */
    @Test
    public void testGetPlateauDeJeu() {
        Pion[][] plateau = jeu.getPlateauDeJeu();

        Pion[][] nouveau = new Pion[10][10];
        for (int j = 0; j < 4; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                nouveau[j][i] = new Pion(Couleur.NOIR, j, i, List.of(Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE));
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                nouveau[j][i] = new Pion(Couleur.BLANC, j, i, List.of(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE));
            }
        }

        assertTrue(Arrays.deepEquals(plateau, nouveau));
    }

    /**
     * Method: getJoueurCourant()
     */
    @Test
    public void testGetJoueurCourant() {
        Joueur joueurCourant = jeu.getJoueurCourant();

        assertSame(joueurCourant.getCouleur(), Couleur.BLANC);
    }

    /**
     * Method: estDansLePlateau(Coordonnee coordonnee)
     */
    @Test
    public void testEstDansLePlateau() {
        Coordonnee coordonnee = new Coordonnee(1, 1);

        assertTrue(jeu.estDansLePlateau(coordonnee));

        coordonnee.setLigne(11);
        coordonnee.setColonne(11);

        assertFalse(jeu.estDansLePlateau(coordonnee));

        coordonnee.setLigne(-1);
        coordonnee.setColonne(-1);

        assertFalse(jeu.estDansLePlateau(coordonnee));

        coordonnee.setLigne(5);
        coordonnee.setColonne(12);

        assertFalse(jeu.estDansLePlateau(coordonnee));

        coordonnee.setLigne(12);
        coordonnee.setColonne(5);

        assertFalse(jeu.estDansLePlateau(coordonnee));

        coordonnee.setLigne(5);
        coordonnee.setColonne(-1);

        assertFalse(jeu.estDansLePlateau(coordonnee));

        coordonnee.setLigne(-1);
        coordonnee.setColonne(5);

        assertFalse(jeu.estDansLePlateau(coordonnee));
    }

    /**
     * Method: estOccupee(Coordonnee coordonnee)
     */
    @Test
    public void testEstOccupee() {
        //Case occupée
        Coordonnee coordonnee = new Coordonnee(0, 1);
        assertTrue(jeu.estOccupee(coordonnee));

        //Case non-occupée
        coordonnee.setLigne(0);
        coordonnee.setColonne(0);

        assertFalse(jeu.estOccupee(coordonnee));
    }

    /**
     * Method: sautPossible(Coordonnee depart, Deplacement deplacement)
     */
    @Test
    public void testSautPossible() {
        Pion pion1 = jeu.getPlateauDeJeu()[6][1];
        Pion pion2 = jeu.getPlateauDeJeu()[3][0];

        jeu.bougerPion(pion1, Collections.singletonList(Deplacement.DIAGONAL_HAUT_DROITE));
        jeu.bougerPion(pion2, Collections.singletonList(Deplacement.DIAGONAL_BAS_DROITE));

        assertTrue(jeu.sautPossible(pion1, Deplacement.DIAGONAL_HAUT_GAUCHE));

        assertFalse(jeu.sautPossible(pion1, Deplacement.DIAGONAL_HAUT_DROITE));

        Pion pion3 = jeu.getPlateauDeJeu()[6][3];
        assertFalse(jeu.sautPossible(pion3, Deplacement.DIAGONAL_HAUT_GAUCHE));

        pion1.setCoord(new Coordonnee(11, 11));
        assertFalse(jeu.sautPossible(pion1, Deplacement.DIAGONAL_HAUT_DROITE));
    }

    /**
     * Method: bougerPion(Pion pion, List<Deplacement> deplacements)
     */
    @Test
    public void testBougerPion() {
        Pion pion = jeu.getPlateauDeJeu()[6][1];
        jeu.bougerPion(pion, Collections.singletonList(Deplacement.DIAGONAL_BAS_DROITE));

        Coordonnee coordonnee = new Coordonnee(6, 1);
        coordonnee = coordonnee.plus(Deplacement.DIAGONAL_BAS_DROITE.getCoord());
        assertEquals(coordonnee, pion.getCoord());
    }
}
