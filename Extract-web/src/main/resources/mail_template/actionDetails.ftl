<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>大额操作报表</title>
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
				border: solid 1px #A3A3A3;
			}
			td{
				margin: 0;
				padding: 0;
				height: 45px;
				line-height: 45px;
				text-align: center;
				<!-- border: solid 1px #A3A3A3; -->
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
			tbody .tr1{
				background:#CCEBFF;
			}
			thead .tr2{
				color:white;
				background:#005C99;
			}
		</style>
	</head>
	<h2>每日大额提现、投资、充值、回款明细 ${titleDate}</h2>
	<body>
		<h1>大额提现明细(1万以上)</h1>
		<table>
			<thead>
				<tr class="tr2">
					<th>姓名</th>
					<th>手机号</th>
					<th>城市</th>
					<th>提现金额</th>
					<th>提现来源</th>
					<th>提现角色</th>
					<th>渠道</th>
					<th>回访</th>
				</tr>
			</thead>
			<tbody>
				<#assign x=0 />
				<#if cashDetails??>
					<#list cashDetails as cash>
						<#assign x=x+1 />
						<#if x%2 == 0>
							<tr class="tr1">
								<td>${cash.name}</td>
								<td>${cash.phone}</td>
								<#if cash.phoneCity??>
									<td>${cash.phoneCity}</td>
								<#else>
									<td></td>
								</#if>
								<td>${cash.allAmount}</td>
								<td>${cash.cashOrigin}</td>
								<td>${cash.userRole}</td>
								<#if cash.channelName??>
									<td>${cash.channelName}</td>
								<#else>
									<td></td>
								</#if>
								<#if cash.feedback??>
									<td>${cash.feedback}</td>
								<#else>
									<td></td>
								</#if>
							</tr>
						<#else>
							<tr class="tr2">
								<td>${cash.name}</td>
								<td>${cash.phone}</td>
								<#if cash.phoneCity??>
									<td>${cash.phoneCity}</td>
								<#else>
									<td></td>
								</#if>
								<td>${cash.allAmount}</td>
								<td>${cash.cashOrigin}</td>
								<td>${cash.userRole}</td>
								<#if cash.channelName??>
									<td>${cash.channelName}</td>
								<#else>
									<td></td>
								</#if>
								<#if cash.feedback??>
									<td>${cash.feedback}</td>
								<#else>
									<td></td>
								</#if>
							</tr>
						</#if>
					</#list>
				</#if>
			</tbody>
		</table>
		<div>
			<img src="cid:dayCashChart"/>
		</div>
		<h1>大额投资明细(5万以上)</h1>
		<table>
			<thead>
				<tr class="tr2">
					<th>会员ID</th>
					<th>姓名</th>
					<th>定期投资</th>
					<th>债权投资</th>
					<th>总投资</th>
					<th>投资类型</th>
					<th>渠道</th>
				</tr>
			</thead>
			<tbody>
				<#if investDetails??>
					<#assign x=0 />
					<#list investDetails as invest>
						<#assign x=x+1 />
						<#if x%2 == 0>
							<tr class="tr1">
								<#if invest.memberId??>
									<td>${invest.memberId}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.name??>
									<td>${invest.name}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.regularAmount??>
									<td>${invest.regularAmount}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.creditAmount??>
									<td>${invest.creditAmount}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.allAmount??>
									<td>${invest.allAmount}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.investType??>
									<td>${invest.investType}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.channelName??>
									<td>${invest.channelName}</td>
								<#else>
									<td></td>
								</#if>
							</tr>
						<#else>
							<tr>
								<#if invest.memberId??>
									<td>${invest.memberId}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.name??>
									<td>${invest.name}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.regularAmount??>
									<td>${invest.regularAmount}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.creditAmount??>
									<td>${invest.creditAmount}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.allAmount??>
									<td>${invest.allAmount}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.investType??>
									<td>${invest.investType}</td>
								<#else>
									<td></td>
								</#if>
								<#if invest.channelName??>
									<td>${invest.channelName}</td>
								<#else>
									<td></td>
								</#if>
							</tr>
						</#if>
					</#list>
				</#if>
			</tbody>
		</table>
		<h1>大额充值明细(5万以上)</h1>
		<table>
			<thead>
				<tr class="tr2">
					<th>会员ID</th>
					<th>姓名</th>
					<th>充值金额</th>
					<th>定期投资</th>
					<th>债权投资</th>
					<th>账户余额</th>
				</tr>
			</thead>
			<tbody>
				<#if chargeDetails??>
					<#assign x=0 />
					<#list chargeDetails as charge>
						<#assign x=x+1 />
						<#if x%2 == 0>
							<tr class="tr1">
								<#if charge.memberId??>
									<td>${charge.memberId}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.name??>
									<td>${charge.name}</td>
								<#else>
									<td>社会名单</td>
								</#if>
								<#if charge.chargeAmount??>
									<td>${charge.chargeAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.regularAmount??>
									<td>${charge.regularAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.creditAmount??>
									<td>${charge.creditAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.balance??>
									<td>${charge.balance}</td>
								<#else>
									<td>0</td>
								</#if>
							</tr>
						<#else>
							<tr>
								<#if charge.memberId??>
									<td>${charge.memberId}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.name??>
									<td>${charge.name}</td>
								<#else>
									<td>社会名单</td>
								</#if>
								<#if charge.chargeAmount??>
									<td>${charge.chargeAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.regularAmount??>
									<td>${charge.regularAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.creditAmount??>
									<td>${charge.creditAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if charge.balance??>
									<td>${charge.balance}</td>
								<#else>
									<td>0</td>
								</#if>
							</tr>
						</#if>
					</#list>
				</#if>
			</tbody>
		</table>
		<h1>大额定期回款明细(5万以上)</h1>
		<table>
			<thead>
				<tr class="tr2">
					<th>会员ID</th>
					<th>姓名</th>
					<th>手机号</th>
					<th>定期回款金额</th>
					<th>定期复投金额</th>
					<th>提现金额</th>
					<th>账户余额</th>
					<th>复投状态</th>
				</tr>
			</thead>
			<tbody>
				<#if recoveryDetails??>
					<#assign x=0 />
					<#list recoveryDetails as recovery>
						<#assign x=x+1 />
						<#if x%2 == 0>
							<tr class="tr1">
								<#if recovery.memberId??>
									<td>${recovery.memberId}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.name??>
									<td>${recovery.name}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.phone??>
									<td>${recovery.phone}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.regularRecoveryAmount??>
									<td>${recovery.regularRecoveryAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.reinvestAmount??>
									<td>${recovery.reinvestAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.cashAmount??>
									<td>${recovery.cashAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.balance??>
									<td>${recovery.balance}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.reinvestStatus??>
									<td>${recovery.reinvestStatus}</td>
								<#else>
									<td>无状态</td>
								</#if>
							</tr>
						<#else>
							<tr>
								<#if recovery.memberId??>
									<td>${recovery.memberId}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.name??>
									<td>${recovery.name}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.phone??>
									<td>${recovery.phone}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.regularRecoveryAmount??>
									<td>${recovery.regularRecoveryAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.reinvestAmount??>
									<td>${recovery.reinvestAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.cashAmount??>
									<td>${recovery.cashAmount}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.balance??>
									<td>${recovery.balance}</td>
								<#else>
									<td>0</td>
								</#if>
								<#if recovery.reinvestStatus??>
									<td>${recovery.reinvestStatus}</td>
								<#else>
									<td>无状态</td>
								</#if>
							</tr>
						</#if>
					</#list>
				</#if>
			</tbody>
		</table>
	</body>
</html>
