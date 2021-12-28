package a11_directed_grapph;

import java.util.ArrayList;
import java.util.Collections;


public class TopoSort2 {

    private Graph G;

    private ArrayList<Integer> res;
    private boolean hasCycle = false;

    public TopoSort2(Graph G){
        if(!G.isDirected()){
            throw new IllegalArgumentException("TopoSort only works in directed graph");
        }

        this.G = G;

        res = new ArrayList<>();

        hasCycle = new DirectedCycleDetection(G).hasCycle();
        if(hasCycle)
            return;

        GraphDFS dfs = new GraphDFS(G);
        for(int v :dfs.post()){
            res.add(v);
        }

        Collections.reverse(res);

    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g11.txt", true);
        TopoSort2 topoSort2 = new TopoSort2(g);
        System.out.println(topoSort2.result());

    }
}
