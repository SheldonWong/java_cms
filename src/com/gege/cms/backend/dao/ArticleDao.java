package com.gege.cms.backend.dao;

import java.util.List;

import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.vo.PagerVO;

public interface ArticleDao {
	public void addArticle(Article a);
	public void delArticles(String ids[]);
	public Article findArticleById(int id);
	public PagerVO findArticlesByKeyword(String keyword);
	public PagerVO findArticles(String title);
	public void updateArticle(Article a);
	public List findArticles(Channel c, int max);
	public PagerVO findArticles(Channel c);
	public List findHeadline(int max);
	public List findRecommend(int max);
	public PagerVO findRecommend() ;
	public int updateClickNumber(int parseInt);
}
