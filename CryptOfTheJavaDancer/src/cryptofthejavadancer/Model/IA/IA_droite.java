/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Entites.Entite;

/**
 *
 * @author aurian
 */
public class IA_droite extends IA {

    public IA_droite(Entite _entite) {
        super(_entite);
    }

    @Override
    public Type_Action action() {
        //this.getEntite().setIA(new IA_Immobile(this.getEntite()));
        return Type_Action.deplacement_droite;
    }
    
}
