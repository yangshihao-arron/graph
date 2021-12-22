package a10_shortest_path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class OptDijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;  //确认那些顶点最短路径求出来了
    private int[] pre;

    private class Node implements Comparable<Node>{

        public int v, dis;

        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    public OptDijkstra(WeightedGraph G, int s){

        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);

        pre = new int[G.V()];
        Arrays.fill(pre,-1);
        dis[s] = 0;
        pre[s] = s;

        visited = new boolean[G.V()];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while(!pq.isEmpty()){

            int cur = pq.remove().v;
            if(visited[cur]) continue;  //存了多份，前面操作过了，就不需要操作了
            visited[cur] = true;

            for(int w : G.adj(cur))
                if(!visited[w]){
                    if(dis[cur] + G.getWeight(cur, w) < dis[w]){
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w,dis[w]));
                        pre[w] = cur;
                    }
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

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) return res;

        int cur = t;
        while (cur != s){
             res.add(cur);
             cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g10.txt");
        OptDijkstra dij = new OptDijkstra(g,0);
        for(int v = 0; v < g.V(); v++)
            System.out.print(dij.disTo(v) + " ");
        System.out.println();
        System.out.println(dij.path(3));

    }
}
