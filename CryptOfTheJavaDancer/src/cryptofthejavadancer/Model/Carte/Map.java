package cryptofthejavadancer.Model.Carte;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Astar;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Graph;
import cryptofthejavadancer.Model.Carte.Graphes.Vertex;
import cryptofthejavadancer.Model.Carte.Parseur.Fabrique_Cases;
import cryptofthejavadancer.Model.Carte.Parseur.Parseur;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Entite_Cadence;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.Objet.Objet;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Carte du jeu
 * @author Matthieu
 */
public class Map {

    private final HashMap<Coordonnees,Case> hashMapCases;                       //Stockage par coordonnées des cases
    private final ArrayList<Entite> listeEntite;                                //Liste des entités    
    private final ArrayList<Case> listeCase;                                    //Liste des cases
    private final ArrayList<Objet> listeObjet;                                  //Liste des objets
    private Coordonnees depart;                                                 //Position du point de départ
    private Coordonnees fin;                                                    //Position de la sortie
    private Graph graphe_simple;
    private Graph graphe_improved_shovel;
    
    private Entite_Cadence joueur;                                              //Cadence
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    public Map() {
        //Initialisation
        this.hashMapCases = new HashMap<>();
        this.listeCase = new ArrayList<>();
        this.listeEntite = new ArrayList<>();
        this.listeObjet = new ArrayList<>();
        this.joueur = null;
        this.graphe_simple = null;
        this.graphe_improved_shovel = null;
    }
    
    //créer la map à partir d'un fichier texte
    public void chargerFichier(String adresseFichier) throws IOException {
        Parseur parseur = new Parseur(adresseFichier,this);
        parseur.lecture();
        System.out.println("Niveau chargé.");

            genererGrapheSimple();
            System.out.println("Simple shovel graph created");

            genererGrapheImprovedShovel();
            System.out.println("Improved shovel graph created");
    }
    
    private void genererGrapheSimple() {
        graphe_simple =  new Graph(this);
        for(Case c : listeCase) {
            graphe_simple.addVertex(c.toString(),c,c.getLigne(),c.getColonne());
        }
        for(Case c : listeCase) {
            for(Case c1 : listeCase) {
                if((Math.abs(c.getLigne()-c1.getLigne())==0)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==1)) {
                    addEdgeAndLabelGraphSimple(c,c1);
                }
                if(((Math.abs(c.getLigne()-c1.getLigne())==0)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==-1))
                        &&(Math.abs(c.getColonne()-c1.getColonne())>=0)) {
                    addEdgeAndLabelGraphSimple(c,c1);
                }
                if((Math.abs(c.getLigne()-c1.getLigne())==1)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==0)) {
                    addEdgeAndLabelGraphSimple(c,c1);
                }
                if(((Math.abs(c.getLigne()-c1.getLigne())==-1)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==0))
                        &&(Math.abs(c.getLigne()-c1.getLigne())>=0)) {
                    addEdgeAndLabelGraphSimple(c,c1);
                }
                
            }
        }        
    }
    
    private void addEdgeAndLabelGraphSimple(Case c, Case c1) {
        graphe_simple.addEdge(c.toString(), c1.toString());
        if(c1.getType()==Type_Case.Mur) {
            graphe_simple.setLabel(c.toString(), c1.toString(),2);
        }
        if(c1.getType()==Type_Case.Sol) {
            graphe_simple.setLabel(c.toString(), c1.toString(),1);
        }
    }
    
    private void genererGrapheImprovedShovel() {
        graphe_improved_shovel =  new Graph(this);
        for(Case c : listeCase) {
            graphe_improved_shovel.addVertex(c.toString(),c,c.getLigne(),c.getColonne());
        }
        for(Case c : listeCase) {
            for(Case c1 : listeCase) {
                if((Math.abs(c.getLigne()-c1.getLigne())==0)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==1)) {
                    addEdgeAndLabelGraphImprovedShovel(c,c1);
                }
                if(((Math.abs(c.getLigne()-c1.getLigne())==0)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==-1))
                        &&(Math.abs(c.getColonne()-c1.getColonne())>=0)) {
                    addEdgeAndLabelGraphImprovedShovel(c,c1);
                }
                if((Math.abs(c.getLigne()-c1.getLigne())==1)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==0)) {
                    addEdgeAndLabelGraphImprovedShovel(c,c1);
                }
                if(((Math.abs(c.getLigne()-c1.getLigne())==-1)&&
                        (Math.abs(c.getColonne()-c1.getColonne())==0))
                        &&(Math.abs(c.getLigne()-c1.getLigne())>=0)) {
                    addEdgeAndLabelGraphImprovedShovel(c,c1);
                }
            }
        }
    }

    private void addEdgeAndLabelGraphImprovedShovel(Case c, Case c1) {
        graphe_improved_shovel.addEdge(c.toString(), c1.toString());
        if(c1.getType()==Type_Case.Mur) {
            graphe_improved_shovel.setLabel(c.toString(), c1.toString(),2);
            System.out.println("mur");
        }
        if(c1.getType()==Type_Case.MurDur) {
            graphe_improved_shovel.setLabel(c.toString(), c1.toString(),2);
            System.out.println("mur dur");
        }
        if(c1.getType()==Type_Case.Sol) {
            graphe_improved_shovel.setLabel(c.toString(), c1.toString(),1);
            System.out.println("sol");
        }
    }
    
    public String stringMatrixGrapheSimple() {
        String m = "Graphe Simple Matrix :\n";
        for(Case c1 : listeCase) {
            for(Case c2 : listeCase) {
                if(graphe_simple.getLabel(c1.toString(), c2.toString()) != null) {
                    m += graphe_simple.getLabel(c1.toString(), c2.toString())+" ";
                }
                else {
                    m += "0 ";
                }
            }
            m += "\n";
        }
        return m;
    }
    
    public String stringMatrixGrapheImprovedShovel() {
        String m = "Graphe Improved Shovel Matrix :\n";
        for(Case c1 : listeCase) {
            for(Case c2 : listeCase) {
                if(graphe_improved_shovel.getLabel(c1.toString(), c2.toString()) != null) {
                    m += graphe_improved_shovel.getLabel(c1.toString(), c2.toString())+" ";
                }
                else {
                    m += "0 ";
                }
            }
            m += "\n";
        }
        return m;
    }

