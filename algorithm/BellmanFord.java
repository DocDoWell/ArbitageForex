package algorithm;

import graph.Edge;
import graph.Vertex;

import java.util.List;
import java.util.Map;

public class BellmanFord {

    private Map<String,Vertex> vertices;
    private List<Edge> edgeList;

    public BellmanFord(Map<String,Vertex> vertices, List<Edge> edgeList) {
        this.vertices = vertices;
        this.edgeList = edgeList;
    }
}
