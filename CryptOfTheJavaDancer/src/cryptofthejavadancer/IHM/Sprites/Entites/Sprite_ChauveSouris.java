package cryptofthejavadancer.IHM.Sprites.Entites;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import javafx.scene.layout.Pane;

/**
 * Sprite d'une chauve souris
 * @author Matthieu
 */
public class Sprite_ChauveSouris extends Sprite_Entite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_ChauveSouris(Pane panel) {
        super(GestionnaireImages.getImage("ChauveSouris"), 24, 24,panel);
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
            this.setZone((numero-4)*24, 24);
        }
    }
    
    @Override
    public int getCouche() {
        return 3;
    }
//------------------------------------------------------------------------------


}
