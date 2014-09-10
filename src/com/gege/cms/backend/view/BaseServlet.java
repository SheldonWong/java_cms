package com.gege.cms.backend.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gege.cms.SystemContext;
import com.gege.cms.backend.model.Member;
import com.gege.cms.utils.BeanFactory;

public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//System.out.println(this);
		//��ȡ����
		BeanFactory factory = (BeanFactory)getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
		//System.out.println(this);
		//���÷��䣬����this��������ص�setter����
		Method[] methods = this.getClass().getMethods();
		for(Method m:methods){
			if(m.getName().startsWith("set")){
				String propertyName = m.getName().substring(3);
				StringBuffer sb = new StringBuffer(propertyName);
				sb.replace(0, 1,( propertyName.charAt(0)+"").toLowerCase());
				propertyName = sb.toString();
				//Լ����setter�����������ĵ��������������ļ���Ӧ������һ��
				Object bean = factory.getBean(propertyName);
				
				try {
					//����������ע�뵽�ͻ���
					m.invoke(this, bean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			}
		}
		
        try{
		//ȡ����ҳ���������õ�ThreadLocal��
			SystemContext.setOffset(getOffset(request));
			SystemContext.setPagesize(getPagesize(request));
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){ //������ϴ��ļ�
				request = new MultipartRequestWrapper(request);
			}
			
			//ִ�и���ְ�𣬸�������ʽGET/POST����GET/POST����
			super.service(request, response);
        }finally{
        	SystemContext.removeOffset();
        	SystemContext.removePagesize();
        	
        }
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ��������
		String method = request.getParameter("method");
		//����ͻ��˲�����method��������Ĭ�ϵ���execute()����
		if(method == null || method.trim().equals("")){
			execute(request,response);
		}else{
			//����method������ȡ��Ӧ����
				try{
				Method m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				m.invoke(this,request,response);
				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	}
	

	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//ʲôҲ������
		}
	
	protected int getOffset(HttpServletRequest request){
		int offset = 0;
		// ϣ����request�л��offset����
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}
		return offset;
	}
	
	protected int getPagesize(HttpServletRequest request){
		int pagesize = 5;

		// �����request���ݹ�����pagesize��������ô����Ҫ����http session�е�pagesize��ֵ
		if (request.getParameter("pagesize") != null) {
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize")));
		}

		// ϣ����http session�л��pagesize��ֵ�����û�У�������ȱʡֵΪ5
		Integer ps = (Integer) request.getSession().getAttribute("pagesize");
		if (ps == null) {
			request.getSession().setAttribute("pagesize", pagesize);
		} else {
			pagesize = ps;
		}
		return pagesize;
	}
	
	protected Member loginMember(HttpServletRequest request){
		return (Member)request.getSession().getAttribute("LOGIN_MEMBER");
	}

}
