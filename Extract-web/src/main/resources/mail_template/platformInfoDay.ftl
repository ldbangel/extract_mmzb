<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>分终端统计运营日报</title>
		<style>
			body{
				padding-bottom: 40px;
			}
			h1{
				padding-left: 5%;
				font-size: 18px;
			}
			h2{
				font-size: 23px;
				text-align: center;
				<!-- font-family: 楷体; -->
			}
			h5{
				padding-left: 6%;
				font-size: 16px;
			}
			table{
				width: 90%;
				margin: 0 auto;
				padding: 0;
				border: solid 1px #9FB6CD;
			}
			td{
				margin: 0;
				padding: 0;
				height: 45px;
				line-height: 45px;
				text-align: center;
				border-right: solid 1px #9FB6CD;
				border-bottom: solid 1px #9FB6CD;
			}
			th{
				margin: 0;
				padding: 0;
				height: 45px;
				line-height: 45px;
				text-align: center;
			}
			img{
				padding-left: 6%;
			}
			table .tr2{
				color:white;
				background:#005C99;
			}
		</style>
	</head>
	<h2>各终端数据</h2>
	<body>
		<table width="100%" cellspacing="0">
				<tr class="tr2">
					<td width="5%">时间</td>
					<td width="15%">统计项目</th>
					<td width="40%">定义</td>
					<td width="10%">IOS</td>
					<td width="10%">Android</td>
					<td width="10%">H5</td>
					<td width="10%">PC</td>
				</tr>
				<tr>
					<td rowspan="8" width="5%">每日</td>
					<td width="15%">DAU</td>
					<td width="40%">一个账户一天登录大于等于一次算一个DAU</td>
					<td width="10%">${growingioDayData[0].loginNum}</td>
					<td width="10%">${growingioDayData[2].loginNum}</td>
					<td width="10%">${growingioDayData[1].loginNum}</td>
					<td width="10%"></td>
				</tr>
				<tr>
					<td width="15%">打开人数</td>
					<td width="40%">一个账户一天打开大于等于一次算一个人数</td>
					<td width="10%">${growingioDayData[0].visitNum}</td>
					<td width="10%">${growingioDayData[2].visitNum}</td>
					<td width="10%">${growingioDayData[1].visitNum}</td>
					<td width="10%"></td>
				</tr>
				<#if investNum??>
					<tr>
						<td width="15%">投资人数</td>
						<td width="40%">一个账户一天投资大于等于一次算一个人数</td>
						<#list investNum as invest>
							<#if invest.platform?? && invest.platform == 'iOS'>
								<td width="10%">${invest.investNum}</td>
							</#if>
						</#list>
						<#list investNum as invest>
							<#if invest.platform?? && invest.platform == 'Android'>
								<td width="10%">${invest.investNum}</td>
							</#if>
						</#list>
						<#list investNum as invest>
							<#if invest.platform?? && invest.platform == 'H5'>
								<td width="10%">${invest.investNum}</td>
							</#if>
						</#list>
						<#list investNum as invest>
							<#if invest.platform?? && invest.platform == 'PC'>
								<td width="10%">${invest.investNum}</td>
							</#if>
						</#list>
					</tr>
				</#if>
				<#if registerNum??>
					<tr>
						<td width="15%">注册数</td>
						<td width="40%">一天新增注册人数</td>
						<#list registerNum as register>
							<#if register.platform?? && register.platform == 'iOS'>
								<td width="10%">${register.registerNum}</td>
							</#if>
						</#list>
						<#list registerNum as register>
							<#if register.platform?? && register.platform == 'Android'>
								<td width="10%">${register.registerNum}</td>
							</#if>
						</#list>
						<#list registerNum as register>
							<#if register.platform?? && register.platform == 'H5'>
								<td width="10%">${register.registerNum}</td>
							</#if>
						</#list>
						<#list registerNum as register>
							<#if register.platform?? && register.platform == 'PC'>
								<td width="10%">${register.registerNum}</td>
							</#if>
						</#list>
					</tr>
				</#if>
				<#if tieNum??>
					<tr>
						<td width="15%">绑卡数</td>
						<td width="40%">一天新增绑卡人数</td>
						<#list tieNum as tie>
							<#if tie.tieNum??>
								<td colspan="5">${tie.tieNum}</td>
							</#if>
						</#list>
					</tr>
				</#if>
				<#if firstInvest??>
					<tr>
						<td width="15%">首次投资数</td>
						<td width="40%">一天新增首次投资人数</td>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'iOS'>
								<td width="10%">${firstIn.newNums}</td>
							</#if>
						</#list>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'Android'>
								<td width="10%">${firstIn.newNums}</td>
							</#if>
						</#list>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'H5'>
								<td width="10%">${firstIn.newNums}</td>
							</#if>
						</#list>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'PC'>
								<td width="10%">${firstIn.newNums}</td>
							</#if>
						</#list>
					</tr>
				</#if>
				<#if firstInvest??>
					<tr>
						<td width="15%">首次投资金额</td>
						<td width="40%">一天新增首次投资金额总数</td>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'iOS'>
								<td width="10%">${firstIn.sumAmount}</td>
							</#if>
						</#list>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'Android'>
								<td width="10%">${firstIn.sumAmount}</td>
							</#if>
						</#list>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'H5'>
								<td width="10%">${firstIn.sumAmount}</td>
							</#if>
						</#list>
						<#list firstInvest as firstIn>
							<#if firstIn.platform?? && firstIn.platform == 'PC'>
								<td width="10%">${firstIn.sumAmount}</td>
							</#if>
						</#list>
					</tr>
				</#if>
				<#if recoveryRate??>
					<tr>
						<td width="15%">30天复投率</td>
						<td width="40%">回款资金再次投资的占比</td>
						<#list recoveryRate as recovery>
							<#if recovery.reinvestRate??>
								<td colspan="5">${recovery.reinvestRate}</td>
							</#if>
						</#list>
					</tr>
				</#if>
				<tr>
					<td width="5%">每月</td>
					<td width="15%">MAU</td>
					<td width="40%">一个账户一月登录大于等于一次算一个MAU</td>
					<td width="10%">${growingioMonthData[0].loginNum}</td>
					<td width="10%">${growingioMonthData[2].loginNum}</td>
					<td width="10%">${growingioMonthData[1].loginNum}</td>
					<td width="10%"></td>
				</tr>
		</table>
	</body>
</html>
