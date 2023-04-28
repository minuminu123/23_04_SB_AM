package com.KoreaIT.smw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.smw.demo.service.UsrReactionService;
import com.KoreaIT.smw.demo.util.Ut;
import com.KoreaIT.smw.demo.vo.ReactionPoint;
import com.KoreaIT.smw.demo.vo.ResultData;
import com.KoreaIT.smw.demo.vo.Rq;

@Controller
public class UsrReactionController {
	
	@Autowired
	private UsrReactionService usrReactionService;
	@Autowired
	private Rq rq;
	@RequestMapping("/usr/point/doIncreasePoint")
	@ResponseBody
	public String doIncreasePoint(int id, int point, String replaceUri) {
		
		//memberId=rq.getLiginedMemberId, relId=id 받아야됨
		
		ReactionPoint RP = usrReactionService.getArticlePoint(id);
		if(point > 0) {
			ResultData increasePointRd = usrReactionService.increasePointCount(id, point, RP.getRelId());
		}
		ResultData increasePointRd = usrReactionService.decreasePointCount(id, point, RP.getRelId());
		

		if (increasePointRd.isFail()) {
			return rq.jsHitoryBack("F-2", "내용을 입력해주세요");
		}
		
		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}
		ResultData rd = ResultData.newData(increasePointRd, "goodPointCount", usrReactionService.getArticlePointCount(id));

		return rq.jsReplace(Ut.f("%d번 글이 생성되었습니다", id), replaceUri);


		
	}
}
