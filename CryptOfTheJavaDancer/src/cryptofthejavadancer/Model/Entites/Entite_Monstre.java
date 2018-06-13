package cryptofthejavadancer.Model.Entites;

import cryptofthejavadancer.Controleurs.Controleur.Controleur_Action;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.IA.Type_Action;

/**
 * Classe générique des monstres
 * @author Matthieu
 */
public abstract class Entite_Monstre extends Entite {

    
    public Controleur_Action controleur;
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Entite_Monstre(int vieInitiale) {
        super(vieInitiale);
        this.controleur = null;
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public void setControleur(Controleur_Action _controleur) {
        this.controleur = _controleur;
    }
    
    //Gestion de l'attaque des monstres
    @Override
    public boolean attaquer(Entite entite,Type_Action action) {
        boolean res = false;
        if(entite.getType() == Type_Entite.Cadence) {
            entite.changeVie(1);
            this.controleur.attaquer(new Coordonnees(entite.getCase().getLigne(),entite.getCase().getColonne()),action);
            res = true;
        }
        else {
            System.out.println("Action impossible - les montres ne peuvent attaquer que Cadence");
        }
        return res;
    }
//------------------------------------------------------------------------------

}
