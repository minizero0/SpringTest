package com.sist.exam06;

public class WriteArticleService {
	private ArticleDAO articleDAO;

	
	
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}


	public void insert() {
		articleDAO.insert();
	}
	
	      
}
