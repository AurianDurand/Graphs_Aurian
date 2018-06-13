package cryptofthejavadancer.Model.Objet;

/**
 * Une Pelle
 * @author Matthieu
 */
public class Objet_Pelle extends Objet {

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
        return Type_Objet.Pelle;
    }
}
