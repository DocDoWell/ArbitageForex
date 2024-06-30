package main;

import algorithm.BellmanFord;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class App {

   // static Map<Integer, String> vertexLookup;

    public static void main(String[] args) {
        Graph graph = createGraph();
        System.out.println(graph);

        BellmanFord bellmanFord = new BellmanFord(graph);

        long startBell = System.currentTimeMillis();
        bellmanFord.run(bellmanFord.getGraph().getVertexList().get("USD"));
        bellmanFord.getShortestPathTo(bellmanFord.getGraph().getVertexList().get("JPY"));
         System.out.println("BellmanFord shortest path: "+bellmanFord.getShortestPathTo(bellmanFord.getGraph().getVertexList().get("GBP")));
        long finishBell = System.currentTimeMillis();
        long elapsedBell= finishBell-startBell;
        System.out.println("Bellman Alg took "+ elapsedBell +"ms to complete.");
        List<Vertex> cycle = bellmanFord.getCycleList();

        for(Vertex v : cycle){
            System.out.println(v);
        }
    }

    static Graph createGraph(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("docs/test.csv"));
            String header = br.readLine();
            System.out.println(header);
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




  /*      private static Map<String,Vertex> instantiateVertexLookup() {
        Map<String, Vertex> vertices = new HashMap<>();
        try (Scanner scanner = new Scanner(new File("docs/test.csv"))) {
            int count = 0;
            while (scanner.hasNextLine()) {
                if(count==0){
                    String line = scanner.nextLine();
                    String[] currencies = line.split(",");
                    for(int i = 1; i < currencies.length; i++){
                        vertices.put(currencies[i], new Vertex(currencies[i]));
                    }
                    count++;
                }else{
                    break;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
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
    }*/

}

