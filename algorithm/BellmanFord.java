package algorithm;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
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
        for(int i=0;i<graph.getVertexList().size()-1;i++){
            for(Edge edge: graph.getEdges()){
                Vertex start = edge.getStart();
                Vertex target = edge.getTarget();
              //  realxation
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
                    System.out.println("There is an Arbitage Opportunity");
                    Vertex start = edge.getStart();
                    while(!cycleList.contains(start)){
                        this.cycleList.add(start);
                        start = start.getPreviousVertexOnShortestPath();
                    }
                    this.cycleList.add(start);
                    getArbitageOpportunity();
                    return;
                }
            }
        }
    }

    private boolean hasCycle(Edge edge){
        return edge.getStart().getDistance() + edge.getWeight() < edge.getTarget().getDistance();
    }

    public void getArbitageOpportunity(){
        List<Vertex> checker = new ArrayList<>();
        for(Vertex v: cycleList){
            if(!checker.contains(v)){
                System.out.print(v +"----->");
                checker.add(v);
            }else{
                checker.add(v);
                System.out.print(v);
                break;
            }
        }

        computeProfit();
    }

    private double computeProfit(){
        //implement me
        return 0.0;
    }

}
