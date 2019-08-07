<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<jsp:include page="head.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>

<body>
<jsp:include page="menu.jsp">
</jsp:include>
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