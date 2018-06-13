package cryptofthejavadancer.Controleurs.Timer;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.Model.Carte.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Animation de translation de la carte lors des mvt de Cadence
 * @author Matthieu
 */
public class TimerTranslationMap implements EventHandler {


//---------- CONSTRUCTEURS -----------------------------------------------------
    private Map carte;                                                          //carte du jeu
    private PanelCentral panel;                                                 //Panel d'affichage
    private Timeline timer;                                                     //Timer
        
    private int deltaXRestant;                                                  //variation en X restant à faire
    private int deltaYRestant;                                                  //variation en Y restant à faire
    private int nbframe;                                                        //nb de frame de l'animation
    private int deltaX;                                                         //variation des X par frame
    private int deltaY;                                                         //variation des Y par frame
    
    public TimerTranslationMap(Map _carte, PanelCentral _panel,int _deltaX, int _deltaY) {
        //Initialisation
        this.carte = _carte;
        this.panel = _panel;
        this.deltaXRestant = _deltaX;
        this.deltaYRestant = _deltaY;
        this.nbframe = 10;
        
        //Calcul des deltaX et deltaY par frame
        this.deltaX = this.deltaXRestant/this.nbframe;
        this.deltaY = this.deltaYRestant/this.nbframe;
        
        //Creation du timer
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.panel.getTempo()/this.nbframe),this));
        this.timer.setCycleCount(this.nbframe);
    }   
//------------------------------------------------------------------------------

    //Lancement de l'animation
    public void start() {
        this.timer.play();
    }
    
    //Méthode appelée à chaque frame de l'animation
    @Override
    public void handle(Event event) {
        //Gestion des erreurs d'arrondi
        if(Math.abs(deltaXRestant) < 2*Math.abs(deltaX) || Math.abs(deltaX) > Math.abs(deltaXRestant)) {
            deltaX = deltaXRestant;
        }
        if(Math.abs(deltaYRestant) < 2*Math.abs(deltaY) || Math.abs(deltaY) > Math.abs(deltaYRestant)) {
            deltaY = deltaYRestant;
        }
        //Translantion de tous les éléments
        panel.translation(deltaX, deltaY);
        deltaXRestant -= deltaX;
        deltaYRestant -= deltaY;
    }
}
