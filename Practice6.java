class Pair{
    int x, y, time;
    public Pair(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
}
class Solution {
    public int orangesRotting(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==2){
                    return bfs(i,j,grid,0);
                }
            }
        }

        return -1;
    }

    public void bfs(int i, int j, int[][] grid, int minTime){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i,j,0));
        int[] directions = {{0,1},{1,0},{-1,0},{0,-1}};
        while(!queue.isEmpty()){
            Pair popped = queue.remove();
            minTime = popped.time;
            int thisx = popped.x;
            int thisy = popped.y;
            for(int[] dir : directions){

                int dirx = thisx + dir[0];
                int diry = thisy + dir[1];
                if(dirx<0 || diry<0 || dirx>=grid.length || diry>=grid[0].length || grid[dirx][diry]!=1)continue;

                grid[dirx][diry]=2;
                queue.add(new Pair(dirx,diry,minTime+1));
            }
        }

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1){
                    return -1
                }
            }
        }

        return minTime;        


    }
}