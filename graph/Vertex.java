package graph;


import java.util.HashMap;
import java.util.Map;

public class Vertex implements Comparable<Vertex> {
    private final String name;
    private double distance;
    private Vertex previousVertexOnShortestPath;
    private Map<Vertex, Double> originalRate;

    public Vertex(String name) {
        this.name = name;
        this.distance = 0;
        this.originalRate = new HashMap<>();
    }

    public Map<Vertex, Double> getOriginalRate() {
        return originalRate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getPreviousVertexOnShortestPath() {
        return previousVertexOnShortestPath;
    }

    public void setPreviousVertexOnShortestPath(Vertex previousVertexOnShortestPath) {
        this.previousVertexOnShortestPath = previousVertexOnShortestPath;
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.distance, other.getDistance());
    }

    @Override
    public String toString() {
        return name;
    }
}
