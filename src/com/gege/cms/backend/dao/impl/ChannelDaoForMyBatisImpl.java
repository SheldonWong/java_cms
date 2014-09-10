package com.gege.cms.backend.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gege.cms.backend.dao.ChannelDao;
import com.gege.cms.backend.model.Article;
import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.vo.PagerVO;
import com.gege.cms.utils.MyBatisUtil;

public class ChannelDaoForMyBatisImpl extends BaseDao implements ChannelDao {


	public void addChannel(Channel c) {
		add(c);
	}

	public void delChannels(String[] ids) {
		del(Channel.class,ids);
	}

	public Channel findChannelById(int channelId) {
		return (Channel)findById(Channel.class,channelId);
	}

	public PagerVO findChannels() {
		
		Map params = new HashMap();
		return findPaginated(Channel.class.getName()+".findPaginated", params);
	}

	public void updateChannel(Channel c) {
		update(c);
	}

}
