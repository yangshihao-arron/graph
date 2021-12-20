package a9_minimum_spanning_tree;

public class UF{

    private int[] parent;
    private int[] sz;

    public UF(int n){

        parent = new int[n];
        for(int i = 0 ; i < n ; i ++)
            parent[i] = i;
    }

    //找到根
    public int find(int p){
        if( p != parent[p] )
            parent[p] = find( parent[p] );
        return parent[p];
    }

    public boolean isConnected(int p , int q){
        return find(p) == find(q);
    }

    public void unionElements(int p, int q){

        int pRoot = find(p);
        int qRoot = find(q);

        if( pRoot == qRoot )
            return;

        parent[pRoot] = qRoot;
    }
}