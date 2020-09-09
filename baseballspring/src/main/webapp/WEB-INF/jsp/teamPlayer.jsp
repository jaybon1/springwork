<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>선수 관리</title>
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
				<a class="nav-link" href="/stadiumList">야구장 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/teamList">팀 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/playerList">선수 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/positionPlayer">포지션별 선수</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" href="/teamPlayer">팀별 선수</a>
			</li>
		</ul>
	</div>
	<br/>
	<br/>
	<br/>
	<div class="container">
		<ul class="nav nav-tabs">
			<c:forEach var="team" items="${teamList}">
				<li class="nav-item">
					<a class="nav-link" href="javascript:void(0)" onclick="getTeamPlayer(${team.id})">${team.name}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>선수 이름</th>
					<th>포지션</th>
					<th>퇴출 이유</th>
					<th>퇴출일</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
	</div>
</body>
<script>

function getTeamPlayer(teamId){
	
	$.ajax({
		
		type : "get",
		url : "/teamPlayerProc/"+teamId,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json"
		
	}).done(function(resp) {
		console.log(resp)
		
		$("#tbody").empty();
		for(var player of resp){
			
			var data = "";
			
			if(player.outPlayer == null){
				
				var data = 	
					"				<tr>\r\n" + 
					"					<th>"+player.id+"</th>\r\n" + 
					"					<th>"+player.name+"</th>\r\n" + 
					"					<th>"+player.position+"</th>\r\n" + 
	 				"					<th></th>\r\n" + 
	 				"					<th></th>\r\n" + 
					"				</tr>";
					
			} else {
				
				var data = 	
					"				<tr>\r\n" + 
					"					<th>"+player.id+"</th>\r\n" + 
					"					<th>"+player.name+"</th>\r\n" + 
					"					<th>"+player.position+"</th>\r\n" + 
	 				"					<th>"+player.outPlayer.reason+"</th>\r\n" + 
	 				"					<th>"+player.outPlayer.day+"</th>\r\n" + 
					"				</tr>";
					
			}
			
			$("#tbody").append(data);
			
		}
		
	}).fail(function(error) {
		
		alert(error);
		
	});
	
}

</script>

</html>