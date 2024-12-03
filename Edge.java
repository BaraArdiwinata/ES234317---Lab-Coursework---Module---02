public class Edge {
    private String targetNode;
    private int weight;

    public Edge(String targetNode, int weight) {
        this.targetNode = targetNode;
        this.weight = weight;
    }

    public String getTargetNode() {
        return targetNode;
    }

    public int getWeight() {
        return weight;
    }
}
