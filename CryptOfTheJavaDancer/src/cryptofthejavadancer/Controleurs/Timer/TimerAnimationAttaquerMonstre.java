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
 * Animation de l'attaque par un monstre
 * @author Matthieu
 */
public class TimerAnimationAttaquerMonstre implements EventHandler {

    private Coordonnees coo;                                                    //Coordonnées de la cible de l'attaque
    private Type_SpriteAnimation animation;                                     //Type d'attaque
    private PanelCentral vue;                                                   //Panel d'affichage
    private Timeline timer;                                                     //Timer de l'animation
    private int duree;                                                          //Durée totale de l'animation
    private int position;                                                       //Numéro de la frame en cours
    
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public TimerAnimationAttaquerMonstre(Coordonnees _coo,PanelCentral _vue) {
        //Initialisation
        this.coo = _coo;
        this.vue = _vue;
        this.animation = Type_SpriteAnimation.attaque_monstre;
        this.position = 0;
        //Création du timer
        this.duree = this.vue.getTempo()/18;
        this.timer = new Timeline(new KeyFrame(Duration.millis(this.duree),this));
        this.timer.setCycleCount(7);
    }
    
//------------------------------------------------------------------------------

    //Démarrage de l'animation
    public void start(String nomFichier) {
        Lecteur.play(nomFichier, false,0.3);
        this.timer.play();
    }
    
    //Méthode appelée à chaque frame de l'animation
    @Override
    public void handle(Event event) {        
        switch(position) {
            case 2 : this.vue.ajouterAnimation(coo, this.animation); break;
            case 3 : this.vue.avancerAnimation(coo); break;
            case 4 : this.vue.avancerAnimation(coo); break;
            case 5 : this.vue.avancerAnimation(coo); break;
            case 6 : this.vue.supprimerAnimation(coo); break;
        }
        position++;
    }

}
