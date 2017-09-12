/*
 * Balance Binary Tree
 * 
 * According the below website    
 * http://blog.csdn.net/wxbmelisky/article/details/47755753
 */
package tree;

import java.util.Date;

public class AVLTree
{
	static final class Node
	{
		public static final int LL = 1;
		public static final int LR = 2;
		public static final int RR = 3;
		public static final int RL = 4;

		int k;//key value
		Node l;//left child
		Node r;//right child
		Node p;//parent child
		int pos;//position : -1 left, 1 right, 0 none
		int l_d;//left depth
		int r_d;//right depth
		private Node(int k)
		{
			this.k = k;
		}
		public static Node createRandomNode(int range)
		{
			Node n = new Node((int)Math.floor((Math.random() * range)));
////System.out.println("Create node : "+n.k);
			return n;
		}
		public static Node createNode(int k)
		{
			Node n = new Node(k);
////System.out.println("Create node : "+n.k);
			return n;
		}
		
	}
//root node
Node root = null;
//balance the tree from the inserting node n
void update_depth(Node n,int direction)
{
	if( n == null || n.p == null)
	{
		return;
	}
	if(direction == 0)
	{
		return;
	}
	if(direction < 0)
	{//left ++
		n.p.l_d++;
		if(n.p.l_d - n.p.r_d == 1)
		{
			update_depth(n.p, n.p.pos);
		}
		else if(n.p.l_d - n.p.r_d == 2)
		{
			if(n.l_d > n.r_d)
			{//LL
				rotate(this, n.p, Node.LL);
			}
			else if(n.l_d < n.r_d)
			{//LR
				rotate(this, n.p, Node.LR);
			}
		}
	}
	else
	{//right++
		n.p.r_d++;
		if(n.p.r_d - n.p.l_d == 1)
		{
			update_depth(n.p, n.p.pos);
		}
		else if(n.p.r_d - n.p.l_d == 2)
		{
			if(n.l_d > n.r_d)
			{//RL
				rotate(this, n.p, Node.RL);
			}
			else if(n.l_d < n.r_d)
			{//RR
				rotate(this, n.p, Node.RR);
			}
		}
	}

}
//boolean balance(Node n)
//{
//	if( n == null) 
//	{
//		//System.out.println("The rotate node cannot be null!");
//		return false;
//	}
//	int pre_pos = 0;
//	while( n != null )
//	{
//		if(n.pos < 0)
//		{//left position
//			n = n.p;
//			n.l_d++;
//			if(n.l_d - n.r_d == 1)
//			{//update parent
//				pre_pos = -1;
//			}
//			else if(n.l_d - n.r_d == 2)
//			{//rotate
//				if(pre_pos < 0)
//					return rotate(this,n,Node.LL);
//				else
//					return rotate(this,n,Node.LR);
//			}
//			else
//			{
//				return true;
//			}
//		}
//		else if(n.pos > 0)
//		{//right position
//			n = n.p;
//			n.r_d++;
//			if(n.r_d - n.l_d == 1)
//			{//update parent
//				pre_pos = 1;
//			}
//			else if(n.r_d - n.l_d == 2)
//			{//rotate
//				if(pre_pos < 0)
//					return rotate(this,n,Node.RL);
//				else
//					return rotate(this,n,Node.RR);
//			}
//			else
//			{
//				return true;
//			}
//		}
//		else
//		{// position == 0, reach root node
//			
//		}
//	}
//	
//}


//return the depth of the tree
public static int get_depth(Node root)
{	
	if(root == null)
		return 0;
	int l_depth = get_depth(root.l);
	int r_depth = get_depth(root.r);
	return (l_depth > r_depth ? l_depth : r_depth) + 1;
}

//rotate the node
static boolean rotate(AVLTree t,Node n,int type)
{
	if (n == null)
	{
		//System.out.println("the node cannot be null");
		return false;
	}
	if (type >4 || type <1)
	{
		//System.out.println("the rotate type : " + type +" is not defined");
		return false;	
	}
	// from left to right,four node parts
	Node B,C,one,two,three,p;
	p = n.p;
	int p_pos = n.pos;
	switch(type)
	{
		case Node.LL:
			one = n;
			two = n.l;
			B = two.r;
			C = one.r;
			one.p = two;
			one.l = B;
			one.pos = 1;
			if(B != null)
			{
				B.p = one;
				B.pos = -1;
			}
			two.p = p;
			two.r = one;
			// root node
			if( p == null)
			{
				t.root = two;
				two.pos = 0;
			}
			else
			{
				if( p_pos < 0 )
				{
					p.l = two;
					two.pos = -1;
				}
				else if(p_pos > 0)
				{
					p.r = two;
					two.pos = 1;					
				}
			}
			//update depth
			one.l_d = two.r_d;
			two.r_d = (one.r_d > two.r_d ? one.r_d : two.r_d) + 1;
			break;
		case Node.RR:
			one = n;
			two = n.r;
			B = two.l;
			C = two.r;
			one.r = B;
			one.pos = -1;
			if(B != null)
			{
				B.p = one;
				B.pos = 1;
			}
			one.p = two;
			two.l = one;
			two.p = p;
			if( p == null)
			{
				t.root = two;
				two.pos = 0;
			}
			else
			{
				if( p_pos < 0 )
				{
					p.l = two;
					two.pos = -1;
				}
				else if(p_pos > 0)
				{
					p.r = two;
					two.pos = 1;					
				}
			}
			//update depth
			one.r_d = two.l_d;
			two.l_d = (one.l_d > two.l_d ? one.l_d : two.l_d) + 1;
			break;
		case Node.LR:
			one = n;
			two = one.l;
			three = two.r;
			B = three.l;
			C = three.r;
			one.l = C;
			one.pos = 1;
			if( C != null)
			{
				C.p = one;
				C.pos = -1;
			}
			one.p = three;
			two.r = B;
			if( B != null)
			{
				B.p = two;
				B.pos = 1;
			}
			two.p = three;
			three.l = two;
			three.r = one;
			three.p = p;
			if( p == null )
			{
				t.root = three;
				three.pos = 0;
			}
			else
			{
				if( p_pos < 0 )
				{
					p.l = three;
					three.pos = -1;
				}
				else if(p_pos > 0)
				{
					p.r = three;
					three.pos = 1;					
				}
			}
			//update depth
			one.l_d = three.r_d;
			two.r_d = three.l_d;
			three.l_d = (two.l_d > two.r_d ? two.l_d : two.r_d) + 1;
			three.r_d = (one.l_d > one.r_d ? one.l_d : one.r_d) + 1;
			break;
		case Node.RL:
			one  = n;
			two = one.r;
			three = two.l;
			B = three.l;
			C = three.r;
			one.r = B;
			one.pos = -1;
			if ( B != null)
			{
				B.p = one;
				B.pos = 1;
			}
			one.p = three;
			two.l = C;
			if ( C != null )
			{
				C.p = two;
				C.pos = -1;
			}
			two.p = three;
			three.l = one;
			three.r = two;
			three.p = p;
			if ( p == null)
			{
				t.root = three;
				three.pos = 0;
			}
			else
			{
				if( p_pos < 0 )
				{
					p.l = three;
					three.pos = -1;
				}
				else if(p_pos > 0)
				{
					p.r = three;
					three.pos = 1;					
				}
			}
			//update depth
			one.r_d = three.l_d;
			two.l_d = three.r_d;
			three.l_d = (one.l_d > one.r_d ? one.l_d : one.r_d) + 1;
			three.r_d = (two.l_d > two.r_d ? two.l_d : two.r_d) + 1;
			break;
	}
	//System.out.println("The rotate "+type+ " is finished!");
	return true;
}


//insert a node
boolean insert(Node n)
{
	if(root == null)
	{
		//System.out.println("Root node should not be empty!");
		return false;
	}
	if(n == null)
	{
		//System.out.println("The inserting node should not be empty!");
		return false;
	}

	Node pre = null,cur = root;
	int direction = 0;// < 0 : left , > 0 right
	while(cur != null)
	{
		if( n.k < cur.k )
		{// left
			pre = cur;
			cur = cur.l;
			direction = -1;
		}
		else if( n.k > cur.k )
		{//right
			pre = cur;
			cur = cur.r;
			direction = 1;
		}
		else
		{
			//System.out.println("The node key value :"+n.k+" has already existed");
			return false;
		}
	}
	n.p = pre;
	n.pos = direction;
	if( direction < 0)
	{
		pre.l = n;
		//System.out.println("Insert node "+n.k+" into node "+pre.k+" 's left child!");
		update_depth(n, -1);
	}
	else
	{
		pre.r = n;
		//System.out.println("Insert node "+n.k+" into node "+pre.k+" 's right child!");
		update_depth(n, 1);
	}
	return true;
}
public static AVLTree createFromArray(int ... k_values)
{
	if(k_values.length <1)
		return null;
	AVLTree tree = new AVLTree();
	tree.root = Node.createNode(k_values[0]);
	for(int i=1; i<k_values.length; i++)
	{
		tree.insert(Node.createNode(k_values[i]));
	}
	return tree;
}
public static void main(String[] args) 
{
	AVLTree tree = new AVLTree();
	tree.root = Node.createRandomNode(100);
	//System.out.println("Root node is : "+tree.root.k);
	System.out.println(new Date().toString());
	for(int i=0; i < 100000000 ; i++)
	{
		tree.insert(Node.createRandomNode(1000000000));
	}
	System.out.println(new Date().toString());

	//System.out.println(tree.root.l_d);
	//System.out.println(tree.root.r_d);
//	System.out.println(get_depth(tree.root));
	
//	AVLTree t = createFromArray(1,2,3,4,5,6);
//	//System.out.println(t.root.l_d);
//	//System.out.println(t.root.r_d);
//	//System.out.println(get_depth(t.root));
	
}
}
