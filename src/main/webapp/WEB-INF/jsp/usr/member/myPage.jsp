<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MY PAGE" />
<%@ include file="../common/head.jspf"%>
<hr />

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<table border="1">
				<colgroup>
					<col width="200" />
				</colgroup>

				<tbody>
					<tr>
						<th>가입일</th>
						<td>${rq.loginedMember.regDate }</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${rq.loginedMember.loginId }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${rq.loginedMember.name }</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>${rq.loginedMember.nickname }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${rq.loginedMember.cellphoneNum }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${rq.loginedMember.email }</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<a href="../member/checkPw" class="btn btn-active btn-ghost">회원정보 수정</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
	</div>
</section>

<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
				<div class="table-box-type-1">
						내가 작성한 게시물들
						<table class="table table-zebra w-full">
								<colgroup>
										<col width="70" />
										<col width="140" />
										<col width="140" />
										<col width="140" />
										<col width="140" />
										<col width="140" />
										<col width="140" />
										<col width="140" />
								</colgroup>
								<thead>
										<tr>
												<th>번호</th>
												<th>날짜</th>
												<th>제목</th>
												<th>작성자</th>
												<th>조회수</th>
												<th>좋아요</th>
												<th>싫어요</th>
												<th>댓글 수</th>
										</tr>
								</thead>

								<tbody>
										<c:forEach var="article" items="${articles }">
												<tr class="hover">
														<td>
																<div class="badge">${article.id}</div>
														</td>
														<td>${article.regDate.substring(2,16)}</td>
														<td>
																<a class="hover:underline" href="../article/detail?id=${article.id}">${article.title}</a>
														</td>
														<td>${article.extra__writer}</td>
														<td>${article.hitCount}</td>
														<td>${article.goodReactionPoint}</td>
														<td>${article.badReactionPoint}</td>

												</tr>
										</c:forEach>
								</tbody>

						</table>
				</div>
				<div class="pagination flex justify-center mt-3">
						<div class="btn-group">

								<c:set var="paginationLen" value="4" />
								<c:set var="startPage" value="${page - paginationLen >= 1 ? page - paginationLen : 1}" />
								<c:set var="endPage" value="${page + paginationLen <= pagesCount ? page + paginationLen : pagesCount}" />

								<c:set var="baseUri" value="?boardId=${boardId }" />
								<c:set var="baseUri" value="${baseUri }&searchKeywordTypeCode=${searchKeywordTypeCode}" />
								<c:set var="baseUri" value="${baseUri }&searchKeyword=${searchKeyword}" />

								<c:if test="${startPage > 1 }">
										<a class="btn" href="${baseUri }&page=1">1</a>
										<button class="btn btn-disabled">...</button>
								</c:if>

								<c:forEach begin="${startPage }" end="${endPage }" var="i">
										<a class="btn ${page == i ? 'btn-active' : '' }" href="${baseUri }&page=${i}">${i }</a>
								</c:forEach>

								<c:if test="${endPage < pagesCount }">
										<button class="btn btn-disabled">...</button>
										<a class="btn" href="${baseUri }&page=${pagesCount}">${pagesCount }</a>
								</c:if>
						</div>
				</div>
		</div>
		<div class="btns">
			<button class="btn-text-link btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>


		</div>
</section>

<%@ include file="../common/foot.jspf"%>