package com.kejin.extract.time.task.daemon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.DocumentException;
import com.kejin.extract.domainservice.common.PdfGeneraterUtils;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * 运营报表周报附件  PDF自动生成
 */
public class OperationPdfGeneraterWeekDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute() {
		
		try {
			logger.info("-----------day operation pdf generate start");
			PdfGeneraterUtils.generaterOperationPdf("week");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
