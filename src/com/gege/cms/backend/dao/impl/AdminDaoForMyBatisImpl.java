package com.gege.cms.backend.dao.impl;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gege.cms.backend.dao.AdminDao;
import com.gege.cms.backend.model.Admin;
import com.gege.cms.utils.MyBatisUtil;

public class AdminDaoForMyBatisImpl extends BaseDao implements AdminDao {

	public void addAdmin(Admin admin) {
		add(admin);
	}

	public Admin findAdminByUsername(String username) {
		//打开一个session
				SqlSession session = MyBatisUtil.getSession();
				Admin admin = null;
				try {
					
					admin = (Admin)session.selectOne(Admin.class.getName()+".findAdminByUsername", username);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					//关闭session
					session.close();
				}
				return admin;
	}

}
