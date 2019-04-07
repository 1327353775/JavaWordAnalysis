<%@page import="com.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>

<script src="js/adapter.js"></script> <!--rem适配js-->
<script src="js/jquery.js"></script>
<link rel="stylesheet" href="css/base.css"> <!--初始化文件-->
<link rel="stylesheet" href="css/menu.css"> <!--主样式-->

</head>
<body>

<div id="menu">
    <!--隐藏菜单-->
    <div id="ensconce">
        <h2>
            <img src="images/show.png" alt="">
            分类
        </h2>
    </div>

    <!--显示菜单-->
    <div id="open">
        <div class="navH">
            分类
            <span><img class="obscure" src="images/obscure.png" alt=""></span>
        </div>
        <div class="navBox">
            <ul>
                <li>
                    <h2 class="obtain">云计算<i></i></h2>
                    <div class="secondary">
                        <h3>自媒体</h3>
                        <h3>智能家居</h3>
                        <h3>车联网</h3>
                        <h3>工业4.0</h3>
                        <h3>智慧农业</h3>
                        <h3>分布式存储</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">信息化热词<i></i></h2>
                    <div class="secondary">
                        <h3>虚拟现实（VR）</h3>
                        <h3>增强现实（AR）</h3>
                        <h3>互联网数据中心（IDC）</h3>
                        <h3>虚拟专用网络（VPN）</h3>
                        <h3>办公自动化（OA）</h3>
                        <h3>企业资源计划（ERP）</h3>
                    </div>
                </li>
                <li>
                    <h2 class="obtain">大数据<i></i></h2>
                    <div class="secondary">
                        <h3>数据挖掘</h3>
                        <h3>大数据</h3>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
</div>
<div style="float: left;width: 950px;height: 600px;margin-left: 60px;border: 1px solid;margin-top: 50px">
	<div style="width: 80%;height: 10%;border: 0px solid;margin: 10px auto;text-align: center;">
		<form action="./compareServlet" method="post">
			<input id="name" style="width: 80%;height: 40px;border: 1px solid;font-size: 20px;" value="${param.name}" type="text" name="find" />
			<input id="find" style="width: 60px;height: 40px;cursor: pointer;" type="submit" value="搜 索"/>
		</form>
	</div>
	<div style="width: 80%;height: 85%;border: 0px solid;margin: 0 auto">
		<table cellpadding="0" cellspacing="0" border="1" style="border: 1px solid;width: 90%;height: 90%;margin: 0 auto;text-align: center;">
			<tr>
				<td style="width: 20%;height: 50px">名字</td><td>${sessionScope.user.name}</td>
			</tr>
			<tr>
				<td style="width: 20%;height: 50px">比例</td><td>${sessionScope.user.category}</td>
			</tr>
			<tr>
				<td style="width: 20%;height: 50px">关键词</td><td>${sessionScope.user.word}</td>
			</tr>
			<tr>
				<td style="width: 20%;height: 50px">摘要</td><td>${sessionScope.user.sentence}</td>
			</tr>
			<tr>
				<td id="content">内容</td><td>${sessionScope.user.content}</td>
			</tr>
		</table>
	</div>
</div>
<script src="js/menu.js"></script> <!--控制js-->
</body>
</html>