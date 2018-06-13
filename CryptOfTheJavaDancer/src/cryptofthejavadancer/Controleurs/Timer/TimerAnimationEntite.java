package cryptofthejavadancer.Controleurs.Timer;

import cryptofthejavadancer.IHM.Scene.ScenePrincipale;
import cryptofthejavadancer.Model.Carte.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Animation des entités (danse)
 * @author Matthieu
 */   

public class TimerAnimationEntite implements EventHandler {

    private Map carte;                                                          //Carte du jeu
    private ScenePrincipale scene;                                              //Scene générale
    private Timeline timer;                                                     //Timer de l'animation de danse
    private int duree;                                                          //Durée totale de l'animation
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public TimerAnimationEntite(Map _carte, ScenePrincipale _scene, int miliSec) {
        //Initialisation
        this.carte = _carte;
        this.scene = _scene;
        this.duree = miliSec/4;
        //Creation du timer
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.duree),this));
        this.timer.setCycleCount(Timeline.INDEFINITE);
    }
   
//------------------------------------------------------------------------------
 
    //Lancement de l'animation
    public void start() {
        this.timer.play();
    }
    
    //Arret du timer si fin de partie (désactivé)
    public void finPartie() {
        this.timer.stop();
    }
    
    //Méthode appelée à chaque frame de l'animation
    @Override
    public void handle(Event event) {
        this.scene.danse();
    }
    

}
