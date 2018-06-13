package cryptofthejavadancer.IHM.Sprites.Animations;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite de l'animation d'attaque d'un monstre
 * @author Matthieu
 */
public final class Sprite_AnimationAttaqueMonstre extends Sprite {

    public Sprite_AnimationAttaqueMonstre(Pane panel) {
        super(GestionnaireImages.getImage("AnimationAttaqueMonstre"), 24, 24,panel);
        this.setNumero(0);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        this.setZone(24*numero, 0);
    }

    @Override
    public int getCouche() {
        return 5;
    }
}
