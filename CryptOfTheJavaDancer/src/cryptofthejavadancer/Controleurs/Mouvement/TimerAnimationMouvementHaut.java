package cryptofthejavadancer.Controleurs.Mouvement;

import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Sprites.Entites.Sprite_Entite;
import javafx.event.Event;

/**
 * Animation de mouvement vers le haut
 * @author Matthieu
 */
public class TimerAnimationMouvementHaut extends TimerAnimationMouvementEntite {

    private double distance;
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public TimerAnimationMouvementHaut(Sprite_Entite _sprite, PanelCentral _panel) {
        super(_sprite, _panel);
        distance = this.getPanel().getSpriteRef().getWidth();
    }
    
//------------------------------------------------------------------------------


    @Override
    public void handle(Event event) {
        if(this.getPosition()<4 && this.getPosition()>0) {
            this.getPanel().moveSprite(this.getSprite(), 0, -(int) (this.getPanel().getSpriteRef().getWidth()*9/(3*8)));
            distance -= (int) (this.getPanel().getSpriteRef().getWidth()*9/(3*8));
        }
        else if(this.getPosition()<4 && this.getPosition()>3) {
            this.getPanel().moveSprite(this.getSprite(), 0, (int) (this.getPanel().getSpriteRef().getWidth()/8));
            distance += (int) (this.getPanel().getSpriteRef().getWidth()/8);
        }
        else if(this.getPosition() == 4) {
            this.getPanel().moveSprite(this.getSprite(), 0, -(int) distance);
        }
        this.nextPosition();
    }

}
