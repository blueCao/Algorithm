package cnic.cjh.algorithm.leetcode;

import java.util.Map;
import java.util.TreeMap;
 
public class Problem_3
{

    public int lengthOfLongestSubstring(String s) {
    	if(s.length() < 1) return 0;
    	int history[] = new int[s.length()];
    	int inclusive[] = new int[s.length()];
    	int inclusive_start = 0;
    	char c[] = s.toCharArray();
    	history[0] = 1;
    	inclusive[0] = 1;
    	Map<Integer,Integer> char_set = new TreeMap<Integer,Integer>();
    	String max_substring = s.substring(0, 1);
    	char_set.put(Integer.valueOf(c[0]), 0);
    	
    	for(int i = 1 ; i < c.length ; i++)
    	{
    		Integer add_result = char_set.put(Integer.valueOf(c[i]), i);
    		if( add_result == null)
    		{
    			inclusive[i] = inclusive[i-1] + 1;
    		}else
    		{
    			for(int j = inclusive_start; j < add_result; j++)
    			{
    				char_set.remove(Integer.valueOf(c[j]));
    			}
    			inclusive_start = add_result + 1;
    			inclusive[i] = i - inclusive_start + 1;
    		}
    		
			if(history[i - 1] < inclusive[i] )
			{
				history[i] = inclusive[i];
				max_substring = s.substring(inclusive_start,i+1);
			}else
			{
				history[i] = history[i - 1];
			}
    	}
    	return max_substring.length();
    }
    public static void main(String[] args)
    {
    	System.out.println(new Problem_3().lengthOfLongestSubstring("pwwkew"));
    }
}