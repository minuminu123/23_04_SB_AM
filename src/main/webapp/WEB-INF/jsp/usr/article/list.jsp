<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.code}" />
<%@ include file="../common/head.jspf"%>
<hr />
<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
				<div class="table-box-type-1">
						<table class="table table-zebra w-full">
								<colgroup>
										<col width="70" />
										<col width="140" />
										<col width="140" />
										<col width="140" />
								</colgroup>
								<thead>
										<tr>
												<th style="--tw-bg-opacity: 1; background-color: rgba(191, 219, 254, var(--tw-bg-opacity));">번호</th>
												<th style="--tw-bg-opacity: 1; background-color: rgba(191, 219, 254, var(--tw-bg-opacity));">날짜</th>
												<th style="--tw-bg-opacity: 1; background-color: rgba(191, 219, 254, var(--tw-bg-opacity));">제목</th>
												<th style="--tw-bg-opacity: 1; background-color: rgba(191, 219, 254, var(--tw-bg-opacity));">작성자</th>
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

												</tr>
										</c:forEach>
								</tbody>

						</table>
				</div>
		</div>
</section>
&nbsp;
<div class="middle">
		<c:if test="${rq.isLogined() }">
				<a class="btn-text-link btn btn-active btn-ghost a-middle" href="../article/write">게시글 쓰기</a>
		</c:if>
</div>

<%@ include file="../common/foot.jspf"%>