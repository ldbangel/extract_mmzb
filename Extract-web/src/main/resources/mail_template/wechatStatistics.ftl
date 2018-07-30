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
				<tr>
					<td>可使用微信登录的人数</td>  
					<#if ableWechatLoginNums??>
						<td>${ableWechatLoginNums}</td>
					<#else>
						<td>0</td>
					</#if>
				</tr>
				<tr>
					<td>可收到微信推送消息人数</td>  
					<#if ableReceivedWechatPushNums??>
						<td>${ableReceivedWechatPushNums}</td>
					<#else>
						<td>0</td>
					</#if>
				</tr>
				<tr>
					<td>查询当天微信消息推送的数量</td>  
					<#if todayWechatPushNums??>
						<td>${todayWechatPushNums}</td>
					<#else>
						<td>0</td>
					</#if>
				</tr>
				<tr>
					<td>用户取消关注微信公众号,但已绑定</td>  
					<#if bindAndCancledNums??>
						<td>${bindAndCancledNums}</td>
					<#else>
						<td>0</td>
					</#if>
				</tr>
				<tr>
					<td>债权购买成功通知</td>  
					<#if 债权购买成功通知??>
						<td>${债权购买成功通知}</td>
					<#else>
						<td>0</td>
					</#if> 
				</tr>
				<tr>
					<td>债权转让成功通知</td>  
					<#if 债权转让成功通知??>
						<td>${债权转让成功通知}</td>
					<#else>
						<td>0</td>
					</#if> 
				</tr>
				<tr>
					<td>充值到账通知</td>  
					<#if 充值到账通知??>
						<td>${充值到账通知}</td>
					<#else>
						<td>0</td>
					</#if>
				</tr>
				<tr>
					<td>利息到账通知</td>  
					<#if 利息到账通知??>
						<td>${利息到账通知}</td>
					<#else>
						<td>0</td>
					</#if> 
				</tr>
				<tr>
					<td>回款成功通知</td>  
					<#if 回款成功通知??>
						<td>${回款成功通知}</td>
					<#else>
						<td>0</td>
					</#if>
				</tr>
				<tr>
					<td>回款提醒</td>  
					<#if 回款提醒??>
						<td>${回款提醒}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>开户成功通知</td>  
					<#if 开户成功通知??>
						<td>${开户成功通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>投资到期通知</td>  
					<#if 投资到期通知??>
						<td>${投资到期通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>投资结果通知</td>  
					<#if 投资结果通知??>
						<td>${投资结果通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>提现成功通知</td>  
					<#if 提现成功通知??>
						<td>${提现成功通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>提现申请成功通知</td>  
					<#if 提现申请成功通知??>
						<td>${提现申请成功通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>注册成功通知</td>  
					<#if 注册成功通知??>
						<td>${注册成功通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>自动投标成功通知</td>  
					<#if 自动投标成功通知??>
						<td>${自动投标成功通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>订阅模板消息</td>  
					<#if 订阅模板消息??>
						<td>${订阅模板消息}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>账户余额提醒</td>  
						<#if 账户余额提醒??>
							<td>${账户余额提醒}</td>
						<#else>
							<td>0</td>
						</#if>  
					</tr>
				<tr>
					<td>账户变动通知</td>  
					<#if 账户变动通知??>
						<td>${账户变动通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>账户收益通知</td>  
					<#if 账户收益通知??>
						<td>${账户收益通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>账户绑定通知</td>  
					<#if 账户绑定通知??>
						<td>${账户绑定通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>项目满标通知</td>  
					<#if 项目满标通知??>
						<td>${项目满标通知}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
				<tr>
					<td>首页访问量</td>  
					<#if indexNums??>
						<td>${indexNums}</td>
					<#else>
						<td>0</td>
					</#if> 
				</tr>
				<tr>
					<td>定期列表访问量</td>  
					<#if productNums??>
						<td>${productNums}</td>
					<#else>
						<td>0</td>
					</#if> 
				</tr>
				<tr>
					<td>邀请有奖访问量</td>  
					<#if inviteNums??>
						<td>${inviteNums}</td>
					<#else>
						<td>0</td>
					</#if> 
				</tr>
				<tr>
					<td>会员中心访问量</td>  
					<#if vipNums??>
						<td>${vipNums}</td>
					<#else>
						<td>0</td>
					</#if>  
				</tr>
			</tbody>
		</table>
	</body>
</html>
