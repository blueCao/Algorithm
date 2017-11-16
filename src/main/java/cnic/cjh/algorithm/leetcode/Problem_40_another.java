package cnic.cjh.algorithm.leetcode;

import java.util.*;

public class Problem_40_another {
    public static void main(String[] args)
    {
        int[] candidates = {10,1,2,7,6,1,5};
        new Problem_40_another().combinationSum2(candidates,8);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(candidates,0,target,result,new ArrayList<>());
        return result;
    }

    /**
     * 深度优先搜素
     *
     * @param v         数组
     * @param cur       当前位置
     * @param target   距离目标值的差
     * @param result    存放结果
     * @param list      当前搜索过的路径
     */
    public void dfs(int[] v,int cur,int target,List<List<Integer>> result,List<Integer> list){
        if(target == 0) {
            result.add(new ArrayList(list));
            return;
        }
        else if(target < 0)
            return;
        for(int i=cur; i<v.length; i++) {
            //如果是与前一个值一样，不再扩长新的兄弟节点
            if (i > cur && (v[i] == v[i - 1]))
                continue;
            //加入新的孩子节点，寻找
            list.add(list.size(),v[i]);
            dfs(v,i+1,target - v[i],result,list);

            list.remove(list.size()-1);
        }
    }
}
