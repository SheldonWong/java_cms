package com.gege.cms.backend.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gege.cms.backend.dao.CommentDao;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Comment;
import com.gege.cms.backend.vo.PagerVO;
import com.gege.cms.utils.MyBatisUtil;

public class CommentDaoForMyBatisImpl extends BaseDao implements CommentDao {


	public void addComment(Comment c) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			c.setCommentTime(new Date());
			session.insert(Comment.class.getName()+".add", c);
			
			//�������µ�������
			int leaveNumber = (Integer)session.selectOne(Article.class.getName()+".selectLeaveNumber", 
					c.getArticleId());
			
			
			Article a = new Article();
			a.setId(c.getArticleId());
			a.setLeaveNumber(leaveNumber + 1);
			session.update(Article.class.getName()+".updateLeaveNumber", a);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}

	/**
	 * ɾ�����ԣ�Ҳ��Ҫͬʱ���´����Զ�Ӧ�����µ���������
	 */

	public void delComments(String[] ids) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			for(String id:ids){
				//���Ȳ�������Ե���Ϣ
				Comment c = (Comment)session.selectOne(Comment.class.getName()+".findById", Integer.parseInt(id));
				
				//���¶�Ӧ���µ�������
				int leaveNumber = (Integer)session.selectOne(Article.class.getName()+".selectLeaveNumber", 
						c.getArticleId());
				Article a = new Article();
				a.setId(c.getArticleId());
				a.setLeaveNumber(leaveNumber - 1);
				session.update(Article.class.getName()+".updateLeaveNumber", a);
				
				//ɾ�����Լ�¼
				session.delete(Comment.class.getName()+".del", Integer.parseInt(id));
			}

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
	}


	public PagerVO findAllComments() {
		Map params = new HashMap();
		return findPaginated(Comment.class.getName()+".findPaginated", params);
	}


	public Comment findCommentById(int id) {
		return (Comment)findById(Comment.class,id);
	}


	public List findCommentsByArticleId(int articleId) {
		SqlSession session = MyBatisUtil.getSession();
		try {
			
			return session.selectList(Comment.class.getName()+".findByArticleId", articleId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}


	public void updateComment(Comment c) {
		update(c);
	}

}
