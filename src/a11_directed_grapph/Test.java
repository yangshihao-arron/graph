package a11_directed_grapph;

public class Test {
    public static void main(String[] args) {
        double[][] array = {{0,0},{0,2},{1,1},{3,2},{6,0},{6,2}};
        double[] res = new double[6];
        int[] tes = {0,0};
        res = res(array,tes);
        for(int i = 0; i < res.length; i++ )
            System.out.println(res[i]);
    }
    public static double[] res(double[][] arr, int[] tes){
        double[] res = new double[6];
        for(int i = 0; i < arr.length; i++){
            double temp = Math.sqrt(Math.pow(arr[0][0]-tes[0],2) + Math.pow(arr[0][1]-tes[1],2));
            res[i] = temp;
        }
        return res;
    }
}
