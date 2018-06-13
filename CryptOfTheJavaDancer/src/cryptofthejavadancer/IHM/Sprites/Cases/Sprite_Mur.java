package cryptofthejavadancer.IHM.Sprites.Cases;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import cryptofthejavadancer.Model.Carte.Aleatoire;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un mur classique (bas)
 * @author Matthieu
 */
public class Sprite_Mur extends Sprite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_Mur(Pane panel) {
        super(GestionnaireImages.getImage("Mur"), 24, 24,panel);
        if(Aleatoire.getInt(0, 6) != 0) {
            this.setNumero(0);
        }
        else {
            this.setNumero(Aleatoire.getInt(1, 15));
        }
    }

//------------------------------------------------------------------------------

    @Override
    public void setNumero(int numero) {
        super.setNumero(numero);
        this.setZone(numero*24, 0);
    }

    @Override
    public int getCouche() {
        return 1;
    }
}
