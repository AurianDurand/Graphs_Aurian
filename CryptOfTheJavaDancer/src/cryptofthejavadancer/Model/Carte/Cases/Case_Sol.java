package cryptofthejavadancer.Model.Carte.Cases;

import cryptofthejavadancer.Model.Carte.Map;

/**
 * Le sol
 * @author Matthieu
 */
public class Case_Sol extends Case {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Case_Sol(int _ligne, int _colonne, Map _map) {
        super(_ligne, _colonne, _map);
    }

//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public Type_Case getType() {
        return Type_Case.Sol;
    }

    @Override
    public int destructible() {
        return 0;
    }
    
//------------------------------------------------------------------------------


}
