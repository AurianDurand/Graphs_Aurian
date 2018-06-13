package cryptofthejavadancer.IHM.Scene;

import cryptofthejavadancer.Controleurs.Controleur.Controleur_Cadence;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_Entite;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_GUI;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_Monstre;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_Objet;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_Squelette;
import cryptofthejavadancer.IHM.Sprites.Animations.Type_SpriteAnimation;
import cryptofthejavadancer.IHM.Sprites.Fabrique_Sprite;
import cryptofthejavadancer.IHM.Sprites.Sprite;
import cryptofthejavadancer.IHM.Sprites.Cases.Sprite_Mur;
import cryptofthejavadancer.IHM.Sprites.Entites.Sprite_Entite;
import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Comparateur_Coordonnees;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Entites.Entite_Squelette;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Panel d'affichage du jeu
 * @author Matthieu
 */
public class PanelCentral extends StackPane {

    private final ScenePrincipale scene;                                        //Scene du jeu
    private final Pane coucheSol;                                               //Panel des sprites niveau : Sol
    private final Pane coucheBasMur;                                            //Panel des sprites niveau : Bas des murs
    private final Pane coucheObjet;                                             //Panel des sprites niveau : Objet
    private final Pane coucheEntite;                                            //Panel des sprites niveau : Entite
    private final Pane coucheHautMur;                                           //Panel des sprites niveau : Haut des murs
    private final Pane coucheAnimation;                                         //Panel des sprites niveau : Animation
    private final Pane coucheGUI;                                               //Panel des sprites niveau : Interface
    
    private int numeroSol;                                                      //Numéro de la frame dans l'animation du sol disco
    
    private final Sprite_Mur spriteRef;                                         //Sprite de référence autoScale pour adapté les déplacements
    private int deltaXTotal;                                                    //Translation totale effectuée (en X)
    private int deltaYTotal;                                                    //Translation totale effectuée (en X)    
    
    private final TreeMap<Coordonnees,Sprite> TreeMapSpriteCoucheSol;           //Liste triée par coordonnées des sprites de la couche Sol
    private final TreeMap<Coordonnees,Sprite> TreeMapSpriteCoucheBasMur;        //Liste triée par coordonnées des sprites de la couche Bas Mur
    private final TreeMap<Coordonnees,Sprite> TreeMapSpriteCoucheObjet;         //Liste triée par coordonnées des sprites de la couche Objetl
    private final TreeMap<Coordonnees,Sprite> TreeMapSpriteCoucheEntite;        //Liste triée par coordonnées des sprites de la couche Entité
    private final TreeMap<Coordonnees,Sprite> TreeMapSpriteCoucheHautMur;       //Liste triée par coordonnées des sprites de la couche Haut Mur
    private final TreeMap<Coordonnees,Sprite> TreeMapSpriteCoucheAnimation;     //Liste triée par coordonnées des sprites de la couche Animation
    
    private final ArrayList<Sprite_Entite> listeSpriteEntite;                         //Liste de toutes les entitées (vu commes des entitées)
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public PanelCentral(ScenePrincipale _scene) {
        //Initialisation
        this.scene = _scene;
        this.numeroSol = 1;
        this.deltaXTotal = 0;
        this.deltaYTotal = 0;
        this.spriteRef = new Sprite_Mur(this);
        
        this.TreeMapSpriteCoucheSol = new TreeMap<>(new Comparateur_Coordonnees());
        this.TreeMapSpriteCoucheBasMur = new TreeMap<>(new Comparateur_Coordonnees());
        this.TreeMapSpriteCoucheObjet = new TreeMap<>(new Comparateur_Coordonnees());
        this.TreeMapSpriteCoucheEntite = new TreeMap<>(new Comparateur_Coordonnees());
        this.TreeMapSpriteCoucheHautMur = new TreeMap<>(new Comparateur_Coordonnees());
        this.TreeMapSpriteCoucheAnimation = new TreeMap<>(new Comparateur_Coordonnees());
        
        this.listeSpriteEntite = new ArrayList<>();
        
        //Gestion de la taille
        this.prefHeightProperty().bind(this.scene.getPane().heightProperty());
        this.prefWidthProperty().bind(this.scene.getPane().widthProperty());
        
        //Gestion de la couleur
        this.setStyle("-fx-background-color: #454447;");
        
        //création des ccouches
        this.coucheSol = new Pane();
        this.coucheBasMur = new Pane();
        this.coucheObjet = new Pane();
        this.coucheEntite = new Pane();
        this.coucheHautMur = new Pane();
        this.coucheAnimation = new Pane();
        this.coucheGUI = new Pane();
        
        //Ajout des couches au panel principal
        this.getChildren().add(coucheSol);
        this.getChildren().add(coucheBasMur);
        this.getChildren().add(coucheObjet);
        this.getChildren().add(coucheEntite);
        this.getChildren().add(coucheHautMur);
        this.getChildren().add(coucheAnimation);
        this.getChildren().add(coucheGUI);
        
        //Clipping
        Rectangle recClip = new Rectangle (0,0);
        recClip.widthProperty().bind(this.prefWidthProperty());
        recClip.heightProperty().bind(this.prefHeightProperty().multiply(23).divide(22));
        recClip.translateYProperty().bind(this.prefHeightProperty().multiply(-1).divide(22));
        this.setClip(recClip);
        
    }
    
//------------------------------------------------------------------------------ 
    
//---------- GETEUR/SETEUR -----------------------------------------------------

