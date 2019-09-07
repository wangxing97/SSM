<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>LastName</th>
			<th>gender</th>
			<th>email</th>
		</tr>
		<c:forEach items="${emps.list }" var="emp">
			<tr>
				<td>${emp.id }</td>
				<td>${emp.lastName }</td>
				<td>${emp.gender }</td>
				<td>${emp.email }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">
				<a href="getAllEmps?pn=1">首页</a>
				<a href="getAllEmps?pn=${emps.prePage }">上一页</a>
				<c:forEach items="${emps.navigatepageNums }" var="num">
					<c:if test="${num==emps.pageNum }">
						【${emps.pageNum}】
					</c:if>
					<c:if test="${num!=emps.pageNum }">
						<a href="getAllEmps?pn=${num }">${num }</a>
					</c:if>
				</c:forEach>
				<a href="getAllEmps?pn=${emps.nextPage }">下一页</a>
				<a href="getAllEmps?pn=${emps.pages }">末页</a>
			</td>
		</tr>
	</table>
</body>
</html>