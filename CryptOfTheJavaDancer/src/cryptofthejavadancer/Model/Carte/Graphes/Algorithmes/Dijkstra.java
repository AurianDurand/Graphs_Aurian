/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer.Model.Carte.Graphes.Algorithmes;

//import cryptofthejavadancer.Model.Carte.Cases;
import cryptofthejavadancer.Model.Carte.Graphes.Graph;
import cryptofthejavadancer.Model.Carte.Graphes.Vertex;
import cryptofthejavadancer.Model.Carte.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



/**
 *
 * @author aurian
 */
public class Dijkstra {
    
    private Graph graph;
    private Vertex start;
    private Vertex end;
    private Map map;
    private HashMap<Vertex,Integer> distance;
    private HashMap<Vertex,Boolean> seen;
    private HashMap<Vertex,Vertex> predecessor;
    private ArrayList<Vertex> path;
    
    public Dijkstra(Graph _graph) {
        this.graph = _graph;
        this.distance = new HashMap<>();
        this.seen = new HashMap<>();
        this.predecessor = new HashMap<>();
        this.path = new ArrayList<>();
    }
    
    private void initialisation() {
        for(Vertex v : graph.getVertices()) {
            seen.put(v, Boolean.FALSE);
            predecessor.put(v,null);
            distance.put(v, Integer.MAX_VALUE);
        }
        distance.put(start,0);
    }
    
    private Vertex closerVertex() {
        int min = Integer.MAX_VALUE;
        Vertex closerOne = null;
        for(Vertex v : graph.getVertices()) {
            if(seen.get(v)==false) {
                if(distance.get(v)<min) {
                    min = distance.get(v);
                    closerOne = v;
                }
            }
        }
        return closerOne;
    }
    
    public void calcul(Vertex _start, Vertex _end) {
        this.start = _start;
        this.end = _end;
        initialisation();
        Vertex a = minDistVertexNotSeen();
        while(a != null) {
            seen.put(a, Boolean.TRUE);
            for(Vertex b : a.getNeighbours()) {
                //System.out.println("Neighbour of "+a.getCoordinates()+": "+b.getCoordinates());
                //System.out.println("Dist between a and b : "+graph.getLabel(a,b));
                relaxing(a,b);
            }
            a = minDistVertexNotSeen();
        }
        findShorterPath();
    }
    
    private void findShorterPath() {
        Vertex current = null;
        current = end;
        path.add(end);
        while(current != null) {
            if(predecessor.get(current) !=null) {
                path.add(predecessor.get(current));
            }
            current = predecessor.get(current);
        }
        Collections.reverse(path);
        destroyFirst();
        //path.remove(end);
    }
    
    private void relaxing(Vertex a, Vertex b) {
        Integer labelAB = graph.getLabel(a,b);
        if(labelAB == null){
            return;
        }
        if(distance.get(b) > (distance.get(a) + labelAB)) {
            distance.put(b,distance.get(a) + labelAB);
            predecessor.put(b, a);
        }
    }
    
    private Vertex minDistVertexNotSeen() {
        Vertex a = null;
        int min = Integer.MAX_VALUE;
        for(Vertex v : graph.getVertices()) {
            if((seen.get(v) == false)&&(distance.get(v) < min)) {
                a = v;
                min = distance.get(v);
            }
        }
        return a;
    }
    
    private boolean allVerticiesVisited() {
        boolean visited = true;
        for(boolean b : seen.values()) {
            if(b == false) {
                visited = false;
            }
        }
        return visited;
    }
    
    public ArrayList<Vertex> getPath() {
        return path;
    }

    public void destroyFirst(){
        this.path.remove(0);
    }

    public Integer getDistance(Vertex v) {
        return distance.get(v);
    }

    public void clearPath(){
        path.clear();
    }
}
