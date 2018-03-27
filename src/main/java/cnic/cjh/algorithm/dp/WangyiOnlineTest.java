package cnic.cjh.algorithm.dp;
/**
 * 网易笔试题目：
 *  给定n个职位，每一对职位有对应难度和金额
 *  给定k个人能胜任的难度，求最终这k个人能拿的金额
 */

import java.util.Iterator;
import java.util.Scanner;
import  java.lang.Comparable;
import java.util.Set;
import java.util.TreeSet;

public class WangyiOnlineTest {
    public static void main(String[] args) {
        Set<Conbine> d_m_set = new TreeSet<Conbine>();
        Set<Index> v_i_set = new TreeSet<Index>();

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        for(int i=0; i<N; i++){
            d_m_set.add(new Conbine(in.nextInt(),in.nextInt()));
        }
        d_m_set.add(new Conbine(Integer.MAX_VALUE,Integer.MIN_VALUE));
        int[] compares = new int[M];
        for(int i=0; i<M; i++){
            v_i_set.add(new Index(in.nextInt(),i));
        }
        int[] result = new int[M];
        Iterator<Index> i_iterable = v_i_set.iterator();
        Iterator<Conbine> c_iterable = d_m_set.iterator();
        Index index = null;
        Conbine conbine = c_iterable.next();
        int cur_d=conbine.d,pre_m=0;
        while(i_iterable.hasNext() && c_iterable.hasNext()){
            index = i_iterable.next();
            while(true){
                if(cur_d > index.v){
                    /**
                     * 不在区间内，则结束比较
                     */
                    result[index.i] = pre_m;
                    break;
                }else {
                    /**
                     * 在区间内，保留目前为止的最大值
                     */
                    if(conbine.m > pre_m){
                        pre_m = conbine.m;
                    }
                    conbine = c_iterable.next();
                    cur_d = conbine.d;
                }
            }
        }
        if(i_iterable.hasNext()){
            /**
             * 剩余赋予最大值
             */
            while(i_iterable.hasNext()){
                index = i_iterable.next();
                result[index.i] = pre_m;
            }
        }
        System.out.println();
    }

}

class Conbine implements Comparable<Conbine>{
    int d;
    int m;
    @Override
    public int compareTo(Conbine o){
        return this.d - o.d;
    }
    public Conbine(int d,int m){
        this.d = d;
        this.m=m;
    }
}

class Index implements Comparable<Index>{
    int v;
    int i;
    @Override
    public int compareTo(Index o){
        return this.v - o.v;
    }
    public Index(int v,int i){
        this.v = v;
        this.i = i;
    }
}