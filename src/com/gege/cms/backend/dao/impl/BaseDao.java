package com.gege.cms.backend.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.gege.cms.SystemContext;
import com.gege.cms.backend.model.Admin;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.vo.PagerVO;
import com.gege.cms.utils.MyBatisUtil;

public class BaseDao {
	
	/////////////////////////////////////增加
	public void add(Object entity){
		//创建SqlSession的工厂！
		SqlSession session = MyBatisUtil.getSession();
		try {
				//插入
				session.insert(entity.getClass().getName()+".add",entity);
				//提交事务
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
			} finally{
				//关闭session
				session.close();
			}

	}
	
	/////////////////////////////////////删除
	public void del(Class entityClass,int id){
		SqlSession session = MyBatisUtil.getSession();
		try {
			session.delete(entityClass.getName()+".del", id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
		
	}
	
    public void del(Class entityClass,int[] ids){
    	SqlSession session = MyBatisUtil.getSession();
		try {
			for(int id:ids){
				session.delete(entityClass.getName()+".del", id);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally{
			session.close();
		}
		
	}
    
    public void del(Class entityClass,String[] ids){
    	SqlSession session = MyBatisUtil.getSession();
		try {
			for(String id:ids){
				session.delete(entityClass.getName()+".del", id);
			}
				//提交事务
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
			} finally{
				//关闭session
				session.close();
			}
		
	}
    
    /////////////////////////////查水表
	public Object findById(Class entityClass,int id){
		SqlSession session = MyBatisUtil.getSession();
		try {
			return session.selectOne(entityClass.getName()+".findById", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}
	
	public List findAll(Class entityClass){
		SqlSession session = MyBatisUtil.getSession();
		try {
			return session.selectList(entityClass.getName()+".findAll");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null;
	}
	
	public PagerVO findPaginated(String sqlId,Map params){
		SqlSession session = MyBatisUtil.getSession();
		List datas = null;
		int total = 0;
		try {
			//取出分页参数
			params.put("offset", SystemContext.getOffset());
			params.put("pagesize", SystemContext.getPagesize());
			
			datas = session.selectList(sqlId, params);
			total = (Integer)session.selectOne(sqlId+"-count", params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		PagerVO pv = new PagerVO();
		pv.setDatas(datas);
		pv.setTotal(total);
		return pv;
	}
	///////////////////////////////更新
	
	public void update(Object entity){
		SqlSession session = MyBatisUtil.getSession();
		try {
			//更新文章基本信息
			session.update(entity.getClass().getName()+".update", entity);
			//提交事务
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
			} finally{
				//关闭session
				session.close();
			}
		
	}
	

}
