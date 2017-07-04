package com.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.University;
public class UniversityDao {

	
	public List<University> getUniversiyByRank() throws SQLException, PropertyVetoException
	{//升序查询大学
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
    	String sql="select * from University,province where university.provinceId=province.provinceId order by rank asc";
    	
    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
    	
    	List<University> universityRankList=new ArrayList<University>();
    	System.out.println("升序查询大学"); 
    	while(rs.next())
    	{
    		University u=new University();
    		u.setUniversityId(rs.getString("universityId"));
    		u.setUniversityName(rs.getString("universityName"));
    		u.setType(rs.getString("type"));
    		u.setProvinceId(rs.getString("provinceId"));
    		u.setRank(rs.getInt("rank"));
    		u.setIntroduction(rs.getString("introduction"));
    		u.setEnrollmentPlan(rs.getString("enrollmentPlan"));
    		u.setFaculty(rs.getString("faculty"));
    		u.setComment(rs.getString("comment"));
    	    u.setProvinceName(rs.getString("provinceName"));
    		System.out.println(u.toString());
    		universityRankList.add(u);
    		
    	}
    	
    	
    	ConnectionManager.close(conn, rs, ptmt);
		
    	return universityRankList;
	}
	
	public University getUniversityDetail(String universityId) throws SQLException, PropertyVetoException
	{//获取主页大学
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from University,province where university.provinceId=province.provinceId and universityId='"+universityId+"' ;";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		University u=new University();
		System.out.println("获取主页大学");  
		while(rs.next())
    	{
    		
    		u.setUniversityId(rs.getString("universityId"));
    		u.setUniversityName(rs.getString("universityName"));
    		u.setType(rs.getString("type"));
    		u.setProvinceId(rs.getString("provinceId"));
    		u.setRank(rs.getInt("rank"));
    		u.setIntroduction(rs.getString("introduction"));
    		u.setEnrollmentPlan(rs.getString("enrollmentPlan"));
    		u.setFaculty(rs.getString("faculty"));
    		u.setComment(rs.getString("comment"));
    	    u.setProvinceName(rs.getString("provinceName"));
    		System.out.println(u.toString());    		
    	}
    	
		ConnectionManager.close(conn, rs, ptmt);
    	return u;
	}
	
	public void followUniversity()
	{//关注大学
		
	}
	
	public void addUniversityPreference()
	{//添加志愿大学
		
	}
}
