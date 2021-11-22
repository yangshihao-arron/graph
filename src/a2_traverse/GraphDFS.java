package a2_traverse;

import a1_basic.Graph;

import java.util.ArrayList;

//所有联通分量都可以遍历了
public class GraphDFS {

    private Graph G;
    private boolean[] visited;


    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);
        for(int w : G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }
    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphDFS graphDFS1 = new GraphDFS(graph);
        System.out.println(graphDFS1.pre());
        System.out.println(graphDFS1.post());
    }
}
