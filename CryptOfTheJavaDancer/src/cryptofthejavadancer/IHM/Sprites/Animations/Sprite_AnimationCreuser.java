package cryptofthejavadancer.IHM.Sprites.Animations;

import cryptofthejavadancer.IHM.Sprites.Objets.*;
import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import cryptofthejavadancer.Model.Carte.Aleatoire;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Sprite d'animation de minage
 * @author Matthieu
 */
public final class Sprite_AnimationCreuser extends Sprite {

    public Sprite_AnimationCreuser(Pane panel, int numero) {
        super(GestionnaireImages.getImage("Pelle"), 24, 24,panel);
        this.setNumero(numero);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        this.setZone(0, 24*numero);
    }

    @Override
    public int getCouche() {
        return 5;
    }
}
