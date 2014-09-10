package com.gege.cms.backend.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.gege.cms.backend.dao.ArticleDao;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.vo.PagerVO;
import com.gege.cms.utils.BeanFactory;
import com.gege.cms.utils.PropertiesBeanFactory;

import junit.framework.TestCase;

public class ArticleDaoTest extends TestCase {
	
	BeanFactory factory = new PropertiesBeanFactory("beans.properties");
	ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
	

	public void testAddArticle(){
		
		Random r = new Random();
		
		Article a = new Article();
		a.setTitle("测试标题"+r.nextInt(99999));
		a.setContent("testcontent"+r.nextInt(99999));
		a.setHeadline(true);
		a.setType("creater");
		
		articleDao.addArticle(a);
		System.out.println(articleDao);
	}
	
	//测试吧文章放到某个频道下面
	public void testAddArticle01(){
		
		Random r = new Random();
		
		Article a = new Article();
		a.setTitle("测试标题"+r.nextInt(99999));
		a.setContent("testcontent"+r.nextInt(99999));
		a.setHeadline(true);
		a.setType("creater");
		
		//设置文章属于哪些频道
		Set channels = new HashSet();
		Channel c = new Channel();
		c.setId(1);
		channels.add(c);
		a.setChannels(channels);
		
		articleDao.addArticle(a);
		
	}
	
	
	//测试吧文章放到某些频道下面
		public void testAddArticle02(){
			
			Random r = new Random();
			
			Article a = new Article();
			a.setTitle("测试标题"+r.nextInt(99999));
			a.setContent("testcontent"+r.nextInt(99999));
			a.setHeadline(true);
			a.setType("creater");
			
			//设置文章属于哪些频道
			Set channels = new HashSet();
			Channel c = new Channel();
			c.setId(1);
			channels.add(c);
			
			Channel c2 = new Channel();
			c2.setId(5);
			channels.add(c2);
			a.setChannels(channels);
			articleDao.addArticle(a);
		}
		
		public void testDelArticle(){
			articleDao.delArticles(new String[]{"1","2","3","4","11","12","13","14"});
		}
		
		public Article testFindArticleById() {
			Article a = articleDao.findArticleById(13);
			System.out.println(a.getId()+","+a.getTitle()+","+a.getContent()+","+a.getChannels());
			return a;
		}
		
//		public void testSearchArticlesByTitle(){
//			PagerVO pv = articleDao.findArticles((String)null,0, 5);
//			System.out.println("总记录数："+pv.getTotal());
//			List datas = pv.getDatas();
//			for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
//				Article a = (Article) iterator.next();
//				System.out.println(a.getId()+","+a.getTitle());
//			}
//			
//			System.out.println(articleDao);
//		}
		
//		public void testSearchArticlesByChannel01(){
//			
//			Channel c = new Channel();
//			c.setId(1);
//			PagerVO pv = articleDao.findArticles(c,  5);
//			System.out.println("总记录数："+pv.getTotal());
//			List datas = pv.getDatas();
//			for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
//				Article a = (Article) iterator.next();
//				System.out.println(a.getId()+","+a.getTitle());
//			}
//		}
//		
//		public void testSearchArticlesByChannel02(){
//			PagerVO pv = articleDao.findArticles((Channel)null, 0, 5);
//			System.out.println("总记录数："+pv.getTotal());
//			List datas = pv.getDatas();
//			for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
//				Article a = (Article) iterator.next();
//				System.out.println(a.getId()+","+a.getTitle());
//			}
//		}
}
