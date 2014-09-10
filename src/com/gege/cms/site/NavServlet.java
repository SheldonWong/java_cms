package com.gege.cms.site;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gege.cms.SystemContext;
import com.gege.cms.backend.dao.ArticleDao;
import com.gege.cms.backend.dao.ChannelDao;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.view.BaseServlet;

public class NavServlet extends BaseServlet {
	
	private ChannelDao channelDao;
	private ArticleDao articleDao;
	
	
	//************************��������**************************
	//����Ƶ���б�
	public void channelList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		//TODO ����Ƶ����ID����ѯ��Ƶ������������б����ݵ�JSP
		Channel c = new Channel();
		c.setId(Integer.parseInt(channelId));
		request.setAttribute("c1", articleDao.findArticles(c, 20));		
		
		request.getRequestDispatcher("/channel.jsp").forward(request, response);
	}
	
	//************************��ҳ����**************************
	//��ѯĳ��Ƶ������������б���ʾ����ҳ����
	public void indexChannelArticleList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		//TODO ����Ƶ����ID����ѯ��Ƶ������������б����ݵ�JSP
		Channel c = new Channel();
		c.setId(Integer.parseInt(channelId));
		request.setAttribute("articles", articleDao.findArticles(c, 10));		
		
		request.getRequestDispatcher("portlet/index_channel_article_list.jsp").include(request, response);
	}
	
	//��ѯƵ���б�
	public void navList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ѯ����Ƶ���б�
		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		request.setAttribute("channels", channelDao.findChannels().getDatas());	
		
		request.getRequestDispatcher("portlet/channel_list.jsp").include(request, response);
	}
	
	//��ѯ��ҳͷ��
	public void headline(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//��ѯ��ҳheadline��2��
		request.setAttribute("headline",articleDao.findHeadline(2));
		
		request.getRequestDispatcher("portlet/headline.jsp").include(request, response);
	}
	//��ѯ�Ƽ�����
	public void recommend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ѯ�Ƽ����£�10��
		request.setAttribute("recommend",articleDao.findRecommend(10));	
		
		request.getRequestDispatcher("portlet/recommend.jsp").include(request, response);
	}
	
	//��ѯ���·����10ƪ����
	public void latest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String channelId = request.getParameter("channelId");
		
		Channel c = null;
		if(channelId != null){
			c = new Channel();
			c.setId(Integer.parseInt(channelId));
		}
		request.setAttribute("latest", articleDao.findArticles(c,10));
		
		request.getRequestDispatcher("portlet/latest.jsp").include(request, response);
	}
	//Ƶ����������
	public void channelIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String channelId = request.getParameter("channelId");
		
		Channel c = null;
		if(channelId != null){
			c = channelDao.findChannelById(Integer.parseInt(channelId));
			request.setAttribute("channel", c);
		}
		
		//��ѯ���·��������
		request.setAttribute("pv", articleDao.findArticles(c));	
		
		request.getRequestDispatcher("/portlet/channel_index.jsp").include(request, response);
	}
	
	//��ѯ����ĳƪ������ص���������
		public void keywords(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			String articleId = request.getParameter("articleId");
			
			Article a = articleDao.findArticleById(Integer.parseInt(articleId));
			
			request.setAttribute("pv", articleDao.findArticlesByKeyword(a.getKeyword()));
			
			request.getRequestDispatcher("/portlet/keywords.jsp").include(request, response);
		}
	
	//��ҳ��ѯ���б��Ƽ��������б�
		public void recommendIndex(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			//��ѯ���·��������
			request.setAttribute("pv", articleDao.findRecommend());	
			
			request.getRequestDispatcher("/portlet/recommend_index.jsp").include(request, response);
		}	
		
		//�������µ�ID����ѯ��ĳƪ������
		public void articleDetail(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			String articleId = request.getParameter("articleId");
			
			request.setAttribute("article",
					articleDao.findArticleById(Integer.parseInt(articleId)));
			
			request.getRequestDispatcher("/portlet/article_detail.jsp").include(request, response);
		}	
	
		
		//�������µ������¼
		public void click(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			String articleId = request.getParameter("articleId");
			
			//���ȣ���ServletContext��ȡ��һ��Map
			Map visitors = (Map)getServletContext().getAttribute("visitors");
			if(visitors == null){
				visitors = new ConcurrentHashMap();
				getServletContext().setAttribute("visitors", visitors);
			}
			
			//�õ��ͻ���IP��ַ
			String clickIp = request.getRemoteAddr();
			
			String key = articleId+"_"+clickIp; //������ID���û�IPΪ��
			
			Date lastVisitTime = (Date)visitors.get(key);
			Article a = articleDao.findArticleById(Integer.parseInt(articleId));
			int clickNumber = a.getClickNumber(); //�ɵĵ����
			/**
			 * û�з��ʼ�¼�������һ�η�����һ��Сʱ֮ǰ�����ٴμ�¼������
			 * ���������ٴμ�¼������
			 */
			if(lastVisitTime == null || !withinOneHour(lastVisitTime)){
				//���µ����
				clickNumber = articleDao.updateClickNumber(Integer.parseInt(articleId));
				visitors.put(key, new Date());
			}

			
			response.setContentType("text/javascript");
			response.getWriter().println("document.write('"+clickNumber+"')");
		}
		
		private boolean withinOneHour(Date lastVisitTime){
			//���ڵ�ʱ��
			Calendar now = Calendar.getInstance();
			
			//�ϴη���ʱ��
			Calendar last = Calendar.getInstance();
			last.setTime(lastVisitTime);
			
			last.add(Calendar.HOUR_OF_DAY, 1);
			
			if(last.before(now)){
				return false;
			}
			return true;
		}
		
	public void setChannelDao(ChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	
}
