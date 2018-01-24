package cnic.cjh.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/combinations/description/
 *
 * 排列组合问题,列出所有可能的排列
 */
public class Problem_77 {
    public List<List<Integer>> combine(int n, int k) {
        int[] nodes = new int[n];
        for(int i = 0; i <  n; i++){
            nodes[i]  = i + 1;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>> ();
        List<Integer> q = new ArrayList<>(k);
        for(int i = 0; i < n; i++){
            q.add(i);
            recurese(result,nodes,k,q);
            q.remove(0);
        }
        return result;
    }

    /**
     * 深度优先，递归搜索
     * @param result
     * @param nodes
     * @param depth
     * @param q
     */
    public void recurese(List<List<Integer>> result, int[] nodes, int depth, List<Integer> q){
        //退出
        if(q.size() == 0) {
            return ;
        }
        //深度足够，添加结果
        if( q.size() == depth ){
            List<Integer> r = new ArrayList<>(depth);
            for(int i=0; i < q.size(); i++){
                r.add(nodes[q.get(i)]);
            }
            result.add(r);
        }
        //深度 + 1
        else if(q.size() < depth ){
            for(int i = q.get(q.size()-1); i < nodes.length - 1; i++ ){
                q.add(i + 1);
                recurese(result,nodes,depth,q);
                q.remove(q.size() - 1);
            }
        }
    }

    public static void main(String[] args){
        System.out.println(new Problem_77().combine(5,3));
    }

}
