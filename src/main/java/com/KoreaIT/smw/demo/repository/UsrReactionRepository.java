package com.KoreaIT.smw.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.smw.demo.vo.ReactionPoint;

@Mapper
public interface UsrReactionRepository {

	@Update("""
			<script>
				UPDATE reactionPoint
				<if test="`point` > 0">
				SET `point` = `point` + 1
				</if>
				SET `point` = `point` -1
				WHERE id = #{id}
			</script>
			""")
	public int increasePointCount(int id);

	
	@Select("""
			<script>
				SELECT `point`
				FROM reactionPoint
				WHERE id = #{id}
			</script>
			""")
	public int getArticlePointCount(int id);


	@Select("""
			SELECT *
			FROM reactionPoint
			WHERE relId = #{id}
			""")
	public ReactionPoint getArticlePoint(int id);

}
