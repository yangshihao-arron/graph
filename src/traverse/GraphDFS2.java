package traverse;

import basic.Graph;
import java.util.ArrayList;

//所有联通分量都可以遍历了
public class GraphDFS2 {

    private Graph G;
    private boolean[] visited;


    private ArrayList<Integer> order = new ArrayList<>();

    public GraphDFS2(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        order.add(v);
        for(int w : G.adj(v))
            if(!visited[w])
                dfs(w);
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphDFS2 graphDFS1 = new GraphDFS2(graph);
        System.out.println(graphDFS1.order());
    }
}
