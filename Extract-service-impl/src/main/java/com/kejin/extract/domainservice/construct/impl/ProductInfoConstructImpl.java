package com.kejin.extract.domainservice.construct.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.construct.ProductInfoConstruct;
import com.kejin.extract.domainservice.extract.ProductInfoService;
import com.kejin.extract.entity.kejinTest.DProductModel;
import com.kejin.extract.kejin.process.dao.DProductDao;

@Service("productInfoConstruct")
public class ProductInfoConstructImpl implements ProductInfoConstruct   {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DProductDao dProductDao;
	@Resource(name="productInfoService")
	private ProductInfoService productInfoService;

	@Override
	@Transactional
	public int constructProduct(Date recordBeginDatetime,Date recordEndDatetime) {
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {
			List<DProductModel> products = productInfoService
					.readFromInvest(recordBeginDatetime, recordEndDatetime, continueRead);

			if (products.size() > 0) {
				logger.info("开始插入商品数据");
				for (DProductModel product : products) {

					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("loanProjectNo", product.getLoanProjectNo());
					parameter.put("projectNo", product.getProjectNo());

					List<DProductModel> oldProducts = dProductDao.select(parameter);

					if (oldProducts.size() > 0) {
						logger.info("更新商品数据： "
								+ JSON.toJSONString(new ArrayList<DProductModel>()
										.add(product)));
						dProductDao.update(product);
					} else {
						logger.info("插入商品数据： "
								+ JSON.toJSONString(new ArrayList<DProductModel>()
										.add(product)));
						dProductDao.insert(product);
					}
				}
				logger.info("结束插入商品数据");
				handlerCount = handlerCount + products.size();
				continueRead++;
			} else {
				continueRead = -1;
			}
		}
		return handlerCount;
	}



}
