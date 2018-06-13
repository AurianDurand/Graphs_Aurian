package cryptofthejavadancer.IHM.Sprites.GUI;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite du slot de la pelle dans l'interface
 * @author Matthieu
 */
public class Sprite_Slot extends Sprite {

    public Sprite_Slot(Pane panel) {
        super(GestionnaireImages.getImage("Slot"), 30, 33,panel);
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
        return 0;
    }
}
