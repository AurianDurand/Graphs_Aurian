package cryptofthejavadancer.IHM.Sprites.Entites;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import javafx.scene.layout.Pane;

/**
 * Sprite de Cadence
 * @author Matthieu
 */
public class Sprite_Cadence extends Sprite_Entite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_Cadence(Pane panel) {
        super(GestionnaireImages.getImage("Cadence"), 26, 24,panel);
        this.setZone(0, 0);
    }

//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        if(numero < 4) {
            this.setZone(numero*24, 0);
        }
        else {
            this.setZone((numero-4)*24, 26);
        }
    }
    
    @Override
    public int getCouche() {
        return 3;
    }
//------------------------------------------------------------------------------

}
