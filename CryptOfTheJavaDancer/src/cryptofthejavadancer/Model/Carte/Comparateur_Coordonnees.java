package cryptofthejavadancer.Model.Carte;

import java.util.Comparator;

/**
 * Comparateur de coordonnées
 * @author Matthieu
 */
public class Comparateur_Coordonnees implements Comparator<Coordonnees> {

    @Override
    public int compare(Coordonnees o1, Coordonnees o2) {
        int val = 1;
        if(o1.getLigne() < o2.getLigne()) {
            val = -1;
        }
        else if(o1.getLigne() == o2.getLigne() && o1.getColonne() < o2.getColonne()) {
            val = -1;
        }
        else if(o1.getLigne() == o2.getLigne() && o1.getColonne() == o2.getColonne()) {
            val = 0;
        }
        return val;
    }


}
