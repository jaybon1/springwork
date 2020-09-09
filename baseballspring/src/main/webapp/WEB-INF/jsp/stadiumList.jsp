<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>구장 관리</title>
</head>
<body>

	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link" href="/stadiumAdd">야구장 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link " href="/teamAdd">팀 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/playerAdd">선수 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/outPlayerAdd">퇴출선수 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" href="/stadiumList">야구장 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link " href="/teamList">팀 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/playerList">선수 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/positionPlayer">포지션별 선수</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/teamPlayer">팀별 선수</a>
			</li>
		</ul>
	</div>


	<div class="container">
		<table class="table">
			<thead>

				<tr>
					<th>NO</th>
					<th>구장</th>
					<th>팀</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach var="rankStadiumDto" items="${rankStadiumDtoList}">
					<tr>
						<td>${rankStadiumDto.rank}</td>
						<td>${rankStadiumDto.stadium.name}</td>
						<c:choose>
							<c:when test="${rankStadiumDto.stadium.team.name == null}">
								<td>없음</td>
							</c:when>
							<c:otherwise>
								<td>${rankStadiumDto.stadium.team.name}</td>
							</c:otherwise>
						</c:choose>
						<td><button class="btn btn-danger btn-delete" onclick="stadiumDelete(${rankStadiumDto.stadium.id})">삭제</button></td>
					</tr>
				</c:forEach>
		</table>
	</div>
</body>
<script>

function stadiumDelete(stadiumId){
	
	$.ajax({
		
		type : "delete",
		url : "/stadiumList/"+stadiumId,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json"
		
	}).done(function(resp) {
		console.log(resp)
		
		$("#tbody").empty();
		
		for(var rankStadiumDto of resp){

				var data = 	
					"					<tr>\r\n" + 
					"						<td>"+rankStadiumDto.rank+"</td>\r\n" + 
					"						<td>"+rankStadiumDto.stadium.name+"</td>\r\n" + 
					"						<td>"+rankStadiumDto.stadium.team.name+"</td>\r\n" + 
					"						<td><button class=\"btn btn-danger btn-delete\" onclick=\"stadiumDelete("+rankStadiumDto.stadium.id+")\">삭제</button></td>\r\n" + 
					"					</tr>";

			$("#tbody").append(data);
			
		}
		
	}).fail(function(error) {
		
		alert("에러");
		
	});
	
}

</script>
</html>
