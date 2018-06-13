package cryptofthejavadancer.Model.Carte.Cases;

import cryptofthejavadancer.Model.Carte.Map;

/**
 * Un mur dur
 * @author Matthieu
 */
public class Case_MurDur extends Case_Mur {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Case_MurDur(int _ligne, int _colonne, Map _map) {
        super(_ligne, _colonne, _map);
    }

//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public Type_Case getType() {
        return Type_Case.MurDur;
    }

    @Override
    public int destructible() {
        return 2;
    }
    
//------------------------------------------------------------------------------

}
