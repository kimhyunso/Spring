<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
		$j("#boardDelete").on("click",function(){
			const flag = confirm("정말 삭제하시겠습니까?");
			
			if (flag === true){
				$j.ajax({
				    url : "boardDeleteAction.do",
				    dataType: "json",
				    type: "POST",
				    success: function(data, textStatus, jqXHR)
				    {
						alert("삭제완료");
						alert("메세지:"+data.success);
						location.href = "/board/boardList.do?pageNo=" + data.pageNo;
				    },
				    error: function (jqXHR, textStatus, errorThrown)
				    {
				    	alert("실패");
				    }
				});
			}
		});
	});
</script>
<body>
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">	
			<a href="boardUpdate.do">수정</a>
			<a id="boardDelete">삭제</a>
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
</table>	
</body>
</html>