<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER MODIFY" />
<%@ include file="../common/head.jspf"%>
<hr />

<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
		${member.loginId }
		</div>
		<div class="btns">
				<button class="btn-text-link btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>


		</div>

</section>

<%@ include file="../common/foot.jspf"%>