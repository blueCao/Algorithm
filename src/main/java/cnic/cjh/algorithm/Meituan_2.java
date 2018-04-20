package cnic.cjh.algorithm;

public class Meituan_2 {
    public static void main(String[] args){
        System.out.println(new Meituan_2().f(1000000000000L));
        System.out.println(new Meituan_2().f(10000000000000L));
        System.out.println(new Meituan_2().f(100000000000000L));
        System.out.println(new Meituan_2().f(10000000000000000L));
    }

    public long f(long x){
        if(x <= 0){
            return 0;
        }else {
            /**
             * 1. caculate the pow
             */
            long i= 1,n=0;
            for(; i < 1000000000000000000L; i = i * 10,n++){
                if(x / i == 0){
                    i = i / 10;
                    n--;
                    break;
                }
            }
            return f(i - 1) + (n + 1)*(x - i + 1);
        }
    }
}
