package a7_hamilton_loop;

import a1_basic.Graph;

import java.util.ArrayList;
import java.util.Collections;


public class HamiltonLoop2 {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop2(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;

        dfs(0,0,G.V());
    }

    private boolean dfs(int v, int parent, int left){

        visited[v] = true;
        pre[v] = parent;
        left--;
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w : G.adj(v))
            if(!visited[w]){
                if(dfs(w, v, left)) return true;
            }
        visited[v] = false;
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
        HamiltonLoop2 hamiltonLoop = new HamiltonLoop2(g);
        System.out.println(hamiltonLoop.result());
        Graph g2 = new Graph("g92.txt");
        HamiltonLoop2 hamiltonLoop2 = new HamiltonLoop2(g2);
        System.out.println(hamiltonLoop2.result());
    }
}
