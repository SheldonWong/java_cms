package com.gege.cms.utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.Converter;

import com.gege.cms.backend.model.Channel;

public class ChannelSetConverter implements Converter {

	/**
	 * 把一个字符串或字符串数组转化为一个channelSet集合
	 */
	public Object convert(Class targetClass, Object value) {
		String[] channelIds = null;
		if(value instanceof String){
			channelIds = new String[]{(String)value};
		}
		if(value instanceof String[]){
			channelIds =(String[]) value; 
		}
		if(channelIds != null){
			Set channels = new HashSet();
			for(String channelId:channelIds){
				Channel c = new Channel();
				c.setId(Integer.parseInt(channelId));
				channels.add(c);
			}
			return channels;
		}
		
		return null;
		
	}

}
