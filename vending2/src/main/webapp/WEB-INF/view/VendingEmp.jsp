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
 <script src="https://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
	<h1>vending</h1>
	
	<form action="${pageContext.request.contextPath}/drinkCreate" method="post">
		<table border="1">
			<tr>
				<td>음료수</td>
				<td>가격</td>
				<td>재고</td>
			</tr>
			
			<c:forEach var="d" items="${vending}" varStatus="status">
				<tr>
					<td>
					<a href="${pageContext.request.contextPath}/VendingOne?drink=${d.drink}">${d.drink}</a>
					</td>
					<td>${d.price}</td>
					<td>${d.amount}</td>
				</tr>
			</c:forEach>
				<tr>
					<td Colspan="3" style="text-align: center;">
						음료수 추가
					</td>
				</tr>
				
				<tr>
					<td>음료수</td>
					<td>가격</td>
					<td>재고</td>
				</tr>
				
				<tr>
					<td>	
						<input type="text" name="drink" min=1 max=15 required="required" id="test" onkeyup="chk_han('test')"/>
					</td>
					
					<td>	
						<input type="number" name="price" min = 0  max=1000000 required="required">
					</td>
					
					<td>	
						<input type="number" name="amount" min = 0  max = 99 required="required">
					</td>
				</tr>		
		</table>
		<button type="submit">음료수 추가하기</button>
	</form>
	
	<script>
	// 한글을 제외한 값을 입력시 ''로교체
		function chk_han(id) {
		    var regexp = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
		    var value = $("#"+id).val();
		    if (regexp.test(value)) {
		        $("#"+id).val(value.replace(regexp, ''));
		    }
		}

	
	</script>
</body>
</html>