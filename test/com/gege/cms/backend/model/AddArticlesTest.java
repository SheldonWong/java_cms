package com.gege.cms.backend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.gege.cms.utils.DBUtil;


public class AddArticlesTest {
	public static void main(String args[]){
		String sql = "insert into t_article (title,content,createtime) values (?,?,?)";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		
		try{
			for(int i=0;i<100;i++){
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "²âÊÔÎÄÕÂ"+Math.random());
			pstmt.setString(2, "²âÊÔÎÄÕÂ¡£¡£¡£");
			pstmt.setTimestamp(3,new java.sql.Timestamp(new Date().getTime()));
			
			pstmt.executeUpdate();
			
			conn.commit();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally{
			//DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
	}

}
