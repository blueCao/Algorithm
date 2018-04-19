package cnic.cjh.algorithm;

import java.util.Scanner;

/**
 * Game 2048
 */
public class Game_2048 {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 2},
                {0, 0, 2, 16},
                {4, 0, 4, 2},
                {2, 2, 2, 16}
        };
        Scanner sc = new Scanner(System.in);
        Game_2048 g = new Game_2048();
        while(true){
            g.displayMatrix(grid);
            if(g.next(grid,sc.nextInt()) != 0){
                break;
            }
        }
    }

    /**
     * @param grid      current number for example
     *                  0 0 0 2
     *                  0 0 0 2
     *                  0 0 2 4
     * @param direction up:1, down: 2, left:3, right:4, others: keep the same
     *                  get the next station, and choose a 0 position setting 2
     *                  for example, down, and setting <0,0> with 2
     *                  2 0 0 0
     *                  0 0 0 4
     *                  0 0 2 4
     * @return
     *      1: game passed
     *      -1: game failed because all grid is fulled up
     *      -2: game failed because there are number > 2048
     *      0:  game to be continue
     */
    public int next(int[][] grid, int direction) {
        int height = grid.length,
                width = grid[0].length,
                result = 0;
        switch (direction) {
            case 1:
                result = up(width, height, grid);
                break;
            case 2:
                result = down(width, height, grid);
                break;
            case 3:
                result = left(width, height, grid);
                break;
            case 4:
                result = right(width, height, grid);
                break;
            default:
        }
        return result;
    }

    /**
     * move up
     *
     * @param width
     * @param height
     * @param grid
     */
    private int up(int width, int height, int[][] grid) {
        int[] empty_row_index = new int[height * height],
                empty_col_index = new int[width * height];
        int empty_size = 0;
        for (int col = 0; col < width; col++) {
            int pre_index = Integer.MIN_VALUE + 1, pre = Integer.MIN_VALUE + 1;
            // 1. find first none 0 value
            for (int row = height - 1; row >= 0; row--) {
                if (grid[row][col] > 0) {
                    pre = grid[row][col];
                    pre_index = row;
                    break;
                }
            }
            // 2. set nearby same number into n * n and 0
            for (int row = pre_index - 1; row >= 0; row--) {
                if (grid[row][col] == pre) {
                    grid[pre_index][col] = 0;
                    grid[row][col] = pre * pre;
                    if(grid[row][col] == 2048){
                        return 1;
                    }else if(grid[row][col] > 2048) {
                        return -2;
                    }
                    break;
                } else if (grid[row][col] > 0) {
                    pre = grid[row][col];
                    pre_index = row;
                }
            }
            // 3. move the 0 number out
            pre = -1;
            for (int row = 0; row < height; row++) {
                if (grid[row][col] > 0) {
                    if (pre >= 0) {
                        grid[pre][col] = grid[row][col];
                        grid[row][col] = 0;
                        pre = pre + 1;
                    }
                } else if (grid[row][col] == 0) {
                    if (pre < 0) {
                        pre = row;
                    }
                }
            }
            // 4. mark 0 position
            for(int row = pre; row < height && row >=0; row++){
                empty_row_index[empty_size] = row;
                empty_col_index[empty_size] = col;
                empty_size = empty_size + 1;
            }
        }
        if(empty_size == 0){
            if(isGameOve(grid)) {
                return -1;
            }else {
                return 0;
            }
        }
        // randomly choose a  value-0 position and set 2
        int random_position  = (int)(Math.random() * empty_size);
        grid[empty_row_index[random_position]][empty_col_index[random_position]] = 2;
        return 0;
    }

    /**
     * move down
     *
     * @param width
     * @param height
     * @param grid
     */
    private int down(int width, int height, int[][] grid) {
        upsideDown(width, height, grid);
        int result = up(width, height, grid);
        upsideDown(width, height, grid);
        return result;
    }
    /**
     * move left
     *
     * @param width
     * @param height
     * @param grid
     */
    private int left(int width, int height, int[][] grid) {
        int[][] tmp = transpose(width, height, grid);
        int result = up(width, height, tmp);
        tmp = transpose(width, height, tmp);
        copy(tmp,grid);
        return result;
    }
    /**
     * move right
     *
     * @param width
     * @param height
     * @param grid
     */
    private int right(int width, int height, int[][] grid) {
        left2Right(width,height,grid);
        int result = left(width,height,grid);
        left2Right(width,height,grid);
        return result;
    }

    /**
     * Upside down the grid
     *
     * @param width
     * @param height
     * @param grid
     */
    private void upsideDown(int width, int height,int[][] grid){
        for(int col=0; col < width; col++){
            for(int row=0; row < height / 2; row++){
                int tmp = grid[row][col];
                grid[row][col] = grid[height -1 - row][col];
                grid[height -1 - row][col] = tmp;
            }
        }
    }

    /**
     * left right exchange the grid
     *
     * @param width
     * @param height
     * @param grid
     */
    private void left2Right(int width, int height,int[][] grid){
        for(int col=0; col < width / 2; col++){
            for(int row=0; row < height; row++){
                int tmp = grid[row][col];
                grid[row][col] = grid[row][width - 1 - col];
                grid[row][width - 1 - col] = tmp;
            }
        }
    }

    /**
     * matrix transpose
     *
     * @param width
     * @param height
     * @param grid
     * @return  result
     */
    private int[][] transpose(int width, int height,int[][] grid){
        int[][] t = new int [width][height];
        for(int col=0; col < width; col++){
            for(int row=0; row < height; row++){
                t[col][row] = grid[row][col];
            }
        }
        return t;
    }

    private void copy(int[][] src, int[][] dst){
        if(src.length != dst.length){
            System.out.println("src and dst array length is not equal");
            return;
        }
        for(int i = 0; i < src.length; i++){
            if(dst.length == 0 || src[0].length != dst[0].length){
                System.out.println("src and dst array length is not equal");
                return;
            }
            for(int j=0; j<src[0].length; j++){
                dst[i][j] = src[i][j];
            }
        }
    }

    private void displayMatrix(int[][] grid){
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * judge if the grid has the 2 nearby same number
     *
     * @param grid
     * @return
     */
    private boolean isGameOve(int[][] grid){
        for(int i=0; i<grid.length - 1; i++){
            for(int j=0; j<grid[i].length - 1; j++){
                if(grid[i][j] == grid[i+1][j] || grid[i][j] == grid[i][j+1]){
                    return false;
                }
            }
        }
        return true;
    }
}