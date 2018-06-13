package cryptofthejavadancer.IHM.Sprites.Entites;

import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Classe générique d'un Sprite d'une entitée
 * @author Matthieu
 */
public abstract class Sprite_Entite extends Sprite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_Entite(Image _image, int _width, int _height, Pane _panel) {
        super(_image, _width, _height, _panel);
    }
    
//------------------------------------------------------------------------------

    //Méthode appelée lors de l'animation de danse (en 4 frames)
    public void danse() {
        int serie = this.getNumero()/4;
        this.setNumero((this.getNumero()+1-4*serie)%4+4*serie);
    }
    
    //Tourne le sprite vers la droite
    public void tournerVersLaDroite() {
        if((this.getNumero()%8)<4) {
            this.setNumero(this.getNumero()+4);
        }
    }
    
    //Tourne le sprite vers la gauche
    public void tournerVersLaGauche() {
        if((this.getNumero()%8)>=4) {
            this.setNumero(this.getNumero()-4);
        }
    }
    
}
