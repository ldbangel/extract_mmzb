<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>微信数据统计</title>
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
	<h2>微信数据统计</h2>
	<body>
		<table width="100%" cellspacing="0">
			<thead>
				<tr class="tr2">
					<th width="60%">统计项</th>
					<th width="40%">数量</th>
				</tr>
			</thead>
			<tbody>
				<#if resultList??>
					<#list resultList as result>
			 			<tr>  
    						<td>${result.title}</td>  
    						<td>${result.nums}</td>  
						</tr>  
					</#list>
				</#if>
			</tbody>
		</table>
	</body>
</html>
