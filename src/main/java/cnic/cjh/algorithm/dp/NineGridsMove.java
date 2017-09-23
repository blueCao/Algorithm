package cnic.cjh.algorithm.dp;

import java.util.Stack;

/**
 * 1-9����λ�ھŸ����ӣ���1���ڵ����ֿ��Ժ�1�໥��������ʹ�þŹ������ִ�С����������Ҫ���������ٴ���
 * ������뺯��
 * 		a1....a9 : ��ʾ��һ�����ھŸ����ӷ��õ�����
 * 		�ܾ��뺯��=ˮƽ����+��ֱ����		f(a1,a2...a9) = f_x(a1)+...f_x(a9) + f_y(a1)+...f_y(a9)
 * 		ˮƽ���뺯��=|Ŀ��ˮƽλ�� - ��ǰˮƽλ��|		f_x(a) = |current_x(a) - destination_x(a)|
 * 		��ֱ���뺯��=|Ŀ�괹ֱλ�� - ��ǰ��ֱλ��|		f_y(a) = |current_y(a) - destination_y(a)|
 * 
 */
public class NineGridsMove
{
	public static int relax = 3;
	// Ŀ��λ��
	public static int[][] d = {
			{0,0},{0,1},{0,2},
			{1,0},{1,1},{1,2},
			{2,0},{2,1},{2,2}			
	};
	public NineGridsMove()
	{
	}
	
	/*
	*��������Ĺ켣������·��ջ����������
	*		��						2
	*	��		��		�ֱ�Ϊ	-1		1
	*		��						-2
	*/
	public static Stack<Integer> dp(Stack<Integer> route,int a[][],int x,int y,int d)
	{
System.out.println(d);
		if(route == null)
		{
System.out.println("route should not be null!");
			return null;
		}
		if(d <= 0)
			return route;
		int	t = 0;
		if( x - 1 >= 0 && (route.isEmpty() || route.peek() != 1))
		{//����
			route.add(-1);
			//1�����λ�ý���
			t = a[x-1][y];
			a[x-1][y] = a[x][y];
			a[x][y] = t;
			//�������
			t = f(a);
			if( t - relax<= d )
				//������ǵ����ķ���Ѱ��
				return dp(route,a,x-1,y,t);
			//�ָ�ԭ��λ��
			t = a[x-1][y];
			a[x-1][y] = a[x][y];
			a[x][y] = t;
		}
		if( x + 1 <= 2 && (route.isEmpty() || route.peek() != -1))
		{//����
			route.add(1);
			//1���ұ�λ�ý���
			t = a[x+1][y];
			a[x+1][y] = a[x][y];
			a[x][y] = t;
			//�������
			t = f(a);
			if( t - relax<= d )
				//������ǵ����ķ���Ѱ��
				return dp(route,a,x+1,y,t);
			//�ָ�ԭ��λ��
			t = a[x+1][y];
			a[x+1][y] = a[x][y];
			a[x][y] = t;
		}
		if( y - 1 >= 0 && (route.isEmpty() || route.peek() != 2))
		{//����
			route.add(-2);
			//1���±�λ�ý���
			t = a[x][y-1];
			a[x][y-1] = a[x][y];
			a[x][y] = t;
			//�������
			t = f(a);
			if( t - relax<= d )
				//������ǵ����ķ���Ѱ��
				return dp(route,a,x,y-1,t);
			//�ָ�ԭ��λ��
			t = a[x][y-1];
			a[x][y-1] = a[x][y];
			a[x][y] = t;
		}
		if( y + 1 <= 2 && (route.isEmpty() || route.peek() != -2))
		{//����
			route.add(2);
			//1���ϱ�λ�ý���
			t = a[x][y+1];
			a[x][y+1] = a[x][y];
			a[x][y] = t;
			//�������
			t = f(a);
			if( t - relax<= d )
				//������ǵ����ķ���Ѱ��
				return dp(route,a,x,y+1,t);
			//�ָ�ԭ��λ��
			t = a[x][y+1];
			a[x][y+1] = a[x][y];
			a[x][y] = t;
		}
		return null;
	}
	
	// �ܾ��뺯��
	public static int f(int a[][])
	{
		return f_x(a) + f_y(a);
	}
	
	// ˮƽ���뺯��
	public static int f_x(int a[][])
	{
		int dist = 0;
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
					dist += Math.abs(d[a[x][y]][0] - x);
		return dist;
	}
	
	// ��ֱ���뺯��
	public static int f_y(int a[][])
	{
		int dist = 0;
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				dist += Math.abs(d[a[x][y]][1] - y);
		return dist;
	}
	public static void main(String[] args)
	{

		int[][] a = {
				{1,4,2},
				{6,3,5},
				{7,0,8}
		};
		Stack<Integer> route = new Stack<Integer>();
		dp(route, a, 2, 2, f(a));
	}

}
