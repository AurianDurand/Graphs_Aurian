package cryptofthejavadancer.Controleurs.Timer;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Son.Lecteur;
import cryptofthejavadancer.IHM.Sprites.Animations.Type_SpriteAnimation;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Animation de minage
 * @author Matthieu
 */
public class TimerAnimationCreuser implements EventHandler {

    private Coordonnees coo;                                                    //Coordonnée du mur miné
    private Type_SpriteAnimation animation;                                     //Type d'animation
    private PanelCentral vue;                                                   //Panel d'affichage
    private Timeline timer;                                                     //Timer de l'animation
    private int duree;                                                          //Durée totale de l'animation
    private int position;                                                       //Numéro de la frame en cours
    
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public TimerAnimationCreuser(Coordonnees _coo,PanelCentral _vue, boolean _amelioree) {
        //Initialisation
        this.coo = _coo;
        this.vue = _vue;
        this.position = 0;
        //Détermine l'animation à faire
        this.animation = Type_SpriteAnimation.creuser;
        if(_amelioree) {
            this.animation = Type_SpriteAnimation.creuser_amelioree;
        }
        //Création du timer
        this.duree = this.vue.getTempo()/5;
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.duree),this));
        this.timer.setCycleCount(4);
    }
    
//------------------------------------------------------------------------------

    //Lance l'animation
    public void start() {
        this.timer.play();
    }
    
    //Méthode appelée à chaque frame de l'animation
    @Override
    public void handle(Event event) {        
        switch(position) {
            case 0 : this.vue.ajouterAnimation(coo, this.animation);  break;
            case 1 : this.vue.supprimerMur(coo); 
                     Lecteur.play("Cadence_Creuse.mp3", false,0.6); break;
            case 3 : this.vue.supprimerAnimation(coo); break;
        }
        position++;
    }

}
