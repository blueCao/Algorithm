package cnic.cjh.algorithm.leetcode;

/**
 * 字符标记问题
 * https://leetcode.com/problems/first-missing-positive/description/
 */
public class Problem_41 {
    public int firstMissingPositive(int[] nums) {
        //用于标记出现过的数字,leble[0]=1,表示整数“1”出现过
        int[] labels = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            if( nums[i] >  nums.length || nums[i] < 1){
                //超出了范围 [1 , nums.length]
                continue;
            }
            else{
                //在范围[1 , nums.length]内
                //进行标记
                labels[nums[i]-1] = 1;
            }
        }
        //寻找第一个未被标记的位置，返回该位置对应的值
        for(int i=0 ; i < labels.length; i++){
            if(nums[i] != 1){
                return i+1;
            }
        }
        return -1;
    }
}
