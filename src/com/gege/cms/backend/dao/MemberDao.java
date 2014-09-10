package com.gege.cms.backend.dao;

import com.gege.cms.backend.model.Member;
import com.gege.cms.backend.vo.PagerVO;


public interface MemberDao {

	public void addMember(Member member);
	public void delMembers(String[] ids);
	public void updateMember(Member member);
	public void updatePassword(int memberId,String oldPass,String newPass);
	public Member findMemberById(int id);
	public Member findMemberByNickname(String nickname);
	public PagerVO findAllMembers();
}
