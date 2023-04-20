package com.KoreaIT.smw.demo.controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.smw.demo.service.ArticleService;
import com.KoreaIT.smw.demo.util.Ut;
import com.KoreaIT.smw.demo.vo.Article;
import com.KoreaIT.smw.demo.vo.ResultData;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/modify")
	
	public String doModify(HttpSession httpSession, int id, String title, String body, HttpServletResponse response, Model model) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			response.getWriter().append(
					String.format("<script>alert('로그인 후 이용해주세요'); history.back();</script>"));
		}

		Article article = articleService.getArticle(id);
		if (article == null) {
			response.getWriter().append(
					String.format("<script>alert('%d번글은 존재하지 않습니다'); history.back();</script>", id));
			
		}

		else if (article.getMemberId() != loginedMemberId) {
			response.getWriter().append(
					String.format("<script>alert('%d번글에대한 권한이 없습니다'); history.back();</script>", id));
		

		}
		else {
			articleService.modifyArticle(id, title, body);
			model.addAttribute("article", article);
			response.getWriter().append(
					String.format("<script>alert('수정완료');location.replace('../article/list');</script>"));
		}

		return "usr/article/modify";

	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpSession httpSession, int id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			response.getWriter().append(
					String.format("<script>alert('로그인 후 이용해주세요'); history.back();</script>"));


		}

		Article article = articleService.getArticle(id);
		if (article == null) {
			response.getWriter().append(
					String.format("<script>alert('%d번글은 존재하지 않습니다'); history.back();</script>", id));
			

		}

		else if (article.getMemberId() != loginedMemberId) {
			response.getWriter().append(
					String.format("<script>alert('%d번글에대한 권한이 없습니다'); history.back();</script>", id));
		

		}
		else {
			articleService.deleteArticle(id);

		response.getWriter().append(
				String.format("<script>alert('삭제완료');location.replace('../article/list');</script>"));
			
		}
		return null;
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession httpSession, String title, String body) {

		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticle(id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.articles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {

		Article article = articleService.getArticle(id);
		
		model.addAttribute("article", article);

		return "usr/article/detail";
	}

}