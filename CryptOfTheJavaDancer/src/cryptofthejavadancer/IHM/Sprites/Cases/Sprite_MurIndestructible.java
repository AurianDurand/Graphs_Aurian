package cryptofthejavadancer.IHM.Sprites.Cases;

import cryptofthejavadancer.IHM.Sprites.GestionnaireImages;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import cryptofthejavadancer.Model.Carte.Aleatoire;
import javafx.scene.layout.Pane;

/**
 * Sprite d'un mur indestructible (bas)
 * @author Matthieu
 */
public class Sprite_MurIndestructible extends Sprite {

//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite_MurIndestructible(Pane panel) {
        super(GestionnaireImages.getImage("MurIndestructible"), 24, 24,panel);
        this.setNumero(Aleatoire.getInt(0, 7));
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
