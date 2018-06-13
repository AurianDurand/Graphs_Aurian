package cryptofthejavadancer.Model.Entites;

import cryptofthejavadancer.Controleurs.Controleur.Controleur_Action;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_Entite;
import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.IA.IA;
import cryptofthejavadancer.Model.IA.Type_Action;

/**
 * Classe générique des entités
 * @author Matthieu
 */
public abstract class Entite {

    private Case saCase;                                                        //Case de l'entité
    private int vie;                                                            //nb de point de vie
    private IA ia;                                                              //IA de l'entité
    private Controleur_Entite controleur;                                       //Controleur de gestion des mouvements
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    public Entite(int vieInitiale) {
        this.saCase = null;
        this.vie = vieInitiale;
        this.ia = null;
        this.controleur = null;
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------
    //Change la case de l'entité
    public void setCase(Case _case) {
        this.saCase = _case;
    }
    
    //Renvoie la case où est l'entité
    public Case getCase() {
        return this.saCase;
    }
    
    //Renvoie la map du jeu
    public Map getMap() {
        return this.saCase.getMap();
    }
    
    //Change le nombre de pv de l'entité (avec gestion de la mort)
    public void changeVie(int degats) {
        this.vie = Math.max(this.vie-degats, 0);
        if(this.vie == 0) {
            this.mort();
        }
        else {
            this.controleur.blesse();
        }
    }
    
    //Mort de l'entité
    public void mort() {
        this.saCase.getMap().getJoueur().ajouterPieceOr(this.getGainOr());
        this.saCase.setEntite(null);
        this.saCase.getMap().getListeEntite().remove(this);
        this.controleur.mort();
    }
    
    //Renvoie le nombre de pv de la vie
    public int getVie() {
        return this.vie;
    }
    
    //Fixe le controleur
    public void setControleur(Controleur_Entite _controleur) {
        this.controleur = _controleur;
    }
    
    //Fixe l'IA de l'entité
    public final void setIA(IA _ia) {
        this.ia = _ia;
    }
    
    //Renvoie l'IA de l'entité
    public IA getIA() {
        return this.ia;
    }
    
    //Renvoie le type d'entité
    public abstract Type_Entite getType();
    
    //Renvoie le nombre de pièce d'or que l'entité rapporte à la mort
    public abstract int getGainOr();
    
    //Fixe le controleur d'action
    public abstract void setControleur(Controleur_Action controleur);
//------------------------------------------------------------------------------


//------GESTION DES ACTIONS ----------------------------------------------------
    
    //Gestion des actions
    public void agir() {
        boolean actionPossible = false;
        //récupère l'action proposé par l'IA
        Type_Action action = Type_Action.attendre;
        if(this.ia != null) {
            action = this.ia.action();
        }        
        //La réalise sur le model
        switch(action) {
            case attendre: actionPossible = true;
                break;
            case deplacement_droite: case deplacement_gauche: case deplacement_haut: case deplacement_bas:  actionPossible=this.bouger(action);
                break;
            case interagir_droite: case interagir_gauche: case interagir_haut: case interagir_bas : actionPossible=this.interagir(action);
                break;
            case ramasser: actionPossible=this.ramasser();
                break;
            case acheter: actionPossible=this.acheter();
                break;
            case sortir: actionPossible=this.sortir();
        }
        //Si l'action est possible et que le controleur d'action existe
        if(actionPossible && this.controleur != null) {
            this.controleur.modification(action);
        }
        
    }
    
    //Gestion des interactions (minage et attaque)
    public boolean interagir(Type_Action action) {
        boolean res = false;
        //récupérer la case destination
        Case caseDepart = this.saCase;
        int numLigneArrivee = this.saCase.getLigne();
        int numColonneArrivee = this.saCase.getColonne();
        
        switch(action) {
            case interagir_droite: numColonneArrivee++;
                break;
            case interagir_gauche: numColonneArrivee--;
                break;
            case interagir_haut: numLigneArrivee--;
                break;
            case interagir_bas: numLigneArrivee++;
                break;
        }
        
        Case caseArrivee = this.saCase.getMap().getCase(numLigneArrivee, numColonneArrivee);
        
        //Détermine quelle action à réaliser (creuser ou attaquer)
        if(caseDepart != caseArrivee) {
            if(caseArrivee.getEntite() != null) {
                res = this.attaquer(caseArrivee.getEntite(),action);
            }
            else if(caseArrivee.destructible() != 0) {
                res = this.creuser(caseArrivee);
            }
        }
        
        return res;
    }
    
    //Gestion de l'action : Sortir
    public boolean sortir() {
       System.out.println("Action impossible - seule Cadence peut sortir");
       return false;
    }
    
    //Gestion de l'action : Creuser
    public boolean creuser(Case caseArrivee) {
        System.out.println("Action impossible - seule Cadence peut creuser");
        return false;
    }
    
    //Gestion de l'action : Attaquer
    public boolean attaquer(Entite entite,Type_Action action) {
        System.out.println("Action impossible - seule Cadence et les montres peuvent attaquer");
        return false;
    }
    
    //Gestion de l'action : Ramasser
    public boolean ramasser() {
        System.out.println("Action impossible - seule Cadence peut ramasser un objet");
        return false;
    }
    
    //Gestion de l'action : Acheter
    public boolean acheter() {
        System.out.println("Action impossible - seule Cadence peut acheter un objet");
        return false;
    }
    
    //Gestion de l'action : Bouger
    public boolean bouger(Type_Action action) {
        //Récupère la case d'arrivée
        boolean res = false;
        Case caseDepart = this.saCase;
        int numLigneArrivee = this.saCase.getLigne();
        int numColonneArrivee = this.saCase.getColonne();
        
        switch(action) {
            case deplacement_droite: numColonneArrivee++;
                break;
            case deplacement_gauche: numColonneArrivee--;
                break;
            case deplacement_haut: numLigneArrivee--;
                break;
            case deplacement_bas: numLigneArrivee++;
                break;
        }
        
        Case caseArrivee = this.saCase.getMap().getCase(numLigneArrivee, numColonneArrivee);
        
        //le mouvement est-elle possible ?
        if(caseArrivee != null) {
            if(caseArrivee.franchissable()) {
                this.setCase(caseArrivee);
                caseDepart.setEntite(null);
                caseArrivee.setEntite(this);
                res = true;
            }
            else {
                System.err.println("Mouvement impossible - case d'arrivée occupée");
            }
        }
        else {
            System.err.println("Mouvement impossible - pas de case à cet endroit");
        }
        
        return res;
    }
//------------------------------------------------------------------------------

}
