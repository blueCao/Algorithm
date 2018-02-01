package cnic.cjh.algorithm.leetcode;

import java.util.List;

/**
 * 数字按钮排列字母问题
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
public class Problem_17 {
    private String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        //计算总数
        int result_total = 1;
        for(int i=0; i < digits.getBytes().length; i++)
    }
}
