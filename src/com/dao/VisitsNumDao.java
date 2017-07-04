package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitsNumDao {

	public static int views(String time) {
		int view=0;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="select sum(Pageview) as views from visitsnum where Time like '"+time+"%'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			if(rs.next()){
				view=rs.getInt("views");
			}
			return view;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return view;
		}
		finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
		
	}
	public static void addVisitsNum(String time){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		try {
			int today_views=VisitsNumDao.views(time);//得到今日访问量
			if(today_views==0)//访问量是0代表数据库没有数据
			{
				//插入今天记录
				sql="insert into visitsnum values( '"+time+"' ,1)";
				ConnectionManager.excuteIUD(sql, conn, ptmt);
			}
			else{
				//今日已有记录
				today_views++;//+1
				sql="update visitsnum set Pageview="+today_views
						+" where time = '"+time+"'";
				ConnectionManager.excuteIUD(sql, conn, ptmt);
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}

}
