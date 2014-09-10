package com.gege.cms.backend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gege.cms.utils.DBUtil;


public class FindArticlesTest {
	public static void main(String args[]){
		String sql = "select * from t_article limit 5,5";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				String title=rs.getString("title");
				String content =rs.getString("content");
				Date createTime = rs.getTimestamp("createtime");
				System.out.println(title+"||"+content+"||"+format.format(createTime));
			}

		}catch (SQLException e) {
			e.printStackTrace();
			//DBUtil.rollback(conn);
		} finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
	}

}
