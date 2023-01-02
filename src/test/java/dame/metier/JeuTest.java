package dame.metier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
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
        HashMap<Coordonnee, Pion> plateau = jeu.getPlateauDeJeu();

        HashMap<Coordonnee, Pion> nouveau = new HashMap<>();
        for (int j = 0; j < 4; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                nouveau.put(new Coordonnee(j, i), new Pion(Couleur.NOIR, j, i,
                        List.of(Deplacement.DIAGONAL_BAS_DROITE, Deplacement.DIAGONAL_BAS_GAUCHE)));
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1 - j % 2; i < 10; i += 2) {
                nouveau.put(new Coordonnee(j, i), new Pion(Couleur.BLANC, j, i,
                        List.of(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_HAUT_GAUCHE)));
            }
        }

        assertEquals(plateau.hashCode(), nouveau.hashCode());
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
        Pion pion1 = jeu.getPlateauDeJeu().get(new Coordonnee(6,1));
        Pion pion2 = jeu.getPlateauDeJeu().get(new Coordonnee(3,0));

        jeu.bougerPion(pion1, Collections.singletonList(Deplacement.DIAGONAL_HAUT_DROITE));
        jeu.bougerPion(pion2, Collections.singletonList(Deplacement.DIAGONAL_BAS_DROITE));

        assertFalse(jeu.deplacementAvecSaut(pion1).isEmpty());

        assertTrue(jeu.deplacementAvecSaut(pion2).isEmpty());

        Pion pion3 = jeu.getPlateauDeJeu().get(new Coordonnee(6,3));
        assertTrue(jeu.deplacementAvecSaut(pion3).isEmpty());

        pion1.setCoord(new Coordonnee(11, 11));
        assertTrue(jeu.deplacementAvecSaut(pion1).isEmpty());
    }

    /**
     * Method: bougerPion(Pion pion, List<Deplacement> deplacements)
     */
    @Test
    public void testBougerPion() {
        Pion pion = jeu.getPlateauDeJeu().get(new Coordonnee(6,1));
        jeu.bougerPion(pion, Collections.singletonList(Deplacement.DIAGONAL_BAS_DROITE));

        Coordonnee coordonnee = new Coordonnee(6, 1);
        coordonnee = coordonnee.plus(Deplacement.DIAGONAL_BAS_DROITE.getCoord());
        assertEquals(coordonnee, pion.getCoord());
    }
}
