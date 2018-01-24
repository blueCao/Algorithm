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
        return constructDepthTree(nodes,k);
    }

    /**
     * 构造生成树，并把指定深度的值填入结果中
     *
     * @param result
     * @param nodes
     * @param depth
     */
    private List<List<Integer>> constructDepthTree(int[] nodes, int depth){
        if(nodes == null || nodes.length == 0 || depth <= 0) {
            return null;
        }
        // 值为nodes里头的index索引
        List<Integer> q = new ArrayList<Integer>(depth);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        q.add(0,0);

        //深度优遍历
        while( q.size() > 0 ){
            //长度为 depth
            if( q.size() == depth){
                List<Integer> r = new ArrayList<Integer>(depth);
                for(int i = 0; i < depth; i++){
                    r.add(nodes[q.get(i)]);
                }
                //添加一条结果
                result.add(r);
                //插入的位置
                int insert_index = q.size() - 1;
                int peak_index = q.get(insert_index);
                //出栈
                q.remove(insert_index);
                if(peak_index < depth - 1.){
                    //入栈
                    q.add(insert_index,peak_index + 1);
                }
            }
            //长度小于 depth
            else if(q.size() < depth){
                int peak_index = q.get(q.size() - 1);
                //入栈
                q.add(q.size(),peak_index + 1);
            }
        }
        return result;
    }

}
