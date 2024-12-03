class Graph {
    int[][] matrix;
    int size;

    public Graph(int size) {
        this.size = size;
        matrix = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int vertex1, int vertex2, int value) {
        matrix[vertex1][vertex2] = value;
        matrix[vertex2][vertex1] = value;
    }

    public int[][] getGraphMatrix() {
        return matrix;
    }
}
