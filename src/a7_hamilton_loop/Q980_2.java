package a7_hamilton_loop;

public class Q980_2 {

        private int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        private int [][] grid;
        private int R, C;
        private int start, end;

        public int uniquePathsIII(int[][] grid) {

            this.grid = grid;
            R = grid.length;
            C = grid[0].length;
            int left = R * C;

            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j ++){
                    if(grid[i][j] == 1){
                        start = i * C + j;
                        grid[i][j] = 0;
                    }
                    else if(grid[i][j] == 2){
                        end = i * C + j;
                        grid[i][j] = 0;
                    }else if(grid[i][j] == -1)
                        left--;
                }
            }
            int visited = 0;
            return dfs(visited,start,left);
        }
        int dfs(int visited, int v, int left) {
            visited += (1 << v);
            left--;
            if (left == 0 && v == end) {
                visited -= (1 << v);
                return 1;
            }
            int res = 0;
            int x = v / C, y = v % C;
            for (int d = 0; d < 4; d++) {
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];
                int next = nextx * C + nexty;
                if (inArea(nextx,nexty) && grid[nextx][nexty] == 0 && (visited &(1 << next)) == 0){
                    res += dfs(visited,next, left);
                }
            }

            visited -= (1 << v);
            return res;
        }
        private boolean inArea(int x, int y){
            return x >= 0 && x < R && y >= 0 && y < C;
    }
}
