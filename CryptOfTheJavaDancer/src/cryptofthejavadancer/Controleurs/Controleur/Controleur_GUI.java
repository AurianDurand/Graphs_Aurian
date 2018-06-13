package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.IHM.Scene.PanelGUI;
import cryptofthejavadancer.Model.Entites.Entite_Cadence;

/**
 * Controleur de l'interface graphique (pelle,piece,diamant)
 * @author Matthieu
 */
public final class Controleur_GUI {

    private PanelGUI vue;                                                       //panel de l'interface graphique
    private Entite_Cadence modele;                                              //Cadence
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Controleur_GUI(PanelGUI _vue, Entite_Cadence _modele) {
        //Initialisation
        this.vue = _vue;
        this.modele = _modele;
        this.modele.setControleurGUI(this);
        //met les compteurs visuels aux valeurs de départ
        this.changerDiamant(this.modele.getNombreDiamants());
        this.changerPelle(this.modele.getPelle());
        this.changerPiece(this.modele.getGainOr());
    }
    
//------------------------------------------------------------------------------

    //Méthode appelée si le nombre de diamants collectés change
    public void changerDiamant(int nombre) {
        this.vue.changerDiamant(nombre);
    }
    
    //Méthode appelée si le nombre de pieces collectées change
    public void changerPiece(int nombre) {
        this.vue.changerPiece(nombre);
    }
    
    //Méthode appelée si la pelle est ramassée
    public void changerPelle(boolean bool) {
        this.vue.changerPelle(bool);
    }
}
