package cryptofthejavadancer.Model.Objet;

/**
 * Un diamant
 * @author Matthieu
 */
public class Objet_Diamant extends Objet {

    @Override
    public boolean ramassable() {
        return true;
    }

    @Override
    public boolean destructible() {
        return false;
    }

    @Override
    public Type_Objet getType() {
        return Type_Objet.Diamant;
    }
}
