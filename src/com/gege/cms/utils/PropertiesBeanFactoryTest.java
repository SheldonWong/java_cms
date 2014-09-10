package com.gege.cms.utils;

import com.gege.cms.backend.dao.ArticleDao;

public class PropertiesBeanFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BeanFactory beanFactory = new PropertiesBeanFactory();
		System.out.println(beanFactory.getBean("articleDao"));
//		beanFactory.getChannelDao();

	}

}
