package com.kejin.extract.common.enums;

public enum ScreenShotTypeEnum {
	DAY_SIMPLE_REPORT("daySimpleReport.js", "daySimpleReport.html", "daySimpleReport.jpg"),
	DAY_INCREASE_REPORT("dayIncreaseReport.js", "dayIncreaseReport.html", "dayIncreaseReport.jpg"),
	DAY_FUNDFLOW_REPORT("dayFundflowReport.js", "dayFundflowReport.html", "dayFundflowReport.jpg"),
	DAY_TRADE_REPORT("dayTradeReport.js", "dayTradeReport.html", "dayTradeReport.jpg"),
	DAY_REGULAR_REPORT("dayRegularReport.js", "dayRegularReport.html", "dayRegularReport.jpg"),
	DAY_ACHIEVEMENT_REPORT("dayAchievementReport.js", "dayAchievementReport.html", "dayAchievementReport.jpg"),
	
	WEEK_SIMPLE_REPORT("weekSimpleReport.js", "weekSimpleReport.html", "weekSimpleReport.jpg"),
	WEEK_INCREASE_REPORT("weekIncreaseReport.js", "weekIncreaseReport.html", "weekIncreaseReport.jpg"),
	WEEK_FUNDFLOW_REPORT("weekFundflowReport.js", "weekFundflowReport.html", "weekFundflowReport.jpg"),
	WEEK_TRADE_REPORT("weekTradeReport.js", "weekTradeReport.html", "weekTradeReport.jpg"),
	WEEK_REGULAR_REPORT("weekRegularReport.js", "weekRegularReport.html", "weekRegularReport.jpg"),
	WEEK_ACHIEVEMENT_REPORT("weekAchievementReport.js", "weekAchievementReport.html", "weekAchievementReport.jpg"),
	
	MONTH_SIMPLE_REPORT("monthSimpleReport.js", "monthSimpleReport.html", "monthSimpleReport.jpg"),
	MONTH_INCREASE_REPORT("monthIncreaseReport.js", "monthIncreaseReport.html", "monthIncreaseReport.jpg"),
	MONTH_FUNDFLOW_REPORT("monthFundflowReport.js", "monthFundflowReport.html", "monthFundflowReport.jpg"),
	MONTH_TRADE_REPORT("monthTradeReport.js", "monthTradeReport.html", "monthTradeReport.jpg"),
	MONTH_REGULAR_REPORT("monthRegularReport.js", "monthRegularReport.html", "monthRegularReport.jpg"),
	
	
	MONTH_ACHIEVEMENT_REPORT("monthAchievementReport.js", "monthAchievementReport.html", "monthAchievementReport.jpg"),
	
	DAY_CASH_CHART("dayCashChart.js", "dayCashChart.html", "dayCashChart.jpg");
	
	private String jsName;
	private String htmlName;
	private String imageName;
	
	ScreenShotTypeEnum(String jsName, String htmlName, String imageName){
		this.jsName = jsName;
		this.htmlName = htmlName;
		this.imageName = imageName;
	}
	
	public String getJsName() {
		return jsName;
	}
	public String getHtmlName() {
		return htmlName;
	}
	public String getImageName() {
		return imageName;
	}
	
}
