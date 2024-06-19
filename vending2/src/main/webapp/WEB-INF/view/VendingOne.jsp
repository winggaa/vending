<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>재고수정</h1>
	<form action="VendingOne" method="post">
		<table border ="1">
			<tr>
			<td>음료수</td>
			<td>가격</td>
			<td>재고</td>
			</tr>
			
			<tr>
				<c:forEach var="o"   items="${one}">
					<td>
						<span>${o.drink}</span>
					</td>
					<td>
						<input type="number" name="price" value="${o.price}" min=0 >
					</td>
					
					<td>
						<input type="number" name="amount" value="${o.amount}"  min=0 max= 99>
					</td>
					<input type="hidden" name=drink value="${o.drink}">
				</c:forEach>
			</tr>
		</table>
					
		<button type="submit">수정하기</button>
	</form>
	<span>${error}</span>
	<form action="vendingDelete" method="post">
		<c:forEach var="o" items="${one}">
		<input type="hidden" name="drink" value="${o.drink}">
			<button type="submit">삭제하기</button>
		</c:forEach>
	</form>
	
</body>
</html>