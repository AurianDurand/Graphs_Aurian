package cryptofthejavadancer.Model.Carte.Parseur;

import cryptofthejavadancer.Model.Carte.Cases.*;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Map;

/**
 * Fabrique des cases
 * @author Matthieu
 */
public class Fabrique_Cases {

    //singleton
    private static Fabrique_Cases instance;
    
//---------- CONSTRUCTEURS -----------------------------------------------------
    private Fabrique_Cases() {
    }
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------
    public static Fabrique_Cases get(Map _map) {
        if(instance == null) {
            instance = new Fabrique_Cases();
        }
        return instance;
    }
//------------------------------------------------------------------------------

    //construction d'une case
    public static Case construireCase(Type_Case type,int ligne,int colonne,Map map) {
        Case nouvelleCase = null;
        switch(type) {
            case Sol: nouvelleCase = new Case_Sol(ligne,colonne,map);
                break;
            case Mur: nouvelleCase = new Case_Mur(ligne,colonne,map);
                break;
            case MurDur: nouvelleCase = new Case_MurDur(ligne,colonne,map);
                break;
            case MurIndestructible: nouvelleCase = new Case_MurIndestructible(ligne,colonne,map);
                break;
        }
        return nouvelleCase;
    }
}
