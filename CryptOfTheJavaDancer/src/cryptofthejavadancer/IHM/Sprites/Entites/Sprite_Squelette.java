package cryptofthejavadancer.IHM.Sprites.Entites;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un squelette
 * @author Matthieu
 */
public class Sprite_Squelette extends Sprite_Entite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_Squelette(Pane panel) {
        super(GestionnaireImages.getImage("Squelette"), 24, 25,panel);
        this.setZone(0,0);
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        if((numero%8) < 4) {
            this.setZone((numero%4)*24+(numero/8)*4*24, 0);
        }
        else {
            this.setZone((numero%4)*24+(numero/8)*4*24, 25);
        }
    }
    
    @Override
    public int getCouche() {
        return 3;
    }

}
