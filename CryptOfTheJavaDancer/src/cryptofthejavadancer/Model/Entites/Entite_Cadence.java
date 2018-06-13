package cryptofthejavadancer.Model.Entites;

import cryptofthejavadancer.Controleurs.Controleur.Controleur_Action;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_Cadence;
import cryptofthejavadancer.Controleurs.Controleur.Controleur_GUI;
import cryptofthejavadancer.IHM.Son.Lecteur;
import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Coordonnees;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.IA.AI_Exit;
import cryptofthejavadancer.Model.IA.IA_Immobile;
import cryptofthejavadancer.Model.IA.IA_droite;
import cryptofthejavadancer.Model.IA.Type_Action;
import cryptofthejavadancer.Model.Objet.Objet;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import cryptofthejavadancer.Model.Carte.Parseur.Parseur;

/**
 * Cadence
 * @author Matthieu
 */
public class Entite_Cadence extends Entite {

    private int nombreDiamant;                                                  //Nombre de diamants ramassés
    private int nombrePieceOr;                                                  //Nombre de pièces ramassées
    private boolean pelle;                                                      //la pelle est-elle améliorée ?
    private Controleur_GUI controleurGUI;                                       //Controleur en lien avec l'IHM
    private Controleur_Cadence controleur;                                      //Controleur d'action pour Cadence


//---------- CONSTRUCTEURS -----------------------------------------------------

    public Entite_Cadence(Parseur _parseur) {
        //Fixe le nb de pv
        super(3);
        //Fixe l'IA de Cadence
        this.setIA(new AI_Exit(this));
        //Initialisation
        this.nombreDiamant = 0;
        this.nombrePieceOr = 0;
        this.pelle = false;
        this.controleurGUI = null;
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Fixe le controleur IHM
    public void setControleurGUI(Controleur_GUI _controleur) {
        this.controleurGUI = _controleur;
    }
    
    //Fixe le controleur Std (inutile ici)
    @Override
    public void setControleur(Controleur_Action _controleur) {
    }
     
    //Fixe le controleur d'action
    public void setControleur(Controleur_Cadence _controleur) {
        this.controleur = _controleur;
    }
    
    //Renvoie le type d'entité
    @Override
    public Type_Entite getType() {
        return Type_Entite.Cadence;
    }
    
    //Renvoie si Candence possède ou non la pelle améliorée
    public boolean getPelle() {
        return this.pelle;
    }
    
    //Renvoie le nombre de diamants que Cadence a ramassés.
    public int getNombreDiamants() {
        return this.nombreDiamant;
    }
    
    //Renvoie le nombre pièces que Cadence a colectées
    @Override
    public int getGainOr() {
        return this.nombrePieceOr;
    }
    
//------------------------------------------------------------------------------

    //Ajoute des pièces
    public void ajouterPieceOr(int nombre) {
        this.nombrePieceOr += nombre;
        this.controleurGUI.changerPiece(nombrePieceOr);
    }
    
    //Change le nombre de pv
    @Override
    public void changeVie(int degats) {
        super.changeVie(degats);
        if(this.getVie()>0) {
            System.err.println("Cadence a été blessée, il lui reste "+this.getVie()+" PV");
        }
    }
    
    //Cadence essaye de ramasser un objet
    @Override
    public boolean ramasser() {
        boolean res = false;
        if(this.getCase().getObjet() != null) {
            if(this.getCase().getObjet().getType() == Type_Objet.Diamant) {
                this.nombreDiamant++;
                Lecteur.play("Cadence_Yeah.mp3", false,0.3);
                this.controleurGUI.changerDiamant(nombreDiamant);
            }
            else if(this.getCase().getObjet().getType() == Type_Objet.Pelle) {
                this.pelle = true;
                this.controleurGUI.changerPelle(pelle);
            }
            this.getCase().getObjet().ramasser();
            res = true;
        }
        else {
            System.err.println("Action impossible - Pas d'objet ici");
        }
        return res;
    }
    
    //Cadence essaye d'acheter la pelle
    @Override
    public boolean acheter() {
       boolean res = false;
        if(this.getCase().getObjet() != null) {
            if(this.getCase().getObjet().getType() == Type_Objet.Pelle) {
                if(this.nombrePieceOr >= 40) {
                    this.pelle = true;
                    this.controleurGUI.changerPelle(pelle);
                    this.nombrePieceOr -= 40;
                    this.controleurGUI.changerPiece(nombrePieceOr);
                    this.getCase().getObjet().ramasser();
                    res = true;
                }
                else {
                    System.err.println("Action impossible - Cadence n'a pas assez d'or"); 
                }
            }
            else {
               System.err.println("Action impossible - Ce n'est pas une pelle ça"); 
            }
            
        }
        else {
            System.err.println("Action impossible - Pas d'objet ici");
        }
        return res; 
    }
    
    //Cadence essaye de creuser
    @Override
    public boolean creuser(Case caseArrivee) {
        boolean res = false;
        if(caseArrivee.destructible() == 1 || (caseArrivee.destructible() == 2 && this.pelle)) {
            this.controleur.creuser(new Coordonnees(caseArrivee.getLigne(),caseArrivee.getColonne()));
            this.getCase().getMap().changeTypeCase(caseArrivee, Type_Case.Sol);
            res = true;
        }
        else if(caseArrivee.destructible() == 2) {
            System.err.println("Action impossible - Cadence ne dispose pas de la pelle améliorée");
        }
        else {
            System.err.println("Action impossible - Impossible de creuser ici");
        }
        return res;
    }
    
    //Cadence essaye d'attaquer
    @Override
    public boolean attaquer(Entite entite,Type_Action action) {
        entite.changeVie(1);
        this.controleur.attaquer(new Coordonnees(entite.getCase().getLigne(),entite.getCase().getColonne()),action);
        return true;
    }
    
    //Cadence essaye de sortir
    @Override
    public boolean sortir() {
        boolean res = false;
        if(this.getCase().getObjet() != null) {
            if(this.getCase().getObjet().getType() == Type_Objet.Sortie) {
                int nbDiamantNonRamasse = 0;
                for(Objet o : this.getCase().getMap().getListeObjet()) {
                    if(o.getType() == Type_Objet.Diamant) {
                        nbDiamantNonRamasse++;
                    }
                }
                System.out.println("");
                System.out.println("    -- Fin de partie --");
                System.out.println("Cadence a ramassé "+this.nombreDiamant+" diamant(s)");
                System.out.println("Il reste "+nbDiamantNonRamasse+" diamant(s) dans le niveau");
                System.out.println("    -------------------");
                res = true;
            }
            else {
                System.err.println("Action impossible - Ce n'est pas la sortie");
            }
        }
        else {
            System.err.println("Action impossible - Ce n'est pas la sortie");
        }
        return res;
    }
    

}
