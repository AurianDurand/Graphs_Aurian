package cryptofthejavadancer.IHM.Sprites.Cases;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un mur classique (haut)
 * @author Matthieu
 */
public class Sprite_MurHaut extends Sprite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_MurHaut(Pane panel,int numero) {
        super(GestionnaireImages.getImage("MurHaut"), 24, 24,panel);
        this.setNumero(numero);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        this.setZone(numero*24, 0);
    }

    @Override
    public int getCouche() {
        return 4;
    }
}
