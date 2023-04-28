package com.KoreaIT.smw.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.smw.demo.repository.UsrReactionRepository;
import com.KoreaIT.smw.demo.vo.ReactionPoint;
import com.KoreaIT.smw.demo.vo.ResultData;

@Service
public class UsrReactionService {
	
	@Autowired
	private UsrReactionRepository usrReactionRepository;

	public UsrReactionService(UsrReactionRepository usrReactionRepository) {
		this.usrReactionRepository = usrReactionRepository;
	}
	
	public ResultData increasePointCount(int id, int point, int relId) {

		int affectedRow = usrReactionRepository.increasePointCount(id);

		if (affectedRow == 0) {
			return ResultData.from("F-1", "문제 발생", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", "좋아요 증가", "affectedRowRd", affectedRow);
	}

	public int getArticlePointCount(int id) {
		
		return usrReactionRepository.getArticlePointCount(id);

	}

	public ResultData decreasePointCount(int id, int point, int relId) {
		int affectedRow = usrReactionRepository.increasePointCount(id);

		if (affectedRow == 0) {
			return ResultData.from("F-1", "문제 발생", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", "좋아요 증가", "affectedRowRd", affectedRow);
	}

	public ReactionPoint getArticlePoint(int id) {
		return usrReactionRepository.getArticlePoint(id);
	}
}
