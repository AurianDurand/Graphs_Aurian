package cryptofthejavadancer.IHM.Scene;

import cryptofthejavadancer.IHM.Sprites.GUI.Type_SpriteGUI;
import cryptofthejavadancer.IHM.Sprites.Fabrique_Sprite;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Panel de l'interface graphique
 * @author Matthieu
 */
public class PanelGUI extends GridPane {

    private final Label labelDiamant;                                                 //Compteur du nb de diamants
    private final Label labelPiece;                                                   //Compteur du nb de pièces
    private final PanelCentral panel;                                                 //Panel d'affichage
    private final Sprite spritePelle;                                                 //Sprite de la pelle
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    
    public PanelGUI(PanelCentral _panel) {
        //Intialisation
        super();
        this.panel = _panel;
        //Positionnement en haut à droite de l'écran
        this.translateXProperty().bind(panel.widthProperty().subtract(this.widthProperty()));
        //Création des compteurs
        this.labelDiamant = new Label("x0");
        this.labelDiamant.setFont(new Font("Arial", 30));
        labelDiamant.setTextFill(Color.web("#000000"));
        this.labelPiece = new Label("x0");
        this.labelPiece.setFont(new Font("Arial", 30));
        labelPiece.setTextFill(Color.web("#000000"));
        
        //Contraintes sur les colonnes
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPercentWidth(33);
        col2.setPercentWidth(33);
        col3.setPercentWidth(33);
        this.getColumnConstraints().addAll(col1,col2,col3);
        this.setHgap(10);
        
        //Ajout des sprites
        Sprite spriteDiamant = Fabrique_Sprite.construitSprite(Type_Objet.Diamant, panel);
        this.add(spriteDiamant, 1, 0);
        Sprite spritePiece = Fabrique_Sprite.construitSprite(Type_SpriteGUI.Piece, panel);
        this.add(spritePiece, 1, 1);
        Sprite spriteSlot = Fabrique_Sprite.construitSprite(Type_SpriteGUI.Slot, panel);
        this.add(spriteSlot,0,0,1,2);
        spritePelle = Fabrique_Sprite.construitSprite(Type_Objet.Pelle, panel);
        spritePelle.setNumero(0);
        spritePelle.setTranslateY(5);
        this.add(spritePelle,0,0,1,2);
        
        //Ajout des labels
        this.add(this.labelDiamant, 2, 0);
        this.add(this.labelPiece, 2, 1);
    }
    
//------------------------------------------------------------------------------

    //Changement du nombre de diamants
    public void changerDiamant(int nombre) {
        this.labelDiamant.setText("x"+nombre);
    }
    
    //Changement du nombre de pièce
    public void changerPiece(int nombre) {
        this.labelPiece.setText("x"+nombre);
    }
    
    //Changement du type de pelle
    public void changerPelle(boolean amelioree) {
        if(amelioree) {
            this.spritePelle.setNumero(1);
        }
        else {
            this.spritePelle.setNumero(0);
        }
    }

}
