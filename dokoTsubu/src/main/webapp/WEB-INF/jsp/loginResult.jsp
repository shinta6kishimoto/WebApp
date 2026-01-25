<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<%
//セッションスコープからユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<% if(loginUser != null) {%>
<p>ログイン成功</p>
<p>ようこそ<%= loginUser.getName() %>さん</p>
<a href="<%= request.getContextPath() %>/Main">つぶやき投稿・閲覧へ</a>
<% }else { %>
<p>ログイン失敗</p>
<a href="jsp/index.jsp">ログイン画面へ</a>
<% } %>
</body>
</html>