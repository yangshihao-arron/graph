package a3_application;

import a1_basic.Graph;

public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!visited[v])
                dfs(v,v);
    }

    private void dfs(int v, int parent){

        visited[v] = true;
        for(int w : G.adj(v))
            if(!visited[w])
                dfs(w,v);
            else if(w != parent)
                hasCycle = true;

    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());

        Graph graph2 = new Graph("g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(graph2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
