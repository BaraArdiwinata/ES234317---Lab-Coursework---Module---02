import java.util.*;

public class WeightedGraphAL {
    private Map<String, List<Edge>> adjacencyList;

    public WeightedGraphAL() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String node1, String node2, int weight) {
        adjacencyList.putIfAbsent(node1, new ArrayList<>());
        adjacencyList.putIfAbsent(node2, new ArrayList<>());
        adjacencyList.get(node1).add(new Edge(node2, weight));
        adjacencyList.get(node2).add(new Edge(node1, weight));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String node : adjacencyList.keySet()) {
            sb.append(node).append(" --> ");
            List<Edge> edges = adjacencyList.get(node);
            for (Edge edge : edges) {
                sb.append(edge.getTargetNode()).append("(").append(edge.getWeight()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void dijkstra(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));

        for (String node : adjacencyList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        distances.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            String current = currentNode.getNode();
            int currentDistance = currentNode.getDistance();

            if (currentDistance > distances.get(current)) continue;

            for (Edge edge : adjacencyList.get(current)) {
                String neighbor = edge.getTargetNode();
                int newDist = currentDistance + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(new Node(neighbor, newDist));
                }
            }
        }

        List<String> path = reconstructPath(previous, start, end);
        System.out.print("Path: ");
        System.out.println(String.join(" --> ", path));
        System.out.println("Shortest distance from " + start + " to " + end + " = " + distances.get(end));
    }

    private List<String> reconstructPath(Map<String, String> previous, String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
