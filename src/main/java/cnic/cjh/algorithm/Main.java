package cnic.cjh.algorithm;

import java.util.Iterator;
import java.util.Scanner;
import  java.lang.Comparable;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Conbine> d_m_set = new TreeSet<Conbine>();
        Set<Index> v_i_set = new TreeSet<Index>();

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        for(int i=0; i<N; i++){
            d_m_set.add(new Conbine(in.nextInt(),in.nextInt()));
        }
        int[] compares = new int[M];
        for(int i=0; i<M; i++){
            v_i_set.add(new Index(in.nextInt(),i));
        }
        int[] result = new int[M];
        Iterator<Index> i_iterable = v_i_set.iterator();
        Iterator<Conbine> c_iterable = d_m_set.iterator();
        int pre_d=-1,pre_m=-1;
        while(i_iterable.hasNext()){
            Index index = i_iterable.next();
            Conbine conbine = c_iterable.next();
            while(true){
                if()
            }
        }
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