package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Objet.Objet;

/**
 * Controleur d'objet (suppression)
 * @author Matthieu
 */
public class Controleur_Objet {

    private Objet modele;                                                       //l'objet
    private Sprite vue;                                                         //son sprite
    private PanelCentral panel;                                                 //le panel d'affichage
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    
    public Controleur_Objet(Objet _modele, Sprite _vue,PanelCentral _panel) {
        //Initialisation
        this.modele = _modele;
        this.vue = _vue;
        this.panel = _panel;
        //Lie le controleur au modèle
        this.modele.setControleur(this);
    }
//------------------------------------------------------------------------------

    //Méthode appelée lors de la suppression de l'objet
    public void Supprimer() {
        this.panel.supprimerObjet(new Coordonnees(modele.getCase().getLigne(),modele.getCase().getColonne()));
    }
    
}
