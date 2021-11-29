package a6_Bridge_and_cut_points;

import a1_basic.Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class FindCutPoints {

    private Graph G;
    private boolean[] visited;

    private int ord[];
    private int low[];
    private int cnt; //记录每一个顶点是第几个访问的

    private HashSet<Integer> res;

    public FindCutPoints(Graph G){

        this.G = G;
        visited = new boolean[G.V()];

        res = new HashSet<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent){

        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt ++;

        int child = 0;
        for(int w: G.adj(v))
            if(!visited[w]){
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                if(v != parent && low[w] >= ord[v])
                    res.add(v);
                child ++;
                if(v == parent && child > 1){
                    res.add(v);
                }
            }
            else if(w != parent)
                low[v] = Math.min(low[v], low[w]);
    }

    public HashSet<Integer> result(){
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("gc.txt");
        FindCutPoints fc = new FindCutPoints(g);
        System.out.println("cut points in gc : " + fc.result());

        Graph g2 = new Graph("gc2.txt");
        FindCutPoints fc2 = new FindCutPoints(g2);
        System.out.println("cut points in gc2 : " + fc2.result());

        Graph g3 = new Graph("gc3.txt");
        FindCutPoints fc3 = new FindCutPoints(g3);
        System.out.println("cut points in gc3 : " + fc3.result());

        Graph tree = new Graph("tree.txt");
        FindCutPoints fc_tree = new FindCutPoints(tree);
        System.out.println("cut points in tree : " + fc_tree.result());
    }
}
