package com.gege.cms.backend.dao;

import java.util.Random;

import com.gege.cms.backend.model.Admin;
import com.gege.cms.utils.PropertiesBeanFactory;

import junit.framework.TestCase;

public class AdminDaoTest extends TestCase {

	static PropertiesBeanFactory factory = new PropertiesBeanFactory("beans.properties");
	
	public void testAddAdmin() {
		AdminDao adminDao = (AdminDao)factory.getBean("adminDao");
		
		Admin a = new Admin();
		a.setUsername("addtest"+new Random().nextInt(9999));
		a.setPassword("addtest"+new Random().nextInt(9999));
		
		adminDao.addAdmin(a);
	}

	public void testFindAdminByUsername() {
		AdminDao adminDao = (AdminDao)factory.getBean("adminDao");
		
		Admin a = adminDao.findAdminByUsername("admin");
		
		System.out.println(a.getId()+","+a.getUsername()+","+a.getPassword());
	}

}
