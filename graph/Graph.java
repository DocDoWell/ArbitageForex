package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<String,Vertex>  vertexList;
    List<Edge> edges;

    public Graph(Map<String,Vertex> vertices, List<Edge> edges) {
        this.vertexList = vertices;
        this.edges = edges;
    }

    public Map<String,Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        String output = "";
        for(Edge edge: edges){
            output = output+ edge.toString()+"\n";
        }
        return output;
    }

    private List<Vertex> convertMapToList(Map<String,Vertex> vertices){
        List<Vertex> vertexList = new ArrayList<Vertex>();
        for(String label: vertices.keySet()){
            vertexList.add(vertices.get(label));
        }
        return vertexList;
    }
}
