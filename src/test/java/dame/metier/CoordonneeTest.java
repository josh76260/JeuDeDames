package dame.metier;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Coordonnee Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>dec. 5, 2022</pre>
 */
public class CoordonneeTest {

    Coordonnee coordonnee;

    @Before
    public void before() {
        coordonnee = new Coordonnee(1, 1);
    }

    @After
    public void after() {
    }

    /**
     * Method: getLigne()
     */
    @Test
    public void testGetLigne() {
        assertEquals(coordonnee.getLigne(), 1);
    }

    /**
     * Method: setLigne(int ligne)
     */
    @Test
    public void testSetLigne() {
        coordonnee.setLigne(5);
        assertEquals(coordonnee.getLigne(), 5);
    }

    /**
     * Method: getColonne()
     */
    @Test
    public void testGetColonne() {
        assertEquals(coordonnee.getColonne(), 1);
    }

    /**
     * Method: setColonne(int colonne)
     */
    @Test
    public void testSetColonne() {
        coordonnee.setColonne(5);
        assertEquals(coordonnee.getColonne(), 5);
    }

    /**
     * Method: toString()
     */
    @Test
    public void testToString() {
        assertEquals(coordonnee.toString(), "ligne=1, colonne=1");
    }

    /**
     * Method: plus(Coordonnee other)
     */
    @Test
    public void testPlus() {
        coordonnee = coordonnee.plus(Deplacement.BAS.getCoord());
        assertEquals(coordonnee.getLigne(), 2);

        coordonnee = coordonnee.plus(Deplacement.HAUT.getCoord());
        assertEquals(coordonnee.getLigne(), 1);
    }

    /**
     * Method: equals(Object o)
     */
    @Test
    public void testEquals() {
        Coordonnee coordonnee1 = new Coordonnee(1, 1);
        Coordonnee coordonnee2 = new Coordonnee(1, 1);

        assertEquals(coordonnee1, coordonnee2);
    }
}
