package cryptofthejavadancer.IHM.Sprites;

import cryptofthejavadancer.IHM.Sprites.GUI.*;
import cryptofthejavadancer.IHM.Sprites.Cases.*;
import cryptofthejavadancer.IHM.Sprites.Entites.*;
import cryptofthejavadancer.IHM.Sprites.Objets.*;
import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Sprites.Animations.Sprite_AnimationAttaque;
import cryptofthejavadancer.IHM.Sprites.Animations.Sprite_AnimationAttaqueMonstre;
import cryptofthejavadancer.IHM.Sprites.Animations.Sprite_AnimationCreuser;
import cryptofthejavadancer.IHM.Sprites.Animations.Type_SpriteAnimation;
import cryptofthejavadancer.IHM.Sprites.GUI.Type_SpriteGUI;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.Objet.Type_Objet;

/**
 * Fabrique des sprites
 * @author Matthieu
 */
public class Fabrique_Sprite {

    private static Fabrique_Sprite instance;                                    //singleton
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    private Fabrique_Sprite() {}
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    public static Fabrique_Sprite get() {
        if(instance == null) {
            instance = new Fabrique_Sprite();
        }
        return instance;
    }
    
//------------------------------------------------------------------------------

    //Construction des sprites de décors
    public static Sprite construitSprite(Type_Case type,PanelCentral panel) {
        Sprite sprite = null;
        switch(type) {
            case Sol: sprite = new Sprite_Sol(panel);
                break;
            case Mur: sprite = new Sprite_Mur(panel);
                break;
            case MurDur: sprite = new Sprite_MurDur(panel);
                break;
            case MurIndestructible: sprite = new Sprite_MurIndestructible(panel);
                break;
        }
        return sprite;
    }
    
    public static Sprite construitSpriteHaut(Type_Case type,Sprite spriteBas, PanelCentral panel) {
        Sprite sprite = null;
        switch(type) {
            case Mur: sprite = new Sprite_MurHaut(panel,spriteBas.getNumero());
                break;
            case MurDur: sprite = new Sprite_MurDurHaut(panel,spriteBas.getNumero());
                break;
            case MurIndestructible: sprite = new Sprite_MurIndestructibleHaut(panel,spriteBas.getNumero());
                break;
        }
        return sprite;
    }
    
    //Construction des sprites des entites
    public static Sprite_Entite construitSprite(Type_Entite type,PanelCentral panel) {
        Sprite_Entite sprite = null;
        switch(type) {
            case SlimeVert: sprite = new Sprite_SlimeVert(panel);
                break;
            case SlimeBleu: sprite = new Sprite_SlimeBleu(panel);
                break;
            case SlimeJaune: sprite = new Sprite_SlimeJaune(panel);
                break;
            case ChauveSouris: sprite = new Sprite_ChauveSouris(panel);
                break;
            case Squelette: sprite = new Sprite_Squelette(panel);
                break;
            case Cadence: sprite = new Sprite_Cadence(panel);
                break;
        }
        return sprite;
    }
    
     //Construction des sprites d'objets
    public static Sprite construitSprite(Type_Objet type,PanelCentral panel) {
        Sprite sprite = null;
        switch(type) {
            case Diamant: sprite = new Sprite_Diamant(panel);
                break;
            case Pelle: sprite = new Sprite_Pelle(panel);
                break;
            case Sortie: sprite = new Sprite_Sortie(panel);
                break;
        }
        return sprite;
    }
    
      //Construction des sprites du GUI
    public static Sprite construitSprite(Type_SpriteGUI type,PanelCentral panel) {
        Sprite sprite = null;
        switch(type) {
            case Piece: sprite = new Sprite_Piece(panel);
                break;
            case Slot: sprite = new Sprite_Slot(panel);
                break;
        }
        return sprite;
    }
    
    //construction des sprites d'animation
    public static Sprite construitSprite(Type_SpriteAnimation type,PanelCentral panel) {
        Sprite sprite = null;
        switch(type) {
            case creuser: sprite = new Sprite_AnimationCreuser(panel,0);
                break;
            case creuser_amelioree: sprite = new Sprite_AnimationCreuser(panel,1);
                break;
            case attaque_gauche: sprite = new Sprite_AnimationAttaque(panel,20);
                break;
            case attaque_droite: sprite = new Sprite_AnimationAttaque(panel,0);
                break;
            case attaque_haut: sprite = new Sprite_AnimationAttaque(panel,10);
                break;
            case attaque_bas: sprite = new Sprite_AnimationAttaque(panel,30);
                break;
            case attaque_monstre: sprite = new Sprite_AnimationAttaqueMonstre(panel);
                break;
        }
        return sprite;
    }
}
