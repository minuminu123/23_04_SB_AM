<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE MODIFY" />
<%@ include file="../common/head.jspf"%>
<hr />

<form method="post" action="doModify">
		
		
		<div>
			제목 : <input value="${article.title }" type="text" name="title"
				placeholder="제목을 입력해주세요" />
		</div>
		<div>
			내용 :
			<textarea type="text" name="body" placeholder="내용을 입력해주세요">${artcile.body }</textarea>
		</div>
		<button type="submit">수정</button>
	</form>
	<div>
		<a style="color: green" href="list">리스트로 돌아가기</a>
	</div>

<%@ include file="../common/foot.jspf"%>