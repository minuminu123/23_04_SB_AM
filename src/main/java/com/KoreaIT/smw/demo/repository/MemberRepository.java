package com.KoreaIT.smw.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			set regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPW},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	
	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

}
