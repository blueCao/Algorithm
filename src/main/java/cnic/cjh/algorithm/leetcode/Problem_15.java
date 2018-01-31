package cnic.cjh.algorithm.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * 求解一个数组中所有和为0的3元组
 * 转为深度优先搜索问题
 * https://leetcode.com/problems/3sum/description/
 */
public class Problem_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //排序
System.out.println(new Date());
        Arrays.sort(nums);
System.out.println(new Date()+"sorted done!");
        List<Integer> indexMap = buildIndexMap(nums);
        List<Integer> valueMap = buildValueMap(nums);
System.out.println(new Date()+"map build done!");

        //保存值索引
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0; i < nums.length; i++){
            if(nums[i] > 0) {
                continue;
            }
            List<Integer> tracks = new ArrayList<Integer>();
            tracks.add(i);
            DFS(nums,tracks,3,result,nums[i],indexMap);
        }
System.out.println(new Date()+"DFS done!");
        //过滤重复的结果索引
        return bitmap(result,valueMap);
    }

    private void DFS(int[] nums, List<Integer> tracks, int depth, List<List<Integer>> result, int sum, List<Integer> indexMap){
        if(tracks.size() < depth){
            //剪枝：和大于 0 停止查找，因为nums数组是非递减的排序
            if(sum > 0){
                return;
            }
            for (int i=tracks.get(tracks.size() - 1) + 1; i < nums.length; i++){
                tracks.add(i);
                DFS(nums,tracks,3,result,sum + nums[i],indexMap);
                tracks.remove(tracks.size() - 1);
            }
        }else{
            //达到最大深度：停止查找，保留和 0 的结果索引
            if (sum == 0){
                List<Integer> r = new ArrayList<Integer>();
                for(int i=0; i<depth; i++){
                    //保存值索引
                    r.add(indexMap.get(tracks.get(i)));
                }
                result.add(r);
            }
        }
    }

    /**
     * 根据输入的数组，构造一个每个数组位置对应的位置索引号
     * @param nums
     * @return
     */
    public List<Integer> buildIndexMap(int[] nums){
        List<Integer> result = new ArrayList<Integer>();
        if(nums != null && nums.length > 0){
            int pre = nums[0],index=0;
            result.add(0);
            for(int i=1; i<nums.length; i++){
                if(pre != nums[i]){
                    index++;
                    pre = nums[i];
                }
                result.add(index);
            }
        }
        return result;
    }


    /**
     * 根据输入的数组，构造一个值索引
     * @param nums
     * @return
     */
    public List<Integer> buildValueMap(int[] nums){
        List<Integer> result = new ArrayList<Integer>();
        if(nums != null && nums.length > 0){
            int pre = nums[0];
            result.add(pre);
            for(int i=1; i<nums.length; i++){
                if(pre != nums[i]){
                    result.add(nums[i]);
                    pre = nums[i];
                }
            }
        }
        return result;
    }

    /**
     * 过滤掉重复的索引，返回去重的结果
     * @param origin
     * @return
     */
    public List<List<Integer>> bitmap(List<List<Integer>> origin,List<Integer> valueMap){
        int[][][] map = new int[valueMap.size()][valueMap.size()][valueMap.size()];
        List<Integer> tmp;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0; i<origin.size(); i++){
            tmp = origin.get(i);
            if (tmp != null && tmp.size() > 2){
                if(map[tmp.get(0)][tmp.get(1)][tmp.get(2)] == 0){
                    map[tmp.get(0)][tmp.get(1)][tmp.get(2)] = 1;
                    List<Integer> r = new ArrayList<Integer>(3);
                    r.add(valueMap.get(tmp.get(0)));
                    r.add(valueMap.get(tmp.get(1)));
                    r.add(valueMap.get(tmp.get(2)));
                    result.add(r);
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        int length = 100000;
        int[] input = new int[length];
        for(int i=-0; i<length; i++){
            input[i] = (int)(Math.random() * length -length/2);
        }
        System.out.println(new Problem_15().threeSum(input));
    }

}