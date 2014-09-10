package com.gege.cms.backend.dao;

import com.gege.cms.backend.model.Admin;

public interface AdminDao {
	public void addAdmin(Admin admin);
	public Admin findAdminByUsername(String username);
}
