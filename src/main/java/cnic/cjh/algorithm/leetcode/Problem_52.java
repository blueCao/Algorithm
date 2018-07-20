package cnic.cjh.algorithm.leetcode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * https://leetcode.com/problems/n-queens/description/
 - 考虑的问题: 存储、表示、逻辑、遍历

 - 存储、表示: 用一维数组 4皇后问题
 【3，1，4，2】【2，4，1，3】
 值的大小表示行、槽的位置表示列

 - 逻辑
 不在同一行:表示成数组中不存在重复值
 不在同一列:永远满足
 不在对角线:相邻元素差的绝对值不≠1

 -遍历: 给定一个起始数组，枚举出所有以这个为基础的办法
 4皇后初始数组
 【0，-2，-2，-2】
 【-2，0，-2，-2】
 【-2，-2，0，-2】
 【-2，-2，-2，0】

 - 时间复杂度: N！

 - 空间复杂度: 单线程最大空间消耗N*N
 */
public class Problem_52 {
    public int counter;

    public static void main(String []args){
//        System.out.println(new Problem_52().totalNQueens(1));
//        System.out.println(new Problem_52().totalNQueens(2));
//        System.out.println(new Problem_52().totalNQueens(3));
//        System.out.println(new Problem_52().totalNQueens(4));
        System.out.println(new Problem_52().totalNQueens(5));

    }

    public int totalNQueens(int n){
        if(n==1){
            return 1;
        }
        counter = 0;
        int[] mark = new int[n];
        // 1.初始化 -1
        for(int j=0; j< mark.length; j++){
            mark[j] = -1;
        }
        // 2.遍历
        travelNext(mark,1);
        return counter;
    }

    /**
     * 根据 mark 标记判断是否继续遍历皇后
     *
     * @param mark 皇后的位置标记
     * @param nextLine 下一个皇后的行数
     */
    public void travelNext(int[] mark,int nextLine){
        // 1.判断皇后是否已经填满
        if(nextLine > mark.length){
            // 1.1 满了，结果+1
            counter++;
        }else {
            // 2.1 未满，找位置填充皇后
            for(int i=0,j=0; i<mark.length; i++){
                if(mark[i] == -1 && validateDiagonal(mark,nextLine,i)){
                    // 空闲位置,查看在该处放置是否满足条件
                    if(i == 0 ){
                       // 左边界
                        if(nextLine - mark[i+1] != 1){
                            mark[i] = nextLine;
                            travelNext(mark,++nextLine);
                            mark[i] = -1;
                            nextLine--;
                        }
                    }else if( i == mark.length - 1){
                        // 右边界
                        if(nextLine - mark[i-1] != 1){
                            mark[i] = nextLine;
                            travelNext(mark,++nextLine);
                            mark[i] = -1;
                            nextLine--;
                        }
                    }else{
                        // 中间
                        if(nextLine - mark[i+1] != 1 && nextLine - mark[i-1] != 1){
                            mark[i] = nextLine;
                            travelNext(mark,++nextLine);
                            mark[i] = -1;
                            nextLine--;
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * 检查对角线是否满足
     *
     * @param mark
     * @param x
     * @param y
     * @return
     */
    boolean validateDiagonal(int[] mark,int x,int y){
        for(int i=0; i<mark.length;i++){
            if(mark[i] != -1){
                if(  x-y == mark[i] - i || x + y == mark[i] + i){
                    return false;
                }
            }
        }
        return true;
    }
}