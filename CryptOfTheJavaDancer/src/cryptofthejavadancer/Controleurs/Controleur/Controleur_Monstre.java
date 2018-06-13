package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.Controleurs.Timer.TimerAnimationAttaquerMonstre;
import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.IA.Type_Action;

/**
 * Controleur spécifique aux monstres (attaque)
 * @author Matthieu
 */
public class Controleur_Monstre extends Controleur_Action {

    private PanelCentral vue;                                                   //Panel d'affichage
    private Entite modele;                                                      //Le monstre
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Controleur_Monstre(PanelCentral _vue,Entite _modele) {
        //Initialisation
        this.vue = _vue;
        this.modele = _modele;
        //Lie le controleur au modèle
        this.modele.setControleur(this);
    }
    
//------------------------------------------------------------------------------

    //Méthode appelée lors de l'attaque par le monstre
    @Override
    public void attaquer(Coordonnees coo,Type_Action action)
    {
        //lance l'animation d'attaque avec le son adapté au type de monstre
        TimerAnimationAttaquerMonstre timer = new TimerAnimationAttaquerMonstre(coo,this.vue);
        String nomFichier = "Cadence_Attaque.mp3";
        switch(this.modele.getType()) {
            case ChauveSouris: nomFichier = "ChauveSouris_Attaque.mp3";
                break;
            case Squelette: nomFichier = "Squelette_Attaque.mp3";
                break;
            case SlimeVert: case SlimeBleu: case SlimeJaune: nomFichier = "Slime_Attaque.mp3";
                break;
        }
        timer.start(nomFichier);
    }
    
}
