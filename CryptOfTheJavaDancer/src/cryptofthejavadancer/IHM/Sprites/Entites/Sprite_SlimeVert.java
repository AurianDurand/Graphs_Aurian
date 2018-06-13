package cryptofthejavadancer.IHM.Sprites.Entites;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un slime vert
 * @author Matthieu
 */
public class Sprite_SlimeVert extends Sprite_Entite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_SlimeVert(Pane panel) {
        super(GestionnaireImages.getImage("SlimeVert"), 26, 26,panel);
        this.setZone(0, 0);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        if(numero < 4) {
            this.setZone(numero*26, 0);
        }
        else {
            this.setZone((numero-4)*26, 26);
        }
    }
    
    @Override
    public int getCouche() {
        return 3;
    }

}
