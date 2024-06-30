package main;

import algorithm.BellmanFord;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class App {

   // static Map<Integer, String> vertexLookup;

    public static void main(String[] args) {
        Graph graph = createGraph();
        BellmanFord bellmanFord = new BellmanFord(graph);
        bellmanFord.run(bellmanFord.getGraph().getVertexList().get("USD"));
    }

    static Graph createGraph(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("docs/test.csv"));
            String header = br.readLine();
            String[] columns;
            if (header != null) {
                columns = header.split(",");
            }else{
                throw new Exception("No headers in file!!!!!");
            }
            Map<String,Vertex> vertices = createVertices(columns);
            List<Edge> edgeList = new ArrayList<>();
            String next = br.readLine();
            while(next != null){
                String[] info = next.split(",");
                Vertex start = vertices.get(info[0]);
                for(int i=1; i<info.length;i++){
                    double rate = (-1)*Math.log(Double.parseDouble(info[i]));
                    Vertex target = vertices.get(columns[i]);
                    if(start != target){
                        edgeList.add(new Edge(rate, start, target));
                    }
                }
                next = br.readLine();
            }
            return new Graph(vertices,edgeList);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    static Map<String,Vertex> createVertices(String[] columns) {
        Map<String, Vertex> vertices = new HashMap<>();
        for(int i = 1; i < columns.length; i++){
            vertices.put(columns[i], new Vertex(columns[i]));
        }
        return vertices;
    }
}

