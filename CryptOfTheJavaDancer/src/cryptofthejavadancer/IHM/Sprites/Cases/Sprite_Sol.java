package cryptofthejavadancer.IHM.Sprites.Cases;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un sol
 * @author Matthieu
 */
public class Sprite_Sol extends Sprite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_Sol(Pane panel) {
        super(GestionnaireImages.getImage("Sol"), 24, 24,panel);
        this.setZone(53, 1);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        
        switch(numero) {
            case 0 : this.setZone(53, 1); break;
            case 1 : this.setZone(27, 27); break;
            case 2 : this.setZone(53, 27); break;
        }
    }

    @Override
    public int getCouche() {
        return 0;
    }
}