    //Renvoie la durée d'une pulsation de la musique
    public int getTempo() {
        return this.scene.getTempo();
    }
    
    //Renvoie le sprite de référence
    public Sprite_Mur getSpriteRef() {
        return this.spriteRef;
    }
    
//------------------------------------------------------------------------------ 
    
//---------- CHARGEMENT --------------------------------------------------------
    
    //Chargement initiale de tous les sprites
    public void chargerSprites() {
        //chargement des sprites
        for(Coordonnees coo : this.scene.getMap().getHashMapCases().keySet()) {
            //Gestion des sprites de décors
            Case laCase = this.scene.getMap().getHashMapCases().get(coo);
            Sprite sprite = Fabrique_Sprite.construitSprite(laCase.getType(), this);
            sprite.setTranslateX(coo.getColonne()*sprite.getWidth());
            sprite.setTranslateY(coo.getLigne()*sprite.getWidth());
            switch(sprite.getCouche()) {
                case 0: this.TreeMapSpriteCoucheSol.put(coo, sprite); break;
                case 1: 
                    Sprite spriteHaut = Fabrique_Sprite.construitSpriteHaut(laCase.getType(),sprite, this);
                    sprite.setTranslateY(sprite.getTranslateY()+0.375*sprite.getWidth());
                    this.TreeMapSpriteCoucheBasMur.put(coo, sprite);
                    if(spriteHaut != null) {
                        spriteHaut.setTranslateY(sprite.getTranslateY()-sprite.getWidth());
                        spriteHaut.setTranslateX(sprite.getTranslateX());
                        this.TreeMapSpriteCoucheHautMur.put(coo, spriteHaut);
                    }
                break;
            }
            
            //Gestion des entitees
            if(laCase.getEntite() != null) {
                //Création du sprite
                Sprite_Entite spriteEntite = Fabrique_Sprite.construitSprite(laCase.getEntite().getType(), this);
                spriteEntite.setTranslateX(coo.getColonne()*sprite.getWidth());
                spriteEntite.setTranslateY((coo.getLigne()-0.25)*sprite.getWidth());
                //Ajout dans la liste des entités
                this.TreeMapSpriteCoucheEntite.put(coo,spriteEntite);
                this.listeSpriteEntite.add(spriteEntite);
                //création des controleurs
                new Controleur_Entite(laCase.getEntite(),spriteEntite,this);
                if(laCase.getEntite().getType() != Type_Entite.Cadence) {
                    new Controleur_Monstre(this,laCase.getEntite());
                }
                if(laCase.getEntite().getType() == Type_Entite.Squelette) {
                    new Controleur_Squelette((Entite_Squelette) laCase.getEntite(),spriteEntite);
                }
            }
            
            //Gestion des objets
            if(laCase.getObjet() != null) {
                //Création du sprite
                Sprite spriteObjet = Fabrique_Sprite.construitSprite(laCase.getObjet().getType(), this);  
                spriteObjet.setTranslateX(coo.getColonne()*sprite.getWidth());
                spriteObjet.setTranslateY(coo.getLigne()*sprite.getWidth());
                //Ajout dans la liste des objets
                this.TreeMapSpriteCoucheObjet.put(coo,spriteObjet);
                //Création du controleur
                new Controleur_Objet(laCase.getObjet(),spriteObjet,this);
            }
        }
    }
    
