package dame.metier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeplacementTest {

    @Test
    public void setAlias() {
        Deplacement.HAUT.setAlias(Deplacement.BAS);

        assertEquals(Deplacement.HAUT.getAlias(), Deplacement.BAS);
    }
}