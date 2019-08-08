<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="head.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>

<body>
<ul>
    <li><a href="main">Главная</a></li>
    <li><a href="catalog">Каталог</a></li>
    <li><a href="product">Продукт</a></li>
    <li><a href="cart">Корзина</a></li>
    <li><a href="order">Заказы</a></li>
</ul>
<p></p>
<h2>Корзина</h2>
</body>
</html>