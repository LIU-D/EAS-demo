package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***********************************************************



closeAll();
Connection getCon();
executeQuery(String preparedSql, String[] param);
executeUpdate(String preparedSql, String[] param);




***********************************************************/
public class DBC {
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String dbname = "web_course";
	String user = "root";
	String pwd = "741258o";
	
	public Connection getCon()
			 throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
       try{
    	   String DRIVER = "com.mysql.jdbc.Driver";
     
    	   String URL = "jdbc:mysql://localhost:3306/" + dbname + "?user=" + user + "&password=" + pwd
               + "&useUnicode=true&characterEncoding=utf8";
    	   Class.forName(DRIVER).newInstance();
    	   conn = DriverManager.getConnection(URL);  
    	   System.out.println("数据库连接成功\n");
       } catch(Exception e) {
    	   System.err.println("数据库连接失败\n" + e.getMessage());
       }
       
       return conn;
       
	}
	 
	public void closeAll() {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	
	public ResultSet executeQuery(String preparedSql, String[] param) {
		try {
          pstmt = conn.prepareStatement(preparedSql);
          if (param != null) {
              for (int i = 0; i < param.length; i++) {
                  pstmt.setString(i + 1, param[i]);
              }
          }
          rs = pstmt.executeQuery();
		} catch (SQLException e) {
          e.printStackTrace();
		}
      	
		return rs;   
	}
 
	public int executeUpdate(String preparedSql, String[] param) {
      int num = 0;
      try {
          pstmt = conn.prepareStatement(preparedSql);
          if (param != null) {
              for (int i = 0; i < param.length; i++) {
                  pstmt.setString(i + 1, param[i]);
              }
          }
          num = pstmt.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return num;
  }


}
