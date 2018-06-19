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

    public IA_Diamants(Entite _entite) {
        super(_entite);
    }

    @Override
    public Type_Action action() {
        Vertex Cadence = getMap().getGraphe_simple().getVertex(super.getEntite().getCase().getLigne()+"/"+super.getEntite().getCase().getColonne());
        Type_Action toDo = Type_Action.attendre;
        if(firstTime == true){
            firstTime = false;
            algorithme = new Dijkstra(this.getMap().getGraphe_simple());
        }

        //if cadence hasn't anything to do
        if(algorithme.getPath().isEmpty()){
            //algorithme.calcul(getMap().getGraphe_simple().getVertex(getMap().getDepart().toString()), getMap().getGraphe_simple().getVertex(getMap().getSortie().toString()));
            Cadence = getMap().getGraphe_simple().getVertex(super.getEntite().getCase().getLigne()+"/"+super.getEntite().getCase().getColonne());
            algorithme.calcul(Cadence, getMap().getGraphe_simple().getVertex(getMap().getSortie().toString()));

            if(closerDiamant()!=null){
                //System.out.println("closer diamant : "+closerDiamant().getCoordinates());
                //if cadence is on a diamant
                if(closerDiamant()==Cadence){
                    toDo = Type_Action.ramasser;
                }else{
                    algorithme.clearPath();
                    algorithme.calcul(Cadence,closerDiamant());
                }

            //if cadence is on the exit
            }else if(Cadence==getMap().getGraphe_simple().getVertex(getMap().getSortie().toString())){
                toDo = Type_Action.sortir;
            }else{
                toDo = vertexToAction(getMap().getCase(algorithme.getPath().get(0).getCase().getLigne(), algorithme.getPath().get(0).getCase().getColonne()));
            }
        }else{
            toDo = vertexToAction(getMap().getCase(algorithme.getPath().get(0).getCase().getLigne(),algorithme.getPath().get(0).getCase().getColonne()));
        }
        return toDo;
    }

    private Vertex closerDiamant(){
        int dist = 10000;
        Vertex closerOne = null;
        for(Objet obj : this.getMap().getListeObjet()){
            if(obj.getType()== Type_Objet.Diamant){
                Vertex diamant = this.getMap().getGraphe_simple().getVertex(obj.getCase().getLigne()+"/"+obj.getCase().getColonne());
                if(algorithme.getDistance(diamant)<dist){
                    dist = algorithme.getDistance(diamant);
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
        //System.out.println("toDo : "+toDo);
        return toDo;
    }

}
