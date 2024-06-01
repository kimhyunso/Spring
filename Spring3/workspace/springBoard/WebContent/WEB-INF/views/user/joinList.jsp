<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
<style>
input{
	width:80px;
}
.phoneInput{
	width:50px !important; 
}
</style>
</head>
<body>
<form class="joinForm">
	<table align="center">
		<tr>
			<td align="left">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
		<tr>
			<td>
				<table border = "1">
					<tr>
						<td width="120" align="center">
							id
						</td>
						<td width="300" align="left">
							<input name="userId" type="text">
							<input type="button" id="checkBtn" value="중복확인">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							pw
						</td>
						<td width="300" align="left">
							<input type="password" name="userPw">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							pw check
						</td>
						<td width="300" align="left">
							<input type="password">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							name
						</td>
						<td width="300" align="left">
							<input type="text" name="userName">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							phone
						</td>
						<td width="300" align="left">
							<select name="userPhone1">
								<c:forEach var="list" items="${commandList}">
									<option value="${list.codeID }">${list.codeName}</option>
								</c:forEach>
							</select>
							-
							<input type="text" class="phoneInput" maxlength="4" name="userPhone2">
							-
							<input type="text" class="phoneInput" maxlength="4" name="userPhone3">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							postNo
						</td>
						<td width="300" align="left">
							<input type="text" name="userAddr1">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							address
						</td>
						<td width="300" align="left">
							<input type="text" name="userAddr2">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							company
						</td>
						<td width="300" align="left">
							<input type="text" name="userCompany">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td align="right">
				<a id="joinBtn">join</a>
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">

	const checkBtn = document.getElementById("checkBtn");
	checkBtn.addEventListener("click", validForm);
	const joinBtn = document.getElementById("joinBtn");
	joinBtn.addEventListener("click", joinAction);
	
	let checked;
	

	

	function joinAction(e){
		const $frm = $j('.joinForm :input');
		const param = $frm.serialize();
		
		/*$j.ajax({
		    url : "joinAction.do",
		    dataType: "json",
		    type: "POST",
		    data : param,
		    success: function(data, textStatus, jqXHR)
		    {
		    	
				alert("메세지:"+data.success);
				checked = data.data	
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {	
		    	alert("실패");
		    	checked = false;
		    }
		});*/
	}
	
	function validForm(data){
		const re = new RegExp('^[A-Za-z]/g');
		const $frm = $j('.joinForm :input');
		let flag = true;
		
		const param = $frm.serialize();
		$frm.toArray().forEach((data) => {
			if(re.test(data.value)){
				console.log('asdasd');
			}
			
			
			/*if (data.name == 'userId' && !re.test(data.value)){
				alert("잘 못된 ID값입니다.");
				flag = false;
				return;
			}*/
		});
		
		if (flag){
			$j.ajax({
			    url : "checkid.do",
			    dataType: "json",
			    type: "GET",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
			    	
					alert("메세지:"+data.success);
					checked = data.data;
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {	
			    	alert("실패");
			    	checked = false;
			    }
			});
		}
	}

</script>
</body>
</html>