package cryptofthejavadancer.IHM.Sprites;

import java.util.HashMap;
import javafx.scene.image.Image;

/**
 * Gestionnaire des images
 * @author Matthieu
 */
public class GestionnaireImages {

    private static GestionnaireImages instance;                                 //singleton
    private HashMap<String,Image> listeImages;                                  //multiton
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    private GestionnaireImages() {
        this.listeImages = new HashMap<>();
        //Cases
        this.addImage("Sol", "Sol.png");
        this.addImage("Mur", "Mur.png");
        this.addImage("MurHaut", "MurHaut.png");
        this.addImage("MurDur", "MurDur.png");
        this.addImage("MurDurHaut", "MurDurHaut.png");
        this.addImage("MurIndestructible", "MurIndestructible.png");
        this.addImage("MurIndestructibleHaut", "MurIndestructibleHaut.png");
        //Entites
        this.addImage("SlimeVert", "SlimeVert.png");
        this.addImage("SlimeBleu", "SlimeBleu.png");
        this.addImage("SlimeJaune", "SlimeJaune.png");
        this.addImage("ChauveSouris", "ChauveSouris.png");
        this.addImage("Squelette", "Squelette.png");
        this.addImage("Cadence", "Cadence.png");
        //Objets
        this.addImage("Diamant", "Diam.png");
        this.addImage("Pelle", "Pelle.png");
        this.addImage("Sortie", "Sortie.png");
        //GUI
        this.addImage("Piece", "Coin.png");
        this.addImage("Slot", "Slot.png");
        //Animation
        this.addImage("AnimationAttaque", "AnimationAttaque.png");
        this.addImage("AnimationAttaqueMonstre", "AnimationAttaqueMonstre.png");
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Geteur du singleton
    public static GestionnaireImages get() {
        if(instance == null) {
            instance = new GestionnaireImages();
        }
        return instance;
    }
    
    //getter d'une image
    public static Image getImage(String nom) {
        return get().listeImages.get(nom);
    }
    
//------------------------------------------------------------------------------

    //ajout d'une image dans la bibliothèque
    private void addImage(String nom, String nomFichier) {
        this.listeImages.put(nom, new Image("cryptofthejavadancer/IHM/Graphisme/"+nomFichier));
    }
    
}
