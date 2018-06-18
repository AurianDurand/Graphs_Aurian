package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Graph;
import cryptofthejavadancer.Model.Carte.Map;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.Carte.Graphes.Vertex;

import java.lang.reflect.Type;

public class AI_Exit extends IA {

    private Dijkstra algorithme;
    private boolean firstTime = true;

    public AI_Exit(Entite _entite){
        super(_entite);
    }

    public Type_Action action(){
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
