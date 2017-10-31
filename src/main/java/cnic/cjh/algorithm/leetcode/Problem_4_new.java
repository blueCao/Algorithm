package cnic.cjh.algorithm.leetcode;

/*
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
 * 1234,1,2
 * 12345,1,
 */
public class Problem_4_new
{

	public double findMedianSortedArrays(int[] nums_up, int[] nums_down)
	{
		if(nums_up.length == 0 && nums_down.length % 2 == 1)
			return nums_down[nums_down.length / 2];
		if(nums_up.length == 0 && nums_down.length % 2 == 0)
			return (nums_down[nums_down.length / 2] + nums_down[nums_down.length / 2 - 1]) / 2f;
		if(nums_down.length == 0 && nums_up.length % 2 == 1)
			return nums_up[nums_up.length / 2];
		if(nums_down.length == 0 && nums_up.length % 2 == 0)
			return (nums_up[nums_up.length / 2] + nums_up[nums_up.length / 2 - 1]) / 2f;
		
		if(nums_up.length == 1 && nums_down.length == 1)
			return (nums_up[0] + nums_up[0]) / 2f;
		
		int up_l = 0, up_r = nums_up.length - 1,down_l = 0, down_r = nums_down.length -1,down_m = -1, up_m = -1;
		
		//削减数组大小，直到其中一个数组长度变为1
		for(; (up_r - up_l) > 0 && (down_r - down_l) > 0; )
		{
			if( (up_r - up_l) > (down_r - down_l) )
			{//上长下短	
				down_m =  (down_r + down_l) / 2;
				up_m = up_r - (down_m - down_l);
				
				if(nums_up[up_m] >= nums_down[down_m])
				{//长比短大
					up_r = up_m - 1;
					down_l = down_m + 1;
				}
				else
				{//长比短小
					up_l = up_l + (down_r - down_m + 1);
					down_r = down_m - 1;
				}
			}
			else
			{//上短下长
				up_m  = (up_r + up_l) / 2;
				down_m  = down_r - (up_m - up_l);
				if(nums_down[down_m] >= nums_up[up_m])
				{//长比短大
					down_r = down_m - 1;
					up_l = up_m +1;
				} 
				else
				{//长比短小
					down_l = down_l + (up_r - up_m + 1);
					up_r = up_m - 1;
				}				
			}
		}	
		
		//拷贝数组
		int [] new_array = null;
		int inserted = 0;
		if((up_r - up_l) > 0)
		{
			new_array = new int[up_r - up_l + 3];
			inserted = nums_down[down_l];
			
			for(int i = 0; i < (up_r - up_l + 1); i++)
			{
				new_array[i + 1] = nums_up[i + up_l];
			}
		}
		else
		{
			new_array = new int[down_r - down_l + 3];
			inserted = nums_up[up_l];			
			for(int i = 0; i < (down_r - down_l + 1); i++)
			{
				new_array[i + 1] = nums_down[i + down_l];
			}
		}
		new_array[0] = Integer.MIN_VALUE;
		new_array[new_array.length - 1] = Integer.MAX_VALUE;
		
		//折半查找法，找到将1的那个数字在另外一个数组中插入的位置
		int l = 0, r = new_array.length - 1, m = -1;
		while( (r - l) > 1)
		{
			m = (l + r) / 2 ;
			if( new_array[m] >= inserted)
				r = m;
			else
				l = m;
		}
		
		if( (new_array.length + 1) % 2 == 1)
		{//奇数长度
			m = (new_array.length + 1) / 2;
			if( m == r )
				return inserted;
			else if( m > r)
				return new_array[m - 1];
			else
				return new_array[m];
		}
		else
		{//偶数长度
			int right =  (new_array.length + 1) / 2;
			int left = right - 1;
			if( left == r )
				return (new_array[right - 1] + inserted) / 2.0f;
			else if( right == r)
				return (new_array[left] + inserted) / 2.0f;
			else if( r < left)
				return (new_array[left - 1] + new_array[right -1]) / 2.0f;
			else
				return (new_array[left] + new_array[right]) / 2.0f;				
		}
	}
	public static void main(String[] args)
	{
		int a[] = {1,2};
		int b[] = {1,1};
		System.out.println(new Problem_4_new().findMedianSortedArrays(a, b));
	}
}
