package cryptofthejavadancer.Controleurs.Mouvement;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Sprites.Entites.Sprite_Entite;
import javafx.event.Event;

/**
 * Animation de mouvement vers la droite
 * @author Matthieu
 */
public class TimerAnimationMouvementDroite extends TimerAnimationMouvementEntite {

    private double distance;
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public TimerAnimationMouvementDroite(Sprite_Entite _sprite, PanelCentral _panel) {
        super(_sprite, _panel);
        distance = this.getPanel().getSpriteRef().getWidth();
    }
    
//------------------------------------------------------------------------------


    @Override
    public void handle(Event event) {
        this.getSprite().tournerVersLaDroite();
        if(this.getPosition()<3 && this.getPosition()>0) {
            this.getPanel().moveSprite(this.getSprite(), (int) (this.getPanel().getSpriteRef().getWidth()/4), -(int) (this.getPanel().getSpriteRef().getWidth()/8));
            distance -= (int) (this.getPanel().getSpriteRef().getWidth()/4);
        }
        else if(this.getPosition()<4 && this.getPosition()>2) {
            this.getPanel().moveSprite(this.getSprite(), (int) (this.getPanel().getSpriteRef().getWidth()/4), (int) (this.getPanel().getSpriteRef().getWidth()/8));
            distance -= (int) (this.getPanel().getSpriteRef().getWidth()/4);
        }
        else if(this.getPosition() == 4) {
            this.getPanel().moveSprite(this.getSprite(), (int) distance, (int) (this.getPanel().getSpriteRef().getWidth()/8));
        }
        this.nextPosition();
    }

}
