package a7_hamilton_loop;

import a1_basic.Graph;

import java.util.ArrayList;
import java.util.Collections;


public class HamiltonLoop4 {


    private Graph G;
    private int[] pre;
    private int end;

    public HamiltonLoop4(Graph G){

        this.G = G;
        int visited = 0;
        pre = new int[G.V()];
        end = -1;

        dfs(0,0,G.V(),visited);
    }

    private boolean dfs(int v, int parent, int left, int visited){

        visited += (1 << v) ;
        pre[v] = parent;
        left--;
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w : G.adj(v))
            if((visited & (1 << w))== 0){
                if(dfs(w, v, left,visited)) return true;
            }
        visited -= 1 << v;
        //left++;值传递，每一层的left都不同
        return false;
    }

    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) {
            System.out.println("end:" + end);
            return res;
        }

        int cur =end;
        while (cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g9.txt");
        HamiltonLoop4 hamiltonLoop = new HamiltonLoop4(g);
        System.out.println(hamiltonLoop.result());
        Graph g2 = new Graph("g92.txt");
        HamiltonLoop4 hamiltonLoop2 = new HamiltonLoop4(g2);
        System.out.println(hamiltonLoop2.result());
    }
}
