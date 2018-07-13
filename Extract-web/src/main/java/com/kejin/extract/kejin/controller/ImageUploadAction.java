package com.kejin.extract.kejin.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;

import sun.misc.BASE64Decoder;

@Controller
public class ImageUploadAction {
	
	@RequestMapping(value = "imgUpload.htm",produces = "text/html; charset=utf-8")
	public String base64Upload(@RequestParam String base64Data,
			@RequestParam String imageType,HttpServletRequest request) throws Exception{
		String dataPrix = "";  
        String imgData = "";            
        if(base64Data == null || "".equals(base64Data)){  
            throw new Exception("上传失败，上传图片数据为空");  
        }else{  
            String [] d = base64Data.split("base64,");  
            if(d != null && d.length == 2){  
                dataPrix = d[0];  
                imgData = d[1];  
            }else{  
                throw new Exception("上传失败，数据不合法");  
            }  
        }               
        String suffix = "";  
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix) || "data:image/jpg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据  
            suffix = ".jpg";  
        } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据  
            suffix = ".ico";  
        } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据  
            suffix = ".gif";  
        } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据  
            suffix = ".png";  
        }else{  
            throw new Exception("上传图片格式不合法");  
        }  
        
        String fileName = DateFormatUtils.getYesterdayDate()+imageType+suffix;

       /* InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try	{  
            in = new FileInputStream(imgData);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder(); */ 
        //返回Base64编码过的字节数组字符串  
        GenerateImage(imgData, fileName);  
        return "success";
	}
	
    
    public static boolean GenerateImage(String imgStr, String fileName)  {
    	//对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = SysConstantsConfig.EXCEL_SAVE_PATH+fileName;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
    
}


