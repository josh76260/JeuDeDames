package metier;

public class Plateau {
    private Pion[][] plateau;

    public Plateau() {
        plateau = new Pion[10][10];
        for (int j = 0; j < 4; j++) {
            for (int i = 1-j%2; i < 10; i+=2) {
                plateau[j][i] = new Pion(Couleur.NOIR, i, j);
            }
        }

        for (int j = 6; j < 10; j++) {
            for (int i = 1-j%2; i < 10; i+=2) {
                plateau[j][i] = new Pion(Couleur.BLANC, i, j);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Pion[] ligne :
                plateau) {
            for (Pion p :
                    ligne) {
                if (p != null) toReturn.append(p).append(" ");
                else toReturn.append("null ");
            }
            toReturn.append("\n");
        }

        return toReturn.toString();
    }
}
