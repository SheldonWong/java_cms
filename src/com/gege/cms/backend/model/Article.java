package com.gege.cms.backend.model;

import java.util.Date;
import java.util.Set;

public class Article {
	private int id;
	private String title;  //����
	private String source;  //��Դ
	private String content;  //����
	private String author;   //����
	private String keyword;   //�ؼ���
	private String intro;    //���
	private String type;      //����
	private boolean recommend;  //�Ƿ��Ƽ�
	private boolean headline;   //�Ƿ�ͷ��
	private int leaveNumber;    //������
	private int clickNumber;    //�����
	private Set<Channel> channels;   //����Ƶ��
	private int topicId;           //�������������⣬���������ĳ�����⣬���ֵΪ0
	private Date createTime;     // ����ʱ��
	private Date updateTime;   //����ʱ��
	private Date deployTime;   //����ʱ��
	private int adminId;      //��ƪ���������Ǹ�����Ա������
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isRecommend() {
		return recommend;
	}
	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	public boolean isHeadline() {
		return headline;
	}
	public void setHeadline(boolean headline) {
		this.headline = headline;
	}
	public int getLeaveNumber() {
		return leaveNumber;
	}
	public void setLeaveNumber(int leaveNumber) {
		this.leaveNumber = leaveNumber;
	}
	public int getClickNumber() {
		return clickNumber;
	}
	public void setClickNumber(int clickNumber) {
		this.clickNumber = clickNumber;
	}
	public Set<Channel> getChannels() {
		return channels;
	}
	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	
	
	
}
