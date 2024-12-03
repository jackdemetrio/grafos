import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        RouteFinder finder = new RouteFinder(graph);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Menu de opções
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar cidade");
            System.out.println("2. Adicionar estrada");
            System.out.println("3. Remover estrada");
            System.out.println("4. Encontrar rota mais curta");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida.");
                System.out.print("Escolha uma opção: ");
                scanner.next(); // Consumir entrada inválida
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (choice) {
                case 1:
                    // Adiciona uma cidade ao grafo
                    System.out.print("Nome da cidade: ");
                    String city = scanner.nextLine();
                    graph.addCity(city);
                    break;

                case 2:
                    // Adiciona uma estrada entre duas cidades
                    System.out.print("Cidade 1: ");
                    String city1 = scanner.nextLine();
                    if (!graph.getCities().contains(city1)) {
                        System.out.println("Cidade 1 não encontrada.");
                        break;
                    }

                    System.out.print("Cidade 2: ");
                    String city2 = scanner.nextLine();
                    if (!graph.getCities().contains(city2)) {
                        System.out.println("Cidade 2 não encontrada.");
                        break;
                    }

                    System.out.print("Distância: ");
                    int distance = scanner.nextInt();
                    System.out.print("Custo: ");
                    int cost = scanner.nextInt();
                    graph.addRoad(city1, city2, distance, cost);
                    break;

                case 3:
                    // Remove uma estrada entre duas cidades
                    System.out.print("Cidade 1: ");
                    city1 = scanner.nextLine();
                    System.out.print("Cidade 2: ");
                    city2 = scanner.nextLine();
                    graph.removeRoad(city1, city2);
                    break;

                case 4:
                    // Encontra a rota mais curta entre duas cidades
                    System.out.print("Cidade de partida: ");
                    String start = scanner.nextLine();
                    if (!graph.getCities().contains(start)) {
                        System.out.println("Cidade de partida não encontrada.");
                        break;
                    }

                    System.out.print("Cidade de destino: ");
                    String end = scanner.nextLine();
                    if (!graph.getCities().contains(end)) {
                        System.out.println("Cidade de destino não encontrada.");
                        break;
                    }

                    System.out.print("Custo máximo (ou -1 para ignorar): ");
                    int maxCost = scanner.nextInt();

                    List<String> path = finder.findPath(start, end, maxCost == -1 ? Integer.MAX_VALUE : maxCost, maxCost != -1);
                    if (path.isEmpty()) {
                        System.out.println("Nenhuma rota encontrada.");
                    } else {
                        System.out.println("Rota encontrada: " + path);
                    }
                    break;

                case 5:
                    // Sai do programa
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    // Tratamento para opção inválida
                    System.out.println("Opção inválida.");
            }
        }
    }
}