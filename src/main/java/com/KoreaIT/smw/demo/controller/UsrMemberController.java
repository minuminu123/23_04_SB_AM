package com.KoreaIT.smw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.smw.demo.service.MemberService;
import com.KoreaIT.smw.demo.util.Ut;
import com.KoreaIT.smw.demo.vo.Member;
import com.KoreaIT.smw.demo.vo.ResultData;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("S-1", Ut.f("아이디를 입력해주세요."), null);
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("S-1", Ut.f("비밀번호를 입력해주세요."), null);
		}
		if (Ut.empty(name)) {
			return ResultData.from("S-1", Ut.f("이름을 입력해주세요."), null);
		}
		if (Ut.empty(nickname)) {
			return ResultData.from("S-1", Ut.f("닉네임을 입력해주세요."), null);
		}
		if (Ut.empty(cellphoneNum)) {
			return ResultData.from("S-1", Ut.f("전화번호를 입력해주세요."), null);
		}
		if (Ut.empty(email)) {
			return ResultData.from("S-1", Ut.f("이메일을 입력해주세요."), null);
		}

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (id == -1) {
			return ResultData.from("S-1", Ut.f("아이디가 중복됩니다."), null);
		}

		if (id == -2) {
			return ResultData.from("S-1", Ut.f("이름과 이메일이 중복됩니다."), null);		
			}
		
		Member member = memberService.getMemberById(id);

		return ResultData.from("S-1", Ut.f("회원가입이 완료되었습니다."), member);
	}

}