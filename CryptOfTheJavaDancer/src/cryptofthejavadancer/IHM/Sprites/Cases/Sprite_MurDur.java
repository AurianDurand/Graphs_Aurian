package cryptofthejavadancer.IHM.Sprites.Cases;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un mur dur (bas)
 * @author Matthieu
 */
public class Sprite_MurDur extends Sprite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_MurDur(Pane panel) {
        super(GestionnaireImages.getImage("MurDur"), 24, 24,panel);
        this.setZone(0, 0);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(0);
        this.setZone(0, 0);
    }

    @Override
    public int getCouche() {
        return 1;
    }
}
