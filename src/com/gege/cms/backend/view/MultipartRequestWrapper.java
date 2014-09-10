package com.gege.cms.backend.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import com.gege.cms.backend.model.Attachment;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {
	
	private Map allParams;
	
	

	public MultipartRequestWrapper(HttpServletRequest request) {
		super(request);
		//首先判断是否multipart编码类型
		//如果是multipart编码类型，则将他取出，放到param中
		//如果是普通表单域，则将它取出，放到params中
		//如果是表单域是文件则
		//1、把文件先存储到磁盘的某个目录中
		//2、把文件的相关信息包装成Attachement[]类型
		//3、把包装好的Attachement[]类型放到allParams中
		
		try{
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(!isMultipart){
				allParams = request.getParameterMap();
			}else{
				allParams =new HashMap();
				ServletFileUpload upload = new ServletFileUpload();
				FileItemIterator iter = upload.getItemIterator(request);
				while(iter.hasNext()){
					FileItemStream item = iter.next();
					String name = item.getFieldName();
					InputStream stream = item.openStream();
					if(item.isFormField()){//如果是普通表单域
						String value = Streams.asString(stream, request.getCharacterEncoding());
						addFieldsAndValuesToMap(name,value);
					}else{
						if(stream.available() != 0){
							String filename = item.getName();
							if(filename != null ){
								filename = FilenameUtils.getName(filename);
							}
							//将上传文件的输入流输出到磁盘上
							Streams.copy(stream,new FileOutputStream(Attachment.ATTACHMENT_DIR+filename),true);
							Attachment attachment = new Attachment();
							attachment.setContentType(item.getContentType());
							attachment.setName(filename);
							attachment.setUploadTime(new Date());
							addFieldsAndValuesToMap(name,attachment);
						}
					}
				}
				
			}
		
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
	}



	private void addFieldsAndValuesToMap(String name, Attachment value) {
		Attachment[] fieldValues = (Attachment[])allParams.get(name);
		if(fieldValues == null){
			allParams.put(name,new Attachment[]{value});
		}else{
			//扩充数组 ["1","2"]=>["1","2","null"]
			fieldValues = Arrays.copyOf(fieldValues,fieldValues.length+1);
			fieldValues[fieldValues.length-1] = value;
			allParams.put(name, fieldValues);
		}
		
	}



	private void addFieldsAndValuesToMap(String name, String value) {
		String[] fieldValues = (String[])allParams.get(name);
		if(fieldValues == null){
			allParams.put(name,new String[]{value});
		}else{
			//扩充数组 ["1","2"]=>["1","2","null"]
			fieldValues = Arrays.copyOf(fieldValues,fieldValues.length+1);
			fieldValues[fieldValues.length-1] = value;
			allParams.put(name, fieldValues);
		}
		
	}
	
	@Override
	public String getParameter(String name) {
		String[] values = (String[])allParams.get(name);
		if(values != null){
			return values[0];
		}
		return null;
	}

	@Override
	public Map getParameterMap() {
		return allParams;
	}

}
