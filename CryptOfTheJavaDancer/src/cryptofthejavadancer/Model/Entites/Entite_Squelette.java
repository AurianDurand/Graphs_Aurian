package cryptofthejavadancer.Model.Entites;

import cryptofthejavadancer.Controleurs.Controleur.Controleur_Squelette;
import cryptofthejavadancer.Model.IA.IA_Immobile;

/**
 * Squelette
 * @author Matthieu
 */
public class Entite_Squelette extends Entite_Monstre {

    private Controleur_Squelette controleur;
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Entite_Squelette() {
        super(1);
        this.setIA(new IA_Immobile(this));
        this.controleur = null;
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Controleur spécifique pour la levée des bras
    public void setControleur(Controleur_Squelette _controleur) {
        this.controleur = _controleur;
    }
    
    @Override
    public Type_Entite getType() {
        return Type_Entite.Squelette;
    }
    
//------------------------------------------------------------------------------

    @Override
    public int getGainOr() {
        return 5;
    }
    
    //Lève les bras
    public void leverLesBras() {
        if(this.controleur != null) {
            this.controleur.leverLesBras();
        }
    }
    
    //Baisse les bras
    public void baisserLesBras() {
        if(this.controleur != null) {
            this.controleur.baisserLesBras();
        }
    }


}
