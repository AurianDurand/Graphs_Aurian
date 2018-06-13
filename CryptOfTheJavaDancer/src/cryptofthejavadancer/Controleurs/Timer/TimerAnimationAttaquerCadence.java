package cryptofthejavadancer.Controleurs.Timer;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Son.Lecteur;
import cryptofthejavadancer.IHM.Sprites.Animations.Type_SpriteAnimation;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.IA.Type_Action;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Animation d'attaque de Cadence
 * @author Matthieu
 */
public class TimerAnimationAttaquerCadence implements EventHandler {

    private Coordonnees coo;                                                    //Position de la cible de l'attaaque
    private Type_SpriteAnimation animation;                                     //Type d'attaque utilisée
    private PanelCentral vue;                                                   //Panel d'affichage
    private Timeline timer;                                                     //Timer de l'animation
    private int duree;                                                          //Durée de l'animation
    private int position;                                                       //Numéro de la frame de l'animation
    
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public TimerAnimationAttaquerCadence(Coordonnees _coo,PanelCentral _vue, Type_Action _action) {
        //Initialisation
        this.coo = _coo;
        this.vue = _vue;
        this.position = 0;
        //Détermine le type d'animation
        this.animation = null;
        switch(_action) {
            case interagir_droite: this.animation = Type_SpriteAnimation.attaque_droite; break;
            case interagir_gauche: this.animation = Type_SpriteAnimation.attaque_gauche; break;
            case interagir_haut: this.animation = Type_SpriteAnimation.attaque_haut; break;
            case interagir_bas: this.animation = Type_SpriteAnimation.attaque_bas; break;
        }
        //Calcul de la durée totale de l'animation en fonction du tempo du timer principal
        this.duree = this.vue.getTempo()/15;
        //Création du timer
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.duree),this));
        this.timer.setCycleCount(6);
    }
    
//------------------------------------------------------------------------------

    //Lancement du timer
    public void start() {
        Lecteur.play("Cadence_Attaque.mp3", false,0.3);
        this.timer.play();
    }
    
    //Méthode appelée à chaque frame de l'animation
    @Override
    public void handle(Event event) {        
        switch(position) {
            case 2 : this.vue.ajouterAnimation(coo, this.animation); break;
            case 3 : this.vue.avancerAnimation(coo); break;
            case 4 : this.vue.avancerAnimation(coo); break;
            case 5 : this.vue.supprimerAnimation(coo); break;
        }
        position++;
    }

}
