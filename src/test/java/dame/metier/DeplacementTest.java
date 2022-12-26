package dame.metier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeplacementTest {

    @Test
    public void setAlias() {
        Deplacement.DIAGONAL_BAS_DROITE.setAlias(Deplacement.DIAGONAL_HAUT_DROITE);

        assertEquals(Deplacement.DIAGONAL_HAUT_DROITE, Deplacement.DIAGONAL_BAS_DROITE.getAlias() );
    }
}