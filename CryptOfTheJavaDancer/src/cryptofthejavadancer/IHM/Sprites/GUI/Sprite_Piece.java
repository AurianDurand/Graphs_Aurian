package cryptofthejavadancer.IHM.Sprites.GUI;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite d'une pièce pour l'interface
 * @author Matthieu
 */
public class Sprite_Piece extends Sprite {

    public Sprite_Piece(Pane panel) {
        super(GestionnaireImages.getImage("Piece"), 24, 24,panel);
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
        return 1;
    }
}
