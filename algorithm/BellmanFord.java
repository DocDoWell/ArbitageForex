package algorithm;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFord {

    private Graph graph;

    public BellmanFord(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public void run(Vertex source){
        source.setDistance(0);

        //V-1 iterations (in WC longest path contains V vertices)
        for(int i=0;i<graph.getVertexList().size()-1;i++){
            //Relaxation
            for(Edge edge: graph.getEdges()){
                Vertex start = edge.getStart();
                Vertex target = edge.getTarget();
                if(start.getDistance()+edge.getWeight() < target.getDistance()){
                    target.setDistance(start.getDistance()+edge.getWeight());
                    target.setPreviousVertexOnShortestPath(start);
                }
            }
        }
        //one more relaxation for negative cycles
        for(Edge edge: graph.getEdges()){
            if(edge.getStart().getDistance() != Double.MAX_VALUE){
                if(hasCycle(edge)){
                    System.out.println("There is a negative cycle!!!");
                    return;
                }
            }
        }
    }

    private boolean hasCycle(Edge edge){
        return edge.getStart().getDistance() + edge.getWeight() < edge.getTarget().getDistance();
    }

    public List<Vertex> getShortestPathTo(Vertex target){
        if(target.getDistance() == Double.MAX_VALUE){
            System.out.println("There is no path from start to "+ target.getName());
            return new ArrayList<>();
        }

        List<Vertex> path = new ArrayList<>();

        while(target != null){
            path.add(target);
            target = target.getPreviousVertexOnShortestPath();
        }

        Collections.sort(path);
        return path;
    }

}
