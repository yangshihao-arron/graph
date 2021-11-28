package a2_dfs;

import a1_basic.Graph;
import java.util.ArrayList;

//有bug的，只针对一个联通图有效
public class GraphDFS1 {

    private Graph G;
    private boolean[] visited;


    private ArrayList<Integer> order = new ArrayList<>();

    public GraphDFS1(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        dfs(0);
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
        GraphDFS1 graphDFS = new GraphDFS1(graph);
        System.out.println(graphDFS.order());
    }
}
