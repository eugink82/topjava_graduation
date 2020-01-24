<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <a href="${pageContext.request.contextPath}/logout">Выход</a><br>


 <a href="${pageContext.request.contextPath}/admin/users">Управление пользователями</a><br><br>
 <a href="${pageContext.request.contextPath}/profile/restaurants">Просмотр ресторанов</a><br><br>
 <a href="${pageContext.request.contextPath}/profile/restaurants?withMenu=true">Просмотр ресторанов с меню</a><br><br>


</body>
</html>
