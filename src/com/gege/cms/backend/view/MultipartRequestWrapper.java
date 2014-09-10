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
		//�����ж��Ƿ�multipart��������
		//�����multipart�������ͣ�����ȡ�����ŵ�param��
		//�������ͨ��������ȡ�����ŵ�params��
		//����Ǳ������ļ���
		//1�����ļ��ȴ洢�����̵�ĳ��Ŀ¼��
		//2�����ļ��������Ϣ��װ��Attachement[]����
		//3���Ѱ�װ�õ�Attachement[]���ͷŵ�allParams��
		
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
					if(item.isFormField()){//�������ͨ����
						String value = Streams.asString(stream, request.getCharacterEncoding());
						addFieldsAndValuesToMap(name,value);
					}else{
						if(stream.available() != 0){
							String filename = item.getName();
							if(filename != null ){
								filename = FilenameUtils.getName(filename);
							}
							//���ϴ��ļ��������������������
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
			//�������� ["1","2"]=>["1","2","null"]
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
			//�������� ["1","2"]=>["1","2","null"]
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
