<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="container">

    <div class="starter-template">
        <h1>Yummy Cakes</h1>
        <c:forEach items="${cakes}" var="cake">
            <div><c:out value="${cake.id}"/></div>
            <div><c:out value="${cake.title}"/></div>
            <div><c:out value="${cake.description}"/></div>
            <div><img src="<c:url value='images${cake.image}'/>" alt="" width="25" height="25"/></div>
            <div>&nbsp;</div>
        </c:forEach>
    </div>

</div>
</body>
</html>
