package com.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.model.*;

public class MajorDao {

	
	public List<MajorKind> getMajorKinds() throws SQLException, PropertyVetoException
	{//获取所有专业大类
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
    	String sql="select * from majorKind";
    	
    	
    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
    	List<MajorKind> kindList=new ArrayList<MajorKind>();
    	System.out.println("查询专业大类"); 
    	while(rs.next())
    	{
    		MajorKind mk=new MajorKind();
    		mk.setId(rs.getInt("id"));
    		mk.setName(rs.getString("name"));
    		kindList.add(mk);
    		
    	}
    	
    	    ConnectionManager.close(conn, rs, ptmt);
    	    return kindList;	
	}
	
	public HashMap<String,ArrayList> getClassMajor() throws SQLException, PropertyVetoException
	{//获取专业大类及下属专业
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		System.out.println("查找所有专业大类：");
		String sql="select A.name,B.majorName from majorKind A, major B where A.id=B.kindId ;";
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		
		HashMap<String,ArrayList> classMajor=new HashMap<String,ArrayList>();
		try {
			while(rs.next())
			{
				
				String classify=rs.getString("name");
				String major=rs.getString("majorName");
				
				if(classMajor.containsKey(classify))
				{
					ArrayList<String> majorList=classMajor.get(classify);
					majorList.add(major);
					classMajor.put(classify, majorList);
				}	
			
				else{
			
					ArrayList<String> majorList=new ArrayList<String>();
					majorList.add(major);
					classMajor.put(classify,majorList);
				}
			}
			
			return classMajor;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
		return null;		
	}
	public Major transMajorInfo(String majorName) throws SQLException, PropertyVetoException
	{
		//搜索专业信息
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		
		
		Major major=null;
		System.out.println("查找专业信息：");
		String sql="select * from major where majorName='"+majorName+"';";
		
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		try {
			
			if(rs.next())
			{
			
				major=new Major();				
				major.setMajorId(rs.getString("majorId"));
				major.setMajorName(rs.getString("majorName"));
				major.setMajorDiscription(rs.getString("majorDiscription"));
				major.setCoreCourses(rs.getString("coreCourses"));
				major.setEmployment(rs.getString("employment"));
				major.setRank(rs.getString("rank"));
				major.setKindId(rs.getInt("kindId"));	
				System.out.println(major.toString());
				return major;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
		
		return null;
		
	}
	
	
}
