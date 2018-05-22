package cnic.cjh.algorithm.tcp_ip;

/**
 * CRC(cycle reminder check
 *
 * https://blog.csdn.net/liyuanbhu/article/details/7882789
 */
public class CRC {
    public static byte[] crc(byte[] divisor,byte[] dividor){
        /**
         * 1. 校验除数是否有效
         */
        if(dividor == null || dividor.length == 0){
            throw new RuntimeException("Error:dividor cannot be zero or null");
        }
        /**
         * 2.1 尾部补0
         */
        byte[] tmp = new byte[divisor.length+1];
        int i=0;
        for (i=0; i<divisor.length; i++){
            tmp[i] = divisor[i];
        }
        for (int j = 0; j<dividor.length; j++){
            tmp[i] = dividor[j];
            i++;
        }
        /**
         * 2.2
         */
    }
}
