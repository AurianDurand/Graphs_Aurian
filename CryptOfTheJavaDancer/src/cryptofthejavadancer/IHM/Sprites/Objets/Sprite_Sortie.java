package cryptofthejavadancer.IHM.Sprites.Objets;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite de la sortie
 * @author Matthieu
 */
public class Sprite_Sortie extends Sprite {

    public Sprite_Sortie(Pane panel) {
        super(GestionnaireImages.getImage("Sortie"), 24, 24,panel);
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
