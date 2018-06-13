package cryptofthejavadancer.Model.Carte.Parseur;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.Entites.*;
import cryptofthejavadancer.Model.Objet.Objet_Diamant;
import cryptofthejavadancer.Model.Objet.Objet_Pelle;
import cryptofthejavadancer.Model.Objet.Objet_Sortie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Parseur du fichier de niveau
 * @author Matthieu
 */
public class Parseur {

    private final String adresseFichier;
    private Map map;
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Parseur(String _adresseFichier,Map _map) {
        this.adresseFichier = _adresseFichier;
        this.map = _map;
    }
    
//------------------------------------------------------------------------------

    //Parse une ligne du fichier
    public void parseLigne(int numLigne,String ligne) {
        //lecture caractère par caractère de la ligne et création de la case adaptée
        for (int numColonne = 0; numColonne < ligne.length(); numColonne++) {
            char c = ligne.charAt(numColonne);

            //creation de la case
            Case nouvelleCase = null;
            switch (c) {
                case 'M':
                    nouvelleCase = Fabrique_Cases.construireCase(Type_Case.Mur, numLigne, numColonne, this.map);
                    break;
                case 'D':
                    nouvelleCase = Fabrique_Cases.construireCase(Type_Case.MurDur, numLigne, numColonne, this.map);
                    break;
                case 'I':
                    nouvelleCase = Fabrique_Cases.construireCase(Type_Case.MurIndestructible, numLigne, numColonne, this.map);
                    break;
                default:
                    nouvelleCase = Fabrique_Cases.construireCase(Type_Case.Sol, numLigne, numColonne, this.map);
                    break;
            }
            this.map.setCase(numLigne, numColonne, nouvelleCase);

            //gestion des entites
            switch (c) {
                case 'S':
                    this.map.setJoueur(new Entite_Cadence(this));
                    this.map.ajouteEntite(numLigne, numColonne, this.map.getJoueur());
                    this.map.setDepart(numLigne, numColonne);
                    break;
                case 'V':
                    this.map.ajouteEntite(numLigne, numColonne, new Entite_SlimeVert());
                    break;
                case 'B':
                    this.map.ajouteEntite(numLigne, numColonne, new Entite_SlimeBleu());
                    break;
                case 'J':
                    this.map.ajouteEntite(numLigne, numColonne, new Entite_SlimeJaune());
                    break;
                case 'C':
                    this.map.ajouteEntite(numLigne, numColonne, new Entite_ChauveSouris());
                    break;
                case 'K':
                    this.map.ajouteEntite(numLigne, numColonne, new Entite_Squelette());
                    break;
            }

            //gestion des objets
            switch (c) {
                case 'd':
                    this.map.ajouteObjet(numLigne, numColonne, new Objet_Diamant());
                    break;
                case 'p':
                    this.map.ajouteObjet(numLigne, numColonne, new Objet_Pelle());
                    break;
                case 's':
                    this.map.ajouteObjet(numLigne, numColonne, new Objet_Sortie());
                    this.map.setSortie(numLigne, numColonne);
                    break;
            }
        }
    }

    //Lance le parsage du fichier
    public void lecture() throws FileNotFoundException, IOException {
        
        //ouverture du fichier en lecture
        File file = new File(this.adresseFichier);
        BufferedReader fichier = new BufferedReader(new FileReader(file));
        
        //Lecture ligne à ligne
        String ligne;
        int numLigne = 0;
        while((ligne = fichier.readLine()) != null) {
            //parsage de la ligne
            this.parseLigne(numLigne, ligne);
            numLigne++;
        }
    }

    public Map getMap() {
        return map;
    }
}
