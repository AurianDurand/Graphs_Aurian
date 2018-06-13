package cryptofthejavadancer.Controleurs.Timer;

import cryptofthejavadancer.IHM.Scene.ScenePrincipale;
import cryptofthejavadancer.IHM.Son.Lecteur;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.Entites.Entite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Timer principal basé sur la pulsation de la musique (Disco Descent)
 * @author Matthieu
 */
public class TimerPrincipal implements EventHandler {

    private Map carte;                                                          //Carte du jeu
    private ScenePrincipale scene;                                              //Scene principale
    private Timeline timer;                                                     //Timer
    private int duree;                                                          //Durée total d'un cycle
    private TimerAnimationEntite timerDanse;                                    //Timer de danse des entites
    
   
//---------- CONSTRUCTEURS -----------------------------------------------------
    
    public TimerPrincipal(Map _carte, ScenePrincipale _scene) {
        //Initialisation
        this.carte = _carte;
        this.scene = _scene;
        this.duree = 522;
        //Creation du timer principal
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.duree),this));
        this.timer.setCycleCount(Timeline.INDEFINITE);
        //Création du timer de danse des entités
        this.timerDanse = new TimerAnimationEntite(_carte,_scene,this.duree);
    }

//------------------------------------------------------------------------------
    
    //Lance les timers et la musique
    public void start() {
        this.timer.play();
        this.timerDanse.start();
        this.demarrerMusique();
    }
    
    //Lance la musique
    public void demarrerMusique() {
        Lecteur.play("zone1_1.mp3", true,0.2);
    }
    
    //Renvoie la durée d'un cycle
    public int getTempo() {
        return this.duree; 
    }
    
    //Fin de la partie (arret des timers)
    public void finPartie() {
        this.timer.stop();
    }
    
    //Méthode appelée à chaque pulsation de la musique
    @Override
    public void handle(Event event) {
        //Le joueur joue
        this.carte.getJoueur().agir();
        //Les monstres jouent
        for(Entite e : this.carte.getListeEntite()) {
            e.agir();
        }
        //Animation du sol
        scene.solDisco();
    }
    

}
