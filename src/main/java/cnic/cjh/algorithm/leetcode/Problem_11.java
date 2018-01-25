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
        for(int i=height.length - 2; i >= 0; i--){
            right_max[i] = right_max[i + 1] > height[i + 1] ?  right_max[i + 1] : height[i + 1];
        }
        List<Cor> points = new ArrayList<Cor>();
        //添加首值
        points.add(new Cor(0,height[0]));
        //除去不用比较的元素,构造坐标元素,首尾值不用删除
        for(int i = 1; i < height.length - 1; i++){
            //左右都有比自己高（或者高度相等）的垂线，则跳过
            if(left_max[i] >= height[i] && right_max[i] >= height[i]){
                continue;
            }
            points.add(new Cor(i,height[i]));
        }
        //添加尾值
        points.add(new Cor(height.length - 1,height[height.length - 1]));

        //此时，points的y值分布为，先递增后递减，或者 单调递增
        //依次计算左右峰值的x位置
        int left_max_x =0,right_max_x = 0,left_max_index=0,right_max_index=0;
        for(int i = 1; i < points.size(); i++){
            Cor pre = points.get(i - 1);
            Cor cur = points.get(i);
            if( cur.y < pre.y ){
                //找到右下降点
                left_max_x = pre.x;
                break;
            } else {
                left_max_index = i;
                left_max_x = cur.x;
            }
        }
        for(int i = points.size() - 2; i >= 0; i-- ){
            Cor pre = points.get(i + 1);
            Cor cur = points.get(i);
            if( cur.y < pre.y ){
                //找到左下降点
                right_max_x = pre.x;
                break;
            } else {
                right_max_index = i;
                right_max_x = cur.x;
            }
        }
        //计算左右两侧的最大容量值
        int left_max_space=0, right_max_space=0,space=0;
        for(int i=0; i < points.size(); i++){
            Cor p = points.get(i);
            if( p.x  > left_max_x){
                break;
            }
            //计算右边大于p点高度的最远x点
            int p_right_x = left_max_x;
            for(int j = left_max_index + 1; j < points.size(); j++){
                Cor p_right = points.get(j);
                if( p_right.y < p.y ){
                    break;
                } else {
                    p_right_x = p_right.x;
                }
            }
            space = (p_right_x - p.x) * p.y;
            left_max_space = left_max_space > space ? left_max_space : space;
        }
        for(int i=points.size() - 1; i >= 0; i-- ){
            Cor p = points.get(i);
            if( p.x  < right_max_x){
                break;
            }
            //计算左边大于p点高度的最远x点
            int p_left_x = right_max_x;
            for(int j = right_max_index - 1; j >= 0 ; j-- ){
                Cor p_left = points.get(j);
                if( p_left.y < p.y ){
                    break;
                } else {
                    p_left_x = p_left.x;
                }
            }
            space = ( p.x- p_left_x ) * p.y;
            right_max_space = right_max_space > space ? right_max_space : space;
        }

        return left_max_space > right_max_space ? left_max_space : right_max_space;
    }
    class Cor {
        int x;
        int y;

        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return Integer.toString(y);
        }
    }

    public static void main(String[] args){
        int[] input = new int[]{1,2,1};
        System.out.println(new Problem_11().maxArea(input));
    }

}
