package a11_directed_grapph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//求联通分量个数，并判断两个点是否在同一个联通分量
public class SCC {

    private Graph G;
    private int[] visited;
    private int scccount;

    public SCC(Graph G){

        if(!G.isDirected())
            throw new IllegalArgumentException("SCC only workds in directed graph");

        this.G = G;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);

        GraphDFS dfs = new GraphDFS(G.reverseGraph());
        ArrayList<Integer> order = new ArrayList<>();
        for(int v : dfs.post())
            order.add(v);
        Collections.reverse(order);

        for(int v : order)
            if(visited[v] == -1){
                dfs(v,scccount);
                scccount ++;
            }
    }

    private void dfs(int v, int sccid){

        visited[v] = sccid;
        for(int w : G.adj(v))
            if(visited[w] == -1)
                dfs(w, sccid);
    }

    public int count(){

        return scccount;
    }

    public boolean isStronglyConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){

        ArrayList<Integer>[] res = new ArrayList[scccount];
        for(int i = 0; i < scccount; i++){
            res[i] = new ArrayList<>();
        }

        for(int v = 0; v < G.V(); v++){
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("ug11_2.txt",true);
        SCC scc = new SCC(graph);
        System.out.println(scc.count());

        ArrayList<Integer>[] comp = scc.components();
        for(int sccid = 0; sccid < comp.length; sccid++){
            System.out.print(sccid + " : ");
            for(int w : comp[sccid])
                System.out.print(w+" ");
            System.out.println();
        }
    }
}
