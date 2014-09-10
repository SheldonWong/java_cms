package com.gege.cms.site;

import javax.servlet.jsp.PageContext;

import com.gege.cms.backend.dao.ArticleDao;
import com.gege.cms.backend.dao.ChannelDao;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.view.InitBeanFactoryServlet;
import com.gege.cms.utils.BeanFactory;

public class SiteFunction {
	/**
	 * 根据频道ID得到频道
	 * @param pc
	 * @param channelId
	 * @return
	 */
	public static Channel findChannelById(PageContext pc,String channelId){
		BeanFactory factory = (BeanFactory)pc.getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
		ChannelDao cd = (ChannelDao)factory.getBean("channelDao");
		return cd.findChannelById(Integer.parseInt(channelId));
	}
	
	/**
	 * 根据文章ID得到Article对象
	 * @param pc
	 * @param articleId
	 * @return
	 */
	public static Article findArticleById(PageContext pc,String articleId){
		BeanFactory factory = (BeanFactory)pc.getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
		ArticleDao ad = (ArticleDao)factory.getBean("articleDao");
		return ad.findArticleById(Integer.parseInt(articleId));
	}
	

}
