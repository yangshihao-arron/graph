package a10_shortest_path;

import java.util.Arrays;

public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;  //确认那些顶点最短路径求出来了



    public Dijkstra(WeightedGraph G, int s){

        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s] = 0;

        visited = new boolean[G.V()];

        while(true){
            int curdis = Integer.MAX_VALUE, cur = -1;
            //寻找最小值
            for(int v = 0; v < G.V(); v++)
                if(!visited[v] && dis[v] < curdis){
                    curdis = dis[v];
                    cur = v;
                }
            if(cur == -1) break;

            visited[cur] = true;

            for(int w : G.adj(cur))
                if(!visited[w]){
                    if(dis[cur] + G.getWeight(cur, w) < dis[w])
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                }
        }
    }

    public boolean isConnectedTo(int v){

        G.validateVertex(v);
        return visited[v];
    }

    public int disTo(int v){

        G.validateVertex(v);
        return dis[v];
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g10.txt");
        Dijkstra dij = new Dijkstra(g,0);
        for(int v = 0; v < g.V(); v++)
            System.out.print(dij.disTo(v) + " ");
        System.out.println();
    }
}
