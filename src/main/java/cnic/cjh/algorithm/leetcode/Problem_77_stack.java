package cnic.cjh.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/description/
 *
 * 排列组合问题,列出所有可能的排列
 */
public class Problem_77_stack {
    public List<List<Integer>> combine(int n, int k) {
        int[] nodes = new int[n];
        for(int i = 0; i <  n; i++){
            nodes[i]  = i + 1;
        }
        List<Integer> stack = new ArrayList<>(k);
        stack.add(0);
        return travle(stack,k,n);
    }

    /**
     * 遍历这个栈，返回结果
     * @param depth
     * @param size
     */
    private List<List<Integer>> travle(List<Integer> stack,int depth, int size){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        while(!stack.isEmpty()){
            //判断 深度是否满足
            if(stack.size() == depth){
                //满足，加入结果集中
                List<Integer> r = new ArrayList<Integer>();
                for(int i=0; i < stack.size(); i++){
                    r.add(stack.get(i) + 1);
                }
                result.add(r);
                //判断 头顶值是否达到最大
                int peak = stack.get(stack.size() - 1);
                if( peak == size - 1){
                    // 达到最大,出栈
                    stack.remove(stack.size() - 1);
                    // 判断  是否不为空
                    if(!stack.isEmpty()){
                        //不为空 ： 出栈
                        peak = stack.remove(stack.size() - 1);
                        // 加入弹出的相邻元素
                        stack.add(peak + 1);
                    }
                }
                else{
                    // 未到最大,出栈
                    peak = stack.remove(stack.size() - 1);
                    // 加入弹出的相邻元素
                    stack.add(peak + 1);
                }
            }
            else{
                //不满足 深度

                //判断  头顶是否到达最大值
                int peak = stack.get(stack.size() - 1);
                if( peak == size - 1){
                    //达到最大值
                    stack.remove(stack.size() - 1);
                    //判断  ： 除去顶部最大值后是否为空
                    if(!stack.isEmpty()){
                        peak = stack.remove(stack.size() - 1);
                        //将第二层的相邻元素插入
                        stack.add(peak + 1);
                    }
                }
                else{
                    // 未到最大,获取顶部元素
                    peak = stack.get(stack.size() - 1);
                    // 加入顶部的相邻元素
                    stack.add(peak + 1);
                }
            }
        }
        return result;
    }


    public static void main(String[] args){
        System.out.println(new Problem_77_stack().combine(5,4));
    }

}
