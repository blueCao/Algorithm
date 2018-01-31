package cnic.cjh.algorithm.leetcode;

import java.util.*;

/**
 * 求解一个数组中所有和为0的3元组
 * 转为深度优先搜索问题
 * https://leetcode.com/problems/3sum/description/
 */
public class Problem_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //排序
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0; i < nums.length; i++){
            List<Integer> tracks = new ArrayList<Integer>();
            tracks.add(i);
            DFS(nums,tracks,3,result,nums[i]);
        }
        //过滤
        Set<Trible> set = new HashSet<Trible>(result.size());
        for(int i=0; i<result.size(); i++){
            set.add(new Trible(result.get(i)));
        }
        result.clear();
        Iterator<Trible> i = set.iterator();
        for(;i.hasNext();){
            result.add(i.next().toList());
        }
        return result;
    }
    private void DFS(int[] nums, List<Integer> tracks, int depth, List<List<Integer>> result, int sum){
        if(tracks.size() < depth){
            //剪枝：和大于 0 停止查找，因为nums数组是非递减的排序
            if(sum > 0){
                return;
            }
            for (int i=tracks.get(tracks.size() - 1) + 1; i < nums.length; i++){
                tracks.add(i);
                DFS(nums,tracks,3,result,sum + nums[i]);
                tracks.remove(tracks.size() - 1);
            }
        }else{
            //达到最大深度：停止查找，保留和 0 的结果
            if (sum == 0){
                List<Integer> r = new ArrayList<Integer>();
                for(int i=0; i<depth; i++){
                    r.add(nums[tracks.get(i)]);
                }
                result.add(r);
            }
        }
    }

    class Trible{
        int a;
        int b;
        int c;
        public Trible(List<Integer> array){
            if(array == null || array.size() < 3){
                System.out.println("构造 Trible 错误：数组长度小于三");
                return;
            }
            int[] sorted = {array.get(0),array.get(1),array.get(2)};
            //排序，依次赋值
//            Arrays.sort(sorted);
            a = sorted[0];
            b = sorted[1];
            c = sorted[2];
        }
        public List<Integer> toList(){
            List<Integer> l = new ArrayList<Integer>(3);
            l.add(a);
            l.add(b);
            l.add(c);
            return l;
        }

        @Override
        public int hashCode() {
            return Integer.toString(a).hashCode()+ Integer.toString(b).hashCode() + Integer.toString(c).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Trible t = (Trible)obj;
            return t.a == a && t.b == b && t.c == c;
        }
    }

    public static void main(String[] args){
        System.out.println(new Problem_15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}