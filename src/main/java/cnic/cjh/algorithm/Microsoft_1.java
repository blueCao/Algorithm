package cnic.cjh.algorithm;

import java.util.Arrays;

/**
 * 微软笔试题：
 * 给定一个整数（有可能正、负、0），输出这个整数的重新排列之后的最小值
 *
 * 例如：
 *  输入321，输出123
 *  输出-123，输出-321
 *
 *  解决思路：因为位数有限，利用计数排序即可在 O(n) 时间复杂度下求得
 */
public class Microsoft_1 {
    public static void main(String[] args){
        System.out.println(new Microsoft_1().find_smallest(-567000));
        System.out.println(new Microsoft_1().find_smallest(567000));
        System.out.println(new Microsoft_1().find_smallest(567000));
        System.out.println(new Microsoft_1().find_smallest(4600910));
        System.out.println(new Microsoft_1().find_smallest(-32324));
        System.out.println(new Microsoft_1().find_smallest(-23200110));
    }
    int find_smallest(int lockingKey){
        // 0
        if (lockingKey == 0){
            return 0;
        }
        // 1. 判断正负
        // 2. 拆分为数码,计数排序
        char[] char_array = String.valueOf(lockingKey).toCharArray();
        int[] count = new int[10];
        int start_index = 0;
        if (lockingKey < 0){
            start_index = 1;
        }
        for(int i=start_index; i < char_array.length; i++){
            count[char_array[i] - '0'] = count[char_array[i] - '0'] + 1;
        }
        StringBuilder sb = new StringBuilder();
        // 3. 输出最小值
        if( lockingKey < 0 ){
            // 3.1 小于0
            sb.append("-");
            for(int i=9; i >= 0; i--){
                for(int j =0; j < count[i]; j++){
                    sb.append(i);
                }
            }
        } else {
            // 3.2 大于0
            for(int i = 1; i <= 9; i++){
                if( count[i] > 0){
                    start_index = i;
                    break;
                }
            }
            if(count[start_index] > 0){
                sb.append(start_index);
            }
            count[start_index] = count[start_index] - 1;
            for(int i=0; i < count[0]; i++){
                sb.append(0);
            }
            for(int i=1; i <= 9; i++){
                for(int j =0; j < count[i]; j++){
                    sb.append(i);
                }
            }
        }
        // 5. 转为整数
        return Integer.valueOf(sb.toString());
    }
}