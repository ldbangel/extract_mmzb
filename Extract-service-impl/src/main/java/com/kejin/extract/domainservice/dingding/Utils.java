package com.kejin.extract.domainservice.dingding;

import java.util.Arrays;
import java.util.Random;

/**
 * 加解密工具类
 */
public class Utils {
    
    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getRandomStr(int count) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /*
     * int转byte数组,高位在前
     */
    public static byte[] int2Bytes(int count) {
        byte[] byteArr = new byte[4];
        byteArr[3] = (byte) (count & 0xFF);
        byteArr[2] = (byte) (count >> 8 & 0xFF);
        byteArr[1] = (byte) (count >> 16 & 0xFF);
        byteArr[0] = (byte) (count >> 24 & 0xFF);
        return byteArr;
    }

    /**
     * 高位在前bytes数组转int
     *
     * @param byteArr
     * @return
     */
    public static int bytes2int(byte[] byteArr) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            count <<= 8;
            count |= byteArr[i] & 0xff;
        }
        return count;
    }
    

	public static int ByteArrayToInt(byte[] bArr) {  
		if(bArr.length!=4){  
		    return -1;  
		}  
		return (int) ((((bArr[0] & 0xff) << 24)    
		        | ((bArr[1] & 0xff) << 16)    
		        | ((bArr[2] & 0xff) << 8)
				| ((bArr[3] & 0xff) << 0)));   
	}
	
	public static void main(String[] args) {
		//byte[] b = "ding683a0a936a4cb9cc".getBytes();
		byte[] b = Arrays.copyOfRange("UnTCdrcuISJ33L4yeRSnovaRhVBimgKHGs0IIMGeein".getBytes(), 0, 16);
		byte[] c = "UnTCdrcuISJ33L4y".getBytes();
		System.out.println("--");
	}

}
