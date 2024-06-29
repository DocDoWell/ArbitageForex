package graph;

public class Edge {
    private double weight;
    private Vertex start;
    private Vertex target;

    public Edge(double weight, Vertex start, Vertex target) {
        this.weight = weight;
        this.start = start;
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return start.toString() + "------" + weight +"------->" + target.toString();
    }
}
