package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.model.Activity;
import com.model.ActivityAnswer;
import com.model.ActivityQuestion;

public class ActivityQuestionDao {

	//获取问题信息
	public static ActivityQuestion getActInfo(String qId) throws ParseException{
		ActivityQuestion q=new ActivityQuestion();
		String sql="select * from activityquestion where acQuestionId="+qId;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null,rs2=null;
		try {
			synchronized(ActivityQuestionDao.class){
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	if(rs.next()){
		    		if(rs.getInt("acQeStatus")!=1){
		    			sql="select acAnContext from activityquestion,activityanswer "
			        			+ "where activityquestion.acQuestionId=activityanswer.acQuestionId AND acQuestionId="+qId;
		    			rs2=ConnectionManager.excuteSelect(sql,conn,ptmt,rs2);
		    			if(rs2.next())
		    				q.setAnswer(rs2.getString("acAnContext"));
		    			rs2.close();
		    		}
		    		q.setAcQuestionId(rs.getInt("acQuestionId"));
		    		q.setActivityId(rs.getInt("activityId"));
		    		q.setId(rs.getString("id"));
		    		q.setAcQuContext(rs.getString("acQuContext"));
		    		q.setAcQeStatus(rs.getInt("acQeStatus"));
		    		q.setAcQuTime(rs.getString("acQuTime"));
		    	}
		    	
			}				   		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			q=null;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
		return q;
	}
	
	//获取问题列表
	public static ArrayList<ActivityQuestion> getQuList(String activityId){
		ArrayList<ActivityQuestion> ql=new ArrayList<ActivityQuestion>();
    	String sql="select * from activityquestion where activityId="+activityId+ " order by acQeStatus asc,acQuTime desc";;  	
    	System.out.println(sql);
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null,rs2=null;
		try {
			synchronized(ActivityQuestionDao.class){
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	while(rs.next()){
		    		ActivityQuestion q=new ActivityQuestion();
		    		if(rs.getInt("acQeStatus")!=1){
		    			sql="select acAnContext from activityquestion,activityanswer "
			        			+ "where activityquestion.acQuestionId=activityanswer.acQuestionId AND activityquestion.activityId="+activityId;
		    			rs2=ConnectionManager.excuteSelect(sql,conn,ptmt,rs2);
		    			if(rs2.next())
		    				q.setAnswer(rs2.getString("acAnContext"));
		    			rs2.close();
		    		}
		    		q.setAcQuestionId(rs.getInt("acQuestionId"));
		    		q.setActivityId(rs.getInt("activityId"));
		    		q.setId(rs.getString("id"));
		    		q.setAcQuContext(rs.getString("acQuContext"));
		    		q.setAcQeStatus(rs.getInt("acQeStatus"));
		    		q.setAcQuTime(rs.getString("acQuTime"));		    		
		    		ql.add(q);
		    	}
				return ql;
			}				   		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
	}
	
	//添加问题
	public static boolean addQue(ActivityQuestion q){
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
    	q.setAcQuContext(UserDao.changeSpecialSymbols(q.getAcQuContext()));
        String sql="insert into activityquestion(activityId,id,acQuContext,acQuTime,acQeStatus) "
        		+ "values("+q.getActivityId()+",'"+q.getId()+"','"+q.getAcQuContext()+"','"+q.getAcQuTime()+"',1)";   
        System.out.println(sql);
        try {
          synchronized(ActivityQuestionDao.class){
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
	
	//添加回答
	public static boolean addAns(ActivityAnswer an){
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
    	an.setAcAnContext(UserDao.changeSpecialSymbols(an.getAcAnContext()));
        String sql="insert into activityanswer(activityId,acQuestionId,acAnContext,acAnTime) "
        		+ "values("+an.getActivityId()+","+an.getAcQuestionId()+",'"+an.getAcAnContext()+"','"+an.getAcAnTime()+"')";   
        System.out.println(sql);
        try {
          synchronized(ActivityQuestionDao.class){
        	ptmt = conn.prepareStatement(sql);		
        	if(ConnectionManager.excuteIUD(sql, conn, ptmt)){
        		sql="update activityquestion set acQeStatus=2 where acQuestionId="+an.getAcQuestionId();
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
