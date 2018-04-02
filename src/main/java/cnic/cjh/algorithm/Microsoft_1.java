package cnic.cjh.algorithm;

import java.util.Arrays;

public class Microsoft_1 {
    public static void main(String[] args){
        System.out.println(new Microsoft_1().find_smallest(-567000));
        System.out.println(new Microsoft_1().find_smallest(567000));
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
        if( lockingKey < 0 ){
            sb.append("-");
            for(int i=9; i >= 0; i--){
                for(int j =0; j < count; j--){

                }
            }
        }


        // 5. 转为整数
        return Integer.valueOf(String.valueOf(min_chars));
    }
}