package cryptofthejavadancer.Model.Carte.Cases;

import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Objet.Objet;

/**
 * Classe générique d'une case de la carte
 * @author Matthieu
 */
public abstract class Case {

    private int ligne;                                                          //ligne de la case
    private int colonne;                                                        //colonne de la case
    private Entite entite;                                                      //entité présente sur la case
    private Objet objet;                                                        //objet présent sur la case
    private Map map;                                                            //Map du jeu
    
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    public Case(int _ligne,int _colonne,Map _map) {
        //Initialisation
        this.ligne = _ligne;
        this.colonne = _colonne;
        this.entite = null;
        this.objet = null;
        this.map = _map;
    }
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Renvoie l'entité présente sur la case
    public Entite getEntite() {
        return this.entite;
    }
    
    //Change l'entitée présente sur la case
    public void setEntite(Entite _entite) {
        this.entite = _entite;
    }
    
    //Renvoie l'objet présent sur la case
    public Objet getObjet() {
        return this.objet;
    }
    
    //Change l'objet présent sur la case
    public void setObjet(Objet _objet) {
        this.objet = _objet;
    }
    
    //Renvoie la carte du jeu
    public Map getMap() {
        return this.map;
    }
    
    //Renvoie la ligne de la case
    public int getLigne() {
        return this.ligne;
    }
    
    //Renvoie la colonne de la case
    public int getColonne() {
        return this.colonne;
    }
    
    //Renvoie le type de la case
    public abstract Type_Case getType();                                        
    
    //La case est-elle franchissable (de base = est-elle vide, modifiée par héritage)
    public boolean franchissable() {
        boolean rep = true;
        if(this.entite != null) {
            rep = false;
        }
        if(this.objet != null) {
            rep = rep && !this.objet.destructible();
        }
        return rep;
    }
    
    //la case est-elle destructible (0 : non, 1:oui avec une pelle, 2:oui avec la pelle améliorée) 
    public abstract int destructible();                                                          
//------------------------------------------------------------------------------
    
    public String toString() {
        return getLigne()+"/"+getColonne();
    }

}
