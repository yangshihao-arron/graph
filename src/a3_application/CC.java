package a3_application;


import a1_basic.Graph;

//求联通分量个数
public class CC {

    private Graph G;
    private boolean[] visited;
    private int cccount;

    public CC(Graph G){

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

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.cccount);

    }
}
