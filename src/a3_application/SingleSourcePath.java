package a3_application;


import a1_basic.Graph;

import java.util.ArrayList;
import java.util.Collections;


//求单源路径
public class SingleSourcePath {

    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph G,int s){

        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i++)
            pre[i] = -1;
        dfs(s,s); //源节点就是它自己
    }

    private void dfs(int v,int parent){

        visited[v] = true;
        pre[v] = parent;
        for(int w : G.adj(v))
            if(!visited[w])
                dfs(w,v);
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){

        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) return res;

        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        SingleSourcePath ssPath = new SingleSourcePath(graph,0);
        System.out.println("0 -> 6 : " + ssPath.path(6));
        System.out.println("0 -> 5 : " + ssPath.path(5));
    }
}
