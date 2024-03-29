package a3_application;

import a1_basic.Graph;

import java.util.ArrayList;
import java.util.Collections;

//找到路径就返回
public class Path {
    private Graph G;
    private int s;
    private int t;

    private boolean[] visited;
    private int[] pre;

    public Path(Graph G,int s, int t){

        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i++)
            pre[i] = -1;
        dfs(s,s); //源节点就是它自己
        for(boolean e : visited)
            System.out.print(e + " ");
        System.out.println();
    }

    //关注递归函数语义
    private boolean dfs(int v,int parent){

        visited[v] = true;
        pre[v] = parent;

        if(v == t) return true;

        for(int w : G.adj(v))
            if(!visited[w])
                if(dfs(w,v)) return true;
        return false;
    }

    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){

        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnected()) return res;

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
        Path path = new Path(graph,0,6);
        System.out.println("0 -> 6 : " + path.path());

        Path path2 = new Path(graph,0,1);
        System.out.println("0 -> 1 : " + path2.path());

        Path path3 = new Path(graph,0,5);
        System.out.println("0 -> 5 : " + path3.path());
    }
}