    //Premier affichage
    public void affichage() {
        //affichage des sprites par couches et de haut en bas
        for(Sprite sprite : this.TreeMapSpriteCoucheSol.values()) {
            if(!this.coucheSol.getChildren().contains(sprite)) {
                this.ajouterSprite(sprite);
            }
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheBasMur.values()) {
            if(!this.coucheBasMur.getChildren().contains(sprite)) {
                this.ajouterSprite(sprite);
            }
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheObjet.values()) {
            if(!this.coucheObjet.getChildren().contains(sprite)) {
                this.ajouterSprite(sprite);
            }
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheEntite.values()) {
            if(!this.coucheEntite.getChildren().contains(sprite)) {
                this.ajouterSprite(sprite);
            }
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheHautMur.values()) {
            if(!this.coucheHautMur.getChildren().contains(sprite)) {
                this.ajouterSprite(sprite);
            }
        }
        
        //calcul de la translation initiale
        int deltaX = 0;
        int deltaY = 0;
        Coordonnees depart = this.scene.getMap().getDepart();
        if(depart.getColonne()>4) {
            deltaX = (int) ((4-depart.getColonne())*spriteRef.getWidth());
        }
        if(depart.getLigne()>4) {
            deltaY = (int) ((4-depart.getLigne())*spriteRef.getWidth());
        }
        this.translation(deltaX, deltaY);
        
        //création de l'UI
        PanelGUI panelGUI = new PanelGUI(this);
        this.coucheGUI.getChildren().add(panelGUI);
        
        //création des controleurs du joueur
        new Controleur_GUI(panelGUI,this.scene.getMap().getJoueur());
        new Controleur_Cadence(this,this.scene.getMap().getJoueur());
    }
    
    //Ajoute un sprite dans la couche adaptée
    private void ajouterSprite(Sprite sprite) {
        switch(sprite.getCouche()) {
            case 0 : this.coucheSol.getChildren().add(sprite); break;
            case 1 : this.coucheBasMur.getChildren().add(sprite); break;
            case 2 : this.coucheObjet.getChildren().add(sprite); break;
            case 3 : this.coucheEntite.getChildren().add(sprite); break;
            case 4 : this.coucheHautMur.getChildren().add(sprite); break;
        }
    }
    
    //Fin de partie
    public void finPartie() {
        this.scene.finPartie();
    }
   
//------------------------------------------------------------------------------ 
    
//---------- ANIMATION DE LA MAP -----------------------------------------------
         
    //Animation "disco" du sol
    public void solDisco() {
        //Frame 1
        if(this.numeroSol == 1) {
            for(Coordonnees coo : this.TreeMapSpriteCoucheSol.keySet()) {
                if((coo.getLigne()+coo.getColonne())%2==0) {
                    this.TreeMapSpriteCoucheSol.get(coo).setNumero(0);
                }
                else {
                    this.TreeMapSpriteCoucheSol.get(coo).setNumero(2);
                }
            }
            this.numeroSol = 2;
        }
        //Frame 2
        else {
            for(Coordonnees coo : this.TreeMapSpriteCoucheSol.keySet()) {
                if((coo.getLigne()+coo.getColonne())%2==0) {
                    this.TreeMapSpriteCoucheSol.get(coo).setNumero(1);
                }
                else {
                    this.TreeMapSpriteCoucheSol.get(coo).setNumero(0);
                }
            }
            this.numeroSol = 1;
        }
    }
    
    //Fait danser les entités
    public void danse() {
        for(Sprite_Entite sprite : this.listeSpriteEntite) {
            sprite.danse();
        }
    }
    
