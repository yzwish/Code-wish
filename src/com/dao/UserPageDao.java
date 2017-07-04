package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.*;

public class UserPageDao {

	public int findDuty(String id) throws SQLException
	{//获取ID的身份
		
		int duty=0;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select duty from Account where id='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		
		while(rs.next())
		{
			duty=rs.getInt("duty");
		}
		ConnectionManager.close(conn, rs, ptmt);
		return duty;
	}
	
	public HighSchoolStu findHighschoolstu(String id) throws SQLException
	{
		System.out.println("开始查找高考生信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from province,highschool,highschoolstu where highschoolstu.hId=highschool.hId and highschool.provinceId=province.provinceId  and highschoolstu.id='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		HighSchoolStu h = new HighSchoolStu();
		while(rs.next())
    	{
    		
    		h.setId(id);
    		h.setAge(rs.getInt("age"));
    		h.setSex(rs.getString("sex"));
    		h.sethId(rs.getString("hId"));
    		h.setProvinceName(rs.getString("provinceName"));
    		h.setHname(rs.getString("hname"));
    		System.out.println("找到"+h.toString());
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return h;
	}
	
	public  CollegeStu findCollegeStu(String id) throws SQLException
	{
		System.out.println("开始查找学长学姐信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="SELECT * FROM yzwish.collegestu,university,province,major where collegestu.universityId=university.universityId and collegestu.majorId=major.majorId and university.provinceId=province.provinceId and collegestu.id='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		CollegeStu h = new CollegeStu();
		while(rs.next())
    	{
    		
    		h.setId(id);
    		h.setAge(rs.getInt("age"));
    		h.setSex(rs.getString("sex"));
    		h.setRealName(rs.getString("realName"));
    		h.setProvinceName(rs.getString("provinceName"));
    		h.setUniversityName(rs.getString("universityName"));
    		h.setMajorName(rs.getString("majorName"));
    		h.setsYear(rs.getInt("sYear"));
    		System.out.println("找到"+h.toString());
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return h;
	}
	
	public static Teacher findTeacher(String id) throws SQLException
	{
		System.out.println("开始查找老师信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from teacher,university,province where teacher.universityId=university.universityId and university.provinceId=province.provinceId and teacher.id='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		Teacher h = new Teacher();
		while(rs.next())
    	{
    		
    		h.setId(id);
    		h.setAge(rs.getInt("age"));
    		h.setSex(rs.getString("sex"));
    		h.setRealName(rs.getString("realName"));
    		h.setProvinceName(rs.getString("provinceName"));
    		h.setUniversityName(rs.getString("universityName"));
    		h.setResDirection(rs.getString("resDirection"));
    		h.setPosition(rs.getString("position"));
    		System.out.println("找到"+h.toString());
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return h;
	}
	public Hprivates findHprivates(String id) throws SQLException
	{
		System.out.println("开始查找高考生隐私信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from hprivates where privateUserId='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		Hprivates h = new Hprivates();
		while(rs.next())
    	{
    		
    		h.setPrivateUserId(id);
    		h.setShowBasic(rs.getInt("showBasic"));
    		h.setShowPreference(rs.getInt("showPreference"));
    		h.setShowTopic(rs.getInt("showTopic"));
    		h.setShowQuestion(rs.getInt("showQuestion"));
    		h.setShowAnswer(rs.getInt("showAnswer"));
    		h.setShowFollow(rs.getInt("showFollow"));
    		h.setShowActivity(rs.getInt("showActivity"));
    		System.out.println("找到"+h.toString());
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return h;
	}
	public Cprivates findUprivates(String id) throws SQLException
	{
		System.out.println("开始查找学长学姐隐私信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from cprivates where privateUserId='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		Cprivates h = new Cprivates();
		while(rs.next())
    	{
    		
    		h.setPrivateUserId(id);
    		h.setShowBasic(rs.getInt("showBasic"));
    		h.setShowTopic(rs.getInt("showTopic"));
    		h.setShowAnswer(rs.getInt("showAnswer"));
    		h.setShowFollow(rs.getInt("showFollow"));
    		h.setShowCredit(rs.getInt("showCredit"));
    		System.out.println("找到"+h.toString());
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return h;
	}
	public static Tprivates findTprivates(String id) throws SQLException
	{
		System.out.println("开始查找老师隐私信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from tprivates where privateUserId='"+id+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		Tprivates h = new Tprivates();
		while(rs.next())
    	{
    		
    		h.setPrivateUserId(id);
    		h.setShowBasic(rs.getInt("showBasic"));
    		h.setShowTopic(rs.getInt("showTopic"));
    		h.setShowAnswer(rs.getInt("showAnswer"));
    		h.setShowFollow(rs.getInt("showFollow"));
    		h.setShowActivity(rs.getInt("showActivity"));
    		System.out.println("找到"+h.toString());
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return h;
	}
	
	public boolean changePrivate(String id,String duty,List<String> list)
	{
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		if(duty.equals("1"))
		{//7
			System.out.println("开始修改高考生隐私信息");
			Hprivates h=new Hprivates();
			h.setPrivateUserId(id);
			if(list.contains("1"))
				   h.setShowBasic(1);
			else h.setShowBasic(0);
			
			if(list.contains("2"))
				   h.setShowPreference(1);
			else h.setShowPreference(0);
			
			if(list.contains("3"))
				   h.setShowTopic(1);
			else h.setShowTopic(0);
			
			if(list.contains("4"))
				   h.setShowQuestion(1);
			else h.setShowQuestion(0);
			
			if(list.contains("5"))
				   h.setShowAnswer(1);
			else h.setShowAnswer(0);
			
			if(list.contains("6"))
				   h.setShowFollow(1);
			else h.setShowFollow(0);
			
			if(list.contains("7"))
				   h.setShowActivity(1);
			else h.setShowActivity(0);
			
			System.out.println("修改为："+h.toString());
		   String sql="UPDATE `yzwish`.`hprivates` SET `showBasic`='"+h.getShowBasic()+"', `showPreference`='"+h.getShowPreference()+"', `showTopic`='"+h.getShowTopic()+"', `showQuestion`='"+h.getShowQuestion()+"', `showAnswer`='"+h.getShowAnswer()+"', `showFollow`='"+h.getShowFollow()+"', `showActivity`='"+h.getShowActivity()+"' WHERE `privateUserId`='"+h.getPrivateUserId()+"';";
           Boolean b=ConnectionManager.excuteIUD(sql, conn, ptmt);
           return b;
		}
		
		else if(duty.equals("2"))
		{//5
			System.out.println("开始修改学长学姐隐私信息");
			Cprivates h=new Cprivates();
			h.setPrivateUserId(id);
			if(list.contains("1"))
				   h.setShowBasic(1);
			else h.setShowBasic(0);
			
			if(list.contains("2"))
				   h.setShowTopic(1);
			else h.setShowTopic(0);
			
			if(list.contains("3"))
				   h.setShowAnswer(1);
			else h.setShowAnswer(0);
			
			if(list.contains("4"))
				   h.setShowFollow(1);
			else h.setShowFollow(0);
			
			if(list.contains("5"))
				   h.setShowCredit(1);
			else h.setShowCredit(0);
			
			System.out.println("修改为："+h.toString());
		   String sql="UPDATE `yzwish`.`cprivates` SET `showBasic`='"+h.getShowBasic()+"', `showTopic`='"+h.getShowTopic()+"', `showAnswer`='"+h.getShowAnswer()+"', `showFollow`='"+h.getShowFollow()+"', `showCredit`='"+h.getShowCredit()+"' WHERE `privateUserId`='"+h.getPrivateUserId()+"';";
           Boolean b=ConnectionManager.excuteIUD(sql, conn, ptmt);
           return b;
		}
		
		else if(duty.equals("3"))
		{//5
			System.out.println("开始修改老师隐私信息");
			Tprivates h=new Tprivates();
			h.setPrivateUserId(id);
			if(list.contains("1"))
				   h.setShowBasic(1);
			else h.setShowBasic(0);
			
			if(list.contains("2"))
				   h.setShowTopic(1);
			else h.setShowTopic(0);
			
			if(list.contains("3"))
				   h.setShowAnswer(1);
			else h.setShowAnswer(0);
			
			if(list.contains("4"))
				   h.setShowFollow(1);
			else h.setShowFollow(0);
			
			if(list.contains("5"))
				   h.setShowActivity(1);
			else h.setShowActivity(0);
			
			System.out.println("修改为："+h.toString());
		   String sql="UPDATE `yzwish`.`tprivates` SET `showBasic`='"+h.getShowBasic()+"', `showTopic`='"+h.getShowTopic()+"', `showAnswer`='"+h.getShowAnswer()+"', `showFollow`='"+h.getShowFollow()+"', `showActivity`='"+h.getShowActivity()+"';";
           Boolean b=ConnectionManager.excuteIUD(sql, conn, ptmt);
           return b;	
		}
		ConnectionManager.close(conn, rs, ptmt);
		return false;
	}


	public Map<String, String>  findVolunSchool(String visitId) throws SQLException {
		//查找高考生志愿信息
		
		System.out.println("开始查找高考生志愿信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		
		
		String sqlDuty="select nameDuty,rId from volunschool where id='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sqlDuty,conn,ptmt,rs);	
		
		Map<String, String> params=new HashMap<String, String> ();
		int nameDuty=0;
		
		String rId=null;
		int num1=0;
		int num2=0;
		int num3=0;
		
		while(rs.next())
    	{
    		nameDuty=rs.getInt("nameDuty");
    		rId=rs.getString("rId");
    		
    		
    		
    		if(nameDuty==1)
    		{//省
    			num1++;
    			String sqlP="select provinceName from Province where provinceId='"+rId+"';";
    			ResultSet rs2=ConnectionManager.excuteSelect(sqlP,conn,ptmt,rs);	
    			String provinceName=null;
    			while(rs2.next())
    	    	{
    				provinceName=rs2.getString("provinceName");
    	    	}
    			params.put("省/市"+num1, provinceName);
    			
    			rs2.close();
    		}
    		else if(nameDuty==2)
    		{//大学
    			num2++;
    			String sqlP="select universityName from University where universityId='"+rId+"';";
    			ResultSet rs2=ConnectionManager.excuteSelect(sqlP,conn,ptmt,rs);	
    			String universityName=null;
				while(rs2.next())
    	    	{
					universityName=rs2.getString("universityName");
    	    	}
    			params.put("大学"+num2, universityName);
    			
    			rs2.close();
    		}
    		
    		else if(nameDuty==3)
    		{//专业
    			num3++;
    			String sqlP="select majorName from Major where majorId='"+rId+"';";
    			ResultSet rs2=ConnectionManager.excuteSelect(sqlP,conn,ptmt,rs);	
    			String majorName=null;
				while(rs2.next())
    	    	{
					majorName=rs2.getString("majorName");
    	    	}
    			params.put("专业"+num3, majorName);
    			
    			rs2.close();
    		}
    		
    		
    		
    	}
		
		ConnectionManager.close(conn, rs, ptmt);
		return params;
	}


	public static List<Topic> findTopic(String visitId) throws SQLException {
		// 查找话题信息
		System.out.println("开始查找话题信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select topicName from topic where topicUserId='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		List<Topic> list=new ArrayList<Topic>();
		Topic t = new Topic();
		while(rs.next())
    	{
    		
    		t.setTopicName(rs.getString("topicName"));
    		System.out.println("找到"+t.toString());
    		list.add(t);
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return list;
	}


	public static List<Question> findQuestion(String visitId) throws SQLException {
		// 查找问题信息
		System.out.println("开始查找问题信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select questionTitle,quesCreateTime from Question where userId='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		
		List<Question> list=new ArrayList<Question>();
		Question q = new Question();
		while(rs.next())
    	{
			q.setQuestionTitle(rs.getString("questionTitle"));
    		q.setQuesCreateTime(rs.getString("quesCreateTime"));
    		System.out.println("找到"+q.toString());
    		list.add(q);
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return list;
	}


	public List <Answer> findAnswer(String visitId) throws SQLException {
		// 查找回答信息
		System.out.println("开始查找回答信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select answerContent,answerTime from Answer where answerUserId='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		
		List<Answer> list=new ArrayList<Answer>();
		Answer q = new Answer();
		while(rs.next())
    	{
			q.setAnswerContent(rs.getString("answerContent"));
    		q.setAnswerTime(rs.getString("answerTime"));
    		System.out.println("找到"+q.toString());
    		list.add(q);
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return list;
	}


	public Map<String, String> findFollow(String visitId) throws SQLException {
		System.out.println("开始查找关注信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		
		
		String sqlDuty="select followType,followedId,followTime from Follow where followUserId='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sqlDuty,conn,ptmt,rs);	
		
		Map<String, String> params=new HashMap<String, String> ();
		int followType=0;
		int num1=0;
		int num2=0;
		int num3=0;
		int num4=0;
		String followedId=null;
		
		while(rs.next())
    	{
			followType=rs.getInt("followType");
			followedId=rs.getString("followedId");
    		
    		
    		if(followType==1)
    		{//用户
    			num1++;
    			params.put("用户"+num1, followedId);
    		
    		}
    		else if(followType==2)
    		{//话题
    			num2++;
    			List<Topic> list=new ArrayList<Topic>();
    			list=UserPageDao.findTopic(followedId);
    			for(Topic t:list)
    			params.put("话题"+num2, t.getTopicName());
    			
    		}
    		
    		else if(followType==3)
    		{//问题
    			num3++;
    			List<Question> list=new ArrayList<Question>();
    			list=UserPageDao.findQuestion(followedId);
    			for(Question t:list)
    			params.put("问题"+num3, t.getQuestionTitle());
    			
    		}
    		
    		else if(followType==4)
    		{//活动
    			num4++;
    			String sqlP="select title from Activity where id='"+followedId+"';";
    			ResultSet rs2=ConnectionManager.excuteSelect(sqlP,conn,ptmt,rs);	
    			String title=null;
				while(rs2.next())
    	    	{
					title=rs2.getString("title");
    	    	}
    			params.put("活动"+num4, title);
    			rs2.close();
    		}
    		
    		
    		
    	}
		
		ConnectionManager.close(conn, rs, ptmt);
		return params;
	}


	public List<Activity> findActivity(String visitId) throws SQLException {
		// 查找举办活动信息
		System.out.println("开始查找举办活动信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select title,activityStatus,startTime from Activity where id='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		
		List<Activity> list=new ArrayList<Activity>();
		Activity q = new Activity();
		while(rs.next())
    	{
			q.setTitle(rs.getString("title"));
    		q.setActivityStatus(rs.getInt("activityStatus"));
    		q.setStartTime(rs.getString("startTime"));
    		System.out.println("找到"+q.toString());
    		
    		if(rs.getInt("activityStatus")!=4)
    			//活动未取消则显示该条记录
    		  list.add(q);
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return list;
	}


	public List<EnrollTable> findenrollEnrollTable(String visitId) throws SQLException {
		// 查找参加活动信息
		System.out.println("开始查找参加活动信息");
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select activity.title,enrollTable.enrollTime from enrollTable,activity where activity.activityId=enrollTable.activityId and activity.id='"+visitId+"';";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		
		List<EnrollTable> list=new ArrayList<EnrollTable>();
		EnrollTable q = new EnrollTable();
		while(rs.next())
    	{
			q.setTitle(rs.getString("title"));
    		q.setEnrollTime(rs.getString("enrollTime"));
    		System.out.println("找到"+q.toString());
    		
    		  list.add(q);
    	}
		ConnectionManager.close(conn, rs, ptmt);
		return list;
	}
	
}
