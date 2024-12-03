public class Node {
    private String node;
    private int distance;

    public Node(String node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public String getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }
}
