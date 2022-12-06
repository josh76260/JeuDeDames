package dame.metier;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Joueur Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>dec. 5, 2022</pre>
 */
public class JoueurTest {

    Joueur joueur;

    @Before
    public void before() {
        joueur = new Joueur("Toto", Couleur.BLANC);
    }

    @After
    public void after() {
    }

    /**
     * Method: getNom()
     */
    @Test
    public void testGetNom() {
        assertEquals(joueur.getNom(), "Toto");
    }

    /**
     * Method: getCouleur()
     */
    @Test
    public void testGetCouleur() {
        assertEquals(joueur.getCouleur(), Couleur.BLANC);
    }


}
