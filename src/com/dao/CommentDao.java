package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommentDao {

	public void addComment()
	{
		
		
		
		Date date=new Date(System.currentTimeMillis()); 
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow=format.format(date);
		
		
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
    	String sql="select * from majorKind";
    	
	}
}
