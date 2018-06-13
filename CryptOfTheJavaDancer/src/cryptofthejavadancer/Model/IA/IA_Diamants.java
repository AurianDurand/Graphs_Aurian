package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Graphes.Vertex;
import cryptofthejavadancer.Model.Entites.Entite;

public class IA_Diamants extends IA {

    public IA_Diamants(Entite _entite) {
        super(_entite);
    }

    @Override
    public Type_Action action() {
        return null;
    }

    private Vertex closerDiamant(){
        return null;
    }

}
