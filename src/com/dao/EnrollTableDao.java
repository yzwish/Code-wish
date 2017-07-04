package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.model.Activity;
import com.model.EnrollTable;

public class EnrollTableDao {

	//查找关注表
	public static ArrayList<EnrollTable> getActListOfId(String id){
		ArrayList<EnrollTable> el=new ArrayList<EnrollTable>();
    	String sql="select * from enrolltable where id='"+id+"'";
    	System.out.println(sql);
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {
			synchronized(EnrollTableDao.class){
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	while(rs.next()){
		    		EnrollTable e=new EnrollTable();
		    		e.setActivityId(rs.getString("activityId"));
		    		e.setId(rs.getString("id"));
		    		e.setEnrollTime(rs.getString("enrollTime"));
		    		el.add(e);
		    	}
				return el;
			}				   		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
	}
	
	public static boolean addEnrollTable(EnrollTable et) throws ParseException{
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
    	Activity a=ActivityDao.getActInfo(et.getActivityId());
    	/*判断是否是开始前一小时
    	 * */
        String sql="insert into enrolltable values("+et.getActivityId()+",'"+et.getId()+"','"+et.getEnrollTime()+"')";   
        System.out.println(sql);
        try {
          synchronized(EnrollTableDao.class){
        	ptmt = conn.prepareStatement(sql);
        	if(ConnectionManager.excuteIUD(sql, conn, ptmt)){
        		sql="update activity set numOfEnroll=numOfEnroll+1 where activityId="+et.getActivityId();
        		boolean result=ConnectionManager.excuteIUD(sql, conn, ptmt);
            	return result;
        	}
        	else
        		return false;
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
	
	public static boolean delEnrollTable(String activityId,String id){
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
        String sql="delete from enrolltable where id='"+id+"' AND activityId="+activityId;   
        System.out.println(sql);
        try {
          synchronized(EnrollTableDao.class){
        	ptmt = conn.prepareStatement(sql);				
        	if(ConnectionManager.excuteIUD(sql, conn, ptmt)){
        		sql="update activity set numOfEnroll=numOfEnroll-1 where activityId="+activityId;
        		boolean result=ConnectionManager.excuteIUD(sql, conn, ptmt);
            	return result;
        	}
        	else
        		return false;
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
