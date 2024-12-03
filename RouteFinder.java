import java.util.*;

public class RouteFinder {
    private final Graph graph;

    public RouteFinder(Graph graph) {
        this.graph = graph;
    }

    // Encontra o caminho mais curto entre duas cidades
    public List<String> findShortestPath(String start, String end) {
        return findPath(start, end, Integer.MAX_VALUE, false);
    }

    // Encontra um caminho entre duas cidades com restrição de custo
    public List<String> findPath(String start, String end, int maxCost, boolean strictCost) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Integer> costs = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String city : graph.getCities()) {
            distances.put(city, Integer.MAX_VALUE);
            costs.put(city, Integer.MAX_VALUE);
        }

        pq.add(new Node(start, 0, 0));
        distances.put(start, 0);
        costs.put(start, 0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited.contains(current.city)) continue;
            visited.add(current.city);

            if (current.city.equals(end)) break;

            for (Graph.Edge edge : graph.getEdges(current.city)) {
                if (visited.contains(edge.destination)) continue;

                int newDist = current.distance + edge.distance;
                int newCost = current.cost + edge.cost;

                if (newCost <= maxCost || !strictCost) {
                    if (newDist < distances.get(edge.destination) || (strictCost && newCost < costs.get(edge.destination))) {
                        distances.put(edge.destination, newDist);
                        costs.put(edge.destination, newCost);
                        previous.put(edge.destination, current.city);
                        pq.add(new Node(edge.destination, newDist, newCost));
                    }
                }
            }
        }

        return reconstructPath(previous, end);
    }

    // Reconstrói o caminho a partir do mapa de predecessores
    private List<String> reconstructPath(Map<String, String> previous, String end) {
        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(0, at);
        }
        return path.size() > 1 && path.get(0).equals(end) ? Collections.emptyList() : path;
    }

    // Classe interna Node representa um nó na fila de prioridade
    private static class Node {
        String city;
        int distance;
        int cost;

        Node(String city, int distance, int cost) {
            this.city = city;
            this.distance = distance;
            this.cost = cost;
        }
    }
}