package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Vertex;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.Objet.Objet;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import java.util.ArrayList;

public class IA_Diamants extends IA {

    private Dijkstra algorithme;
    private boolean firstTime = true;
    private ArrayList<Vertex> diamantsList;

    public IA_Diamants(Entite _entite) {
        super(_entite);
        diamantsList = new ArrayList<>();
        for(Objet obj : this.getMap().getListeObjet()){
            if(obj.getType()== Type_Objet.Diamant){
                diamantsList.add(this.getMap().getGraphe_simple().getVertex(obj.getCase().getLigne()+"/"+obj.getCase().getColonne()));
            }
        }
    }

    @Override
    public Type_Action action() {
        Type_Action toDo;
        if(firstTime == true){
            firstTime = false;
            algorithme = new Dijkstra(this.getMap().getGraphe_simple());
            algorithme.calcul(getMap().getGraphe_simple().getVertex(getMap().getDepart().toString()),getMap().getGraphe_simple().getVertex(getMap().getSortie().toString()));
        }
        if(algorithme.getPath().isEmpty()){
            toDo = Type_Action.sortir;
        }else{
            toDo = vertexToAction(getMap().getCase(algorithme.getPath().get(0).getCase().getLigne(),algorithme.getPath().get(0).getCase().getColonne()));
        }
        return toDo;
    }

    private Vertex closerDiamant(){
        algorithme = new Dijkstra(this.getMap().getGraphe_simple());
        int dist = 10000;
        Vertex closerOne = null;
        for(Objet obj : this.getMap().getListeObjet()){
            if(obj.getType()== Type_Objet.Diamant){
                Vertex start = getMap().getGraphe_simple().getVertex(getMap().getDepart().toString());
                Vertex diamant = this.getMap().getGraphe_simple().getVertex(obj.getCase().getLigne()+"/"+obj.getCase().getColonne());
                algorithme.calcul(start,diamant);
                if(algorithme.getPath().size()<dist){
                    dist = algorithme.getPath().size();
                    closerOne = diamant;
                }
            }
        }
        return closerOne;
    }

    public Type_Action vertexToAction(Case nextCase){
        Type_Action toDo = Type_Action.attendre;
        Case c = this.getEntite().getCase();
        int X = c.getLigne();
        int Y = c.getColonne();
        //System.out.println("nexCase : "+nextCase.getType());

        //action if the next case is an unoccupied "sol"
        if(nextCase.getType() == Type_Case.Sol){
            if(nextCase.getEntite() == null){
                if(nextCase.getLigne() == X-1){
                    toDo = Type_Action.deplacement_haut;
                }
                else if(nextCase.getLigne() == X+1){
                    toDo = Type_Action.deplacement_bas;
                }
                else if(nextCase.getColonne() == Y-1){
                    toDo = Type_Action.deplacement_gauche;
                }
                else if(nextCase.getColonne() == Y+1){
                    toDo = Type_Action.deplacement_droite;
                }
                this.algorithme.destroyFirst();
            }
            //action if the next case is an occupied "sol"
            else if(nextCase.getEntite().getType() != Type_Entite.Cadence){
                if(nextCase.getLigne() == X-1){
                    toDo = Type_Action.interagir_haut;
                }
                else if(nextCase.getLigne() == X+1){
                    toDo = Type_Action.interagir_bas;
                }
                else if(nextCase.getColonne() == Y-1){
                    toDo = Type_Action.interagir_gauche;
                }
                else if(nextCase.getColonne() == Y+1){
                    toDo = Type_Action.interagir_droite;
                }
            }
        }
        //action if the next case is a "mur"
        else if(nextCase.getType() == Type_Case.Mur){

            //nextCase.set

            if(nextCase.getLigne() == X-1){
                toDo = Type_Action.interagir_haut;
            }
            else if(nextCase.getLigne() == X+1){
                toDo = Type_Action.interagir_bas;
            }
            else if(nextCase.getColonne() == Y-1){
                toDo = Type_Action.interagir_gauche;
            }
            else if(nextCase.getColonne() == Y+1){
                toDo = Type_Action.interagir_droite;
            }
        }
        System.out.println("toDo : "+toDo);
        return toDo;
    }

}
