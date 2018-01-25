package cnic.cjh.algorithm.leetcode;

/**
 * 寻找最长的相同前缀
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 */
public class Problem_14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        char[] common = strs[0].toCharArray();
        for(int i = 1; i < strs.length; i++){
            char[] chars = strs[i].toCharArray();
            //交换长短顺序
            if(chars.length > common.length){
                char[] tmp =  common;
                common = chars;
                chars = tmp;
            }
            int common_length = 0;
            for(int j=0; j<chars.length; j++){
                if(common[j] == chars[j]){
                    common_length++;
                }else {
                    break;
                }
            }
            if(common_length == 0){
                //没有公共前缀，停止匹配
                return "";
            }
            //保留公共前缀
            common = String.valueOf(chars,0,common_length).toCharArray();
        }
        return String.valueOf(common);
    }
    public static  void main(String[] args){
        System.out.println(new Problem_14().longestCommonPrefix(new String[]{"","a"}));
    }
}
