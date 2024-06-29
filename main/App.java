package main;

import algorithm.BellmanFord;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.File;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String,String> files =  instantiateFiles();

        Map<String,Vertex> vertices = instantiateVertexLookup();

        List<Edge> edges = instantiateEdges(files, vertices);

        Graph graph = new Graph(vertices,edges);

        System.out.println(graph);
       // graph.toString();

        //implement alg
        BellmanFord bellmanFord = new BellmanFord(graph);

        long startBell = System.currentTimeMillis();
        bellmanFord.run(bellmanFord.getGraph().getVertexList().get(0));
        System.out.println("BellmanFord shortest path: "+bellmanFord.getShortestPathTo(bellmanFord.getGraph().getVertexList().get(3)));
        long finishBell = System.currentTimeMillis();
        long elapsedBell= finishBell-startBell;


    }


    private static Map<String,Vertex> instantiateVertexLookup(){
        Map<String,Vertex> vertices = new HashMap<>();
        vertices.put("USD", new Vertex("USD"));
        vertices.put("GBP", new Vertex("GBP"));
        vertices.put("CAD", new Vertex("CAD"));
        vertices.put("EUR", new Vertex("EUR"));
        vertices.put("AUD", new Vertex("AUD"));
        return vertices;
    }


    private static List<Edge> instantiateEdges(Map<String,String> files, Map<String,Vertex> vertexLookup ){
        List<Edge> edgeList = new ArrayList();

        for(String country: files.keySet()){
            try (Scanner scanner = new Scanner(new File(files.get(country)))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String vertex = line.split(",")[0].trim();
                    Double rate = Double.parseDouble(line.split(",")[1].trim());
                    Double newRate = -1*Math.log(rate);
                    edgeList.add(new Edge(newRate, vertexLookup.get(country), vertexLookup.get(vertex)));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return edgeList;
    }

    private static Map<String,String> instantiateFiles(){
        Map<String,String> files = new HashMap<>();
        files.put("USD", "docs/us_exchange.csv");
        files.put("GBP", "docs/gbp_exchange.csv");
        files.put("CAD", "docs/cad_exchange.csv");
        files.put("EUR", "docs/eur_exchange.csv");
        files.put("AUD", "docs/aud_exchange.csv");
        return files;
    }

}

