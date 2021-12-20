package a9_minimum_spanning_tree;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class OptPrim {
    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public OptPrim(WeightedGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return ;

        //Prim
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        //默认就是最小堆
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        for(int w : G.adj(0))
            pq.add(new WeightedEdge(0,w, G.getWeight(0, w)));
        while(!pq.isEmpty()){
            WeightedEdge minEdge = (WeightedEdge)pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;
            mst.add(minEdge);

            int newv = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newv] = true;
            for(int w : G.adj(newv)){
                if(!visited[w])
                    pq.add(new WeightedEdge(newv, w, G.getWeight(newv, w)));
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("gt9.txt");
        OptPrim optPrim = new OptPrim(g);
        System.out.println(optPrim.result());
    }
}
