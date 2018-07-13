package com.kejin.extract.domainservice.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;

/**
 * 用于生成运营报表的pdf附件生成
 * @author liudongbo
 *
 */
public class PdfGeneraterUtils {
	public static void generaterOperationPdf(String pdfType) throws DocumentException, ParseException, MalformedURLException, IOException{
		//创建文件
        Document document = new Document(PageSize.A4);
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(SysConstantsConfig.PDF_OUT_PATH
        		+ DateFormatUtils.getYesterdayDate() + pdfType + "OperationReport.pdf"));
        //打开文件
        document.open();

        /**
         * 1.用户报表
         */
        //图片1
        Image image1 = Image.getInstance(SysConstantsConfig.IMAGE_OUT_PATH+DateFormatUtils.getYesterdayDate()+pdfType+"IncreaseReport.jpg");
        //设置图片的宽度和高度
        image1.scaleAbsolute(590, 600);
        image1.setAlignment(Image.ALIGN_CENTER);
        //将图片1添加到pdf文件中
        document.add(image1);
        
        /**
         * 2.投资报表
         */
        //图片2
        Image image2 = Image.getInstance(SysConstantsConfig.IMAGE_OUT_PATH+DateFormatUtils.getYesterdayDate()+pdfType+"TradeReport.jpg");
        image2.scaleAbsolute(590,800);
        image2.setAlignment(Image.ALIGN_CENTER);
        //将图片2添加到pdf文件中
        document.add(image2);
        
        /**
         * 3.资金流报表
         */
        //图片2
        Image image3 = Image.getInstance(SysConstantsConfig.IMAGE_OUT_PATH+DateFormatUtils.getYesterdayDate()+pdfType+"FundflowReport.jpg");
        image3.scaleAbsolute(590,800);
        image3.setAlignment(Image.ALIGN_CENTER);
        //将图片2添加到pdf文件中
        document.add(image3);
        
        /**
         * 4.定期宝报表---交易金额
         */
        Image image4 = Image.getInstance(SysConstantsConfig.IMAGE_OUT_PATH+DateFormatUtils.getYesterdayDate()+pdfType+"RegularReport.jpg");
        image4.scaleAbsolute(570,800);
        image4.setAlignment(Image.ALIGN_CENTER);
        //将图片2添加到pdf文件中
        document.add(image4);
        
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
	}
}
