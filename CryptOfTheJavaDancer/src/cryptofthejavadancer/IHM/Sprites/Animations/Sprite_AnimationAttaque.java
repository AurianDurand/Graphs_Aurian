package cryptofthejavadancer.IHM.Sprites.Animations;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite d'animation d'une attaque de Cadence
 * @author Matthieu
 */
public final class Sprite_AnimationAttaque extends Sprite {

    public Sprite_AnimationAttaque(Pane panel, int numero) {
        super(GestionnaireImages.getImage("AnimationAttaque"), 24, 24,panel);
        this.setNumero(numero);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        int ligne = numero/10;
        int colonne = numero%10;
        this.setZone(24*colonne, 24*ligne);
    }

    @Override
    public int getCouche() {
        return 5;
    }
}
