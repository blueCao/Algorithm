package cnic.cjh.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 求解一个数组中所有和为0的3元组
 * 转为深度优先搜索问题
 * https://leetcode.com/problems/3sum/description/
 */
public class Problem_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0; i < nums.length; i++){
            List<Integer> tracks = new ArrayList<Integer>();
            tracks.add(i);
            DFS(nums,tracks,3,result);
        }
        return result;
    }
    private void DFS(int[] nums, List<Integer> tracks, int depth, List<List<Integer>> result){
        if(tracks.size() < depth){
            for (int i=tracks.get(tracks.size() - 1) + 1; i < nums.length; i++){
                tracks.add(i);
                DFS(nums,tracks,3,result);
                tracks.remove(tracks.size() - 1);
            }
        }else{
            int sum = 0;
            for(int i=0; i<depth; i++){
                sum += nums[tracks.get(i)];
            }
            if (sum == 0){
                List<Integer> r = new ArrayList<Integer>();
                for(int i=0; i<depth; i++){
                    r.add(nums[tracks.get(i)]);
                }
                //TODO：需要去除重复边
                result.add(r);
            }
        }
    }
    public static void main(String[] args){
        System.out.println(new Problem_15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
