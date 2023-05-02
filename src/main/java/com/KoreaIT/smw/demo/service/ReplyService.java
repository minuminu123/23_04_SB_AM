package com.KoreaIT.smw.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.smw.demo.repository.ReplyRepository;
import com.KoreaIT.smw.demo.util.Ut;
import com.KoreaIT.smw.demo.vo.Article;
import com.KoreaIT.smw.demo.vo.Reply;
import com.KoreaIT.smw.demo.vo.ResultData;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public ResultData<Integer> writeReply(String relTypeCode, int actorId, int id, String body) {
		
		replyRepository.writeReply(relTypeCode, actorId, id, body);
		
		return ResultData.from("S-1", Ut.f("댓글 생성 완료"));
	}

	public List<Reply> getReplys(int id) {
		return replyRepository.getReply(id);
	}

	
	
}
