package com.KoreaIT.smw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.smw.demo.service.ReplyService;
import com.KoreaIT.smw.demo.util.Ut;
import com.KoreaIT.smw.demo.vo.ResultData;
import com.KoreaIT.smw.demo.vo.Rq;

@Controller
public class UsrReplyController {

	@Autowired
	private Rq rq;
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {

		if (Ut.empty(relTypeCode)) {
			return rq.jsHitoryBack("F-1", "relTypeCode 을(를) 입력해주세요");
		}
		if (Ut.empty(relId)) {
			return rq.jsHitoryBack("F-2", "relId 을(를) 입력해주세요");
		}
		if (Ut.empty(body)) {
			return rq.jsHitoryBack("F-3", "body 을(를) 입력해주세요");
		}

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = (int) writeReplyRd.getData1();

		if (Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", relId);
		}

		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}

}
