package a9_minimum_spanning_tree;

//求联通分量个数
public class CC {

    private WeightedGraph G;
    private boolean[] visited;
    private int cccount;

    public CC(WeightedGraph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!visited[v]){
                dfs(v);
                cccount++;
            }
    }

    private void dfs(int v){

        visited[v] = true;
        for(int w : G.adj(v))
            if(!visited[w])
                dfs(w);
    }

    public int count(){
        return cccount;
    }

}
