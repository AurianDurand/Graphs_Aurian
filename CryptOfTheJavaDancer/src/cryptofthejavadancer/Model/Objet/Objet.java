package cryptofthejavadancer.Model.Objet;

import cryptofthejavadancer.Controleurs.Controleur.Controleur_Objet;
import cryptofthejavadancer.Model.Carte.Cases.Case;

/**
 * Classe générique des Objet
 * @author Matthieu
 */
public abstract class Objet {

    private Case saCase;                                                        //Case où se situe l'objet
    private Controleur_Objet controleur;                                        //Controleur de l'objet
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Objet() {
        this.saCase = null;
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Fixe la case de l'objet
    public void setCase(Case _saCase) {
        this.saCase = _saCase;
    }
    
    //Renvoie la case de l'objet
    public Case getCase() {
        return this.saCase;
    }
    
    //Fixe le controleur
    public void setControleur(Controleur_Objet _controleur) {
        this.controleur = _controleur;
    }
    
//------------------------------------------------------------------------------

    //L'objet est-il ramassable
    public abstract boolean ramassable();
    //L'objet est-il destructible
    public abstract boolean destructible();
    //Type de l'objet
    public abstract Type_Objet getType();
    
    //Méthode appelée quand l'objet est ramassé
    public void ramasser() {
        if(this.ramassable()) {
            this.saCase.setObjet(null);
            this.saCase.getMap().getListeObjet().remove(this);
            this.controleur.Supprimer();
        }
    }
    
}
