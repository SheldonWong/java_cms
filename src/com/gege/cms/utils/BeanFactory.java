package com.gege.cms.utils;

import com.gege.cms.backend.dao.ArticleDao;
import com.gege.cms.backend.dao.ChannelDao;

public interface BeanFactory {

	public Object getBean(String name);
	
}
