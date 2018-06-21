package cryptofthejavadancer.Model.IA;

import cryptofthejavadancer.Model.Carte.Cases.Case;
import cryptofthejavadancer.Model.Carte.Cases.Type_Case;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Astar;
import cryptofthejavadancer.Model.Carte.Graphes.Algorithmes.Dijkstra;
import cryptofthejavadancer.Model.Carte.Graphes.Vertex;
import cryptofthejavadancer.Model.Entites.Entite;
import cryptofthejavadancer.Model.Entites.Type_Entite;
import cryptofthejavadancer.Model.Objet.Objet;
import cryptofthejavadancer.Model.Objet.Type_Objet;
import java.util.ArrayList;

public class IA_Improved_Shovel extends IA {

    private Dijkstra algorithme;
    private Dijkstra improvedAlgorithme;
    private boolean firstTime = true;
    private boolean mustTookImprovedShovel = false;
    private Vertex improvedShovelPos;

    public IA_Improved_Shovel(Entite _entite) {
        super(_entite);
    }

    @Override
    public Type_Action action() {
        if(firstTime == true){
            firstTime = false;
            algorithme = new Dijkstra(this.getMap().getGraphe_simple());
            improvedAlgorithme = new Dijkstra(this.getMap().getGraphe_improved_shovel());
            for(Objet obj : this.getMap().getListeObjet()){
                if(obj.getType()== Type_Objet.Pelle){
                    improvedShovelPos = this.getMap().getGraphe_simple().getVertex(obj.getCase().getLigne()+"/"+obj.getCase().getColonne());
                }
            }
        }
        Case cadence = getMap().getJoueur().getCase();
        Type_Action toDo = Type_Action.attendre;
        if(getMap().getJoueur().getPelle() == false) {
            toDo = actionGrapheSimple(cadence);
        }else{
            toDo = actionGrapheImprovedShovel(cadence);
        }
        return toDo;
    }

    private Type_Action actionGrapheSimple(Case cadence){
        Type_Action toDo = Type_Action.attendre;
        if(algorithme.getPath().isEmpty()){
            //calcul between cadence and the exit
            algorithme.calcul(getMap().getGraphe_simple().getVertex(cadence.toString()), getMap().getGraphe_simple().getVertex(getMap().getSortie().toString()));
            if(mustTookImprovedShovel == true){
                toDo = Type_Action.ramasser;
            }else if(closerDiamant() != null){
                //if cadence is on a diamant
                if (closerDiamant() == getMap().getGraphe_simple().getVertex(cadence.toString())) {
                    toDo = Type_Action.ramasser;
                    algorithme.clearPath();
                } else {
                    algorithme.clearPath();
                    algorithme.calcul(getMap().getGraphe_simple().getVertex(cadence.toString()), closerDiamant());
                    int sizeGoToDiamant = algorithme.getPath().size();
                    algorithme.clearPath();
                    algorithme.calcul(getMap().getGraphe_simple().getVertex(cadence.toString()), improvedShovelPos);
                    int sizeGoToImprovedSHovel = algorithme.getPath().size();
                    algorithme.clearPath();
                    improvedAlgorithme.calcul(improvedShovelPos, closerDiamant());
                    int sizeShovelToDiamant = improvedAlgorithme.getPath().size();
                    improvedAlgorithme.clearPath();
                    if (sizeGoToDiamant > sizeGoToImprovedSHovel + sizeShovelToDiamant) {
                        algorithme.calcul(getMap().getGraphe_simple().getVertex(cadence.toString()), improvedShovelPos);
                        mustTookImprovedShovel = true;
                    } else {
                        algorithme.calcul(getMap().getGraphe_simple().getVertex(cadence.toString()), closerDiamant());
                    }
                }
            }else if(getMap().getGraphe_simple().getVertex(cadence.toString()) == getMap().getGraphe_simple().getVertex(getMap().getSortie().toString())) {
                toDo = Type_Action.sortir;
            } else {
                toDo = vertexToAction(getMap().getCase(algorithme.getPath().get(0).getCase().getLigne(), algorithme.getPath().get(0).getCase().getColonne()));
            }
        }else{
            toDo = vertexToAction(getMap().getCase(algorithme.getPath().get(0).getCase().getLigne(), algorithme.getPath().get(0).getCase().getColonne()));
        }
        return toDo;
    }

    private Type_Action actionGrapheImprovedShovel(Case cadence){
        Type_Action toDo = Type_Action.attendre;
        //System.out.println("path size : "+improvedAlgorithme.getPath().size());
        if(improvedAlgorithme.getPath().isEmpty()) {
            improvedAlgorithme.calcul(getMap().getGraphe_improved_shovel().getVertex(cadence.toString()), getMap().getGraphe_improved_shovel().getVertex(getMap().getSortie().toString()));
            if (improvedCloserDiamant() != null) {
                //if cadence is on a diamant
                if (improvedCloserDiamant() == getMap().getGraphe_improved_shovel().getVertex(cadence.toString())) {
                    toDo = Type_Action.ramasser;
                    improvedAlgorithme.clearPath();
                } else {
                    improvedAlgorithme.clearPath();
                    improvedAlgorithme.calcul(getMap().getGraphe_improved_shovel().getVertex(cadence.toString()), improvedCloserDiamant());
                }
                //if cadence is on the exit
            } else if (getMap().getGraphe_improved_shovel().getVertex(cadence.toString()) == getMap().getGraphe_improved_shovel().getVertex(getMap().getSortie().toString())) {
                toDo = Type_Action.sortir;
            } else {
                toDo = vertexToAction(getMap().getCase(improvedAlgorithme.getPath().get(0).getCase().getLigne(), improvedAlgorithme.getPath().get(0).getCase().getColonne()));
            }
        }else{
            toDo = vertexToAction(getMap().getCase(improvedAlgorithme.getPath().get(0).getCase().getLigne(), improvedAlgorithme.getPath().get(0).getCase().getColonne()));
        }
        System.out.println("toDo : "+toDo);
        return toDo;
    }

    private Vertex closerDiamant(){
        int dist = 100000;
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

    private Vertex improvedCloserDiamant(){
        int dist = 100000;
        Vertex closerOne = null;
        for(Objet obj : this.getMap().getListeObjet()){
            if(obj.getType()== Type_Objet.Diamant){
                Vertex diamant = this.getMap().getGraphe_improved_shovel().getVertex(obj.getCase().getLigne()+"/"+obj.getCase().getColonne());
                if(improvedAlgorithme.getDistance(diamant)<dist){
                    dist = improvedAlgorithme.getDistance(diamant);
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
                if(getMap().getJoueur().getPelle() == false) {
                    this.algorithme.destroyFirst();
                }else{
                    this.improvedAlgorithme.destroyFirst();
                }
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
        else if(nextCase.getType() == Type_Case.MurDur){

            //nextCase.set
            if(getMap().getJoueur().getPelle() == true) {

                if (nextCase.getLigne() == X - 1) {
                    toDo = Type_Action.interagir_haut;
                } else if (nextCase.getLigne() == X + 1) {
                    toDo = Type_Action.interagir_bas;
                } else if (nextCase.getColonne() == Y - 1) {
                    toDo = Type_Action.interagir_gauche;
                } else if (nextCase.getColonne() == Y + 1) {
                    toDo = Type_Action.interagir_droite;
                }
            }
        }
        return toDo;
    }

}
