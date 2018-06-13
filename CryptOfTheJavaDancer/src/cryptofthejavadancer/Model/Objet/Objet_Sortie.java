package cryptofthejavadancer.Model.Objet;

/**
 * Sortie de la map
 * @author Matthieu
 */
public class Objet_Sortie extends Objet {

    @Override
    public boolean ramassable() {
        return false;
    }

    @Override
    public boolean destructible() {
        return false;
    }

    @Override
    public Type_Objet getType() {
        return Type_Objet.Sortie;
    }
}
