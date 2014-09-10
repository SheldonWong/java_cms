package com.gege.cms.backend.dao;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.gege.cms.backend.model.Article;


import junit.framework.TestCase;

public class BeanUtilsTest extends TestCase {
	
	//≤‚ ‘String
	public void testBeanUtils01() throws Exception{
		Article  a = new Article();
		BeanUtils.copyProperty(a,"title", "≤‚ ‘±ÍÃ‚");
		System.out.println(a.getTitle());
	}
	//≤‚ ‘int
	public void testBeanUtils02() throws Exception{
		Article  a = new Article();
		BeanUtils.copyProperty(a,"id", "20");
		System.out.println(a.getId());
	}
	//≤‚ ‘boolean
	public void testBeanUtils03() throws Exception{
		Article  a = new Article();
		BeanUtils.copyProperty(a,"recommend", "true");
		System.out.println(a.isRecommend());
	}
	
	//≤‚ ‘»’∆⁄
	public void testBeanUtils04() throws Exception{
		Article  a = new Article();
		ConvertUtils.register(new DateConverter(), Date.class);
		BeanUtils.copyProperty(a,"createTime", "2012-10-12");
		System.out.println(a.getCreateTime());
	}

}
