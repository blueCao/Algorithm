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
        int occur_times=1;
        List<Integer> filter = new ArrayList<Integer>(nums.length);
        if(nums.length > 0){
            filter.add(nums[0]);
        }
        //去除3个以上的重复数据
        for(int i = 1,pre=0; i < nums.length; i++){
            pre = nums[i-1];
            if( pre == nums[i]){
                occur_times++;
                if(occur_times <= 4){
                    //保留出现次数大于3个的元素，让其最多出现3个
                    filter.add(nums[i]);
                }
            } else {
                occur_times = 1;
                filter.add(nums[i]);
            }
        }
        //保留过滤后的值
        nums = new int[filter.size()];
        for(int i=0; i<filter.size(); i++){
            nums[i] = filter.get(i);
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0; i < nums.length; i++){
            List<Integer> tracks = new ArrayList<Integer>();
            tracks.add(i);
            DFS(nums,tracks,3,result);
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
