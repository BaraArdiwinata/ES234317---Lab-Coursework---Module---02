import java.util.*;

public class TravelPlan {
    public static void cheapestRoute(int[][] graphMatrix, String[] labels, int start) {
        int n = graphMatrix.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);

        distances[start] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> distances[i]));
        pq.add(start);

        while (!pq.isEmpty()) {
            int current = pq.poll();
            visited[current] = true;

            for (int i = 0; i < n; i++) {
                if (graphMatrix[current][i] > 0 && !visited[i]) {
                    int newDist = distances[current] + graphMatrix[current][i];
                    if (newDist < distances[i]) {
                        distances[i] = newDist;
                        previous[i] = current;
                        pq.add(i);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == start) {
                System.out.println("Path: " + labels[start]);
                System.out.println("Cost: " + distances[start]);
                System.out.println();
                continue;
            }

            List<String> path = new ArrayList<>();
            int temp = i;
            while (temp != -1) {
                path.add(labels[temp]);
                temp = previous[temp];
            }
            Collections.reverse(path);

            System.out.println("Path: " + String.join(" --> ", path));
            System.out.println("Cost: " + distances[i]);
            System.out.println();
        }
    }
}
