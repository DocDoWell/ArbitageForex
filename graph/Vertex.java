package graph;


public class Vertex implements Comparable<Vertex> {
    private String name;
    private double distance;
    private Vertex previousVertexOnShortestPath;

    public Vertex(String name) {
        this.name = name;
        this.distance = Double.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
