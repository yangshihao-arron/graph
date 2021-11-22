package a3_application;

import a1_basic.Graph;

public class CycleDetection2 {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection2(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!visited[v])
                if(dfs(v,v)){
                    hasCycle = true;
                    break;
                }
    }

    //从顶点v开始，判断图中是否有环
    private boolean dfs(int v, int parent){

        visited[v] = true;
        for(int w : G.adj(v))
            if(!visited[w]){
                if(dfs(w,v)) return true;
            }
            else if(w != parent)
               return true;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CycleDetection2 cycleDetection = new CycleDetection2(graph);
        System.out.println(cycleDetection.hasCycle());

        Graph graph2 = new Graph("g2.txt");
        CycleDetection2 cycleDetection2 = new CycleDetection2(graph2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
