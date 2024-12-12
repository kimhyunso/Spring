<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
	});
</script>
<body>
<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							<c:choose>
								<c:when test="${list.boardType eq 'a01'}">일반</c:when>
								<c:when test="${list.boardType eq 'a02'}">Q&A</c:when>
								<c:when test="${list.boardType eq 'a03'}">익명</c:when>
								<c:when test="${list.boardType eq 'a04'}">자유</c:when>
							</c:choose>
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
	</tr>
	<tr>
		<td align="left">
			<c:forEach items="${commandList}" var="list">
				<input type="checkbox" value="${list.codeID}" name="${list.codeName}">
				<label for="${list.codeName}">${list.codeName}</label>
			</c:forEach>
			
			<input type="button" value="조회" id="searchType">
		</td>
	</tr>
</table>


<script type="text/javascript">
	const searchButton = document.getElementById("searchType");
	const boardType = document.getElementsByTagName("input");
	searchButton.addEventListener("click", searchType);
	
	function searchType(e){
		let data = [];
		for(let i = 0; i < boardType.length; i++){
	        if(boardType[i].getAttribute("type") == "checkbox" && boardType[i].checked){
	        	const value = boardType[i].value;
	        	data.push(value);
	        }
	    }
	
		
		fetch("searchBoardList.do", {
		    method : "POST",
		    body : JSON.stringify({"codeID" : data}),
		    headers : {
	  	      "Content-Type": "application/json",
	      	}
		}).then(data => {
		    console.log("data", data);
		}).catch(excResp => {
		    console.log(excResp);
		});
	}
</script>
</body>
</html>