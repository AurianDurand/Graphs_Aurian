/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.Carte.Graphes;

import cryptofthejavadancer.Model.Carte.Cases.Case;

import java.util.ArrayList;

/**
 *
 * @author aurian
 */
public class Vertex {
    
    private final ArrayList<Vertex> neighbours;
    private final Graph graph;
    private final int line;
    private final int column;
    private final Case c;
    
    public Vertex(Graph g, Case _c, int line, int column) {
        neighbours = new ArrayList();
        graph = g;
        this.line = line;
        this.column = column;
        c = _c;
    }
    
    public ArrayList<Vertex> getNeighbours() {
        return neighbours;
    }
    

    public void addNeighbour(Vertex v) {
        neighbours.add(v);
    }
    
    public String getCoordinates() {
        return line+"/"+column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Case getCase() {
        return c;
    }
}
