package cnic.cjh.algorithm.leetcode;

public class Problem_6
{

	public Problem_6()
	{
	}
    public String convert(String s, int numRows) 
    {
    	int rest = s.length() - numRows;
    	if(rest <= 0 || numRows == 1) return s;
    	int a_patern_size = numRows + numRows - 2,
    			a_patern_width = numRows - 1,
    			width = 1 + ((s.length() - numRows) / a_patern_size) * a_patern_width + ((s.length() - numRows) % a_patern_size);
    	char[][] reset = new char[numRows][width];
    	char[] c = s.toCharArray();
    	for(int i = 0; i < numRows; i++)
    	{
    		reset[i] = new char[width];
    	}
    	boolean down = true;
    	for(int i = 0,row = 0, col = 0; i < s.length(); i++)
    	{
			reset[row][col] = c[i];
			
    		if(down == true)
    		{
    			
    			if(row == numRows-1)
	    		{
	    			row--;
	    			col++;
	    			down = false;
	    		}
    			else
    			{
    				row++;
    			}
    		}
    		else
    		{
    			if(row == 0)
    			{
    				row++;
    				down = true;
    			}
    			else
    			{
    				row--;
    				col++;
    			}
    		}
    	}
    	StringBuilder b = new StringBuilder();
    	//first row
    	for(int i=0; i < width; i = i + a_patern_width)
    	{
    		if(reset[0][i] != 0)
    			b.append(reset[0][i]);
    	}
    	//middle rows
    	for(int i=1; i < numRows - 1; i++)
    	{
    		int gap_1 = a_patern_width - i;
    		b.append(reset[i][0]);
    		for(int j=0; j<width; j = j + a_patern_width )
    		{
    			if((j + gap_1)<width &&  reset[i][j + gap_1] != 0)
    				b.append(reset[i][j + gap_1]);
    			if((j + a_patern_width)< width && reset[i][j + a_patern_width] != 0)
    				b.append(reset[i][j + a_patern_width]);
    		}
    	}
    	//last row
    	for(int i=0; i < width; i = i + a_patern_width)
    	{
    		if(reset[numRows-1][i] != 0)
    			b.append(reset[numRows-1][i]);
    	}

    	return b.toString();
    }
}
