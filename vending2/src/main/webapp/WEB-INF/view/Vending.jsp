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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div">
	<div style=" width:400px; margin: 0 auto ;">
	<h1>vending</h1>
	<%-- <form action="${pageContext.request.contextPath}/Vending" id="drinkForm" method="post"> --%>
		<h2 style="margin-bottom: 5px;">
			금액충전
		</h2>
		
		<!-- 충전기능호출 -->
		<div style="display: flex; margin-bottom: 16px;">
            <button type="button" onclick="chargeAmount(10000)">10000원</button>
            <button type="button" onclick="chargeAmount(5000)">5000원</button>
            <button type="button" onclick="chargeAmount(1000)">1000원</button>
            <button type="button" onclick="chargeAmount(500)">500원</button>
            <button type="button" onclick="chargeAmount(100)">100원</button>
        </div>
		
		<!-- 구입 or 충전시 값 변동 balacne -->
		<h3>현재금액:
			<span id="balance"></span>원
		</h3>
		<!-- 구매 or 반환 or 오류 일시 msg 표시 -->
		<h2 id="msg"></h2>
		<table border="1">
			<tr>
				<td>음료수</td>
				<td>가격</td>
				<td>재고</td>
				<td>선택</td>
			</tr>
			
			<!-- controller 에서 받아온 list 값 뿌리기 -->
			<c:forEach var="d" items="${vending}" varStatus="status">
				<tr>
					<td>${d.drink}</td>
					<td>${d.price}</td>
					
					<!-- 재고가 0이면 품절 -->
					<c:choose>				
						<c:when test="${d.amount == 0}">
							<td>품절</td>
			
							<td>
								<input type="radio" disabled="disabled">
							</td>
						</c:when>	
						
						<c:otherwise>
							<td id ="${d.drink}">${d.amount}</td>
							
							<td>
								<!-- 라디오버튼 value = ${d.drink} 라디오버튼checked 됐을때 d.drink 호출  
									  data-price , data-amount : 선택한 음료수의 가격 + 수량 값  -->
								
								<input type="radio" name="drink" value="${d.drink}" id="${status.count}" data-price="${d.price}" data-amount="${d.amount}">
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>		
		</table>
		
		<div>
			<button type="button" onclick="purchaseDrink()">구매하기</button>
		</div>
		 
		 <div>
		 	<button type="button"  onclick="changeReturn()">잔돈반환</button>
		 </div>
		 <a href="${pageContext.request.contextPath}/VendingEmp">관리자모드</a>
	<!-- </form> -->
		</div>
			    
		</div>
		<script type="text/javascript">
		
		// 로컬스토리지에 저장한 값이 있으면 가져옴
		let userBalance = Number(localStorage.getItem('userBalance'));
		let drink = ""; //음료수 
		let drinkPrice = 0; //음료수 가격 
		let changeAmount = 0; // 
		let currentBalance = 0; // 현재 잔액 변수 초기화
		let msg = ""; // 메세지 변수
		
		// 값이 없을 때 처리
		if (userBalance === null) {
		    console.log('저장된값 X');
		} else {
		    currentBalance = userBalance ;
		    console.log("로컬스토리지 	저장된값:" + currentBalance + typeof(currentBalance) + typeof(userBalance));    
		    document.getElementById("balance").textContent = currentBalance;
		}
		
		
		
		function select() {
			const selectedRadio = document.querySelector('input[type="radio"]:checked')
			if (selectedRadio) {
			count = selectedRadio.id;
			// 체크된 라디오버튼에서 value가져옴 <-- 음료수이름
	   		drink = selectedRadio.value;
			
	   		// 체크된 라디오버튼에서 음료수 가격을 가져옴 // 맨뒤 10은 진수타입 생략가능
	   	 	drinkPrice = parseInt(selectedRadio.getAttribute('data-price'), 10);
	   		
	   	 	// 가져온 음료수 이름을 가진 id를 찾아서 재고가져옴 
	   	 	//let currentAmount = document.getElementById(drink).innerText;
	   	 	
	   	 	// 라디오 버튼에서 음료수 개수(재고) 가져옴 
	   	 	drinkAmount = parseInt(selectedRadio.getAttribute('data-amount'), 10);
	   		console.log(drink , drinkPrice , drinkAmount); 
	   		
			} else {
	   		console.log('non');
			}
		}
		
		function purchase() {
			return	currentBalance >= drinkPrice;
		}
		
		 // ajax 통신
  function purchaseDrink() {
	  // 선택된 음료수 정보 호출
	  select()
	  console.log(drink);
	  // 현재 충전금액이 음료수 가격보다 높으면 실행
	  if(purchase()){
	 	 $.ajax({
			url: '/vending/Vending',
			type: 'post',
			dataType: 'json',
			data: {name:drink}, // key : value  ----> (/vending/Vending) post 전달 
			 
			// 통신 성공시 재고 1개 차감후 업데이트된 db 호출
			success: function(list){
				// ajax 통신 성공시 호출
				console.log("성공");
				console.log(list);
				
				// 가져온 list 에서 select()에서 가져온 drink와 같은값을 찾고 그 drink의 amount를 가져옴
				const amount = list.find(obj => obj.drink === drink).amount;	// 1. < -- 생략하고
				console.log("구입후남은재고: "+amount); 
				
				// 현재충전금액 - 음료수값 
				// 계산된값 표시
			 	currentBalance -= drinkPrice;
				console.log(currentBalance);
				// 로컬스토리지에 구매후 남은값  저장
				localStorage.setItem('userBalance', currentBalance);
				document.getElementById("balance").textContent = currentBalance;
				// 구입후 재고가 0이되면 품절로 바꾸고 checked 해제 drink 초기화
				if(amount > 0){
				
				// 선택된 음료수의 재고수 새로받아온 list의 값으로변경    	
				document.getElementById(drink).textContent = amount; //2. <-- list를 forEach로 뽑아서 모든 목록의재고 상태를 업데이트 가능함 
																	 //     
				
				msg= drink +"를(을) 구입하셨습니다.";
				} else{
					// 재고수가 1 이상이 아니면 품절표시후 체크해제 , 선택불가
					document.getElementById(drink).textContent = "품절";
					document.getElementById(count).disabled = true;
					document.getElementById(count).checked = false;
					msg= drink +"를(을) 구입하셨습니다. (품절되었습니다. 다른제품을 선택해주세요)";
				  }
				// 메세지표시
				document.getElementById("msg").textContent = msg;
				// drink 초기화 하지않으면 -로 재고수량이 계속 내려감 // 재고있으면 checked 해제가 안되어서 연속구매 가능
				drink = "";
				}
		  	})
	  	} else {msg = "잔액이부족합니다." // 현재충전금액이 음료수 구매금액보다 낮으면.
			document.getElementById("msg").textContent = msg;  
		  	//console.log("실행확인");
		  }
	  }
  
		
		
        // 금액 충전 	50000, 10000 , 5000, 1000 , 500 , 100
        function chargeAmount(amount) {
			if( (currentBalance + amount) <= 50000){
            currentBalance += amount; // 현재 잔액에 금액 추가
            
            // 로컬스토리지에 충전금액 저장.
            localStorage.setItem('userBalance', currentBalance);
			} else{
				msg="50000원이상은 충전이 불가합니다.";
			}
			
			console.log(amount);
			console.log(msg);
            console.log("충전된 금액:", amount, "현재 잔액:", currentBalance);
            // 충전금액 표시
            document.getElementById("balance").textContent = currentBalance;
            document.getElementById("msg").textContent = msg;
        }
        
        function changeReturn() {
            // 현재 잔액 계산
            currentBalance = parseInt(document.getElementById("balance").textContent);

            // 잔돈 계산
            changeAmount = currentBalance;

            // 잔액 0으로 설정
            currentBalance = 0;
            // 돈을 반환했으므로 로컬스토리지에 0저장
            localStorage.setItem('userBalance', currentBalance);
            document.getElementById("balance").textContent = currentBalance;
            // 잔돈 개수 입력
            document.getElementById("msg").textContent = "잔돈 " + changeAmount + "원이 반환되었습니다.";
        }
        	
		</script>	
			
		
</body>
</html>