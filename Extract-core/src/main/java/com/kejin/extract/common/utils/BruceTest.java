package com.kejin.extract.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BruceTest {
	public static void main(String[] args) throws IOException {
		
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(new File("D:/mamashuju/kejinTest_20180517.sql")));
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new InputStreamReader(bis,"utf-8"),20*1024*1024);
		while(br.ready()){
			String line = br.readLine();
			System.out.println(line);
			//这里对每行代码进行业务处理
		}
		br.close();
	}
}	
