package com.kejin.extract.common.utils;

import java.util.Random;

public class RandomUtil {
	/**
     * 获取随机字符串
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
    
    public static void main(String[] args) {
    	System.out.println(getRandomStr(43));
	}
}
