<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>兼职理财师业绩报表</title>
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

            .tr1{
                background:#CCEBFF;
            }
		</style>
	</head>
	<h2>兼职理财师业绩日报</h2>
	<body>
		<table width="100%" cellspacing="0">
			<tr class="tr2">
				<td width="20%">兼职理财师</td>
				<td width="20%">新增</th>
				<td width="20%">投资总额</th>
				<td width="20%">年化投资</th>
				<td width="20%">新增用户</td>
			</tr>
			<#if partTimeFinancierListDay??>
				<#assign x=0 />
				<#list partTimeFinancierListDay as partTimeFinancier>
					<#assign x=x+1 />
					<#if x%2 == 0>
						<tr class="tr1">
							<td width="20%">${partTimeFinancier.financier}</td>
							<td width="20%">${partTimeFinancier.newInvest}</th>
							<td width="20%">${partTimeFinancier.allInvest}</th>
							<td width="20%">${partTimeFinancier.yearInvest}</th>
							<td width="20%">${partTimeFinancier.newNums}</td>
						</tr>
					<#else>
                        <tr>
                            <td width="20%">${partTimeFinancier.financier}</td>
                            <td width="20%">${partTimeFinancier.newInvest}</th>
                            <td width="20%">${partTimeFinancier.allInvest}</th>
                            <td width="20%">${partTimeFinancier.yearInvest}</th>
                            <td width="20%">${partTimeFinancier.newNums}</td>
                        </tr>
					</#if>
				</#list>
			</#if>
		</table>
	</body>
	
	
	<h2>兼职理财师业绩月报</h2>
	<body>
		<table width="100%" cellspacing="0">
			<tr class="tr2">
				<td width="20%">兼职理财师</td>
				<td width="20%">新增</th>
				<td width="20%">投资总额</th>
				<td width="20%">年化投资</th>
				<td width="20%">新增用户</td>
			</tr>
			<#if partTimeFinancierListMonth??>
				<#assign x=0 />
				<#list partTimeFinancierListMonth as partTimeFinancier>
					<#assign x=x+1 />
					<#if x%2 == 0>
						<tr class="tr1">
							<td width="20%">${partTimeFinancier.financier}</td>
							<td width="20%">${partTimeFinancier.newInvest}</th>
							<td width="20%">${partTimeFinancier.allInvest}</th>
							<td width="20%">${partTimeFinancier.yearInvest}</th>
							<td width="20%">${partTimeFinancier.newNums}</td>
						</tr>
					<#else>
                        <tr>
                            <td width="20%">${partTimeFinancier.financier}</td>
                            <td width="20%">${partTimeFinancier.newInvest}</th>
                            <td width="20%">${partTimeFinancier.allInvest}</th>
                            <td width="20%">${partTimeFinancier.yearInvest}</th>
                            <td width="20%">${partTimeFinancier.newNums}</td>
                        </tr>
					</#if>
				</#list>
			</#if>
		</table>
	</body>
</html>
