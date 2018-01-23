package cnic.cjh.algorithm.leetcode;

/**
 * https://leetcode.com/problems/regular-expression-matching/description/
 * 模拟正则表达式的"."和"*"
 */
public class Problem_10 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null || s.length() == 0 || p.length() == 0 ){
            return false;
        }
        return  judge(s.toCharArray(), p.toCharArray(),0, 0);
    }

    /**
     * 判断正则是否满足
     *
     * @param chars 字符串
     * @param pattern 匹配的正则
     * @param c     从第几个开始判断
     * @param p     等待判断的一个字符*
     * @return
     */
    private boolean judge(char[] chars, char[] pattern, int c, int p){
        // 匹配结束
        if ( c == chars.length ) {
            // 判断剩余的是否全部为 * 号 或者 已经匹配完成
            for ( int i = p; i < pattern.length; i++ ){
                if( pattern[i] != '*'){
                    return false;
                }
            }
            return true;
        }
        // 匹配结束(还有剩余字符未匹配)
        if ( p == pattern.length){
            return false;
        }
        // "."
        if ( pattern[p] == '.' ) {
            return judge(chars, pattern, c + 1, p + 1);
        }
        // "*"
        else if ( pattern[p] == '*' ){
            // "*"前面是"."，则需要判断剩余所有情况
            if (pattern[p - 1] == '.'){
                for( int i = c, j = 1; i < chars.length; i++){
                    if( judge(chars, pattern, c+j ,p + 1 ) ){
                        return true;
                    }
                }
            }
            // "*"前面为其他
            else{
                // 比较 "*" 的下一个字符
                if( judge(chars, pattern, c , p + 1) ){
                    return true;
                }
                // 重复 "*" 之前的字符
                if ( pattern[p -1] == chars[c]  && judge(chars, pattern, c+1 , p)){
                    return true;
                }
            }
        }
        // 其他字符
        else if( chars[c] == pattern[p] ){
            if ( judge(chars, pattern, c + 1, p + 1) ){
                return true;
            }
        }
        // 字符后面是 "*" 则继续判断
        else if( p + 1 < pattern.length && pattern[p + 1] == '*' ){
            return judge(chars, pattern, c + 1, p + 2);
        }
        // 匹配失败
        return false;
    }

    public static  void main(String[] args){
        System.out.print(new Problem_10().isMatch("ab",".*"));
    }
}
