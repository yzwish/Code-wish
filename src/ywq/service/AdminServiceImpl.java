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
		//�õ���ʦ�����Ϣ
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
		//��ʦ���ͨ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','���ͨ����','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");
        	}
        }
        
			ConnectionManager.close(ptmt,rs,conn);
		
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkTNotPass(String list[],String Treason) {
		//��ʦ���δͨ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','���δͨ�������ɣ�"+Treason+"','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public ArrayList<CheckSenior> getSeniorInfo() {
		//�õ�ѧ��ѧ�������Ϣ
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
		//ѧ��ѧ�����ͨ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','���ͨ����','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkSNotPass(String list[],String Treason) {
		//ѧ��ѧ�����δͨ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
        		String sqlm="insert into message (id,context,time,isRead) values ('"+list[i]+"','���δͨ�������ɣ�"+Treason+"','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");
        	}
        }
        ConnectionManager.close(ptmt,rs,conn);
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public ArrayList<BlockedUser> getBlockedStudentInfo() {
		//�õ�����߿�����Ϣ
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
		//�õ������ʦ��Ϣ
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
		//�õ����ѧ��ѧ����Ϣ
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
		//�õ��ٱ��û���Ϣ-�ٱ���-ѧ�� 
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
		//�õ��ٱ��û���Ϣ-�ٱ���-��ʦ
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
		//�õ��ٱ��û���Ϣ-�ٱ���-ѧ��ѧ��
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
		//�õ��ٱ��û���Ϣ-���ٱ���-ѧ�� 
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
		//�õ��ٱ��û���Ϣ-���ٱ���-��ʦ
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
		//�õ��ٱ��û���Ϣ-���ٱ���-ѧ��ѧ��
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
		//������ٱ��û�
		//0.�ж������Ƿ��Ѵ���
		//1.��account�����û�״̬��Ϊ���			(�ж��Ƿ��ѱ���� �ѷ��1 δ���0)
		//2.blockedusers���������		����+ʱ��	(δ���0--�������)
		//3.����ϵͳ֪ͨ	�ٱ��û�&���ٱ��û�			(δ���0--�򱻾ٱ��û�����ϵͳ֪ͨ)
		//4.report����״̬��1---���оٱ����û��ļ�¼����Ϊ1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;

        for(int i = 0;i < list.length; i ++){
        	
        	String sqlc="select reportStatus from report where reportID='"+list[i]+"'";
        	//���������Ƿ񱻴���
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
        	//�����û��Ƿ��ѱ����
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
        	//��account�����û�״̬��Ϊ���
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		String id=null,reason=null;
        		String sqlg="select reportedID,reportReason from report where reportID='"+list[i]+"'";
        		//�õ����ٱ��û�id���ٱ�ԭ��
        		ResultSet rsn=null;
        		rsn=ConnectionManager.excuteSelect(sqlg,conn,ptmt,rsn);
        		try {
    			if(rsn.next()){
    				id=rsn.getString("reportedID");
    				reason=rsn.getString("reportReason");
    				
    			if(check2!=4){
        		String sqlb="insert into blockedusers values ('"+id+"','"+reason+"','"+Bday+"','"+df.format(date)+"')";
        		//blockedusers���������
        		boolean b=ConnectionManager.excuteIUD(sqlb,conn,ptmt);
        		if(b==true) System.out.println("��ӷ���û���¼�ɹ���");
        		else System.out.println("��ӷ���û���¼ʧ�ܣ�");
    			
        		
        		String sqlm="insert into message (id,context,time,isRead) values ('"+id+"' , '����"+df.format(date)+"ʱ�������ԭ��"+reason+"�����������"+Bday+"��' , '"+df.format(date)+"','0') ";
        		//�򱻷���û�����ϵͳ֪ͨ
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("���ٱ��û�ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("���ٱ��û�ϵͳ֪ͨ����ʧ�ܣ�");
        		
    			}
        		String sqla="select reporterID,reportReason,reportTime from report where reportedID='"+id+"' and reportType='4'";
        		//�õ��ٱ����û��ľٱ��������Ϣ
        		ResultSet a=null;
        		a=ConnectionManager.excuteSelect(sqla,conn,ptmt,a);
        		try {
        			while(a.next()){
        				String rerID=a.getString("reporterID");
        				String reReason=a.getString("reportReason");
        				String rTime=a.getString("reportTime");
        				
                		String sqlm2="insert into message (id,context,time,isRead) values ('"+rerID+"','�ٱ��ɹ�������"+rTime+"ʱ�ٱ��û�id��"+id+"��ԭ��"+reReason+"���ѳɹ��ٱ���','"+df.format(date)+"','0')";
                		//��ٱ��û�����ϵͳ֪ͨ
                		boolean q=ConnectionManager.excuteIUD(sqlm2,conn,ptmt);
                		if(q==true) System.out.println("�ٱ��û�ϵͳ֪ͨ���ͳɹ���");
                		else System.out.println("�ٱ��û�ϵͳ֪ͨ����ʧ�ܣ�");
        				
        			}
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}a.close();
        		      		
        		
        		
        		String sqls="update report set reportStatus='1' where report.reportedID='"+id+"' and reportType='4'";
        		//�ٱ��ñ�����û��ľٱ���¼ȫ��1
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
		//����ٱ���
		//0.�ж������Ƿ��Ѵ���
		//1.��account�����û�״̬��Ϊ���   (�ж��Ƿ��ѱ���� �ѷ��1 δ���0)
		//2.blockedusers���������		�������(�ٱ��������������)+���ʱ��   (δ���0--�������)
		//3.����ϵͳ֪ͨ	�ٱ��û�			 (δ���0--����֪ͨ)
		//4.report����״̬��1---���и��û��ٱ��ļ�¼����Ϊ1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		
		 for(int i = 0;i < list.length; i ++){
	        	
	        	String sqlc="select reportStatus from report where reportID='"+list[i]+"'";
	        	//���������Ƿ񱻴���
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
	            	//�����û��Ƿ��ѱ����
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
	        	//��account�����û�״̬��Ϊ���
	        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
	        	if(s==false) flag=false;
	        	if(s==true){
	        		String id=null;
	        		String sqlg="select reporterID from report where reportID='"+list[i]+"'";
	        		//�õ��ٱ��û�id
	        		ResultSet rsn=null;
	        		rsn=ConnectionManager.excuteSelect(sqlg,conn,ptmt,rsn);
	        		try {
	    			if(rsn.next()){
	    				id=rsn.getString("reporterID");
	    				
	    			if(check2!=4)	{

	        		String sqlb="insert into blockedusers values ('"+id+"','�ٱ��������������','"+Bday+"','"+df.format(date)+"')";
	        		//blockedusers���������
	        		boolean b=ConnectionManager.excuteIUD(sqlb,conn,ptmt);
	        		if(b==true) System.out.println("��ӷ���û���¼�ɹ���");
	        		else System.out.println("��ӷ���û���¼ʧ�ܣ�");
	        		
	        		
	        		String sqlm="insert into message (id,context,time,isRead) values ('"+id+"' , '����"+df.format(date)+"ʱ�������ԭ�򣺾ٱ�������������࣬���������"+Bday+"��' , '"+df.format(date)+"','0') ";
	        		//�򱻷���û�����ϵͳ֪ͨ
	        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
	        		if(t==true) System.out.println("�ٱ���ϵͳ֪ͨ���ͳɹ���");
	        		else System.out.println("�ٱ���ϵͳ֪ͨ����ʧ�ܣ�");
	        		
	    			}      		
	        		
	        		
	        		String sqls="update report set reportStatus='1' where report.reporterID='"+id+"' and reportType='4'";
	        		//�þٱ��ߵľٱ���¼ȫ��1
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
		//���Ը����ٱ��û���Ϣ
		//1.�����ٱ���Ϣ״̬��1
		//2.����ϵͳ֪ͨ�����û� --�ٱ���������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;

        for(int i = 0;i < list.length; i ++){
        	String sql="update report set reportStatus='1' where reportID='"+list[i]+"'";
        	//�ø����ٱ���Ϣ״̬Ϊ1
        	boolean s=ConnectionManager.excuteIUD(sql,conn,ptmt);
        	if(s==false) flag=false;
        	if(s==true){
        		
        		String sqlg="select reporterID from report where reportID='"+list[i]+"'";
        		//��þٱ���id
        		rs=ConnectionManager.excuteSelect(sqlg,conn,ptmt,rs);
        		try {
        			if(rs.next()){
        				String rerID=rs.getString("reporterID");
        				
                		String sqlm="insert into message (id,context,time,isRead) values ('"+rerID+"','�ٱ��û�δ����','"+df.format(date)+"','0') ";
                		//��ٱ��˷���ϵͳ֪ͨ
                		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
                		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
                		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");

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
		//�鿴id�Ƿ����
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
		//�鿴id�Ƿ񱻷��
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
		//account���и��û�״̬��4-���״̬
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
		//blockedusers���������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
		//����ϵͳ֪ͨ��������û�
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="insert into message (id,context,time,isRead) values ('"+privateUserId+"' , '����"+df.format(date)+"ʱ�������ԭ��"+closureReason+"�����������"+closureDay+"��' , '"+df.format(date)+"','0') ";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public boolean sentMtoER(String privateUserId){
		//���ٱ����û����û�����ϵͳ֪ͨ--�ٱ��ɹ�
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
				
				String sql2="insert into message (id,context,time,isRead) values ('"+reporterID+"','�ٱ��ɹ�������"+reportTime+"ʱ�ٱ��û�id��"+privateUserId+"��ԭ��"+reportReason+"���ѳɹ��ٱ���','"+df.format(date)+"','0')";
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
		//report���оٱ����û�������״̬��Ϊ1
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
		//account���и��û�״̬��01-����״̬
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
		//blockedusers��ɾ������
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
		//����ϵͳ֪ͨ��������û�
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="insert into message (id,context,time,isRead) values ('"+privateUserId+"' , '����"+df.format(date)+"ʱ����⡣ ' , '"+df.format(date)+"','0') ";
		boolean t=ConnectionManager.excuteIUD(sql,conn,ptmt);
		ConnectionManager.close(ptmt,rs,conn);
		return t;
	}
	
	@Override
	public ArrayList<Topic> getTopicInfo() {
		//�õ����������Ϣ
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
		//�������ͨ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
        		String sqlm="insert into message (id,context,time,isRead) values ('"+topicUserId+"','���⡰"+topicName+"����"+df.format(date)+"���ͨ����','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");
        	}
        }
			ConnectionManager.close(ptmt,rs,conn);
		
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public boolean checkTOPNotPass(String list[],String Treason) {
		//������˲�ͨ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
        		String sqlm="insert into message (id,context,time,isRead) values ('"+topicUserId+"','���⡰"+topicName+"����"+df.format(date)+"���δͨ����ԭ��"+Treason+"','"+df.format(date)+"','0') ";
        		boolean t=ConnectionManager.excuteIUD(sqlm,conn,ptmt);
        		if(t==true) System.out.println("ϵͳ֪ͨ���ͳɹ���");
        		else System.out.println("ϵͳ֪ͨ����ʧ�ܣ�");
        	}
        }
			ConnectionManager.close(ptmt,rs,conn);
		
		if(flag==true) return true;
		else return false;
	}
	
	@Override
	public ArrayList<Report> getTRerStudentInfo() {
		//�õ��ٱ�������Ϣ-�ٱ���-ѧ�� 
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
		//�õ��ٱ�������Ϣ-�ٱ���-��ʦ 
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
		//�õ��ٱ�������Ϣ-�ٱ���-ѧ��ѧ��
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
		//ɾ������
		//�ж�report���Ƿ�Ϊ0 
		//1.message����ϵͳ֪ͨ�����ⷢ����/���оٱ��û�
		//2.topic��ɾ������(question,answer)
		//3.report����1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		for(int i = 0;i < list.length; i ++){
		String sql1="select reportedID,reportReason,topicName,topicUserId,reportStatus "
				+ " from report,topic where report.reportedID=topic.topicId "
				+ " and reportID='"+list[i]+"' ";
		//��������Ϣ
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
		
		String sql2="insert into message (id,context,time,isRead) values ('"+topicUserId+"','���⡰"+topicName+"����"+df.format(date)+"ʱ���ٱ�ɾ����ԭ��"+reportReason+"','"+df.format(date)+"','0') ";
		//����ϵͳ֪ͨ�����ⷢ����
		ConnectionManager.excuteIUD(sql2, conn, ptmt);
		
		String sql="select reporterID,reportTime from report where reportedID='"+reportedID+"' and reportStatus='0' and reportType='2' ";
		//������оٱ��û�����˵���Ϣ
		ResultSet s=null;
		s=ConnectionManager.excuteSelect(sql,conn,ptmt,s);
		try {
			while(s.next()){
				reporterID=s.getString("reporterID");
				reportTime=s.getString("reportTime");
			String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','����"+reportTime+"ʱ�ٱ��Ļ��⡰"+topicName+"���ٱ��ɹ���������ɾ����','"+df.format(date)+"','0') ";
			//����ϵͳ֪ͨ�����оٱ��û������
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
		//�������id
		ResultSet r=null;
		r=ConnectionManager.excuteSelect(sql4,conn,ptmt,r);
		String questionId=null;
		try {
			while(r.next()){
				questionId=r.getString("questionId");
				String sql5="delete from answer where questionId='"+questionId+"'";
				//ɾ���ش�
				ConnectionManager.excuteIUD(sql5, conn, ptmt);
				String sql6="delete from question where questionId='"+questionId+"'";
				//ɾ������
				ConnectionManager.excuteIUD(sql6, conn, ptmt);
				r.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql7="delete from topic where topicId='"+reportedID+"'";
		//ɾ������
		ConnectionManager.excuteIUD(sql7, conn, ptmt);
		
		String sql8="update report set reportStatus='1' where reportedID='"+reportedID+"' and reportType='2' ";
		//report�����оٱ��û����������1
		ConnectionManager.excuteIUD(sql8, conn, ptmt);
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean TopBU(String list[],int bday) {
		//������ⷢ����
		//�鿴�û��Ƿ񱻷��-��
		//account����4
		//blockedusers���������
		//����ϵͳ֪ͨ��������û�
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
					//�鿴�û��Ƿ񱻷��
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
		//����ٱ���
		//�ж�report���Ƿ�Ϊ0
		//1.�鿴�û��Ƿ񱻷��
		//2.����û� account4
		//3.blockedusers�������
		//4.����ϵͳ֪ͨ��������û�
		//5.report����1 ���û��ٱ�����������
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
					//�鿴�û��Ƿ񱻷��
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(reporterID);
						addTableBU(reporterID,"�ٱ����ⲻ�������������",bday);
						sentMtoBU(reporterID,"�ٱ����ⲻ�������������",bday);
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
		//���Ը����ٱ�������Ϣ
		//1.�����ٱ���Ϣ״̬��1
		//2.����ϵͳ֪ͨ�����û� --�ٱ���������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
				String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','�ٱ�����δ����','"+df.format(date)+"','0') ";
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
		//�õ��ٱ�������Ϣ-�ٱ���-ѧ�� 
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
		//�õ��ٱ�������Ϣ-�ٱ���-��ʦ 
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
		//�õ��ٱ�������Ϣ-�ٱ���-ѧ��ѧ��
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
		//ɾ������
		//�ж�report���Ƿ�Ϊ0 
		//1.message����ϵͳ֪ͨ������ش���/���оٱ��û�
		//2.question��ɾ������(answer)
		//3.report����1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		for(int i = 0;i < list.length; i ++){
		String sql1="select reportedID,reportReason,questionTitle,userId,reportStatus "
				+ " from report,question where report.reportedID=question.questionId "
				+ " and reportID='"+list[i]+"' ";
		//��������Ϣ
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
		
		String sql2="insert into message (id,context,time,isRead) values ('"+userId+"','���⡰"+questionTitle+"����"+df.format(date)+"ʱ���ٱ�ɾ����ԭ��"+reportReason+"','"+df.format(date)+"','0') ";
		//����ϵͳ֪ͨ�����ⷢ����
		ConnectionManager.excuteIUD(sql2, conn, ptmt);
		
		String sql="select reporterID,reportTime from report where reportedID='"+reportedID+"' and reportStatus='0' and reportType='1' ";
		//������оٱ���������˵���Ϣ
		ResultSet s=null;
		s=ConnectionManager.excuteSelect(sql,conn,ptmt,s);
		try {
			while(s.next()){
				reporterID=s.getString("reporterID");
				reportTime=s.getString("reportTime");
			String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','����"+reportTime+"ʱ�ٱ������⡰"+questionTitle+"���ٱ��ɹ���������ɾ����','"+df.format(date)+"','0') ";
			//����ϵͳ֪ͨ�����оٱ����������
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
		//��ûش�id
		ResultSet r=null;
		r=ConnectionManager.excuteSelect(sql4,conn,ptmt,r);
		int answerId=0;
		try {
			while(r.next()){
				answerId=r.getInt("answerId");
				String sql5="delete from answer where answerId='"+answerId+"'";
				//ɾ���ش�
				ConnectionManager.excuteIUD(sql5, conn, ptmt);
				r.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql7="delete from question where questionId='"+reportedID+"'";
		//ɾ������
		ConnectionManager.excuteIUD(sql7, conn, ptmt);
		
		String sql8="update report set reportStatus='1' where reportedID='"+reportedID+"' and reportType='1' ";
		//report�����оٱ��������������1
		ConnectionManager.excuteIUD(sql8, conn, ptmt);
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean QueBU(String list[],int bday) {
		//������ⷢ����
		//�鿴�û��Ƿ񱻷��-��
		//account����4
		//blockedusers���������
		//����ϵͳ֪ͨ��������û�
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
					//�鿴�û��Ƿ񱻷��
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
		//����ٱ���
		//�ж�report���Ƿ�Ϊ0
		//1.�鿴�û��Ƿ񱻷��
		//2.����û� account4
		//3.blockedusers�������
		//4.����ϵͳ֪ͨ��������û�
		//5.report����1 ���û��ٱ�����������
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
					//�鿴�û��Ƿ񱻷��
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(reporterID);
						addTableBU(reporterID,"�ٱ����ⲻ�������������",bday);
						sentMtoBU(reporterID,"�ٱ����ⲻ�������������",bday);
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
		//���Ը����ٱ�������Ϣ
		//1.�����ٱ���Ϣ״̬��1
		//2.����ϵͳ֪ͨ�����û� --�ٱ���������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
				String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','�ٱ�����δ����','"+df.format(date)+"','0') ";
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
		//�õ��ٱ��ش���Ϣ-�ٱ���-ѧ�� 
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
		//�õ��ٱ��ش���Ϣ-�ٱ���-��ʦ 
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
		//�õ��ٱ��ش���Ϣ-�ٱ���-ѧ��ѧ��
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
		//ɾ���ش�
		//�ж�report���Ƿ�Ϊ0 
		//1.message����ϵͳ֪ͨ���ش���/���оٱ��û�
		//2.answer��ɾ������
		//3.report����1
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		Date date = new Date();
		boolean flag=true;
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		for(int i = 0;i < list.length; i ++){
		String sql1="select reportedID,reportReason,answerContent,answerUserId,reportStatus "
				+ " from report,answer where report.reportedID=answer.answerId "
				+ " and reportID='"+list[i]+"' ";
		//��������Ϣ
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
		
		String sql2="insert into message (id,context,time,isRead) values ('"+answerUserId+"','�ش�"+answerContent+"����"+df.format(date)+"ʱ���ٱ�ɾ����ԭ��"+reportReason+"','"+df.format(date)+"','0') ";
		//����ϵͳ֪ͨ���ش���
		ConnectionManager.excuteIUD(sql2, conn, ptmt);
		
		String sql="select reporterID,reportTime from report where reportedID='"+reportedID+"' and reportStatus='0' and reportType='3' ";
		//������оٱ��ûش���˵���Ϣ
		ResultSet s=null;
		s=ConnectionManager.excuteSelect(sql,conn,ptmt,s);
		try {
			while(s.next()){
				reporterID=s.getString("reporterID");
				reportTime=s.getString("reportTime");
			String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','����"+reportTime+"ʱ�ٱ��Ļش�"+answerContent+"���ٱ��ɹ����ش���ɾ����','"+df.format(date)+"','0') ";
			//����ϵͳ֪ͨ�����оٱ����������
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
		//ɾ���ش�
		ConnectionManager.excuteIUD(sql7, conn, ptmt);
		
		String sql8="update report set reportStatus='1' where reportedID='"+reportedID+"' and reportType='3' ";
		//report�����оٱ��������������1
		ConnectionManager.excuteIUD(sql8, conn, ptmt);
		}
		}
		ConnectionManager.close(ptmt,rs,conn);
		return flag;
	}
	
	@Override
	public boolean AnsBU(String list[],int bday) {
		//����ش���
		//�鿴�û��Ƿ񱻷��-��
		//account����4
		//blockedusers���������
		//����ϵͳ֪ͨ��������û�
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
					//�鿴�û��Ƿ񱻷��
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
		//����ٱ���
		//�ж�report���Ƿ�Ϊ0
		//1.�鿴�û��Ƿ񱻷��
		//2.����û� account4
		//3.blockedusers�������
		//4.����ϵͳ֪ͨ��������û�
		//5.report����1 ���û��ٱ�����������
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
					//�鿴�û��Ƿ񱻷��
					int flag2=0;
					ResultSet r2=null;
					r2=ConnectionManager.excuteSelect(sql2,conn,ptmt,r2);
					if(r2.next()){
						flag2=r2.getInt("status");
					}r2.close();
					if(flag2!=4){
						account4(reporterID);
						addTableBU(reporterID,"�ٱ��ش𲻱������������",bday);
						sentMtoBU(reporterID,"�ٱ��ش𲻱������������",bday);
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
		//���Ը����ٱ��ش���Ϣ
		//1.�����ٱ���Ϣ״̬��1
		//2.����ϵͳ֪ͨ�����û� --�ٱ���������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
				String sql3="insert into message (id,context,time,isRead) values ('"+reporterID+"','�ٱ��ش�δ����','"+df.format(date)+"','0') ";
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
