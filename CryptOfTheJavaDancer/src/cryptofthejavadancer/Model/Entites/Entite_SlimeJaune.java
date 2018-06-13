package cryptofthejavadancer.Model.Entites;

import cryptofthejavadancer.Model.IA.IA_Immobile;

/**
 * Slime Jaune
 * @author Matthieu
 */
public class Entite_SlimeJaune extends Entite_Monstre {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Entite_SlimeJaune() {
        super(1);
        this.setIA(new IA_Immobile(this));
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public Type_Entite getType() {
        return Type_Entite.SlimeJaune;
    }
    
//------------------------------------------------------------------------------

    @Override
    public int getGainOr() {
        return 7;
    }


}
