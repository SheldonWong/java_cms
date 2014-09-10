package com.gege.cms.backend.view;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gege.cms.SystemContext;
import com.gege.cms.backend.dao.ArticleDao;
import com.gege.cms.backend.dao.ChannelDao;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Attachment;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.vo.PagerVO;

import com.gege.cms.utils.RequestUtil;


public class ArticleServlet extends BaseServlet {
	
	private ArticleDao articleDao;
	private ChannelDao channelDao;
	
	//�������ִ�в�ѯ����
    public void execute(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    	//�ӽ����ȡtitle����
		String title = request.getParameter("title");
		//��ѯ�����б�
		PagerVO pv = articleDao.findArticles(title);
		request.setAttribute("pv", pv);
		//forward��article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
		
    }
    
    //������������µĽ���
  	public void addInput(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {

  		//������е�Ƶ���б�
  		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
  		PagerVO pv = channelDao.findChannels();
  		request.setAttribute("channels", pv.getDatas());
  		
  		// forward���������ҳ��
  		request.getRequestDispatcher("/backend/article/add_article.jsp").forward(
  				request, response);
  	}
    
    //�������ִ����ӹ���
    public void add(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    
    	Article a = (Article)RequestUtil.copyParam(Article.class, request);
    	Attachment[] attachments =(Attachment[]) request.getParameterMap().get("attachs");
    	
    	articleDao.addArticle(a);
    	//forward����ӳɹ���ҳ��
    	request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
	}
    
    //�������ִ��ɾ������
    public void del(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException{
    	//��ȡɾ��Ƶ��ID
		String ids[] = request.getParameterValues("id");
		
		if(ids == null){
			request.setAttribute("error", "�޷�ɾ��Ƶ����ID����Ϊ��ֵ");
			try {
				request.getRequestDispatcher("backend/common/error.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		articleDao.delArticles(ids);

		// ת���б�ҳ��
		// request.getRequestDispatcher("/backend/ArticleServlet").forward(request,
		// response);
		response.sendRedirect(request.getContextPath()+ "/backend/ArticleServlet");
		
    }
    
    //�������ִ�д򿪸��½���Ĺ���
    public void updateInput(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    	
    	//��ȡ��������ID
    			String id = request.getParameter("id");
    			//�����ݿ��ѯ��Ӧ����
    			
    			Article a = articleDao.findArticleById(Integer.parseInt(id));
    			request.setAttribute("article", a);
    			
    			//������е�Ƶ���б�
    			SystemContext.setOffset(0);
    			SystemContext.setPagesize(Integer.MAX_VALUE);
    	  		PagerVO pv = channelDao.findChannels();
    	  		request.setAttribute("channels", pv.getDatas());
    			//forward������ҳ��  
				request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
    }
    
    //�������ִ�и��¹���
    public void update(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    	
    	Article a = (Article)RequestUtil.copyParam(Article.class, request);
    	articleDao.updateArticle(a);
    	// forward�����³ɹ���ҳ��
		request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request,response);
    	
    }
	
	
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
    public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
    

}
