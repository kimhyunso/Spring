<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>


	<style>
		#editor{
			overflow: auto;
			height : 200px;
			border: 1px solid #CCCCCC;
			border-radius: 5px;
			padding-left:10px;
		}
	</style>
	
	<sql:query var="bbsRs" dataSource="jdbc/Capter1">
		select *, CAST(css as char(10000) character set utf8) as mycss from bbsmanager where idx = '${param.bid }'
	</sql:query>
	
	<c:forEach var="bbsConf" items="${bbsRs.rows }">
		<c:set var="readLevel">${bbsConf.rlevel}</c:set>
		<c:set var="writeLevel">${bbsConf.wlevel}</c:set>
		<c:set var="bbscss">${bbsConf.mycss }</c:set>	
		<c:set var="cssleft">${bbsConf.cssleft }</c:set>
		<c:set var="cssright">${bbsConf.cssright }</c:set>
		<c:set var="WYSIWYG">${bbsConf.wysiwyg }</c:set>
	</c:forEach>
	
	<c:if test="${empty sessionScope.sessID or sessionScope.sessLevel lt writeLevel or (sessionScope.sessRole ne 'admin' and sessionScope.sessRole ne 'dev' and sessionScope.sessID ne BBSItem.id) }">
		<script>
			alert('수정 권한이 없습니다.');
			location.href='main.jsp';
		</script>
	</c:if>

	<c:set var="heads">답변, 정보, 질문, 기타</c:set>

	<h3 class="text-primary">
			<span class="glyphicon glyphicon-edit"> </span>
			글 수정
	</h3>
	<form method="post" class="form" enctype="multipart/form-data" action="bbsUpdate">
	<input type=hidden name="uidx" value="${BBSItem.idx }">
	<input type=hidden name="bid" value="${param.bid }">
	
	
	<div class="row rowLine">
		<div class="${cssleft }">글머리</div>
		<div class="${cssright }">
			<select name="head" class="form-control">
				<option value="">=== 글머리 선택 ===</option>
				<c:forTokens items="${heads }" var="h" delims=",">
					<c:if test="${BBSItem.head eq  h}">
						<option value="${h }" selected>${h }</option>
					</c:if>
					<c:if test="${BBSItem.head ne  h}">
						<option value="${h }" >${h }</option>
					</c:if>
				</c:forTokens>
			</select>
		</div>
	</div>	
	
	
	<div class="row rowLine">
		<div class="${cssleft }">제목</div>
		<div class="${cssright }">
			<input type="text" class="form-control" value="${BBSItem.title }" name="title" placeholder="(필수)제목">
		</div>
	</div> 
	
	<c:if test="${sessionScope.sessRole eq 'admin' or sessionScope.sessRole eq 'dev' }">
		<div class="row rowLine">
			<div class="${cssleft }">알림</div>
			<div class="${cssright }">
				<c:if test="${BBSItem.nall eq 1 }">
					<input type="checkbox" checked class="myCheckbox" name="nall">전체공지     
				</c:if>
				<c:if test="${BBSItem.nall ne 1 }">
					<input type="checkbox" class="myCheckbox" name="nall">전체공지     
				</c:if>
				
				<c:if test="${BBSItem.notice eq 1 }">
					<input type="checkbox" checked class="myCheckbox" name="notice">공지 
				</c:if>
				<c:if test="${BBSItem.notice ne 1 }">
					<input type="checkbox" class="myCheckbox" name="notice">공지  
				</c:if>
			</div>
		</div>
	</c:if>
	
	
	<c:if test="${sessionScope.sessRole eq 'admin' or sessionScope.sessRole eq 'dev' }">
		<c:set var="writerReadOnly" value=""></c:set>
	</c:if>
	<c:if test="${sessionScope.sessRole ne 'admin' and sessionScope.sessRole ne 'dev' }">
		<c:set var="writerReadOnly" value="readonly"></c:set>
	</c:if>
	
	<div class="row rowLine">
		<div class="${cssleft }">작성자</div>
		<div class="${cssright }">
			<input type="text" class="form-control" name="writer" ${writerReadOnly } value="${BBSItem.writer }" placeholder="(필수)작성자">
		</div>
	</div> 
	
	<c:if test="${WYSIWYG eq 1 }">
	
	<script>
			function setHtml(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function bold(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('bold');
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function Italic(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('italic');
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function underLine(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('underline');
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function redFont(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('foreColor', false, "#FF0000");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function blueFont(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('foreColor', false, "#0000FF");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function greenFont(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('foreColor', false, "#00FF00");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function blackFont(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('foreColor', false, "#000000");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function whiteFont(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('foreColor', false, "#DDDDDD");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function redBg(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('backColor', false, "#FF0000");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function greenBg(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('backColor', false, "#00FF00");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
			function yelloBg(){
				var editor = document.getElementById('editor');
				var html = document.getElementById('html');
				var html2 = document.getElementById('html2');
				
				document.execCommand('backColor', false, "#FFFF00");
				html2.innerHTML = editor.innerHTML;
				html.innerText = editor.innerText;
			}
			
		</script>
		
		<div class="row rowLine text-center">
			<div class="col-md-12">
				<button type="button" class="btn btn-primary btn-sm" onClick="bold();"><b>B</b></button>
				<button type="button" class="btn btn-primary btn-sm" onClick="Italic()"><b>I</b></button>
				<button type="button" class="btn btn-primary btn-sm" onClick="underLine();"><b>U</b></button>
				<button type="button" class="btn btn-danger btn-sm"  onClick="redFont();"><b>R</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#0000FF; color:#FFFFFF;" onClick="blueFont();"><b>B</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#00FF00; color:#FFFFFF;" onClick="greenFont();"><b>G</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#000000; color:#FFFFFF;" onClick="blackFont();"><b>B</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#DDDDDD; color:#000000;" onClick="whiteFont();"><b>W</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#FF0000; color:#FFFFFF;" onClick="redBg();"><b>배경</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#00FF00; color:#FFFFFF;" onClick="greenBg();"><b>배경</b></button>
				<button type="button" class="btn btn-sm" style="background-color:#FFFF00; color:#000000;" onClick="yelloBg();"><b>배경</b></button>
			</div>
		</div>
		
		<div class="row rowLine">
			<div class="${cssleft }">내용</div>
			<div class="${cssright }">
				<div id="editor" contenteditable="true" onkeyup="setHtml();">${BBSItem.html2 }</div>
			</div>
		</div>
		
		</c:if>
		
		<c:if test="${WYSIWYG eq 1 }">
			<c:set var="displayMark" value="none"/>
		</c:if>
		
		<c:if test="${WYSIWYG ne 1 }">
			<c:set var="displayMark" value=""/>
		</c:if>
		
		<div class="row rowLine" style="display:${displayMark};">
			<div class="${cssleft }">내용</div>
			<div class="${cssright }">
				<textarea class="form-control" id="html" rows="10" name="html" >${BBSIteml.html}</textarea>
			</div>
		</div>
		
		<div class="row rowLine" style="display:none;">
			<div class="${cssleft }">내용</div>
			<div class="${cssright }">
				<textarea class="form-control" id="html2" rows="10" name="html2" >${BBSIteml.html2}</textarea>
			</div>
		</div> 
	
	<div class="row rowLine">
		<div class="col-md-12">
			<span class="glyphicon glyphicon-paperclip"> </span> 1 
			<c:if test="${not empty BBSItem.file1 }">
				( <input type="checkbox" name="delFile1"> 삭제 ) 
				${BBSItem.file1 }
			</c:if>
			<input type="file" class="form-control" name="upfile1" placeholder="첨부파일 1">
		</div>
	</div> 
	
	<div class="row rowLine">
		<div class="col-md-12">
			<span class="glyphicon glyphicon-paperclip"> </span> 2
			<c:if test="${not empty BBSItem.file2 }">
				( <input type="checkbox" name="delFile2"> 삭제 ) 
				${BBSItem.file2 }
			</c:if>
			<input type="file" class="form-control" name="upfile2" placeholder="첨부파일 1">
		</div>
	</div> 
	
	<div class="row rowLine text-center" style="border-bottom:none;">
		<button type="submit" class="btn btn-danger">
			<span class="glyphicon glyphicon-ok"> </span>
			변경실행
		</button> 
		<button type="button" class="btn btn-primary" onClick="location.href='main.jsp?cmd=bbsList?bid=${param.bid}'">
			<span class="glyphicon glyphicon-list"> </span> 
			목록보기
		</button>
	</div> 
	
	
	</form>

