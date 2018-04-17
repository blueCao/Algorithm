package cnic.cjh.algorithm.leetcode;

/**
 * https://leetcode.com/problems/maximal-rectangle/description/
 */
public class Problem_85 {
    public static void main(String[] args){
        char[][] matrix = {{'1','1','1','1','1','1','1','1'},
                            {'1','1','1','1','1','1','1','0'},
                            {'1','1','1','1','1','1','1','0'},
                            {'1','1','1','1','1','0','0','0'},
                            {'0','1','1','1','1','0','0','0'}};
        System.out.println(new Problem_85().maximalRectangle(matrix));
    }
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int dp_right[][] = new int[matrix.length][matrix[0].length];
        int dp_down[][] = new int[matrix.length][matrix[0].length];
        /**
         * 1. init with 0-1
         */
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if( matrix[i][j] == '1'){
                    dp_right[i][j] = 1;
                    dp_down[i][j] = 1;
                }
            }
        }
        /**
         * 2. calculate from right and bottom
         */
        for(int row = 0; row < matrix.length; row++){
            for(int col = matrix[row].length - 2; col >= 0; col--){
                if(dp_right[row][col] > 0){
                    dp_right[row][col] = dp_right[row][col + 1] + 1;
                }
            }
        }
        for(int  col = 0; col< matrix[0].length; col++){
            for(int row = matrix.length - 2; row >= 0; row--){
                if(dp_down[row][col] > 0){
                    dp_down[row][col] = dp_down[row + 1][col] + 1;
                }
            }
        }
        /**
         * 3. query
         */
        int max = 0;
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[row].length; col ++){
                if(matrix[row][col] == '1'){
                    int min_col_num = dp_down[row][col];
                    for(int i = 0; i < dp_right[row][col]; i++){
                        min_col_num = min_col_num < dp_down[row][col + i] ? min_col_num : dp_down[row][col + i];
                        if( max < (1 + i) * min_col_num ){
                            max = (1 + i) * min_col_num;
                        }
                    }

                }
            }
        }
        return max;
    }
}
