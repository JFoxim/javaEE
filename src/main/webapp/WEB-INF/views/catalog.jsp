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
<h2>Каталог</h2>
<p></p>
<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th>Название товара</th>
        <th>Описание</th>
        <th>Стоимость</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}" >
        <tr>
            <td>
                <c:out value="${product.id}" />
            </td>
            <td>
                <c:out value="${product.name}" />
            </td>
            <td>
                <c:out value="${book.description}" />
            </td>
            <td>
                <c:out value="${book.proce}" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>