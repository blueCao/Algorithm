package cnic.cjh.algorithm.leetcode;

/*
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
 * 1234,1,2
 * 12345,1,
 */
public class Problem_4
{

	public double findMedianSortedArrays(int[] nums_up, int[] nums_down)
	{
		int length = nums_up.length + nums_down.length;
		double num1 = 0, num2 = 0;
		int up = 0, down = 0, count = 0;
		int last = 0;
		int m = -1;
		if( length % 2 == 1)
			m = length / 2 ;
		else
			m = length / 2 - 1;
		for (; count <= m && up < nums_up.length && down < nums_down.length; count++)
		{
			if (nums_up[up] < nums_down[down])
			{
                last = nums_up[up];
				up++;
			} else
			{
                last = nums_down[down];
				down++;
			}
		}

		if (up == nums_up.length)
		{
			for (; count <= m; count++)
			{
                last = nums_down[down];
				down++;
			}
			num1 = last;
			if(down < nums_down.length)
				num2 = nums_down[down];
		} else if (down == nums_down.length)
		{ 
			for (; count <= m; count++)
			{
                last = nums_up[up];
				up++;
			}
			num1 = last;
			if( up < nums_up.length)
				num2 = nums_up[up];
		} else
		{
			num1 = last;
			if (nums_up[up] > nums_down[down])
				num2 = nums_down[down];
			else
				num2 = nums_up[up];
		}
		if (length % 2 == 1)
			return num1;
		else
			return (num1 + num2) / 2;
	}
	public static void main(String[] args)
	{
		int a[] = {1,2};
		int b[] = {3,4};
		System.out.println(new Problem_4().findMedianSortedArrays(a, b));
	}
}
