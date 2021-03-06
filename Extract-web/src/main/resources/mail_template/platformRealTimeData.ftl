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
	<h2>${dateTime}实时数据</h2>
	<body>
		<table width="100%" cellspacing="0">
			<tr class="tr2">
				<td width="12%">定期宝投资</th>
				<td width="12%">定期宝复投</th>
				<td width="12%">定期宝新增</th>
				<td width="10%">债权投资</td>
				<td width="10%">回款金额</td>
				<td width="12%">未满标金额</td>
				<td width="10%">提现总额</td>
				<td width="10%">充值总额</td>
				<td width="12%">用户专户余额</td>
			</tr>
			<tr>
				<td width="12%">${regularInvestAmount}</th>
				<td width="12%">${reInvestAmount}</th>
				<td width="12%">${newInvestAmount}</th>
				<td width="10%">${creditInvestAmount}</td>
				<td width="10%">${recoveryAllAmount}</td>
				<td width="12%">${paySuccessAmount}</td>
				<td width="10%">${cashAmount}</td>
				<td width="10%">${chargeAmount}</td>
				<td width="12%">${balanceAllInvestorAmount}</td>
			</tr>
		</table>
	</body>
</html>
