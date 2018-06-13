package cryptofthejavadancer.Model.Carte.Cases;

import cryptofthejavadancer.Model.Carte.Map;

/**
 * Un mur indestructible
 * @author Matthieu
 */
public class Case_MurIndestructible extends Case_Mur {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Case_MurIndestructible(int _ligne, int _colonne, Map _map) {
        super(_ligne, _colonne, _map);
    }

//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public Type_Case getType() {
        return Type_Case.MurIndestructible;
    }

    @Override
    public int destructible() {
        return 0;
    }
    
//------------------------------------------------------------------------------

}
