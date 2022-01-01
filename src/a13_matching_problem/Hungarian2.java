package a13_matching_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Hungarian2 {

    private Graph G;
    private int maxMatching = 0;
    private int[] matching;
    private boolean[] visited;

    public Hungarian2(Graph G){
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartite())
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        this.G = G;

        int[] colors = bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching, -1);

        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(colors[v] == 0 && matching[v] == -1){
                Arrays.fill(visited, false);
                if(dfs(v)) maxMatching ++;
            }


    }

    private boolean dfs(int v){

        visited[v] = true;
        for(int u : G.adj(v)){
            if(!visited[u]){
                visited[u] = true;
                if(matching[u] == -1 || dfs(matching[u])){
                    matching[v] = u ;
                    matching[u] = v;
                    return true;
                }
//                if(matching[u] == -1){
//                    matching[v] = u ;
//                    matching[u] = v;
//                    return true;
//                }
//                else if(dfs(matching[u])){
//                    matching[v] = u ;
//                    matching[u] = v;
//                    return true;
//                }
            }
        }
        return false;
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args){

        Graph g = new Graph("g13.txt");
        Hungarian2 hungarian = new Hungarian2(g);
        System.out.println(hungarian.maxMatching());

        Graph g2 = new Graph("g13_2.txt");
        Hungarian2 hungarian2 = new Hungarian2(g2);
        System.out.println(hungarian2.maxMatching());
    }
}
