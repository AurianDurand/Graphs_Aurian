package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.IHM.Sprites.Entites.Sprite_Entite;
import cryptofthejavadancer.Model.Entites.Entite_Squelette;

/**
 * Controleur spécifique aux squelettes
 * @author Matthieu
 */
public class Controleur_Squelette {

    private Entite_Squelette modele;                                            //Le squelette
    private Sprite_Entite vue;                                                  //son sprite
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Controleur_Squelette(Entite_Squelette _modele,Sprite_Entite _vue) {
        //Initialisation
        this.modele = _modele;
        this.vue = _vue;
        //Lie le controleur au modèle
        this.modele.setControleur(this);
    }
//------------------------------------------------------------------------------

    //Méthode appelée quand le squelette lève les bras
    public void leverLesBras() {
        if(this.vue.getNumero()<10) {
            this.vue.setNumero(this.vue.getNumero()+10);
        }
    }
    
    //Méthode appelée quand le squelette baisse les bras
    public void baisserLesBras() {
        if(this.vue.getNumero()>=10) {
            this.vue.setNumero(this.vue.getNumero()-10);
        }
    }

}
