package cryptofthejavadancer.Model.Carte.Cases;

import cryptofthejavadancer.Model.Carte.Map;

/**
 * Un mur
 * @author Matthieu
 */
public class Case_Mur extends Case {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Case_Mur(int _ligne, int _colonne, Map _map) {
        super(_ligne, _colonne,_map);
    }

//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public Type_Case getType() {
        return Type_Case.Mur;
    }

    @Override
    public boolean franchissable() {
        return false;
    }

    @Override
    public int destructible() {
        return 1;
    }

//------------------------------------------------------------------------------

}
