package cryptofthejavadancer.IHM.Sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Classe générique d'un sprite
 * @author Matthieu
 */
public abstract class Sprite extends ImageView {

    private final int width;                                                    //largeur initiale
    private final int height;                                                   //hauteur initiale
    private int numero;                                                         //numéro du sprite dans la spritesheet
    private final Pane panel;                                                   //Panel d'affichage (pour le scaling)
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Sprite(Image _image,int _width,int _height,Pane _panel) {
        //Initialisation
        this.setImage(_image);
        this.numero = 0;
        this.width = _width;
        this.height = _height;
        this.panel = _panel;
        this.fixerTaille();
    }
    
    //Scale le sprite avec le panel d'affichage
    private void fixerTaille() {
        this.setPreserveRatio(true);
        this.setSmooth(true);
        if(this.panel.getWidth() < this.panel.getHeight()) {
            this.fitWidthProperty().bind(this.panel.widthProperty().divide(11));
        }
        else {
            this.fitWidthProperty().bind(this.panel.heightProperty().divide(11));
        }
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Renvoie la taille du sprite après scaling
    public double getWidth() {
        return this.getFitWidth(); 
    }
    
    //fixe la zone de la spritesheet à afficher
    protected final void setZone(int x,int y) {
        this.setViewport(new Rectangle2D(x,y,this.width,this.height));
    }
   
    //change le numéro
    public void setNumero(int _numero) {
        this.numero = _numero;
    }
    
    //renvoie le numéro du sprite dans la spritesheet
    public int getNumero() {
        return this.numero;
    }
    
    //renvoie le numéro de la couche où le sprite doit être affiché (0 - sol, 1 - Bas mur, 2 - Objet, 3 - Entité, 4 - Haut mur, 5 - Animation et IHM)
    public abstract int getCouche();
    
//------------------------------------------------------------------------------
 
}
