package algorithm;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFord {

    private Graph graph;
    private List<Vertex> cycleList;

    public BellmanFord(Graph graph) {
        this.graph = graph;
        this.cycleList = new ArrayList<>();
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Vertex> getCycleList() {
        return cycleList;
    }

    public void run(Vertex source){
        source.setDistance(0);

        //V-1 iterations (in WC longest path contains V vertices)
        for(int i=0;i<graph.getVertexList().size()-1;i++){

            for(Edge edge: graph.getEdges()){
                Vertex start = edge.getStart();
                Vertex target = edge.getTarget();
              //  realxation
                if(start.getDistance()+edge.getWeight() < target.getDistance()){
                    target.setDistance(start.getDistance()+edge.getWeight());
                    target.setPreviousVertexOnShortestPath(start);
                    //System.out.println(target + " distance is updated to " + start.getDistance()+edge.getWeight() +
                          //  " and prev vertex is set up " + start);
                }
            }
        }

        //one more relaxation for negative cycles
        for(Edge edge: graph.getEdges()){
            if(edge.getStart().getDistance() != Double.MAX_VALUE){
                if(hasCycle(edge)){
                    System.out.println("There is a negative cycle!!!");

                    Vertex start = edge.getStart();

                    while(!cycleList.contains(start)){
                        this.cycleList.add(start);
                        start = start.getPreviousVertexOnShortestPath();
                    }

                    this.cycleList.add(start);

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
        int counter = 0;
        while(target != null){
            path.add(target);
            if(counter < 5){
                System.out.println(target+ " added to path");
            }
            target = target.getPreviousVertexOnShortestPath();
            counter++;
        }

        Collections.sort(path);
        return path;
    }

}
