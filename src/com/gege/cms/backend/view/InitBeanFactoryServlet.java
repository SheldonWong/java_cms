package com.gege.cms.backend.view;


import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.gege.cms.utils.BeanFactory;
import com.gege.cms.utils.ChannelSetConverter;
import com.gege.cms.utils.PropertiesBeanFactory;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class InitBeanFactoryServlet extends HttpServlet {
	
	public static final String INIT_FACTORY_NAME = "_my_bean_factory";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		BeanFactory factory = null;
		String configLocation = config.getInitParameter("configLocation");
		if(configLocation == null){
			factory = new PropertiesBeanFactory();
		}else{
			factory = new PropertiesBeanFactory(configLocation);
		}
		System.out.println("初始化BeanFactory");
		//如果重写init(ServletConfig)方法，必须通过config.getServeltConfig方法来获取ServletContext对象
		
		config.getServletContext().setAttribute(INIT_FACTORY_NAME, factory);
		
		//初始化BeanUtils的转换器，全局注册，可以全局使用
		ConvertUtils.register(new ChannelSetConverter(),Set.class);
	}

	
	

}
