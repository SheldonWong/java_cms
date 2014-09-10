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
	
	//这个方法执行查询功能
    public void execute(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    	//从界面获取title参数
		String title = request.getParameter("title");
		//查询文章列表
		PagerVO pv = articleDao.findArticles(title);
		request.setAttribute("pv", pv);
		//forward到article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
		
    }
    
    //用来打开添加文章的界面
  	public void addInput(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {

  		//查出所有的频道列表
  		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
  		PagerVO pv = channelDao.findChannels();
  		request.setAttribute("channels", pv.getDatas());
  		
  		// forward到添加文章页面
  		request.getRequestDispatcher("/backend/article/add_article.jsp").forward(
  				request, response);
  	}
    
    //这个方法执行添加功能
    public void add(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    
    	Article a = (Article)RequestUtil.copyParam(Article.class, request);
    	Attachment[] attachments =(Attachment[]) request.getParameterMap().get("attachs");
    	
    	articleDao.addArticle(a);
    	//forward到添加成功的页面
    	request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
	}
    
    //这个方法执行删除功能
    public void del(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException{
    	//获取删除频道ID
		String ids[] = request.getParameterValues("id");
		
		if(ids == null){
			request.setAttribute("error", "无法删除频道，ID不能为空值");
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

		// 转向列表页面
		// request.getRequestDispatcher("/backend/ArticleServlet").forward(request,
		// response);
		response.sendRedirect(request.getContextPath()+ "/backend/ArticleServlet");
		
    }
    
    //这个方法执行打开更新界面的功能
    public void updateInput(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    	
    	//获取更新文章ID
    			String id = request.getParameter("id");
    			//从数据库查询相应文章
    			
    			Article a = articleDao.findArticleById(Integer.parseInt(id));
    			request.setAttribute("article", a);
    			
    			//查出所有的频道列表
    			SystemContext.setOffset(0);
    			SystemContext.setPagesize(Integer.MAX_VALUE);
    	  		PagerVO pv = channelDao.findChannels();
    	  		request.setAttribute("channels", pv.getDatas());
    			//forward到更新页面  
				request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
    }
    
    //这个方法执行更新功能
    public void update(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException{
    	
    	Article a = (Article)RequestUtil.copyParam(Article.class, request);
    	articleDao.updateArticle(a);
    	// forward到更新成功的页面
		request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request,response);
    	
    }
	
	
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
    public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}
    

}
