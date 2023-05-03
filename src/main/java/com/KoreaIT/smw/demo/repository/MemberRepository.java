package com.KoreaIT.smw.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.smw.demo.vo.Member;

@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			set regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("""
			SELECT *
			FROM `member`
			WHERE id = #{id}
			""")
	Member getMemberById(int id);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	int getLastInsertId();

	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	Member getMemberByLoginId(String loginId);
	
	
	@Select("""
			SELECT *
			FROM `member`
			WHERE name = #{name} AND
			email = #{email}
			""")
	Member getMemberByNameAndEmail(String name, String email);

	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId} AND
			loginPw = #{loginPw}
			""")
	Member getMember(String loginId, String loginPw);

	@Update("""
			<script>
			UPDATE `member`
			<set>
				<if test="loginId != null and loginId != ''">loginId = #{loginId},</if>
				<if test="loginPw != null and loginPw != ''">`loginPw` = #{loginPw},</if>
				updateDate= NOW()
			</set>
			WHERE id = #{memberId}
			</script>
			""")
	void modifyMember(int memberId, String loginId, String loginPw);


}