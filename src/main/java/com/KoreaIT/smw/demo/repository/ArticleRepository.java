package com.KoreaIT.smw.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.KoreaIT.smw.demo.vo.Article;
import com.KoreaIT.smw.demo.vo.ResultData;

@Mapper
public interface ArticleRepository {

	public void writeArticle(String title, String body);

	public List<Article> getArticles();

	public Article getArticle(int id);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

	public int getLastInsertId();

}
