<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주식 전략</title>
</head>
<body>
	<h2>주식 전략</h2>
	<div class="input">
	<form action="/home" method="post">
		<div class="standard-date">
			<label>기준일 :</label>
			<input type="date" name="standardDate" value="${condition.standardDate}"/>
		</div>
		<div class="period">
			<label>기간 : </label>
			<input type="radio" name="period" value="oneYear" ${condition.period == 'oneYear' ? 'checked' : ''}>1년 전
			<input type="radio" name="period" value="threeMonth" ${condition.period == 'threeMonth' ? 'checked' : ''}>3개월 전
			<input type="radio" name="period" value="oneMonth" ${condition.period == 'oneMonth' ? 'checked' : ''}>1개월 전
		</div>
		<div class="strategy">
			<label>전략 : </label>
			<input type="radio" name="strategy" value="sv" ${condition.strategy == 'sv' ? 'checked' : ''}>sv
			<input type="radio" name="strategy" value="svqm" ${condition.strategy == 'svqm' ? 'checked' : ''}>sqvm
			<input type="radio" name="strategy" value="newmagic" ${condition.strategy == 'newmagic' ? 'checked' : ''}>new magic
		</div>
		<div>
			<input type="submit" value="검색">
		</div>
	</form>
	</div>
	
	<div class="result">
	<table border="1" width="800">
		<tr>
			<th>순번</th>
			<th>종목 코드</th>
			<th>종목명</th>
			<th>시작일</th>
			<th>시작일 시가</th>
			<th>종료일</th>
			<th>종료일 시가</th>
			<th>이익율</th>
		</tr>

		<c:forEach var="diff" items="${diffList}" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${diff.code}</td>
			<td>${diff.name}</td>
			<td>${diff.beginDate}</td>
			<td>${diff.beginPrice}</td>
			<td>${diff.endDate}</td>
			<td>${diff.endPrice}</td>
			<td>${diff.profit}</td>
		</tr>
		<c:set var="total" value="${total + diff.profit}"/>
		</c:forEach>		
		<tr>
			<td colspan="7">총합:</td>
			<td>${total / 50}</td>
		</tr>
	</table>
	</div>

</body>
</html>