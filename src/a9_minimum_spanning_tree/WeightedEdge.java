package a9_minimum_spanning_tree;

public class WeightedEdge implements Comparable<WeightedEdge> {
    private int v, w, weight;

    public WeightedEdge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //alt + insert 快捷键
    @Override
    public int compareTo(WeightedEdge another) {
        return weight - another.weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return String.format("%d-%d: %d", v, w, weight);
    }
}
