package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.Controleurs.Mouvement.TimerAnimationMouvementBas;
import cryptofthejavadancer.Controleurs.Mouvement.TimerAnimationMouvementDroite;
import cryptofthejavadancer.Controleurs.Mouvement.TimerAnimationMouvementEntite;
import cryptofthejavadancer.Controleurs.Mouvement.TimerAnimationMouvementGauche;
import cryptofthejavadancer.Controleurs.Mouvement.TimerAnimationMouvementHaut;
import cryptofthejavadancer.Controleurs.Timer.TimerTranslationMap;
import cryptofthejavadancer.IHM.Scene.PanelCentral;
import cryptofthejavadancer.IHM.Son.Lecteur;
import cryptofthejavadancer.IHM.Sprites.Entites.Sprite_Entite;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.IA.Type_Action;

/**
 * Controleur générique des entités (déplacement)
 * @author Matthieu
 */
public class Controleur_Entite {

    private Entite modele;                                                      //l'entité
    private Sprite_Entite vue;                                                  //son sprite
    private PanelCentral panel;                                                 //le panel d'affichage
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    public Controleur_Entite(Entite _modele, Sprite_Entite _vue,PanelCentral _panel) {
        //initialisation
        this.modele = _modele;
        this.vue = _vue;
        this.panel = _panel;
        //lie le controleur au modèle
        this.modele.setControleur(this);
    }
//------------------------------------------------------------------------------

    //Méthode appelée à la mort de l'entité
    public void mort() {
        //supprime le sprite
        this.panel.supprimerEntite(vue);
        //fin de partie si c'est Cadence qui est morte
        if(this.modele.getType() == Type_Entite.Cadence) {
            System.err.println("Cadence est morte :(");
            this.panel.finPartie();
        }
        //lance le son de mort adapté
        String nomFichier = "Cadence_Mort.mp3";
        switch(this.modele.getType()) {
            case ChauveSouris: nomFichier = "ChauveSouris_Mort.mp3";
                break;
            case Squelette: nomFichier = "Squelette_Mort.mp3";
                break;
            case SlimeVert: case SlimeBleu: case SlimeJaune: nomFichier = "Slime_Mort.mp3";
                break;
        }
        Lecteur.play(nomFichier, false,0.3);
    }
    
    //Méthode appelée quand l'entité est blessée (hors mort)
    public void blesse() {
        //lance le son adapté
        String nomFichier = "Cadence_Blesse.mp3";
        switch(this.modele.getType()) {
            case ChauveSouris: nomFichier = "ChauveSouris_Blesse.mp3";
                break;
            case Squelette: nomFichier = "Squelette_Blesse.mp3";
                break;
            case SlimeVert: case SlimeBleu: case SlimeJaune: nomFichier = "Slime_Blesse.mp3";
                break;
        }
        Lecteur.play(nomFichier, false,0.3);
    }
    
    //Méthode appelée lors d'une demande de déplacement
    public void modification(Type_Action action) {
        TimerTranslationMap timerTranslation = null;                            //animation de translation de la map si mvt de Cadence
        TimerAnimationMouvementEntite timerMouvement = null;                    //animation de mouvement de l'entité
        switch(action) {
            case deplacement_bas: 
                timerMouvement = new TimerAnimationMouvementBas(vue,panel);
                if(modele.getType().equals(Type_Entite.Cadence)) {
                    timerTranslation = new TimerTranslationMap(modele.getCase().getMap(),panel,0,-(int) panel.getSpriteRef().getWidth());
                }
                break;
            case deplacement_haut: 
                timerMouvement = new TimerAnimationMouvementHaut(vue,panel);
                if(modele.getType().equals(Type_Entite.Cadence)) {
                    timerTranslation = new TimerTranslationMap(modele.getCase().getMap(),panel,0,(int) panel.getSpriteRef().getWidth());
                }
                break;
            case deplacement_droite: 
                timerMouvement = new TimerAnimationMouvementDroite(vue,panel);
                if(modele.getType().equals(Type_Entite.Cadence)) {
                    timerTranslation = new TimerTranslationMap(modele.getCase().getMap(),panel,-(int) panel.getSpriteRef().getWidth(),0);
                }
                break;
            case deplacement_gauche: 
                timerMouvement = new TimerAnimationMouvementGauche(vue,panel);
                if(modele.getType().equals(Type_Entite.Cadence)) {
                    timerTranslation = new TimerTranslationMap(modele.getCase().getMap(),panel,(int) panel.getSpriteRef().getWidth(),0);
                }
                break;
            case sortir:
                this.panel.finPartie();
                break;
        }
        //Lance les animations qui doivent avoir lieu
        if(timerTranslation != null) {
            timerTranslation.start();
        }
        if(timerMouvement != null) {
            timerMouvement.start();
        }
    }
}
