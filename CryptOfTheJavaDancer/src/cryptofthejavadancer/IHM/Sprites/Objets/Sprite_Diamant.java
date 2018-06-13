package cryptofthejavadancer.IHM.Sprites.Objets;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un diamant
 * @author Matthieu
 */
public class Sprite_Diamant extends Sprite {

    public Sprite_Diamant(Pane panel) {
        super(GestionnaireImages.getImage("Diamant"), 24, 24,panel);
        this.setZone(0, 0);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        this.setZone(0, 0);
    }

    @Override
    public int getCouche() {
        return 2;
    }
}
