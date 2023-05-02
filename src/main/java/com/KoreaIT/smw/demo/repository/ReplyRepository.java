package com.KoreaIT.smw.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.smw.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	@Insert("""
			<script>
			INSERT INTO reply
			SET regDate=NOW(),
			updateDate = NOW(),
			memberId = #{actorId},
			relTypeCode = #{relTypeCode},
			relId = #{id},
			`body` = #{body};
			</script>
			""")
	public void writeReply(String relTypeCode, int actorId, int id, String body);

	
	@Select("""
			<script>
			SELECT R.*, M.nickname AS extra__writer
			FROM reply AS R
			INNER JOIN `member` AS M
			ON R.memberId = M.id
			WHERE R.relId = #{id}
			</script>
			""")
	public List<Reply> getReply(int id);

}
