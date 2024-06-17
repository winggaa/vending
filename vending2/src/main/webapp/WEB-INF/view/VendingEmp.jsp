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
 <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
 
</head>
<body>
	<div style=" margin: auto 0;">
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
	<form action="${pageContext.request.contextPath}/vendingFile" method="post" enctype="multipart/form-data">
	<input type="file" name="file">
	<button type="submit">전송하기</button>
	</form>
	</div>
	<div>
		<button type="button" onclick='request_pay()'>구매하기</button>
	</div>
	
	<script>
	 /* var IMP = window.IMP;   // 생략 가능
	 IMP.init("imp27242516"); // 예: imp00000000 */ 
	
	// 한글을 제외한 값을 입력시 ''로교체
		function chk_han(id) {
		    var regexp = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
		    var value = $("#"+id).val();
		    if (regexp.test(value)) {
		        $("#"+id).val(value.replace(regexp, ''));
		    }
		}

	</script>
	<script>
	 
	
	/* IMP.request_pay(
			  {
			    pg: "kakaopay.TC0ONETIME",
			    pay_method: "card", // 생략가
			    merchant_uid: "order_no_0001", // 상점에서 생성한 고유 주문번호
			    name: "주문명:결제테스트",
			    amount: 1004,
			    buyer_email: "test@portone.io",
			    buyer_name: "구매자이름",
			    buyer_tel: "010-1234-5678",
			    buyer_addr: "서울특별시 강남구 삼성동",
			    buyer_postcode: "123-456",
			    m_redirect_url: "{모바일에서 결제 완료 후 리디렉션 될 URL}",
			  },
			  function (rsp) {
			    // callback 로직
			    /* ...중략... */
			  },
			); */
	</script>
</body>
</html>