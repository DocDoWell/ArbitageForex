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
            if(edge.getStart().getDistance() != 0){
                if(hasCycle(edge)){
                    Vertex start = edge.getStart();
                    while(!cycleList.contains(start)){
                        this.cycleList.add(start);
                        start = start.getPreviousVertexOnShortestPath();
                    }
                    this.cycleList.add(start);
                    System.out.println("There is an Arbitage Opportunity: ");
                    getArbitageOpportunity();
                    return;
                }
            }
        }
        if(cycleList.isEmpty()){
            System.out.println("There is no Arbitage Opportunity.");
        }
    }

    private boolean hasCycle(Edge edge){
        return edge.getStart().getDistance() + edge.getWeight() < edge.getTarget().getDistance();
    }

    public void getArbitageOpportunity(){
        for(int i= cycleList.size()-1; i >=0; i--){
            if(i > 0){
                System.out.print(cycleList.get(i) + " ---> ");
            }else{
                System.out.print(cycleList.get(i));
            }

        }
        double profit = computeProfit();
        double gain = profit-1000;
        System.out.println(" ");
        System.out.println("Starting with 1000 units, you could make "+profit+ " which is a gain of "+ gain);
    }

    private double computeProfit(){
        double output = 1000;

        for(int i= cycleList.size()-1; i >0; i--) {
            output = output * cycleList.get(i).getOriginalRate().get(cycleList.get(i-1));
        }

        return output;
    }

}