//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Renvoie la case présente à ses coordonnées
    public Case getCase(int ligne,int colonne) {
        return this.hashMapCases.get(new Coordonnees(ligne,colonne));
    }
    
    //Change la case à ses coordonnées
    public void setCase(int ligne, int colonne, Case _case) {
        this.hashMapCases.put(new Coordonnees(ligne,colonne), _case);
        this.listeCase.add(_case);
    }
    
    //Change le type de case à ses coordonnées (lors du minage)
    public void changeTypeCase(Case caseInitiale, Type_Case typeNouvelleCase) {
        Case nouvelleCase = Fabrique_Cases.construireCase(typeNouvelleCase, caseInitiale.getLigne(), caseInitiale.getColonne(), this);
        this.setCase(caseInitiale.getLigne(), caseInitiale.getColonne(), nouvelleCase);
        this.listeCase.remove(caseInitiale);
    }
    
    //Ajoute une entité à la position donnée
    public void ajouteEntite(int ligne,int colonne, Entite entite) {
        this.getCase(ligne, colonne).setEntite(entite);
        entite.setCase(this.getCase(ligne, colonne));
        if(entite.getType() != Type_Entite.Cadence) {
            this.listeEntite.add(entite);
        }
    }
    
    //Fixe le joueur
    public void setJoueur(Entite_Cadence cadence) {
        this.joueur = cadence;
    }
    
    //Ajoute un objet à la position donnée
    public void ajouteObjet(int ligne,int colonne, Objet objet) {
        this.getCase(ligne, colonne).setObjet(objet);
        objet.setCase(this.getCase(ligne, colonne));
        this.listeObjet.add(objet);
    }
    
    //Renvoie la hashmap des cases
    public HashMap<Coordonnees,Case> getHashMapCases() {
        return this.hashMapCases;
    }
    
    //Fixe le point de départ
    public void setDepart(int numLigne,int numColonne) {
        this.depart = new Coordonnees(numLigne,numColonne);
    }
    
    //Fixe la position de la sortie
    public void setSortie(int numLigne,int numColonne) {
        this.fin = new Coordonnees(numLigne,numColonne);
    }
    
    //Renvoie la position du point de départ
    public Coordonnees getDepart() {
        return this.depart;
    }
    
    //Renvoie la position du point de sortie
    public Coordonnees getSortie() {
        return this.fin;
    }
    
    //Renvoie la liste des entités
    public ArrayList<Entite> getListeEntite() {
        return this.listeEntite;
    }
    
    //Renvoie la liste des cases
    public ArrayList<Case> getListeCase() {
        return this.listeCase;
    }
    
    //Renvoie la liste des objets
    public ArrayList<Objet> getListeObjet() {
        return this.listeObjet;
    }
    
    //Renvoie le joueur
    public Entite_Cadence getJoueur() {
        return this.joueur;
    }
    
    public Graph getGraphe_simple() {
        return graphe_simple;
    }

    public Graph getGraphe_improved_shovel(){
        return graphe_improved_shovel;
    }
    
//------------------------------------------------------------------------------

}
