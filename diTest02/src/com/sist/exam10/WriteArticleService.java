package com.sist.exam10;

public class WriteArticleService {
	private ArticleDAO dao;

	public void setDao(ArticleDAO dao) {
		this.dao = dao;
	}

	public void insert() {
		dao.insert();
	}
	      
}
