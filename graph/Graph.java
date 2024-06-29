package graph;

import java.util.List;
import java.util.Map;

public class Graph {
    Map<String,Vertex> vertices;
    List<Edge> edges;

    public Graph(Map<String,Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public String toString() {
        String output = "";
        for(Edge edge: edges){
            output = output+ edge.toString()+"\n";
        }
        return output;
    }
}
