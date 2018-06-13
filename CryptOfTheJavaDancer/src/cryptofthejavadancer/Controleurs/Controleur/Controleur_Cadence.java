package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.Controleurs.Timer.TimerAnimationAttaquerCadence;
import cryptofthejavadancer.Controleurs.Timer.TimerAnimationCreuser;
import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Entites.Entite_Cadence;
import cryptofthejavadancer.Model.IA.Type_Action;

/**
 * Controleur spécifique à Cadence (creuser et attaquer)
 * @author Matthieu
 */
public class Controleur_Cadence extends Controleur_Action {

    private PanelCentral vue;                                                   //le panel d'affichage
    private Entite_Cadence modele;                                              //Cadence
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Controleur_Cadence(PanelCentral _vue,Entite_Cadence _modele) {
        //initialisation
        this.vue = _vue;
        this.modele = _modele;
        //lie le controleur au modèle
        this.modele.setControleur(this);
    }
    
//------------------------------------------------------------------------------

    //Méthode appelée quand Cadence creuse (lance l'animation)
    public void creuser(Coordonnees coo) {
        TimerAnimationCreuser timer = new TimerAnimationCreuser(coo, this.vue, this.modele.getPelle());
        timer.start();
    }
    
    //méthode appelée quand Cadence attaque (lance l'animation)
    public void attaquer(Coordonnees coo,Type_Action action)
    {
        TimerAnimationAttaquerCadence timer = new TimerAnimationAttaquerCadence(coo,this.vue,action);
        timer.start();
    }
    
}
