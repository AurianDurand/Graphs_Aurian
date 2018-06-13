package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Entites.Entite;

/**
 * Une IA relativement simple...
 * @author Matthieu
 */
public class IA_Immobile extends IA {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public IA_Immobile(Entite _entite) {
        super(_entite);
    }

//------------------------------------------------------------------------------

    @Override
    public Type_Action action() {
        return Type_Action.attendre; //To change body of generated methods, choose Tools | Templates.
    }
}
