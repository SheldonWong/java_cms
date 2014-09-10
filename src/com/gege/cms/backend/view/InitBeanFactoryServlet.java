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
		System.out.println("��ʼ��BeanFactory");
		//�����дinit(ServletConfig)����������ͨ��config.getServeltConfig��������ȡServletContext����
		
		config.getServletContext().setAttribute(INIT_FACTORY_NAME, factory);
		
		//��ʼ��BeanUtils��ת������ȫ��ע�ᣬ����ȫ��ʹ��
		ConvertUtils.register(new ChannelSetConverter(),Set.class);
	}

	
	

}
