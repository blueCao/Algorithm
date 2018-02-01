package cnic.cjh.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字按钮排列字母问题
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
public class Problem_17 {
    private String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private char[][] char_arrays = new char[letters.length][];
    public List<String> letterCombinations(String digits) {
        byte[] bytes = digits.getBytes();
        //转为byte数组
        for(int i=0; i<letters.length; i++){
            char_arrays[i] = letters[i].toCharArray();
        }
        //过滤掉1，2
        //计算总数
        int result_total = 1;
        List<Byte> filered_bytes = new ArrayList<Byte>(bytes.length);
        for(int i=0; i< bytes.length; i++){
            int b = bytes[i] - '0';
            if(b == 0 || b == 1 || b < 0 || b > 9){
                continue;
            }
            filered_bytes.add((byte)b);
            result_total *= char_arrays[b].length;
        }
        //过滤掉为空的结果
        if(filered_bytes.size() == 0){
            return new ArrayList<String>(0);
        }
        byte[] bs = new byte[filered_bytes.size()];
        for(int i=0; i < filered_bytes.size(); i++){
            bs[i] = filered_bytes.get(i);
        }
        //计算每一个数字位之前的可能性有多少种
        int[] total_before = new int[bs.length];
        if(total_before.length > 0){
            total_before[0] = 1;
        }
        for(int i = 1; i < bs.length; i++){
            total_before[i] = total_before[i - 1] * char_arrays[bs[i - 1]].length;
        }
        //遍历所有可能
        List<String> result = new ArrayList<String>(result_total);
        for(int i = 0; i < result_total; i++){
            StringBuilder build  = new StringBuilder();
            for(int j = 0; j < bs.length; j++){
                build.append(char_arrays[bs[j]][ i / total_before[j] % char_arrays[bs[j]].length]);
            }
            result.add(build.toString());
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(new Problem_17().letterCombinations(""));
    }
}
