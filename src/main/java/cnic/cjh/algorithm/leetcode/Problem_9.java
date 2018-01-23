package cnic.cjh.algorithm.leetcode;

/**
 * https://leetcode.com/problems/palindrome-number/description/
 *
 * 判读数字回文数
 */
public class Problem_9 {
    public boolean isPalindrome(int x) {
        // [0,9] 只有一位，是回文数
        if( -1 < x && x < 10){
            return true;
        }
        // 小于0
        if( x < 0){
            return false;
        }
        //以0结尾的一定不为回文
        if( x % 10 == 0){
            return false;
        }
        int digitLength = 0;
        int factor = 1;
        //判断总共有几位（除数不溢出  且  除得的结果大于0）
        while( x / factor > 0 ){
            digitLength++;
            if(Integer.MAX_VALUE / factor / 10 == 0){
                break;
            }
            factor *= 10;
        }
        if(Integer.MAX_VALUE / factor / 10 != 0){
            factor /= 10;
        }
        //中间位，低位、高位除数
        int middle = digitLength / 2,
                low_factor = 10,
                high_factor = factor;
        //高低位比较是否回文
        for(int i = 0; i < middle; i++){
            int low = 0, high = 0;
            high = x / high_factor % 10;
            low = x % low_factor / (low_factor / 10);
            //比较高、低位
            if ( low != high){
                return false;
            }
            high_factor /= 10;
            low_factor *= 10;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(
                new Problem_9().isPalindrome(1410110141)
        );
        System.out.println(
                new Problem_9().isPalindrome(-20)
        );
        System.out.println(
                new Problem_9().isPalindrome(101)
        );
    }

}
