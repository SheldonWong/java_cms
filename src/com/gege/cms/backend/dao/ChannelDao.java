package com.gege.cms.backend.dao;

import com.gege.cms.backend.model.Channel;
import com.gege.cms.backend.vo.PagerVO;

public interface ChannelDao {
	public void addChannel(Channel c);
	public void delChannels(String ids[]);
	public Channel findChannelById(int channelId);
	public PagerVO findChannels();
	public void updateChannel(Channel c);


}
