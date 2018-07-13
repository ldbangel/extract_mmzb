package com.kejin.extract.domainservice.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

import com.kejin.extract.common.enums.ScreenShotTypeEnum;
import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;

public class ScreenShotUtils {
	public static void ScreenShot(ScreenShotTypeEnum demo) throws IOException, ParseException{
		String BLANK = " ";
		String phantomJsPath = SysConstantsConfig.PHANTOMJS_PATH + BLANK; //你的phantomjs.exe路径
		String scriptPath = SysConstantsConfig.SCRIPT_PATH + demo.getJsName() + BLANK; //就是上文中那段javascript脚本的存放路径
		String targetUrl = SysConstantsConfig.TARGET_URL + demo.getHtmlName() + BLANK; //你的目标url地址
		String imageOutPath = SysConstantsConfig.IMAGE_OUT_PATH + DateFormatUtils.getYesterdayDate() + demo.getImageName(); //你的图片输出路径
        Process process = Runtime.getRuntime().exec(phantomJsPath + scriptPath + targetUrl + imageOutPath);

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while((tmp = reader.readLine()) != null){
            sbf.append(tmp);
        }
        reader.close();
        inputStream.close();
	}
	
}
