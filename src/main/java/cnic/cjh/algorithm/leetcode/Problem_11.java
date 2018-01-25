package cnic.cjh.algorithm.leetcode;

import sun.plugin.dom.core.CoreConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 最优化问题
 *
 * https://leetcode.com/problems/container-with-most-water/description/
 */
public class Problem_11 {
    public int maxArea(int[] height) {
        int[] left_max = new int[height.length],
              right_max = new int[height.length];
        //分别找每一条垂线的左边、右边最大值
        for(int i=1; i < height.length; i++){
            left_max[i] = left_max[i - 1] > height[i - 1] ?  left_max[i - 1] : height[i - 1];
        }
        for(int i=height.length - 2; i > 0; i--){
            right_max[i] = right_max[i + 1] > height[i + 1] ?  right_max[i + 1] : height[i + 1];
        }
        List<Cor> points = new ArrayList<Cor>();
        //除去不用比较的元素,构造坐标元素
        for(int i = 0; i < height.length; i++){
            //左右都有比自己高（或者高度相等）的垂线，则跳过
            if(left_max[i] >= height[i] && right_max[i] >= height[i]){
                continue;
            }
            points.add(new Cor(i,height[i]));
        }
        //暴力求解所有的组合,保留最大值
        int max = 0;
        for(int i = 0; i < points.size(); i++){
            Cor point_a = points.get(i);
            for(int j=0,space=0; j < points.size(); j++){
                 Cor point_b = points.get(j);
                 space = Math.abs(point_a.x - point_b.x) * Math.min(point_a.y, point_b.y);
                 max = space > max ? space : max;
            }
        }
        return max;
    }
    class Cor{
        int x;
        int y;
        public Cor(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static  void main(String[] args){
        System.out.println(new Problem_11().maxArea(new int[]{1,2}));
    }

}
