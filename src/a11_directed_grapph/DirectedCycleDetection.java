package a11_directed_grapph;


public class DirectedCycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean[] onPath;
    private boolean hasCycle = false;

    public DirectedCycleDetection(Graph G){
        if(!G.isDirected())
            throw new IllegalArgumentException("DirectedCycleDetection only detect directed graph");
        this.G = G;
        visited = new boolean[G.V()];
        onPath = new boolean[G.V()];
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
        onPath[v] = true;
        for(int w : G.adj(v))
            if(!visited[w]){
                if(dfs(w,v)) return true;
            }
            else if(onPath[w])  //1->2,2->1也是一个环
               return true;
         onPath[v] = false;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g11.txt",true);
        DirectedCycleDetection cycleDetection = new DirectedCycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());

    }
}
