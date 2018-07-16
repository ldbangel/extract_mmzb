<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>平台实时交易数据统计</title>
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
	<h2>平台实时交易数据</h2>
	<body>
		<table width="100%" cellspacing="0">
			<tr class="tr2">
				<td width="10%">时间</td>
				<td width="8%">定期宝投资额</th>
				<td width="8%">定期宝复投</th>
				<td width="8%">定期宝新增</th>
				<td width="8%">债权投资额</td>
				<td width="7%">回款金额</td>
				<td width="8%">满标项目数</td>
				<td width="7%">满标金额</td>
				<td width="8%">未满标金额</td>
				<td width="8%">投资用户专户余额</td>
				<td width="8%">融资用户专户余额</td>
				<td width="8%">月度定期投资额</td>
				<td width="6%">余额大于1000未激活</td>
			</tr>
			<tr>
				<td width="10%">${dateTime}</td>
				<td width="8%">${regularInvestAmount}</th>
				<td width="8%">${reInvestAmount}</th>
				<td width="8%">${newInvestAmount}</th>
				<td width="8%">${creditInvestAmount}</td>
				<td width="7%">${recoveryAllAmount}</td>
				<td width="8%">${bidSuccessNum}</td>
				<td width="7%">${bidSuccessAmount}</td>
				<td width="8%">${paySuccessAmount}</td>
				<td width="8%">${balanceAllInvestorAmount}</td>
				<td width="8%">${balanceAllBorrowersAmount}</td>
				<td width="8%">${regularInvestAmountOfMonth}</td>
				<td width="6%">${isNoActivePersonNums}</td>
			</tr>
		</table>
	</body>
</html>