    //Translate la map pour la centrer sur Cadence
    public void translation(int deltaX, int deltaY) {
        for(Sprite sprite : this.TreeMapSpriteCoucheSol.values()) {
            this.moveSprite(sprite, deltaX, deltaY);
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheBasMur.values()) {
            this.moveSprite(sprite, deltaX, deltaY);
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheObjet.values()) {
            this.moveSprite(sprite, deltaX, deltaY);
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheEntite.values()) {
            this.moveSprite(sprite, deltaX, deltaY);
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheHautMur.values()) {
            this.moveSprite(sprite, deltaX, deltaY);
        }
        for(Sprite sprite : this.TreeMapSpriteCoucheAnimation.values()) {
            this.moveSprite(sprite, deltaX, deltaY);
        }
        this.deltaXTotal += deltaX;
        this.deltaYTotal += deltaY;
    }
    
    //Fait bouger un sprite
    public void moveSprite(Sprite sprite, int deltaX,int deltaY) {
            sprite.setTranslateX(sprite.getTranslateX()+deltaX);
            sprite.setTranslateY(sprite.getTranslateY()+deltaY);
    }
    
    
//------------------------------------------------------------------------------ 
    
//---------- SUPPRESSION D'ELEMENTs --------------------------------------------
    
    //Supprime l'objet à la position donnée   
    public void supprimerObjet(Coordonnees coo) {
        Sprite spriteObjet = this.TreeMapSpriteCoucheObjet.get(coo);
        this.coucheObjet.getChildren().remove(spriteObjet);
        this.TreeMapSpriteCoucheObjet.remove(coo);
    }
    
    //Supprime l'entité à la position donnée
    public void supprimerEntite(Sprite spriteEntite) {
        this.coucheEntite.getChildren().remove(spriteEntite);
        this.listeSpriteEntite.remove(spriteEntite);
    }
 
    //Supprime le mur à la position donnée et fabrique un sol à la place
    public void supprimerMur(Coordonnees coo) {
        //On enleve le mur (ou le mur dur) (haut et bas)
        Sprite spriteBas = this.TreeMapSpriteCoucheBasMur.get(coo);
        Sprite spriteHaut = this.TreeMapSpriteCoucheHautMur.get(coo);
        this.coucheBasMur.getChildren().remove(spriteBas);
        this.coucheHautMur.getChildren().remove(spriteHaut);
        this.TreeMapSpriteCoucheBasMur.remove(coo);
        this.TreeMapSpriteCoucheHautMur.remove(coo);
        //on ajoute une case sol
        Sprite spriteSol = Fabrique_Sprite.construitSprite(Type_Case.Sol, this);
        spriteSol.setTranslateX(spriteBas.getTranslateX());
        spriteSol.setTranslateY(spriteBas.getTranslateY()-0.375*spriteSol.getWidth());
        this.TreeMapSpriteCoucheSol.put(coo, spriteSol);
        this.coucheSol.getChildren().add(spriteSol);
        
    }
    
//------------------------------------------------------------------------------ 
    
//---------- ANIMATION DE LA MAP -----------------------------------------------
    
    //Ajoute une animation donnée à la position donnée
    public void ajouterAnimation(Coordonnees coo,Type_SpriteAnimation animation) {
        Sprite sprite = Fabrique_Sprite.construitSprite(animation, this);
        sprite.setTranslateX(coo.getColonne()*spriteRef.getWidth()+this.deltaXTotal);
        sprite.setTranslateY(coo.getLigne()*spriteRef.getWidth()+this.deltaYTotal);
        
        if(animation == Type_SpriteAnimation.creuser || animation == Type_SpriteAnimation.creuser_amelioree) {
            sprite.setTranslateY(sprite.getTranslateY()-spriteRef.getWidth()+0.375*spriteRef.getWidth());
        }
        
        this.TreeMapSpriteCoucheAnimation.put(coo,sprite);
        this.coucheAnimation.getChildren().add(sprite);
    }
    
    //Fait avancer l'animation présente à la position donnée à la frame suivante
    public void avancerAnimation(Coordonnees coo) {
        Sprite sprite = this.TreeMapSpriteCoucheAnimation.get(coo);
        sprite.setNumero(sprite.getNumero()+1);
    }
    
    //Supprime l'animation présente à la position donnée
    public void supprimerAnimation(Coordonnees coo) {
        Sprite sprite = this.TreeMapSpriteCoucheAnimation.get(coo);
        this.TreeMapSpriteCoucheAnimation.remove(coo);
        this.coucheAnimation.getChildren().remove(sprite);
    }
    
    
}
