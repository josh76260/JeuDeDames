package dame.metier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Pion Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>dec. 5, 2022</pre>
 */
public class PionTest {

    Pion pion;

    @Before
    public void before() {
        pion = new Pion(Couleur.BLANC, 1, 1, List.of(Deplacement.BAS, Deplacement.HAUT));
    }

    @After
    public void after() {
    }

    /**
     * Method: getCoord()
     */
    @Test
    public void testGetCoord() {
        Coordonnee coordonnee = new Coordonnee(1, 1);

        assertEquals(pion.getCoord(), coordonnee);
    }

    /**
     * Method: setCoord(Coordonnee coord)
     */
    @Test
    public void testSetCoord() {
        Coordonnee coordonnee = new Coordonnee(2, 2);
        pion.setCoord(coordonnee);

        assertEquals(pion.getCoord(), coordonnee);
    }

    /**
     * Method: getCouleur()
     */
    @Test
    public void testGetCouleur() {
        assertEquals(pion.getCouleur(), Couleur.BLANC);
    }

    /**
     * Method: getListDeplacement()
     */
    @Test
    public void testGetListDeplacement() {
        List<Deplacement> attendu = List.of(Deplacement.BAS, Deplacement.HAUT);

        assertEquals(pion.getListDeplacement(), attendu);
    }

    /**
     * Method: setListDeplacement(ArrayList<Deplacement> listDeplacement)
     */
    @Test
    public void testSetListDeplacement() {
        ArrayList<Deplacement> attendu = new ArrayList<>(List.of(Deplacement.DROITE, Deplacement.GAUCHE));
        pion.setListDeplacement(attendu);

        assertEquals(pion.getListDeplacement(), attendu);
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() {
        assertEquals(pion.toString(), Couleur.BLANC.getCouleur());
    }

    /**
     * Method: equals(Object o)
     */
    @Test
    public void testEquals() {
        Pion autre = new Pion(Couleur.BLANC, 1, 1, List.of(Deplacement.BAS, Deplacement.HAUT));

        assertEquals(pion, autre);

        assertEquals(pion, pion);

        String p = "";
        assertNotEquals(pion, p);

        autre = new Pion(Couleur.NOIR, 1, 1, List.of(Deplacement.BAS, Deplacement.HAUT));
        assertNotEquals(pion, autre);

        autre = new Pion(Couleur.NOIR, 2, 1, List.of(Deplacement.BAS, Deplacement.HAUT));
        assertNotEquals(pion, autre);

        autre = new Pion(Couleur.NOIR, 1, 2, List.of(Deplacement.BAS, Deplacement.HAUT));
        assertNotEquals(pion, autre);

        autre = new Pion(Couleur.NOIR, 1, 1, List.of(Deplacement.DROITE, Deplacement.GAUCHE));
        assertNotEquals(pion, autre);
    }

    /**
     * Method: hashCode()
     */
    @Test
    public void testHashCode(){
        Pion autre = new Pion(Couleur.BLANC, 1, 1, List.of(Deplacement.BAS, Deplacement.HAUT));

        assertEquals(autre.hashCode(), pion.hashCode());
    }
}
