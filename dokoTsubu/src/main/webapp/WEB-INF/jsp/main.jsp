<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<%
//セッションスコープからユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
//アプリケーションスコープからつぶやきリストを取得
List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p><font color="red">
<%= request.getAttribute("errMsg") %>
</font></p>
<p>
	<%= loginUser.getName() %>さん、ログイン中</p>
	<a href="Logout">ログアウト</a>
	<a href="Main">更新</a>
	<form action="<%= request.getContextPath() %>/Main" method="post">
	<input type="text" name="text">
	<input type="submit" value="つぶやく">
	</form>
	<% for(Mutter mutter:mutterList){%>
	<p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
	<% }%>
</body>
</html>