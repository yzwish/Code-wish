package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.model.Activity;

public class ActivityDao {

	//获取活动消息
	public static Activity getActInfo(String activityId) throws ParseException{
		Activity a=new Activity();
		String sql="select activityId,title,info,realName,activity.id as id,startTime,duration,leastNumOfEnroll,activityStatus,numOfEnroll "
    			+ "from activity,teacher where teacher.id=activity.id AND activityId="+activityId;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {
			synchronized(ActivityDao.class){
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	if(rs.next()){
		    		a.setActivityId(rs.getInt("activityId"));
		    		a.setTitle(rs.getString("title"));
		    		a.setActivityStatus(rs.getInt("activityStatus"));
		    		a.setDuration(rs.getString("duration"));
		    		a.setId(rs.getString("id"));
		    		a.setInfo(rs.getString("info"));
		    		a.setLeastNumOfEnroll(rs.getInt("leastNumOfEnroll"));
		    		a.setNumOfEnroll(rs.getInt("numOfEnroll"));
		    		a.setName(rs.getString("realName"));
		    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		Date date = sdf.parse(rs.getString("startTime"));
		    		String st=sdf.format(date);
		    		a.setStartTime(st);
		    	}
			}				   		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a=null;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
		return a;
	}
	
	//获取活动列表
	public static ArrayList<Activity> getActList(String startTime,int duration,int activityStatus,int choice) throws ParseException{
		ArrayList<Activity> al=new ArrayList<Activity>();
    	String sql="select activityId,title,info,realName,activity.id as id,startTime,duration,leastNumOfEnroll,activityStatus,numOfEnroll "
    			+ "from activity,teacher where teacher.id=activity.id";
    	String str=null;
    	if(choice!=0){
    		if(!startTime.equals("0")){
    			str=" AND startTime='"+startTime+"'";
    			sql=togeStr(sql,str);
        	}
    		if(duration!=0){
    			switch(duration){
    			case 1:
    				str=" AND duration<3";
    				break;
    			case 2:
    				str=" AND duration>3 AND duration<24";
    				break;
    			case 3:
    				str=" AND duration>24";
    				break;
    			}
    			sql=togeStr(sql,str);
    		}
    		if(activityStatus!=0){
    			str=" AND activityStatus="+activityStatus;
    			sql=togeStr(sql,str);
    		}
    	}
    	System.out.println(sql);
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {
			synchronized(ActivityDao.class){
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	while(rs.next()){
		    		Activity a=new Activity();
		    		a.setActivityId(rs.getInt("activityId"));
		    		a.setTitle(rs.getString("title"));
		    		a.setActivityStatus(rs.getInt("activityStatus"));
		    		a.setDuration(rs.getString("duration"));
		    		a.setId(rs.getString("id"));
		    		a.setInfo(rs.getString("info"));
		    		a.setLeastNumOfEnroll(rs.getInt("leastNumOfEnroll"));
		    		a.setNumOfEnroll(rs.getInt("numOfEnroll"));
		    		a.setName(rs.getString("realName"));
		    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		Date date = sdf.parse(rs.getString("startTime"));
		    		String st=sdf.format(date);
		    		a.setStartTime(st);
		    		al.add(a);
		    	}
				return al;
			}				   		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
	}
	
	//添加活动
	public static boolean addActivity(Activity act){
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
    	act.setTitle(UserDao.changeSpecialSymbols(act.getTitle()));
    	act.setInfo(UserDao.changeSpecialSymbols(act.getInfo()));
        String sql="insert into activity(title,info,id,startTime,duration,leastNumOfEnroll,activityStatus,numOfEnroll) "
        		+ "values('"+act.getTitle()+"','"+act.getInfo()+"','"+act.getId()+"','"+act.getStartTime()+"',"
        				+ ""+act.getDuration()+","+act.getLeastNumOfEnroll()+",1,0)";   
        System.out.println(sql);
        try {
          synchronized(ActivityDao.class){
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
	
	//取消活动
	public static boolean cancelActivity(String activityId,String reason){
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
        String sql="update activity set activityStatus=4,cancelReason='"+reason+"' where activityId="+activityId;   
        System.out.println(sql);
        try {
          synchronized(ActivityDao.class){
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
	
	
	//**字符串连接**//
	private static String togeStr(String s1,String s2){
		return s1+s2;
	}
}
