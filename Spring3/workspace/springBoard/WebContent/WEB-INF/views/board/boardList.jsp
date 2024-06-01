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
			<span style="margin-right:300px;">
				<a href="">login</a> <a href="/user/join.do">join</a>
			</span>
			
			total : <span id="totalCnt">${totalCnt}</span>
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
						
						<c:forEach items="${commandList}" var="commandList">
							<c:if test="${commandList.codeID eq list.boardType}">
								${commandList.codeName}
							</c:if>							
						</c:forEach>

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
			<input type="checkbox" id="allSearch" name="checkbox">
			<label for="allSearch">전체</label>
			
			<c:forEach items="${commandList}" var="list">
				<input type="checkbox" id="${list.codeID}" name="checkbox" value="${list.codeID}">
				<label for="${list.codeID}">${list.codeName}</label>
			</c:forEach>
			<input type="button" id="searchType" value="조회">
		</td>
	</tr>
</table>
<script type="text/javascript">
	const searchButton = document.getElementById("searchType");
	const checkbox = document.getElementsByName("checkbox");
	const totalCnt = document.getElementById("totalCnt");
	
	checkbox.forEach((element) => {
		element.addEventListener("click", checkboxContoller);
	});
	searchButton.addEventListener("click", searchType);
	
	function checkboxContoller(e){
		const allSearch = checkbox[0];
		let cnt = 0;
		
		checkbox.forEach((element, index) => {
			if (index != 0 && element.checked)
				cnt += 1;
		});		
		
		if (e.target == allSearch){
			checkbox.forEach((element) => {
				element.checked = e.target.checked;
			});
		}else if(cnt < checkbox.length - 1){
			allSearch.checked = false;
			
		}else{
			allSearch.checked = true;
		}
	}

	function searchType(e){
		let streamData = [];
		
		checkbox.forEach((element) => {
			if(element.checked){
				streamData.push(element.value);
			}			
		});
		
		if (streamData.length != 0){
			fetch("searchBoardList.do",{
			  method: "POST",
			  headers: {"Content-Type": "application/json; charset=utf-8",},
			  body: JSON.stringify({"CodeId" : streamData}),
			})
			.then((response) => response.json())
			.then((data) => {
				const totalCnt = document.getElementById("totalCnt");
		    	totalCnt.innerHTML = data["totalCnt"];
		    	replaceTable(data);
			})
			.catch((error) => {
				alert("실패");
			});
		}
	}
	
	function replaceTable(data){
		const table = document.getElementById("boardTable");
		const tableRowLength = table.rows.length;
		const tableCellLength = table.rows[0].cells.length;
		const commandList = data["commandList"];
		const boardList = data["boardList"];
		
		for (let i=tableRowLength-1; i>0; i--){
			table.deleteRow(i);
		}
	
		for (let i=0; i<boardList.length; i++){
			const row = table.insertRow(i+1);
			
			for (let j=0; j<tableCellLength; j++){
				if (j == 0){
					for (let k=0; k<commandList.length; k++){
						if(boardList[i]["boardType"] == commandList[k]["codeID"]){
							row.insertCell(j).innerHTML = commandList[k]["codeName"];
							break;
						}
					}
				}else if(j == 1){
					row.insertCell(j).innerHTML = boardList[i]["boardNum"];	
				}else if(j == 2){
					row.insertCell(j).innerHTML = "<a href = '/board/"+ boardList[i]["boardType"] +"/"+boardList[i]["boardNum"]+"/boardView.do?pageNo=${pageNo}'>" + boardList[i]["boardTitle"] + "</a>";	
				}
			}
			
			console.log("boardList : " , boardList, "commandList", commandList);
		}
	}
</script>
</body>
</html>