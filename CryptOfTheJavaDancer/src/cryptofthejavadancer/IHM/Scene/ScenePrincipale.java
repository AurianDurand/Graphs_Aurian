package cryptofthejavadancer.IHM.Scene;

import cryptofthejavadancer.Controleurs.Timer.TimerPrincipal;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Carte.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Scene principal de l'application
 * @author Matthieu
 */
public class ScenePrincipale extends Scene {

    private final Pane root;                                                    //Panel racine
    private final PanelCentral panelCentral;                                    //Panel d'affichage
    private Map map;                                                            //Map du jeu
    private TimerPrincipal controleur;                                          //Timer principal
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public ScenePrincipale(Pane root, double width, double height) {
        //Initialisation
        super(root, width, height);
        this.root = root;
        this.root.setStyle("-fx-background-color: #000000;");
        this.controleur = null;
        
        //Création du panel d'affihcage
        this.panelCentral = new PanelCentral(this);
        this.root.getChildren().add(this.panelCentral);
    }
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Renvoie le panel racine
    public Pane getPane() {
        return this.root;
    }
    
    //Set la map du jeu, lance le chargement des sprites et crée le timer principal
    public void setMap(Map _map) {
        this.map = _map;
        this.panelCentral.chargerSprites();
        this.controleur = new TimerPrincipal(_map,this);
    }
    
    //Renvoie la map du jeu
    public Map getMap() {
        return this.map;
    }
    
//------------------------------------------------------------------------------

    //Lance le jeu
    public void start(Map _map) { 
        this.setMap(_map);
        this.affichage();
        this.controleur.start();
    }
    
    //lance le premier affichage
    public void affichage() {
        this.panelCentral.affichage();
    }
    
    //fin de la partie
    public void finPartie() {
        this.controleur.finPartie();
    }
    
    //animation disco du sol
    public void solDisco() {
        this.panelCentral.solDisco();
    }
    
    //fait danser les entités
    public void danse() {
        this.panelCentral.danse();
    }
    
    //Renvoie la durée d'une pulsation de la musique
    public int getTempo() {
        return this.controleur.getTempo();
    }
    
}
