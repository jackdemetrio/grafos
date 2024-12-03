import java.util.*;

public class Graph {

    // Classe interna Edge representa uma estrada conectando cidades
    public static class Edge {
        public String destination;
        public int distance;
        public int cost;

        public Edge(String destination, int distance, int cost) {
            this.destination = destination;
            this.distance = distance;
            this.cost = cost;
        }
    }

    // Mapa de adjacências para armazenar as conexões das cidades
    private final Map<String, List<Edge>> adjList = new HashMap<>();

    // Adiciona uma cidade ao grafo
    public void addCity(String city) {
        adjList.putIfAbsent(city, new ArrayList<>());
    }

    // Adiciona uma estrada conectando duas cidades
    public void addRoad(String city1, String city2, int distance, int cost) {
        adjList.get(city1).add(new Edge(city2, distance, cost));
        adjList.get(city2).add(new Edge(city1, distance, cost));
    }

    // Remove uma estrada entre duas cidades
    public void removeRoad(String city1, String city2) {
        if (adjList.containsKey(city1)) {
            adjList.get(city1).removeIf(edge -> edge.destination.equals(city2));
        }
        if (adjList.containsKey(city2)) {
            adjList.get(city2).removeIf(edge -> edge.destination.equals(city1));
        }
    }

    // Retorna as estradas conectadas a uma cidade
    public List<Edge> getEdges(String city) {
        return adjList.getOrDefault(city, new ArrayList<>());
    }

    // Retorna o conjunto de cidades no grafo
    public Set<String> getCities() {
        return adjList.keySet();
    }
}