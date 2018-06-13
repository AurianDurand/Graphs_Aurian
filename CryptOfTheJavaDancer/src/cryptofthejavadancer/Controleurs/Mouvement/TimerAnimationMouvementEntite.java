package cryptofthejavadancer.Controleurs.Mouvement;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Sprites.Entites.Sprite_Entite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Animation générique de mouvement d'une entité
 * @author Matthieu
 */
public abstract class TimerAnimationMouvementEntite implements EventHandler {

    private Sprite_Entite sprite;                                               //le sprite de l'entité
    private PanelCentral panel;                                                 //le panel d'affichage
    private Timeline timer;                                                     //le timer de l'animation
    private int duree;                                                          //le temps totale de l'animation
    private int position;                                                       //le numero de la frame en cours
    
    public TimerAnimationMouvementEntite(Sprite_Entite _sprite, PanelCentral _panel) {
        //Initialisation
        this.sprite = _sprite;
        this.panel = _panel;
        this.position = 0;
        this.duree = this.panel.getTempo()/6;
        //Creation du timer
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.duree),this));
        this.timer.setCycleCount(6);
    }
    
    //Lancement de l'animation
    public void start() {
        this.timer.play();
    }
    
    //Renvoie le numero de la frame en cours dans l'animation
    protected int getPosition() {
        return this.position;
    }
    
    //Passe à la frame suivante
    protected void nextPosition() {
        this.position++;
    }
    
    //Renvoie le sprite de l'animation
    protected Sprite_Entite getSprite() {
        return this.sprite;
    }
    
    //Renvoie le panel d'affichage
    protected PanelCentral getPanel() {
        return this.panel;
    }
    
    //Méthode appelée à chaque frame de l'animation
    @Override
    public abstract void handle(Event event);
    
}
