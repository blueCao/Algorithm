package cnic.cjh.algorithm;

import java.util.Scanner;

/**
 * Game 2048
 */
public class Game_2048 {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 2, 0, 0},
                {2, 0, 2, 0},
                {0, 2, 0, 2},
                {2, 0, 0, 0}
        };
        Scanner sc = new Scanner(System.in);
        Game_2048 g = new Game_2048();
        while(true){
            g.next(grid,sc.nextInt());
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
     * @return 1: game passed
     * -1: game failed
     * 0:  game to be continue
     */
    public int next(int[][] grid, int direction) {
        int height = grid.length,
                width = grid[0].length;
        switch (direction) {
            case 1:
                up(width, height, grid);
                break;
            case 2:
                down(width, height, grid);
                break;
            case 3:
                left(width, height, grid);
                break;
            case 4:
                right(width, height, grid);
                break;
            default:
        }
        return 0;
    }

    /**
     * move up
     *
     * @param width
     * @param height
     * @param grid
     */
    private void up(int width, int height, int[][] grid) {
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
        }
    }

    /**
     * move down
     *
     * @param width
     * @param height
     * @param grid
     */
    private void down(int width, int height, int[][] grid) {
        upsideDown(width, height, grid);
        up(width, height, grid);
        upsideDown(width, height, grid);
    }
    /**
     * move left
     *
     * @param width
     * @param height
     * @param grid
     */
    private void left(int width, int height, int[][] grid) {
        int[][] tmp = transpose(width, height, grid);
        up(width, height, tmp);
        tmp = transpose(width, height, tmp);
        copy(tmp,grid);
    }
    /**
     * move right
     *
     * @param width
     * @param height
     * @param grid
     */
    private void right(int width, int height, int[][] grid) {
        left2Right(width,height,grid);
        left(width,height,grid);
        int[][] tmp = transpose(width,height,grid);
        copy(tmp,grid);
        left2Right(width,height,grid);
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
}