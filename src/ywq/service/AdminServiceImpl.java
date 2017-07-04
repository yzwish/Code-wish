package ywq.service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import ywq.dao.ConnectionManager;
import ywq.model.CheckTeacher;
import ywq.model.CheckSenior;
import ywq.model.BlockedUser;
import ywq.model.Report;
import ywq.model.Topic;

@Service
public class AdminServiceImpl implements AdminService{

	@Override
	public ArrayList<CheckTeacher> getTeacherInfo() {
		//得到老师审核信息
		ArrayList<CheckTeacher> ctl=new ArrayList<CheckTeacher>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select teacher.id,realName,provinceName,universityName,resDirection,position,picSrc "
				+ " from teacher,university,province,account "
				+ " where teacher.universityId=university.universityId and province.provinceId=university.provinceId and teacher.id=account.id "
				+ " and account.status='2' and account.duty='3'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				CheckTeacher ct=new CheckTeacher();
				ct.setId(rs.getString("teacher.id"));
				ct.setRealName(rs.getString("realName"));
				ct.setProvinceName(rs.getString("provinceName"));
				ct.setUniversityName(rs.getString("universityName"));
				ct.setResDirection(rs.getString("resDirection"));
				ct.setPosition(rs.getString("position"));
				ct.setPicSrc(rs.getString("picSrc"));
				ctl.add(ct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return ctl;
	}
	
	@Override
	public boolean checkTPass(String list[]) {
		//老师审核通过
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		
		boolean flag=true;
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		


        for(int i = 0;i < list.length; i ++){
        	String sql="update account set status='1' where id='"+list[i]+"' and account.duty='3'";
        	boolean s=ConnectionManager.excuteIUD(sql, conn, ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','审核通过！','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("系统通知发送成功！");
        		else System.out.println("系统通知发送失败！");
        	}
        }
        
			ConnectionManager.close(ptmt,rs,conn);
		
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkTNotPass(String list[],String Treason) {
		//老师审核未通过
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
        for(int i = 0;i < list.length; i ++){
        	String sql="update account set status='3' where id='"+list[i]+"' and account.duty='3'";
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','审核未通过！理由："+Treason+"','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("系统通知发送成功！");
        		else System.out.println("系统通知发送失败！");
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public ArrayList<CheckSenior> getSeniorInfo() {
		//得到学长学姐审核信息
		ArrayList<CheckSenior> csl=new ArrayList<CheckSenior>();
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select collegestu.id,realName,provinceName,universityName,majorName,sYear,picSrc "
				+ " from collegestu,account,province,university,major where "
				+ " collegestu.id=account.id and collegestu.universityId=university.universityId and university.provinceId=province.provinceId and collegestu.majorId=major.majorId "
				+ " and account.status='2' and account.duty='2'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				CheckSenior cs=new CheckSenior();
				cs.setId(rs.getString("collegestu.id"));
				cs.setRealName(rs.getString("realName"));
				cs.setProvinceName(rs.getString("provinceName"));
				cs.setUniversityName(rs.getString("universityName"));
				cs.setMajorName(rs.getString("majorName"));
				cs.setSyear(rs.getInt("sYear"));
				cs.setPicSrc(rs.getString("picSrc"));
				csl.add(cs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return csl;
	}
	
	@Override
	public boolean checkSPass(String list[]) {
		//学长学姐审核通过
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;

        for(int i = 0;i < list.length; i ++){
        	String sql="update account set status='1' where id='"+list[i]+"' and account.duty='2'";
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','审核通过！','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("系统通知发送成功！");
        		else System.out.println("系统通知发送失败！");
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkSNotPass(String list[],String Treason) {
		//学长学姐审核未通过
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();

		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
        for(int i = 0;i < list.length; i ++){
        	String sql="update account set status='3' where id='"+list[i]+"' and account.duty='2'";
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','审核未通过！理由："+Treason+"','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("系统通知发送成功！");
        		else System.out.println("系统通知发送失败！");
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public ArrayList<BlockedUser> getBlockedStudentInfo() {
		//得到封禁高考生信息
		ArrayList<BlockedUser> bsl=new ArrayList<BlockedUser>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select privateUserId,closureReason,closureDay,closureTime "
				+ " from blockedusers,account "
				+ " where blockedusers.privateUserId=account.id and account.duty='1'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				BlockedUser bu=new BlockedUser();
				bu.setPrivateUserId(rs.getString("privateUserId"));
				bu.setClosureReason(rs.getString("closureReason"));
				bu.setClosureTime(rs.getString("closureTime"));
				bu.setClosureDay(rs.getInt("closureDay"));
				bsl.add(bu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return bsl;
	}
	
	@Override
	public ArrayList<BlockedUser> getBlockedTeacherInfo() {
		//得到封禁老师信息
		ArrayList<BlockedUser> btl=new ArrayList<BlockedUser>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select privateUserId,closureReason,closureDay,closureTime "
				+ " from blockedusers,account "
				+ " where blockedusers.privateUserId=account.id and account.duty='3'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				BlockedUser bu=new BlockedUser();
				bu.setPrivateUserId(rs.getString("privateUserId"));
				bu.setClosureReason(rs.getString("closureReason"));
				bu.setClosureTime(rs.getString("closureTime"));
				bu.setClosureDay(rs.getInt("closureDay"));
				btl.add(bu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return btl;
	}
	
	@Override
	public ArrayList<BlockedUser> getBlockedSeniorInfo() {
		//得到封禁学长学姐信息
		ArrayList<BlockedUser> bsl=new ArrayList<BlockedUser>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select privateUserId,closureReason,closureDay,closureTime "
				+ " from blockedusers,account "
				+ " where blockedusers.privateUserId=account.id and account.duty='2'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				BlockedUser bu=new BlockedUser();
				bu.setPrivateUserId(rs.getString("privateUserId"));
				bu.setClosureReason(rs.getString("closureReason"));
				bu.setClosureTime(rs.getString("closureTime"));
				bu.setClosureDay(rs.getInt("closureDay"));
				bsl.add(bu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return bsl;
	}
	
	@Override
	public ArrayList<Report> getReporterStudentInfo() {
		//得到举报用户信息-举报人-学生 
		ArrayList<Report> rerStuL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime "
				+ " from report,account where report.reporterID=account.id "
				+ " and account.duty='1' and report.reportStatus='0' and reportType='4'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				rerStuL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return rerStuL;
	}
	
	@Override
	public ArrayList<Report> getReporterTeacherInfo() {
		//得到举报用户信息-举报人-老师
		ArrayList<Report> rerTeaL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime "
				+ " from report,account where report.reporterID=account.id "
				+ " and account.duty='3' and report.reportStatus='0' and reportType='4'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				rerTeaL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return rerTeaL;
	}
	
	@Override
	public ArrayList<Report> getReporterSeniorInfo() {
		//得到举报用户信息-举报人-学长学姐
		ArrayList<Report> rerSenL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime "
				+ " from report,account where report.reporterID=account.id "
				+ " and account.duty='2' and report.reportStatus='0' and reportType='4'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				rerSenL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return rerSenL;
	}
	
	@Override
	public ArrayList<Report> getReportedStudentInfo() {
		//得到举报用户信息-被举报人-学生 
		ArrayList<Report> redStuL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime "
				+ " from report,account where report.reportedID=account.id "
				+ " and account.duty='1' and report.reportStatus='0' and reportType='4'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				redStuL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return redStuL;
	}
	
	@Override
	public ArrayList<Report> getReportedTeacherInfo() {
		//得到举报用户信息-被举报人-老师
		ArrayList<Report> redTeaL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime "
				+ " from report,account where report.reportedID=account.id "
				+ " and account.duty='3' and report.reportStatus='0' and reportType='4'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				redTeaL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return redTeaL;
	}
	
	@Override
	public ArrayList<Report> getReportedSeniorInfo() {
		//得到举报用户信息-被举报人-学长学姐
		ArrayList<Report> redSenL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime "
				+ " from report,account where report.reportedID=account.id "
				+ " and account.duty='2' and report.reportStatus='0' and reportType='4'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				redSenL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return redSenL;
	}
	
	@Override
	public boolean blockED(String list[], String Bday) {
		//封禁被举报用户
		//0.判断数据是否已处理
		//1.将account表中用户状态改为封禁			(判断是否已被封禁 已封禁1 未封禁0)
		//2.blockedusers表添加数据		理由+时间	(未封禁0--添加数据)
		//3.发送系统通知	举报用户&被举报用户			(未封禁0--向被举报用户发送系统通知)
		//4.report表中状态置1---所有举报该用户的记录都置为1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;

        for(int i = 0;i < list.length; i ++){
        	
        	String sqlc="select reportStatus from report where reportID='"+list[i]+"'";
        	//检查该数据是否被处理
        	int check=0;
        	ResultSet ck=null;
        	ck=ConnectionManager.excuteSelect(sqlc,conn,ptmt,ck);
        	try {
    			if(ck.next()){
    				check=ck.getInt("reportStatus");
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	try {
				ck.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(check==0){
        	
        	String sqlck="select status from account,report where account.id=report.reportedID and reportID='"+list[i]+"'";
        	//检查该用户是否已被封禁
        	int check2=0;
        	ResultSet ck2=null;
        	ck2=ConnectionManager.excuteSelect(sqlck,conn,ptmt,ck2);
        	try {
				if(ck2.next()){
					check2=ck2.getInt("status");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
				ck2.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	String sql="update account set status='4' "
        			+ " where id in ( select reportedID from report "
        			+ " where report.reportedID=account.id and reportID='"+list[i]+"')";
        	//将account表中用户状态改为封禁
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String id=null,reason=null;
        		String sqlg="select reportedID,reportReason from report where reportID='"+list[i]+"'";
        		//得到被举报用户id，举报原因
        		ResultSet rsn=null;
        		rsn=ConnectionManager.excuteSelect(sqlg,conn,ptmt,rsn);
        		try {
    			if(rsn.next()){
    				id=rsn.getString("reportedID");
    				reason=rsn.getString("reportReason");
    				
    			if(check2!=4){
        		String sqlb="insert into blockedusers values ('"+id+"','"+reason+"','"+Bday+"','"+df.format(date)+"')";
        		//blockedusers表添加数据
        		boolean b=ConnectionManager.excuteIUD(sqlb,conn,ptmt);
        		if(b==true) System.out.println("添加封禁用户记录成功！");
        		else System.out.println("添加封禁用户记录失败！");
    			
        		
        		String sqlm="insert into message (id,context,time,isRead) values ('"+id+"' , '您在"+df.format(date)+"时被封禁，原因："+reason+"，封禁天数："+Bday+"天' , '"+df.format(date)+"','0') ";
        		//向被封禁用户发送系统通知
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("被举报用户系统通知发送成功！");
        		else System.out.println("被举报用户系统通知发送失败！");
        		
    			}
        		String sqla="select reporterID,reportReason,reportTime from report where reportedID='"+id+"' and reportType='4'";
        		//得到举报该用户的举报人相关信息
        		ResultSet a=null;
        		a=ConnectionManager.excuteSelect(sqla,conn,ptmt,a);
        		try {
        			while(a.next()){
        				String rerID=a.getString("reporterID");
        				String reReason=a.getString("reportReason");
        				String rTime=a.getString("reportTime");
        				
                		String sqlm2="insert into message (id,context,time,isRead) values ('"+rerID+"','举报成功！您在"+rTime+"时举报用户id："+id+"，原因："+reReason+"，已成功举报！','"+df.format(date)+"','0')";
                		//向举报用户发送系统通知
                		boolean q=ConnectionManager.excuteIUD(sqlm2,conn,ptmt);
                		if(q==true) System.out.println("举报用户系统通知发送成功！");
                		else System.out.println("举报用户系统通知发送失败！");
        				
        			}
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}a.close();
        		      		
        		
        		
        		String sqls="update report set reportStatus='1' where report.reportedID='"+id+"' and reportType='4'";
        		//举报该被封禁用户的举报记录全置1
        		boolean m=ConnectionManager.excuteIUD(sqls,conn,ptmt);
    			}} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}try {
					rsn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	
	@Override
	public boolean blockER(String list[], String Bday) {
		//封禁举报者
		//0.判断数据是否已处理
		//1.将account表中用户状态改为封禁   (判断是否已被封禁 已封禁1 未封禁0)
		//2.blockedusers表添加数据		封禁理由(举报不受理次数过多)+封禁时间   (未封禁0--添加数据)
		//3.发送系统通知	举报用户			 (未封禁0--发送通知)
		//4.report表中状态置1---所有该用户举报的记录都置为1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		
		 for(int i = 0;i < list.length; i ++){
	        	
	        	String sqlc="select reportStatus from report where reportID='"+list[i]+"'";
	        	//检查该数据是否被处理
	        	int check=0;
	        	ResultSet ck=null;
	        	ck=ConnectionManager.excuteSelect(sqlc,conn,ptmt,ck);
	        	try {
	    			if(ck.next()){
	    				check=ck.getInt("reportStatus");
	    			}
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        	try {
					ck.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	if(check==0){
	        	
	        		String sqlck="select status from account,report where account.id=report.reporterID and reportID='"+list[i]+"'";
	            	//检查该用户是否已被封禁
	            	int check2=0;
	            	ResultSet ck2=null;
	            	ck2=ConnectionManager.excuteSelect(sqlck,conn,ptmt,ck2);
	            	try {
	    				if(ck2.next()){
	    					check2=ck2.getInt("status");
	    				}
	    			} catch (SQLException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}
	            	try {
	    				ck2.close();
	    			} catch (SQLException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}
	        		
	        		
	        		
	        	String sql="update account set status='4' "
	        			+ " where id in ( select reporterID from report "
	        			+ " where report.reporterID=account.id and reportID='"+list[i]+"')";
	        	//将account表中用户状态改为封禁
	        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
	        	if(s==false) flag=false;
	        	if(s==true){
	        		String id=null;
	        		String sqlg="select reporterID from report where reportID='"+list[i]+"'";
	        		//得到举报用户id
	        		ResultSet rsn=null;
	        		rsn=ConnectionManager.excuteSelect(sqlg,conn,ptmt,rsn);
	        		try {
	    			if(rsn.next()){
	    				id=rsn.getString("reporterID");
	    				
	    			if(check2!=4)	{

	        		String sqlb="insert into blockedusers values ('"+id+"','举报不受理次数过多','"+Bday+"','"+df.format(date)+"')";
	        		//blockedusers表添加数据
	        		boolean b=ConnectionManager.excuteIUD(sqlb,conn,ptmt);
	        		if(b==true) System.out.println("添加封禁用户记录成功！");
	        		else System.out.println("添加封禁用户记录失败！");
	        		
	        		
	        		String sqlm="insert into message (id,context,time,isRead) values ('"+id+"' , '您在"+df.format(date)+"时被封禁，原因：举报不受理次数过多，封禁天数："+Bday+"天' , '"+df.format(date)+"','0') ";
	        		//向被封禁用户发送系统通知
	        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
	        		if(t==true) System.out.println("举报者系统通知发送成功！");
	        		else System.out.println("举报者系统通知发送失败！");
	        		
	    			}      		
	        		
	        		
	        		String sqls="update report set reportStatus='1' where report.reporterID='"+id+"' and reportType='4'";
	        		//该举报者的举报记录全置1
	        		boolean m=ConnectionManager.excuteIUD(sqls,conn,ptmt);
	    			}} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}try {
						rsn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
		}
		 	ConnectionManager.close(ptmt,rs,conn);
			if(flag==true) return true;
			else return false;
	}
	
	@Override
	public boolean neglect(String list[]) {
		//忽略该条举报用户信息
		//1.该条举报消息状态置1
		//2.发送系统通知给该用户 --举报不被受理
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;

        for(int i = 0;i < list.length; i ++){
        	String sql="update report set reportStatus='1' where reportID='"+list[i]+"'";
        	//置该条举报信息状态为1
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		
        		String sqlg="select reporterID from report where reportID='"+list[i]+"'";
        		//获得举报人id
        		rs=ConnectionManager.excuteSelect(sqlg,conn,ptmt,rs);
        		try {
        			if(rs.next()){
        				String rerID=rs.getString("reporterID");
        				
                		String sqlm="insert into message (id,context,time,isRead) values ('"+rerID+"','举报用户未受理','"+df.format(date)+"','0') ";
                		//向举报人发送系统通知
                		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
                		if(t==true) System.out.println("系统通知发送成功！");
                		else System.out.println("系统通知发送失败！");

        			}
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkUexist(String privateUserId){
		//查看id是否存在
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="select id from account where id='"+privateUserId+"'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		String id=null;
		try {
			if(rs.next()){
				id=rs.getString("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ConnectionManager.close(ptmt,rs,conn);
		if(id!=null) return true;
		else return false;
	}
	
	@Override
	public boolean checkUB(String privateUserId){
		//查看id是否被封禁
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="select privateUserId from blockedusers where privateUserId='"+privateUserId+"'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		String id=null;
		try {
			if(rs.next()){
				id=rs.getString("privateUserId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ConnectionManager.close(ptmt,rs,conn);
		if(id!=null) return true;
		else return false;
	}
	
	@Override
	public boolean account4(String privateUserId){
		//account表中该用户状态置4-封禁状态
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="update account set status='4' where id='"+privateUserId+"'";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public boolean addTableBU(String privateUserId,String closureReason,int closureDay){
		//blockedusers表添加数据
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="insert into blockedusers values ('"+privateUserId+"','"+closureReason+"','"+closureDay+"','"+df.format(date)+"')";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}

	@Override
	public boolean sentMtoBU(String privateUserId,String closureReason,int closureDay){
		//发送系统通知给被封禁用户
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="insert into message (id,context,time,isRead) values ('"+privateUserId+"' , '您在"+df.format(date)+"时被封禁，原因："+closureReason+"，封禁天数："+closureDay+"天' , '"+df.format(date)+"','0') ";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public boolean sentMtoER(String privateUserId){
		//给举报该用户的用户发送系统通知--举报成功
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		boolean flag=true;
		
		String sql1="select reporterID,reportReason,reportTime from report where reportedID='"+privateUserId+"' and reportType='4' and reportStatus='0'";
		rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
		try {
			while(rs.next()){
				String reporterID=rs.getString("reporterID");
				String reportReason=rs.getString("reportReason");
				String reportTime=rs.getString("reportTime");
				
				String sql2="insert into message (id,context,time,isRead) values ('"+reporterID+"','举报成功！您在"+reportTime+"时举报用户id："+privateUserId+"，原因："+reportReason+"，已成功举报！','"+df.format(date)+"','0')";
				boolean t=ConnectionManager.excuteIUD(sql2,conn,ptmt);
				if(t==false) flag=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return flag;
	}
	
	@Override
	public boolean setReportED1(String privateUserId){
		//report表中举报该用户的数据状态置为1
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="update report set reportStatus='1' where reportedID='"+privateUserId+"' and reportType='4' ";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public boolean account01(String privateUserId){
		//account表中该用户状态置01-正常状态
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql0="select duty from account where id='"+privateUserId+"'";
		rs=ConnectionManager.excuteSelect(sql0, conn, ptmt, rs);
		int duty=4;
		String sql=" ";
		try {
			if(rs.next()){
				duty=rs.getInt("duty");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(duty==0||duty==1){
		sql="update account set status='0' where id='"+privateUserId+"'";}
		if(duty==2||duty==3){
		sql="update account set status='1' where id='"+privateUserId+"'";}
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public boolean delTableBU(String privateUserId){
		//blockedusers表删除数据
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql="delete from blockedusers where privateUserId='"+privateUserId+"'";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public boolean sentMtoUBU(String privateUserId){
		//发送系统通知给被解封用户
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="insert into message (id,context,time,isRead) values ('"+privateUserId+"' , '您在"+df.format(date)+"时被解封。 ' , '"+df.format(date)+"','0') ";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public ArrayList<Topic> getTopicInfo() {
		//得到话题审核信息
		ArrayList<Topic> tpl=new ArrayList<Topic>();
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select topicId,topicName,topicDiscription,topicAvatar,topicBanner from topic where topicState='0'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Topic tp=new Topic();
				tp.setTopicId(rs.getInt("topicId"));
				tp.setTopicName(rs.getString("topicName"));
				tp.setTopicDiscription(rs.getString("topicDiscription"));
				tp.setTopicAvatar(rs.getString("topicAvatar").substring(3));
				tp.setTopicBanner(rs.getString("topicBanner").substring(3));
				tpl.add(tp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return tpl;
	}
	
	@Override
	public boolean checkTOPPass(String list[]) {
		//话题审核通过
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
        for(int i = 0;i < list.length; i ++){
        	String sql="update topic set topicState='1' where topicId='"+list[i]+"'";
        	boolean s=ConnectionManager.excuteIUD(sql, conn, ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String topicName=null,topicUserId=null;
        		String sqls="select topicName,topicUserId from topic where topicId='"+list[i]+"'";
        		rs=ConnectionManager.excuteSelect(sqls,conn,ptmt,rs);
        		try {
					if(rs.next()){
						topicName=rs.getString("topicName");
						topicUserId=rs.getString("topicUserId");
						rs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		String sqlm="insert into message (id,context,time,isRead) values ('"+topicUserId+"','话题“"+topicName+"”在"+df.format(date)+"审核通过！','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("系统通知发送成功！");
        		else System.out.println("系统通知发送失败！");
        	}
        }
			ConnectionManager.close(ptmt,rs,conn);
		
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkTOPNotPass(String list[],String Treason) {
		//话题审核不通过
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
        for(int i = 0;i < list.length; i ++){
        	String sql="update topic set topicState='2' where topicId='"+list[i]+"'";
        	boolean s=ConnectionManager.excuteIUD(sql, conn, ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String topicName=null,topicUserId=null;
        		String sqls="select topicName,topicUserId from topic where topicId='"+list[i]+"'";
        		rs=ConnectionManager.excuteSelect(sqls,conn,ptmt,rs);
        		try {
					if(rs.next()){
						topicName=rs.getString("topicName");
						topicUserId=rs.getString("topicUserId");
						rs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		String sqlm="insert into message (id,context,time,isRead) values ('"+topicUserId+"','话题“"+topicName+"”在"+df.format(date)+"审核未通过！原因："+Treason+"','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("系统通知发送成功！");
        		else System.out.println("系统通知发送失败！");
        	}
        }
			ConnectionManager.close(ptmt,rs,conn);
		
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public ArrayList<Report> getTRerStudentInfo() {
		//得到举报话题信息-举报人-学生 
		ArrayList<Report> TrerStuL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,topicName,topicUserId "
				+ " from report,account,topic where report.reporterID=account.id and report.reportedID=topic.topicId "
				+ " and account.duty='1' and report.reportStatus='0' and reportType='2'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("topicName"));
				r.setTopicUserId(rs.getString("topicUserId"));
				TrerStuL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return TrerStuL;
	}
	
	@Override
	public ArrayList<Report> getTRerTeacherInfo() {
		//得到举报话题信息-举报人-老师 
		ArrayList<Report> TrerTeaL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,topicName,topicUserId "
				+ " from report,account,topic where report.reporterID=account.id and report.reportedID=topic.topicId "
				+ " and account.duty='3' and report.reportStatus='0' and reportType='2'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("topicName"));
				r.setTopicUserId(rs.getString("topicUserId"));
				TrerTeaL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return TrerTeaL;
	}
	
	@Override
	public ArrayList<Report> getTRerSeniorInfo() {
		//得到举报话题信息-举报人-学长学姐
		ArrayList<Report> TrerSenL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,topicName,topicUserId "
				+ " from report,account,topic where report.reporterID=account.id and report.reportedID=topic.topicId "
				+ " and account.duty='2' and report.reportStatus='0' and reportType='2'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("topicName"));
				r.setTopicUserId(rs.getString("topicUserId"));
				TrerSenL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return TrerSenL;
	}
	
	@Override
	public boolean delTopic(String list[]) {
		//删除话题
		//判断report表是否为0 
		//1.message发送系统通知给话题发起者/所有举报用户
		//2.topic表删除数据(question,answer)
		//3.report表置1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		for(int i = 0;i < list.length; i ++){
		String sql1="select reportedID,reportReason,topicName,topicUserId,reportStatus "
				+ " from report,topic where report.reportedID=topic.topicId "
				+ " and reportID='"+list[i]+"' ";
		//获得相关信息
		rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
		String reporterID=null,reportReason=null,reportTime=null,topicName=null,topicUserId=null,reportedID=null;
		int reportStatus=0;
		try {
			if(rs.next()){
				reportedID=rs.getString("reportedID");
				topicName=rs.getString("topicName");
				topicUserId=rs.getString("topicUserId");
				reportReason=rs.getString("reportReason");
				reportStatus=rs.getInt("reportStatus");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reportStatus==0){
		
		String sql2="insert into message (id,context,time,isRead) values ('"+topicUserId+"','话题“"+topicName+"”在"+df.format(date)+"时被举报删除！原因："+reportReason+"','"+df.format(date)+"','0') ";
		//发送系统通知给话题发起人
		ConnectionManager.excuteIUD(sql2, conn, ptmt);
		
		String sql="select reporterID,reportTime from report where reportedID='"+reportedID+"' and reportStatus='0' and reportType='2' ";
		//获得所有举报该话题的人的信息
		ResultSet s=null;
		s=ConnectionManager.excuteSelect(sql,conn,ptmt,s);
		try {
			while(s.next()){
				reporterID=s.getString("reporterID");
				reportTime=s.getString("reportTime");
			String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','您在"+reportTime+"时举报的话题“"+topicName+"”举报成功，话题已删除！','"+df.format(date)+"','0') ";
			//发送系统通知给所有举报该话题的人
			ConnectionManager.excuteIUD(sql3, conn, ptmt);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}try {
			s.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql4="select questionId from question where topicId='"+reportedID+"'";
		//获得问题id
		ResultSet r=null;
		r=ConnectionManager.excuteSelect(sql4,conn,ptmt,r);
		String questionId=null;
		try {
			while(r.next()){
				questionId=r.getString("questionId");
				String sql5="delete from answer where questionId='"+questionId+"'";
				//删除回答
				ConnectionManager.excuteIUD(sql5, conn, ptmt);
				String sql6="delete from question where questionId='"+questionId+"'";
				//删除问题
				ConnectionManager.excuteIUD(sql6, conn, ptmt);
				r.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql7="delete from topic where topicId='"+reportedID+"'";
		//删除话题
		ConnectionManager.excuteIUD(sql7, conn, ptmt);
		
		String sql8="update report set reportStatus='1' where reportedID='"+reportedID+"' and reportType='2' ";
		//report中所有举报该话题的数据置1
		ConnectionManager.excuteIUD(sql8, conn, ptmt);
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean TopBU(String list[],int bday) {
		//封禁话题发起者
		//查看用户是否被封禁-否
		//account表置4
		//blockedusers表添加数据
		//发送系统通知给被封禁用户
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
			String sql1="select reportReason,topicUserId "
					+ " from report,topic where report.reportedID=topic.topicId "
					+ " and reportID='"+list[i]+"' ";
			String topicUserId=null,reportReason=null;
			rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
			try {
				while(rs.next()){
					topicUserId=rs.getString("topicUserId");
					reportReason=rs.getString("reportReason");
					
					String sql2="select status from account where id='"+topicUserId+"'";
					//查看用户是否被封禁
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(topicUserId);
						addTableBU(topicUserId,reportReason,bday);
						sentMtoBU(topicUserId,reportReason,bday);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean TopBUer(String list[],int bday) {
		//封禁举报人
		//判断report表是否为0
		//1.查看用户是否被封禁
		//2.封禁用户 account4
		//3.blockedusers添加数据
		//4.发送系统通知给被封禁用户
		//5.report表置1 该用户举报的所有数据
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
			String sql1="select reportReason,reporterID,reportStatus "
					+ " from report,topic where report.reportedID=topic.topicId "
					+ " and reportID='"+list[i]+"' ";
			String reporterID=null;
			int reportStatus=0;
			rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
			try {
				while(rs.next()){
					reporterID=rs.getString("reporterID");
					reportStatus=rs.getInt("reportStatus");
					if(reportStatus==0){
					String sql2="select status from account where id='"+reporterID+"'";
					//查看用户是否被封禁
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(reporterID);
						addTableBU(reporterID,"举报话题不被受理次数过多",bday);
						sentMtoBU(reporterID,"举报话题不被受理次数过多",bday);
						String sql3="select reportID from report where reporterID='"+reporterID+"' and reportStatus='0' and reportType='2' ";
						ResultSet r3=null;
						r3=ConnectionManager.excuteSelect(sql3,conn,ptmt,r3);
						while(r3.next()){
							int reportID=r3.getInt("reportID");
							String sql4="update report set reportStatus='1' where reportID='"+reportID+"'";
							ConnectionManager.excuteIUD(sql4, conn, ptmt);
						}r3.close();
					}
					String sql5="update report set reportStatus='1' where reportID='"+list[i]+"'";
					ConnectionManager.excuteIUD(sql5, conn, ptmt);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean neglectT(String list[]) {
		//忽略该条举报话题信息
		//1.该条举报消息状态置1
		//2.发送系统通知给该用户 --举报不被受理
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
		String sql1="update report set reportStatus='1' where reportID='"+list[i]+"' ";
		ConnectionManager.excuteIUD(sql1, conn, ptmt);
		String sql2="select reporterID from report where reportID='"+list[i]+"' ";
		rs=ConnectionManager.excuteSelect(sql2,conn,ptmt,rs);
		try {
			if(rs.next()){
				String reporterID=rs.getString("reporterID");
				String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','举报话题未受理','"+df.format(date)+"','0') ";
				ConnectionManager.excuteIUD(sql3, conn, ptmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	public ArrayList<Report> getQRerStudentInfo() {
		//得到举报问题信息-举报人-学生 
		ArrayList<Report> QrerStuL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,questionTitle,userId "
				+ " from report,account,question where report.reporterID=account.id and report.reportedID=question.questionId "
				+ " and account.duty='1' and report.reportStatus='0' and reportType='1'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("questionTitle"));
				r.setTopicUserId(rs.getString("userId"));
				QrerStuL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return QrerStuL;
	}
	
	@Override
	public ArrayList<Report> getQRerTeacherInfo() {
		//得到举报问题信息-举报人-老师 
		ArrayList<Report> QrerTeaL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,questionTitle,userId "
				+ " from report,account,question where report.reporterID=account.id and report.reportedID=question.questionId "
				+ " and account.duty='3' and report.reportStatus='0' and reportType='1'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("questionTitle"));
				r.setTopicUserId(rs.getString("userId"));
				QrerTeaL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return QrerTeaL;
	}
	
	@Override
	public ArrayList<Report> getQRerSeniorInfo() {
		//得到举报话题信息-举报人-学长学姐
		ArrayList<Report> QrerSenL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,questionTitle,userId "
				+ " from report,account,question where report.reporterID=account.id and report.reportedID=question.questionId "
				+ " and account.duty='2' and report.reportStatus='0' and reportType='1'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("questionTitle"));
				r.setTopicUserId(rs.getString("userId"));
				QrerSenL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return QrerSenL;
	}
	
	
	@Override
	public boolean delQuestion(String list[]) {
		//删除问题
		//判断report表是否为0 
		//1.message发送系统通知给问题回答者/所有举报用户
		//2.question表删除数据(answer)
		//3.report表置1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		for(int i = 0;i < list.length; i ++){
		String sql1="select reportedID,reportReason,questionTitle,userId,reportStatus "
				+ " from report,question where report.reportedID=question.questionId "
				+ " and reportID='"+list[i]+"' ";
		//获得相关信息
		rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
		String reporterID=null,reportReason=null,reportTime=null,questionTitle=null,userId=null,reportedID=null;
		int reportStatus=0;
		try {
			if(rs.next()){
				reportedID=rs.getString("reportedID");
				questionTitle=rs.getString("questionTitle");
				userId=rs.getString("userId");
				reportReason=rs.getString("reportReason");
				reportStatus=rs.getInt("reportStatus");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reportStatus==0){
		
		String sql2="insert into message (id,context,time,isRead) values ('"+userId+"','问题“"+questionTitle+"”在"+df.format(date)+"时被举报删除！原因："+reportReason+"','"+df.format(date)+"','0') ";
		//发送系统通知给问题发起人
		ConnectionManager.excuteIUD(sql2, conn, ptmt);
		
		String sql="select reporterID,reportTime from report where reportedID='"+reportedID+"' and reportStatus='0' and reportType='1' ";
		//获得所有举报该问题的人的信息
		ResultSet s=null;
		s=ConnectionManager.excuteSelect(sql,conn,ptmt,s);
		try {
			while(s.next()){
				reporterID=s.getString("reporterID");
				reportTime=s.getString("reportTime");
			String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','您在"+reportTime+"时举报的问题“"+questionTitle+"”举报成功，问题已删除！','"+df.format(date)+"','0') ";
			//发送系统通知给所有举报该问题的人
			ConnectionManager.excuteIUD(sql3, conn, ptmt);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}try {
			s.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql4="select answerId from answer where questionId='"+reportedID+"'";
		//获得回答id
		ResultSet r=null;
		r=ConnectionManager.excuteSelect(sql4,conn,ptmt,r);
		int answerId=0;
		try {
			while(r.next()){
				answerId=r.getInt("answerId");
				String sql5="delete from answer where answerId='"+answerId+"'";
				//删除回答
				ConnectionManager.excuteIUD(sql5, conn, ptmt);
				r.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql7="delete from question where questionId='"+reportedID+"'";
		//删除问题
		ConnectionManager.excuteIUD(sql7, conn, ptmt);
		
		String sql8="update report set reportStatus='1' where reportedID='"+reportedID+"' and reportType='1' ";
		//report中所有举报该问题的数据置1
		ConnectionManager.excuteIUD(sql8, conn, ptmt);
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean QueBU(String list[],int bday) {
		//封禁问题发起者
		//查看用户是否被封禁-否
		//account表置4
		//blockedusers表添加数据
		//发送系统通知给被封禁用户
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
			String sql1="select reportReason,userId "
					+ " from report,question where report.reportedID=question.questionId "
					+ " and reportID='"+list[i]+"' ";
			String topicUserId=null,reportReason=null;
			rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
			try {
				while(rs.next()){
					topicUserId=rs.getString("userId");
					reportReason=rs.getString("reportReason");
					
					String sql2="select status from account where id='"+topicUserId+"'";
					//查看用户是否被封禁
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(topicUserId);
						addTableBU(topicUserId,reportReason,bday);
						sentMtoBU(topicUserId,reportReason,bday);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}

	@Override
	public boolean QueBUer(String list[],int bday) {
		//封禁举报人
		//判断report表是否为0
		//1.查看用户是否被封禁
		//2.封禁用户 account4
		//3.blockedusers添加数据
		//4.发送系统通知给被封禁用户
		//5.report表置1 该用户举报的所有数据
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
			String sql1="select reporterID,reportStatus "
					+ " from report "
					+ " where reportID='"+list[i]+"' ";
			String reporterID=null;
			int reportStatus=0;
			rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
			try {
				while(rs.next()){
					reporterID=rs.getString("reporterID");
					reportStatus=rs.getInt("reportStatus");
					if(reportStatus==0){
					String sql2="select status from account where id='"+reporterID+"'";
					//查看用户是否被封禁
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(reporterID);
						addTableBU(reporterID,"举报问题不被受理次数过多",bday);
						sentMtoBU(reporterID,"举报问题不被受理次数过多",bday);
						String sql3="select reportID from report where reporterID='"+reporterID+"' and reportStatus='0' and reportType='1' ";
						ResultSet r3=null;
						r3=ConnectionManager.excuteSelect(sql3,conn,ptmt,r3);
						while(r3.next()){
							int reportID=r3.getInt("reportID");
							String sql4="update report set reportStatus='1' where reportID='"+reportID+"'";
							ConnectionManager.excuteIUD(sql4, conn, ptmt);
						}r3.close();
					}
					String sql5="update report set reportStatus='1' where reportID='"+list[i]+"'";
					ConnectionManager.excuteIUD(sql5, conn, ptmt);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}

	@Override
	public boolean neglectQ(String list[]) {
		//忽略该条举报问题信息
		//1.该条举报消息状态置1
		//2.发送系统通知给该用户 --举报不被受理
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
		String sql1="update report set reportStatus='1' where reportID='"+list[i]+"' ";
		ConnectionManager.excuteIUD(sql1, conn, ptmt);
		String sql2="select reporterID from report where reportID='"+list[i]+"' ";
		rs=ConnectionManager.excuteSelect(sql2,conn,ptmt,rs);
		try {
			if(rs.next()){
				String reporterID=rs.getString("reporterID");
				String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','举报问题未受理','"+df.format(date)+"','0') ";
				ConnectionManager.excuteIUD(sql3, conn, ptmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	public ArrayList<Report> getARerStudentInfo() {
		//得到举报回答信息-举报人-学生 
		ArrayList<Report> ArerStuL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,answerContent,answerUserId "
				+ " from report,account,answer where report.reporterID=account.id and report.reportedID=answer.answerId "
				+ " and account.duty='1' and report.reportStatus='0' and reportType='3'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("answerContent"));
				r.setTopicUserId(rs.getString("answerUserId"));
				ArerStuL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return ArerStuL;
	}
	
	@Override
	public ArrayList<Report> getARerTeacherInfo() {
		//得到举报回答信息-举报人-老师 
		ArrayList<Report> ArerTeaL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,answerContent,answerUserId "
				+ " from report,account,answer where report.reporterID=account.id and report.reportedID=answer.answerId "
				+ " and account.duty='3' and report.reportStatus='0' and reportType='3'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("answerContent"));
				r.setTopicUserId(rs.getString("answerUserId"));
				ArerTeaL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return ArerTeaL;
	}
	
	@Override
	public ArrayList<Report> getARerSeniorInfo() {
		//得到举报回答信息-举报人-学长学姐
		ArrayList<Report> ArerSenL=new ArrayList<Report>();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select reportID,reporterID,reportedID,reportReason,reportTime,answerContent,answerUserId "
				+ " from report,account,answer where report.reporterID=account.id and report.reportedID=answer.answerId "
				+ " and account.duty='2' and report.reportStatus='0' and reportType='3'";
		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);
		try {
			while(rs.next()){
				Report r=new Report();
				r.setReportID(rs.getInt("reportID"));
				r.setReporterID(rs.getString("reporterID"));
				r.setReportedID(rs.getString("reportedID"));
				r.setReportReason(rs.getString("reportReason"));
				r.setReportTime(rs.getString("reportTime"));
				r.setTopicName(rs.getString("answerContent"));
				r.setTopicUserId(rs.getString("answerUserId"));
				ArerSenL.add(r); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
		return ArerSenL;
	}
	
	@Override
	public boolean delAnswer(String list[]) {
		//删除回答
		//判断report表是否为0 
		//1.message发送系统通知给回答者/所有举报用户
		//2.answer表删除数据
		//3.report表置1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		for(int i = 0;i < list.length; i ++){
		String sql1="select reportedID,reportReason,answerContent,answerUserId,reportStatus "
				+ " from report,answer where report.reportedID=answer.answerId "
				+ " and reportID='"+list[i]+"' ";
		//获得相关信息
		rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
		String reporterID=null,reportReason=null,reportTime=null,answerContent=null,answerUserId=null,reportedID=null;
		int reportStatus=0;
		try {
			if(rs.next()){
				reportedID=rs.getString("reportedID");
				answerContent=rs.getString("answerContent");
				answerUserId=rs.getString("answerUserId");
				reportReason=rs.getString("reportReason");
				reportStatus=rs.getInt("reportStatus");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reportStatus==0){
		
		String sql2="insert into message (id,context,time,isRead) values ('"+answerUserId+"','回答“"+answerContent+"”在"+df.format(date)+"时被举报删除！原因："+reportReason+"','"+df.format(date)+"','0') ";
		//发送系统通知给回答者
		ConnectionManager.excuteIUD(sql2, conn, ptmt);
		
		String sql="select reporterID,reportTime from report where reportedID='"+reportedID+"' and reportStatus='0' and reportType='3' ";
		//获得所有举报该回答的人的信息
		ResultSet s=null;
		s=ConnectionManager.excuteSelect(sql,conn,ptmt,s);
		try {
			while(s.next()){
				reporterID=s.getString("reporterID");
				reportTime=s.getString("reportTime");
			String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','您在"+reportTime+"时举报的回答“"+answerContent+"”举报成功，回答已删除！','"+df.format(date)+"','0') ";
			//发送系统通知给所有举报该问题的人
			ConnectionManager.excuteIUD(sql3, conn, ptmt);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}try {
			s.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql7="delete from answer where answerId='"+reportedID+"'";
		//删除回答
		ConnectionManager.excuteIUD(sql7, conn, ptmt);
		
		String sql8="update report set reportStatus='1' where reportedID='"+reportedID+"' and reportType='3' ";
		//report中所有举报该问题的数据置1
		ConnectionManager.excuteIUD(sql8, conn, ptmt);
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean AnsBU(String list[],int bday) {
		//封禁回答者
		//查看用户是否被封禁-否
		//account表置4
		//blockedusers表添加数据
		//发送系统通知给被封禁用户
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
			String sql1="select reportReason,answerUserId "
					+ " from report,answer where report.reportedID=answer.answerId "
					+ " and reportID='"+list[i]+"' ";
			String answerUserId=null,reportReason=null;
			rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
			try {
				while(rs.next()){
					answerUserId=rs.getString("answerUserId");
					reportReason=rs.getString("reportReason");
					
					String sql2="select status from account where id='"+answerUserId+"'";
					//查看用户是否被封禁
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(answerUserId);
						addTableBU(answerUserId,reportReason,bday);
						sentMtoBU(answerUserId,reportReason,bday);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean AnsBUer(String list[],int bday) {
		//封禁举报人
		//判断report表是否为0
		//1.查看用户是否被封禁
		//2.封禁用户 account4
		//3.blockedusers添加数据
		//4.发送系统通知给被封禁用户
		//5.report表置1 该用户举报的所有数据
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
			String sql1="select reporterID,reportStatus "
					+ " from report "
					+ " where reportID='"+list[i]+"' ";
			String reporterID=null;
			int reportStatus=0;
			rs=ConnectionManager.excuteSelect(sql1,conn,ptmt,rs);
			try {
				while(rs.next()){
					reporterID=rs.getString("reporterID");
					reportStatus=rs.getInt("reportStatus");
					if(reportStatus==0){
					String sql2="select status from account where id='"+reporterID+"'";
					//查看用户是否被封禁
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(reporterID);
						addTableBU(reporterID,"举报回答不被受理次数过多",bday);
						sentMtoBU(reporterID,"举报回答不被受理次数过多",bday);
						String sql3="select reportID from report where reporterID='"+reporterID+"' and reportStatus='0' and reportType='3' ";
						ResultSet r3=null;
						r3=ConnectionManager.excuteSelect(sql3,conn,ptmt,r3);
						while(r3.next()){
							int reportID=r3.getInt("reportID");
							String sql4="update report set reportStatus='1' where reportID='"+reportID+"'";
							ConnectionManager.excuteIUD(sql4, conn, ptmt);
						}r3.close();
					}
					String sql5="update report set reportStatus='1' where reportID='"+list[i]+"'";
					ConnectionManager.excuteIUD(sql5, conn, ptmt);
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean neglectA(String list[]) {
		//忽略该条举报回答信息
		//1.该条举报消息状态置1
		//2.发送系统通知给该用户 --举报不被受理
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		for(int i = 0;i < list.length; i ++){
		String sql1="update report set reportStatus='1' where reportID='"+list[i]+"' ";
		ConnectionManager.excuteIUD(sql1, conn, ptmt);
		String sql2="select reporterID from report where reportID='"+list[i]+"' ";
		rs=ConnectionManager.excuteSelect(sql2,conn,ptmt,rs);
		try {
			if(rs.next()){
				String reporterID=rs.getString("reporterID");
				String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','举报回答未受理','"+df.format(date)+"','0') ";
				ConnectionManager.excuteIUD(sql3, conn, ptmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public int views(String time) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionManager.close(ptmt,rs,conn);
		return view;
	}
}
