package cryptofthejavadancer.IHM.Sprites.Objets;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite de la pelle
 * @author Matthieu
 */
public final class Sprite_Pelle extends Sprite {

    public Sprite_Pelle(Pane panel) {
        super(GestionnaireImages.getImage("Pelle"), 24, 24,panel);
        this.setNumero(1);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        this.setZone(0, 24*numero);
    }

    @Override
    public int getCouche() {
        return 2;
    }
}
