package com.KoreaIT.smw.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KoreaIT.smw.demo.vo.ResultData;
import com.KoreaIT.smw.demo.vo.Rq;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		Rq rq = (Rq) req.getAttribute("rq");

		if (!rq.isLogined()) {
//			resp.getWriter().append("<script~~~~)

			rq.printHitoryBackJs("로그인 후 이용해 주세요");
			return false;
		}
		
		if(rq.isLogined() == true) {
			rq.printHitoryBackJs("이미 로그인이 되어있습니다");
			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}