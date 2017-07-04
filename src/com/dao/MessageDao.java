package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Message;

public class MessageDao {

	//Ìí¼ÓÍ¨Öª
	public static boolean addMessage(Message m){
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
    	m.setContext(UserDao.changeSpecialSymbols(m.getContext()));
        String sql="insert into message(id,context,time,isRead) values('"+m.getId()+"','"+m.getContext()+"','"+m.getTime()+"',0)";   
        System.out.println(sql);
        try {
          synchronized(MessageDao.class){
        	ptmt = conn.prepareStatement(sql);
        	boolean result=ConnectionManager.excuteIUD(sql, conn, ptmt);
            return result;
          }
        }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
	}
}
