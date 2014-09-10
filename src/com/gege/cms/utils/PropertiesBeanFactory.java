package com.gege.cms.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.gege.cms.backend.dao.ArticleDao;
import com.gege.cms.backend.dao.ChannelDao;

public class PropertiesBeanFactory implements BeanFactory {
	
	Properties pros = new Properties();
	Map beans = new HashMap();
	
	public PropertiesBeanFactory(){
		this("beans.properties");
	}
	
	public PropertiesBeanFactory(String configurationFile) {
		//读取配置文件，得到具体DAO的实现类名
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configurationFile));
			
			Set set = pros.entrySet();
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String key = (String)entry.getKey();
				String className = (String)entry.getValue();
				Class clz = Class.forName(className);
				Object bean = clz.newInstance();
				beans.put(key, bean);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public Object getBean(String name){
		return beans.get(name);
	}
	

}
