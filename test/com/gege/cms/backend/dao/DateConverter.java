package com.gege.cms.backend.dao;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public Object convert(Class targetClass, Object value) {
		/**
		 * Ҫ���ַ������͵�valueת��Ϊjava.util.Date
		 */
		if(targetClass != Date.class){
			return null;
		}
		try {
			  if(value instanceof String ){
				String v = (String)value;
				return format.parse(v);
			  }
		} catch (ParseException e) {
				e.printStackTrace();
		}
			
	
		return null;
	}

}
