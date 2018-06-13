package cryptofthejavadancer.Controleurs.Controleur;

import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.IA.Type_Action;

/**
 * Classe générale des controleurs des entités
 * @author Matthieu
 */
public abstract class Controleur_Action {

    //méthode appelée lors d'une attaque
    public abstract void attaquer(Coordonnees coo,Type_Action action);
    
}
