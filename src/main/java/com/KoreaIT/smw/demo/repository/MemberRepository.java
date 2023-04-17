package com.KoreaIT.smw.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class MemberRepository {

//	@Insert()
	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		
	}

}
