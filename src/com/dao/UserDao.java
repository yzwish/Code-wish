package com.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import com.model.Account;
import com.model.CollegeStu;
import com.model.HighSchool;
import com.model.HighSchoolStu;
import com.model.Major;
import com.model.Message;
import com.model.Province;
import com.model.Teacher;
import com.model.TransRecord;
import com.model.University;
import com.model.VolunSchool;

public class UserDao {

	//登录
	public static int login(Account account){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
    	//处理密码
    	account.setPassword(UserDao.changeSpecialSymbols(account.getPassword()));
		try {
			
	    	String sql="select * from Account where id='"+account.getId()+"' "
	    			+"and password='"+account.getPassword()+"' ";
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    	
	    	if(rs==null||!rs.next())
	    	{
				return 0;//失败
			}
			else{
				account.setDuty(rs.getInt(3));
				return 1;//成功
			}
	    	
	    			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		}finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
	}

	//得到省/市和高中
	public static HashMap<String,ArrayList<HighSchool>> getPCHS(HashMap<String,String> proMap){
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {	
			synchronized(UserDao.class){
		    	String sql="select B.provinceId,A.hId,A.hname,B.provinceName from highschool A,province B where A.provinceId= B.provinceId ";
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	HashMap<String,ArrayList<HighSchool>> hm=new HashMap<String,ArrayList<HighSchool>>();
		    	while(rs.next()){
		    		proMap.put(rs.getString(1), rs.getString(4));
		    		HighSchool hs=new HighSchool(rs.getString(2),rs.getString(3));
		    		ArrayList<HighSchool> hsList=hm.get(rs.getString(1));
		    		if(hsList==null||hsList.isEmpty()){
		    			hsList=new ArrayList<HighSchool>();
		    		}
		    		hsList.add(hs);
	    			hm.put(rs.getString(1), hsList);
		    	}
		    	System.out.println("-------");
		    	return hm;
			}
			
	    			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	//得到省/市和大学
	public static HashMap<String,ArrayList<University>> getPC(HashMap<String,String> proMap){
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {
			synchronized(UserDao.class){
		    	String sql="select B.provinceId,A.universityId,A.universityName,B.provinceName from university A,province B where A.provinceId= B.provinceId ";
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	HashMap<String,ArrayList<University>> hm=new HashMap<String,ArrayList<University>>();
		    	while(rs.next()){
		    		proMap.put(rs.getString(1), rs.getString(4));
		    		University hs=new University(rs.getString(2),rs.getString(3));
		    		System.out.println(hs.toString());
		    		ArrayList<University> hsList=hm.get(rs.getString(1));
		    		if(hsList==null||hsList.isEmpty()){
		    			hsList=new ArrayList<University>();
		    		}
		    		hsList.add(hs);
	    			hm.put(rs.getString(1), hsList);
		    	}
		    	System.out.println("-------");
		    	return hm;
			}
			
	    			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	
	//根据大学id得到专业列表
	public static ArrayList<Major> getMajorList(University us){
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {
			synchronized(UserDao.class){
		    	String sql="select A.majorId,A.majorName from major A,university B,majorlog C "
		    			+ "where A.majorId = C.majorId and B.universityId = C.universityId "
		    			+ "and B.universityId= '"+us.getUniversityId()+"' ";
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	  
		    	ArrayList<Major> mList=new ArrayList<Major>();
		    	while(rs.next()){
		    		Major mj=new Major(rs.getString(1),rs.getString(2));
		    		System.out.println(mj.toString());
		    		mList.add(mj);
		    	}
		    	System.out.println("-------");
		    	return mList;
			}				    			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
		
	//检查账户是否存在
	public static int hasAccount(Account account){
		
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {

			synchronized(UserDao.class){
		    	String sql="select * from Account where id='"+account.getId()+"' ";
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    	
		    	if(rs==null||!rs.next())
		    	{
					return 1;//可以注册
				}
				else{
					return 0;//用户名存在
				}
		    	
			}
			
	    			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
    	

	}


	//注册
	public static int register(Account ac,HighSchoolStu hsstu,CollegeStu cs,Teacher tea){
			//记录初始id
			String init_id=ac.getId();
			//进行字符串转换
			ac.setId(changeSpecialSymbols(ac.getId()));
			ac.setPassword(changeSpecialSymbols(ac.getPassword()));
			
			
			Connection conn = ConnectionManager.getInstance().getConnection();  
	        String sql=null;   
	        try {
			
	        	if(ac.getDuty()==1){
	    			hsstu.setId(changeSpecialSymbols(hsstu.getId()));
	    			hsstu.setSafetyAnswer(changeSpecialSymbols(hsstu.getSafetyAnswer()));			          
	    			sql="insert into account values(?,?,?,?)";
	        		PreparedStatement ptmt1 = conn.prepareStatement(sql);				
	        		ptmt1.setString(1, ac.getId());
	        		ptmt1.setString(2, ac.getPassword());
	        		ptmt1.setInt(3, 1);
	        		ptmt1.setInt(4, 0);  		
	        		int rs1 = ptmt1.executeUpdate();	
	        		//服务器错误
	        		if(rs1==0)
	        			return 3;
	        		sql="insert into highschoolstu values(?,?,?,?,?,?,?)";
	        		PreparedStatement ptmt2 = conn.prepareStatement(sql);				
	        		ptmt2.setString(1, hsstu.getId());
	        		if(!hsstu.getHsage().equals(""))        		
	        			ptmt2.setInt(2, Integer.parseInt(hsstu.getHsage()));
	        		else{
	        			ptmt2.setInt(2,0);
	        		}
	        		if(hsstu.gethId().equals("unknown"))
	        			hsstu.sethId(null);
	        		ptmt2.setString(3,hsstu.gethId());
	        		ptmt2.setInt(4,hsstu.getSafetyProblem());
	        		//处理
	        		hsstu.setSafetyAnswer(UserDao.changeSpecialSymbols(hsstu.getSafetyAnswer()));
	        		ptmt2.setString(5, hsstu.getSafetyAnswer());
	        		if(hsstu.getSex().equals("unknown"))
	        			hsstu.setSex(null);
	        		ptmt2.setString(6, hsstu.getSex());
	        		ptmt2.setDouble(7, 0.0);   		
	        		int rs2 = ptmt2.executeUpdate();	
	        		//服务器错误
	        		if(rs2==0)
	        			return 3;
	        		if(ptmt1!=null)
	        			ptmt1.close();
	        		if(ptmt2!=null)
	        			ptmt2.close();
	        		
	    		}	
	    		else if(ac.getDuty()==2){
	    			cs.setId(changeSpecialSymbols(cs.getId()));
	    			cs.setSafetyAnswer(changeSpecialSymbols(cs.getSafetyAnswer()));
	    			sql="insert into account values(?,?,?,?)";
	        		PreparedStatement ptmt1 = conn.prepareStatement(sql);				
	        		ptmt1.setString(1, ac.getId());
	        		ptmt1.setString(2, ac.getPassword());
	        		ptmt1.setInt(3, 2);
	        		ptmt1.setInt(4, 2);  		
	        		int rs1 = ptmt1.executeUpdate();	
	        		//服务器错误
	        		if(rs1==0)
	        			return 3;
	        		sql="insert into collegestu values(?,?,?,?,?,?,?,?,?,?,?,?)";
	        		PreparedStatement ptmt2 = conn.prepareStatement(sql);				
	        		ptmt2.setString(1, cs.getId());
	        		if(!cs.getHsage().equals(""))              		
	        			ptmt2.setInt(2, cs.getAge());
	        		else{
	        			ptmt2.setInt(2, 0);
	        		}
	        		ptmt2.setString(3, cs.getSex());
	        		ptmt2.setString(4, cs.getRealName());
	        		ptmt2.setString(5, cs.getUniversityId());
	        		ptmt2.setString(6, cs.getMajorId());
	        		cs.setsYear(Integer.parseInt(cs.getSyear()));
	        		ptmt2.setInt(7, cs.getsYear());
	        		String fileName = cs.getPic().getOriginalFilename();  
	        		fileName="verifiedImg/"+init_id+fileName.substring(fileName.indexOf("."),fileName.length());
	        		ptmt2.setString(8,fileName);
	        		ptmt2.setTimestamp(9,new Timestamp(System.currentTimeMillis()));
	        		ptmt2.setInt(10,cs.getSafetyProblem());
	        		//处理
	        		cs.setSafetyAnswer(UserDao.changeSpecialSymbols(cs.getSafetyAnswer()));
	        		ptmt2.setString(11, cs.getSafetyAnswer());
	        		ptmt2.setDouble(12, 0.0);   		
	        		int rs2 = ptmt2.executeUpdate();	
	        		//服务器错误
	        		if(rs2==0)
	        			return 3;
	    			//上传图片
	        		if(!uploadPic(ac,cs,tea,init_id))
	        			return 3;
	        		if(ptmt1!=null)
	        			ptmt1.close();
	        		if(ptmt2!=null)
	        			ptmt2.close();
	    		}
	    		else if(ac.getDuty()==3){
	    			tea.setId(changeSpecialSymbols(tea.getId()));
	    			tea.setSafetyAnswer(changeSpecialSymbols(tea.getSafetyAnswer()));
	    			sql="insert into account values(?,?,?,?)";
	        		PreparedStatement ptmt1 = conn.prepareStatement(sql);				
	        		ptmt1.setString(1, ac.getId());
	        		ptmt1.setString(2, ac.getPassword());
	        		ptmt1.setInt(3, 3);
	        		ptmt1.setInt(4, 2);  		
	        		int rs1 = ptmt1.executeUpdate();	
	        		//服务器错误
	        		if(rs1==0)
	        			return 3;
	        		sql="insert into teacher values(?,?,?,?,?,?,?,?,?,?,?,?)";
	        		PreparedStatement ptmt2 = conn.prepareStatement(sql);				
	        		ptmt2.setString(1, tea.getId());
	        		if(!tea.getHsage().equals(""))              		
	        			ptmt2.setInt(2, tea.getAge());
	        		else{
	        			ptmt2.setInt(2, 0);
	        		}
	        		ptmt2.setString(3, tea.getSex());
	        		ptmt2.setString(4, tea.getRealName());
	        		ptmt2.setString(5, tea.getUi());
	        		ptmt2.setString(6, tea.getResDirection());
	        		ptmt2.setString(7, tea.getPosition());
	        		String fileName = tea.getPic().getOriginalFilename();  
	        		fileName="verifiedImg/"+init_id+fileName.substring(fileName.indexOf("."),fileName.length());
	        		ptmt2.setString(8,fileName);
	        		ptmt2.setTimestamp(9,new Timestamp(System.currentTimeMillis()));
	        		ptmt2.setInt(10,tea.getSafetyProblem());
	        		//处理
	        		tea.setSafetyAnswer(UserDao.changeSpecialSymbols(tea.getSafetyAnswer()));
	        		ptmt2.setString(11, tea.getSafetyAnswer());
	        		ptmt2.setDouble(12, 0.0);   		
	        		int rs2 = ptmt2.executeUpdate();	
	        		//服务器错误
	        		if(rs2==0)
	        			return 3;
	    			//上传图片
	        		if(!uploadPic(ac,cs,tea,init_id))
	        			return 3;
	        		if(ptmt1!=null)
	        			ptmt1.close();
	        		if(ptmt2!=null)
	        			ptmt2.close();
	    		}
	        	if(conn!=null){
	        		conn.close();
	        	}
	    		return 1;
	        }
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 3;
			}
			
					
	}
	//上传审核图片，ac为账号，
	public static boolean uploadPic(Account ac,CollegeStu cs,Teacher tea,String init_id){
		
		String fileName;
		if(ac.getDuty()==2){
			fileName = cs.getPic().getOriginalFilename();  
    		fileName=init_id+fileName.substring(fileName.indexOf("."),fileName.length());
		}
		else if(ac.getDuty()==3){ 
			fileName = tea.getPic().getOriginalFilename();  
    		fileName=init_id+fileName.substring(fileName.indexOf("."),fileName.length());
		}
		else{
			return false;
		}
        String path=ConnectionManager.picUploadPath;
        File targetFile = new File(path, fileName);  
        
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        } 
        //保存  
        try {  
            cs.getPic().transferTo(targetFile);  
            return true;
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;
        }  
	}
	
	//将 '改为''
	public static String changeSpecialSymbols(String str){
		if(str !=null)		
			str=str.replaceAll("'", "''");
		return str;
	}
	
	public static void showBasicInfo(HighSchoolStu hss,CollegeStu cs,Teacher tea,String id,int duty){

		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {			
	    	String sql=null;
	    	if(duty==1){	    	
	    		sql="select B.sex,B.age,B.hId,C.provinceId from account A,highschoolstu B,highschool C "
	    				+ "where A.id=B.id and A.id='"+id+"' "
	    						+ "and B.hId=C.hId ";	    	
	    		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    		
		    	if(rs==null||!rs.next())
		    	{

				}
				else{
					hss.setSex(rs.getString(1));
					hss.setAge(rs.getInt(2));
					hss.sethId(rs.getString(3));
					hss.setProvinceId(rs.getString(4));
				}
	    		    
	    	}
	    	else if(duty==2){
	    		sql="select B.sex,B.age,A.status from account A,collegestu B where A.id=B.id and A.id='"+id+"' ";
	    		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    		
		    	if(rs==null||!rs.next())
		    	{

				}
				else{
					cs.setSex(rs.getString(1));
					cs.setAge(rs.getInt(2));
					cs.setStatus(rs.getInt(3));
				}
	    	}
	    	else if(duty==3){
	    		sql="select B.sex,B.age,A.status from account A,teacher B where A.id=B.id and A.id='"+id+"' ";
	    		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    		
		    	if(rs==null||!rs.next())
		    	{

				}
				else{
					tea.setSex(rs.getString(1));
					tea.setAge(rs.getInt(2));
					tea.setStatus(rs.getInt(3));
				}
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	//设置基础信息
	public static int setBasicInfo(String duty,String sex,String age,String hId,String id){

		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {

	    	String sql=null;
	    	int tempage=0;
	    	if(sex!=null&&sex.equals("unknown"))
	    		sex=null;
    		if(age!=null&&!age.equals(""))
    			tempage=Integer.parseInt(age);
	    	if(duty.equals("1")){
	    		
	    		System.out.println(tempage);
	    		if(hId.equals("unknown"))
	    			hId=null;
	    		if(sex==null&&hId!=null){
	    			sql="update highschoolstu set sex=null, age="+tempage
		    				+",hId='"+hId+"' where id= '"+id+"'";
	    		}
	    		else if(hId==null&&sex!=null){
	    			sql="update highschoolstu set sex= '"+sex
		    				+"', age="+tempage
		    				+",hId=null where id= '"+id+"'";
	    		}
	    		else if(hId==null&&sex==null)
	    			sql="update highschoolstu set sex=null, age="+tempage
    				+",hId=null where id= '"+id+"'";
	    		else{
	    			sql="update highschoolstu set sex= '"+sex
		    				+"', age="+tempage
		    				+",hId='"+hId+"' where id= '"+id+"'";
	    		}
	    		ConnectionManager.excuteIUD(sql, conn, ptmt);
	    	}
	    	else if(duty.equals("2")||duty.equals("3")){
	    		String[] roles={"","collegestu","teacher"};
	    		int tempduty=Integer.parseInt(duty);
	    		String role=roles[tempduty];
                //sex不可能为null
	    		sql="update "+role+" set sex= '"+sex
	    				+"', age="+tempage
	    				+" where id='"+id+"'";
	    		ConnectionManager.excuteIUD(sql, conn, ptmt);
	    	}
	    	return 1;
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return 3;
		}finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	//显示审核信息
	public static void showAuditInfo(CollegeStu cs,Teacher tea,String id,int duty){

		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
    	ResultSet rs=null;
		try {			
	    	String sql=null;
	    	if(duty==2){
	    		sql="select B.sYear,A.status,C.provinceId,B.universityId,B.majorId,B.realName,B.picSrc "
	    				+ "from account A,collegestu B,university C "
	    				+ "where A.id='"+id+"' and B.id=A.id and B.universityId=C.universityId ";
	    		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    		
		    	if(rs==null||!rs.next())
		    	{

				}
				else{
					cs.setsYear(rs.getInt(1));
					cs.setStatus(rs.getInt(2));
					cs.setProvinceId(rs.getString(3));
					cs.setUniversityId(rs.getString(4));
					cs.setMajorId(rs.getString(5));
					cs.setRealName(rs.getString(6));
					cs.setPicSrc(rs.getString(7));
				}
	    	}
	    	else if(duty==3){
	    		sql="select B.position,A.status,C.provinceId,B.universityId,B.realName,B.picSrc,B.resDirection "
	    				+ "from account A,teacher B,university C "
	    				+ "where A.id='"+id+"' and B.id=A.id and B.universityId=C.universityId ";
	    		rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	    		
		    	if(rs==null||!rs.next())
		    	{

				}
				else{
					tea.setPosition(rs.getString(1));
					tea.setStatus(rs.getInt(2));
					tea.setProvinceId(rs.getString(3));
					tea.setUniversityId(rs.getString(4));
					tea.setRealName(rs.getString(5));
					tea.setPicSrc(rs.getString(6));
					tea.setResDirection(rs.getString(7));

				}
	    	}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	
	//设置审核信息		
	public static int setAuditInfo(CollegeStu cs,Teacher tea,String id,String duty){

		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;

		try {

	    	String sql=null;
	    	if(duty.equals("2")){
	    		//得到原来上传的图片信息并删除
	    		sql="select picSrc from collegestu where id='"+id+"'";
	    		ptmt=conn.prepareStatement(sql);
	    		rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
	    		if(rs.next()){
	    			cs.setPicSrc(rs.getString(1));
	    		}
	    		if(rs!=null)
	    			rs.close();
	    		if(ptmt!=null)
	    			ptmt.close();
	    		//删除原来图片
	    		String origin_picSrc=cs.getPicSrc().substring(12,cs.getPicSrc().length());
	    		File file=new File(ConnectionManager.picUploadPath+origin_picSrc);
	    		file.delete();
    		   //更新数据信息
	    		sql="update collegestu set universityId=?,majorId=?, "
	    				+ "sYear=?,realName=?,picSrc=?,requestTime=? "
	    				+ "where id=?";
	    		ptmt=conn.prepareStatement(sql);
	    		ptmt.setString(1, cs.getUniversityId());
	    		ptmt.setString(2, cs.getMajorId());
	    		ptmt.setInt(3,Integer.parseInt(cs.getSyear()));
	    		ptmt.setString(4, cs.getRealName());
	    		String fileName = cs.getPic().getOriginalFilename();  
        		fileName="verifiedImg/"+id+fileName.substring(fileName.indexOf("."),fileName.length());
        		ptmt.setString(5,fileName);
	    		ptmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
	    		ptmt.setString(7, id);
	    		int result = ptmt.executeUpdate();	
        		//服务器错误
        		if(result==0)
        			return 3;
        		else{
        			if(ptmt!=null)
        				ptmt.close();
        			//更新账号状态
        			sql="update account set status=2 where id='"+id+"'";
        			ptmt=conn.prepareStatement(sql);
        			
        			if(!ConnectionManager.excuteIUD(sql, conn, ptmt)){
        				if(ptmt!=null)
            				ptmt.close();
        				return 3;
        			}
        				
        			//上传审核图片
        			else{
        				Account ac=new Account();
        				ac.setId(id);
        				ac.setDuty(Integer.parseInt(duty));
        				if(!UserDao.uploadPic(ac, cs, tea, ac.getId()))
        					return 3;
        			}
        			
        		}
	    	}
	    	else if(duty.equals("3")){
               //取原来图片信息
	    		sql="select picSrc from teacher where id='"+id+"'";
	    		ptmt=conn.prepareStatement(sql);
	    		rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
	    		if(rs.next()){
	    			tea.setPicSrc(rs.getString(1));
	    		}
	    		if(rs!=null)
	    			rs.close();
	    		if(ptmt!=null)
	    			ptmt.close();
	    		//删除原来图片
	    		String origin_picSrc=tea.getPicSrc().substring(12,tea.getPicSrc().length());
	    		System.out.println("os:"+origin_picSrc);
	    		File file=new File(ConnectionManager.picUploadPath+origin_picSrc);
	    		file.delete();
	    		//更新数据信息
	    		sql="update teacher set universityId=?, "
	    				+ "resDirection=?,position=?,realName=?,picSrc=?,requestTime=? "
	    				+ "where id=?";
	    		ptmt=conn.prepareStatement(sql);
	    		ptmt.setString(1, tea.getUi());
	    		ptmt.setString(2, tea.getResDirection());
	    		ptmt.setString(3,tea.getPosition());
	    		ptmt.setString(4, tea.getRealName());
	    		String fileName = tea.getPic().getOriginalFilename();  
        		fileName="verifiedImg/"+id+fileName.substring(fileName.indexOf("."),fileName.length());
	    		ptmt.setString(5,fileName);
	    		ptmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
	    		ptmt.setString(7, id);
	    		int result = ptmt.executeUpdate();	
        		//服务器错误
        		if(result==0)
        			return 3;
        		else{
        			if(ptmt!=null)
        			ptmt.close();
        			//更新账号状态
        			sql="update account set status=2 where id='"+id+"'";
        			if(!ConnectionManager.excuteIUD(sql, conn, ptmt)){
        				if(ptmt!=null)
            				ptmt.close();
        				return 3;
        			}
        			//上传审核图片
        			else{
        				Account ac=new Account();
        				ac.setId(id);
        				ac.setDuty(Integer.parseInt(duty));
        				if(!UserDao.uploadPic(ac, cs, tea, ac.getId()))
        					return 3;
        			}
        		}
	    	}
	    	if(conn!=null)
	    		conn.close();
	    	return 1;
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return 3;
		}
	}

	//显示志愿信息
	public static ArrayList<VolunSchool> getVSList(String id){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		ArrayList<VolunSchool> vsList=new ArrayList<VolunSchool>();
		try {		    	
			String sql="select rId,nameDuty,vsId from volunschool where id='"+id+"' ";
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
	    	while(rs.next()){
	    		VolunSchool temp=new VolunSchool();
	    		temp.setrId(rs.getString(1));
	    		temp.setNameDuty(rs.getInt(2));
	    		temp.setVsId(rs.getInt(3));
	    		vsList.add(temp);
	    	} 
	    	return vsList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	
	//得到所有省/市
	public static ArrayList<Province> getPList(){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		ArrayList<Province> pList=new ArrayList<Province>();
		try {		    	
			
			String sql="select * from province";
			synchronized(sql){
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
	    	while(rs.next()){
	    		Province temp=new Province();
	    		temp.setProvinceId(rs.getString(1));
	    		temp.setProvinceName(rs.getString(2));
	    		pList.add(temp);
	    	} 
			}
	    	return pList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	//得到所有大学
	public static ArrayList<University> getUList(){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		ArrayList<University> uList=new ArrayList<University>();
		try {		
			
			String sql="select universityId,universityName from university";
			synchronized(sql){
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
	    	while(rs.next()){
	    		University temp=new University();
	    		temp.setUniversityId(rs.getString(1));
	    		temp.setUniversityName(rs.getString(2));
	    		uList.add(temp);
	    	} 
			}
	    	return uList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	//得到所有专业
	public static ArrayList<Major> getMList(){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		ArrayList<Major> mList=new ArrayList<Major>();
		try {	
			
			String sql="select majorId,majorName from major";
			synchronized(sql){
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
	    	while(rs.next()){
	    		Major temp=new Major();
	    		temp.setMajorId(rs.getString(1));
	    		temp.setMajorName(rs.getString(2));
	    		mList.add(temp);
	    	} 
			}
	    	return mList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
		
	//删除志愿信息
	public static boolean deleteVS(int vsId){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		try {	
			
			String sql="delete from volunschool where vsId="+vsId+"";
			synchronized(sql){
	    	if(!ConnectionManager.excuteIUD(sql, conn, ptmt))	    		
	    		return false;
			}
	    	return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			ConnectionManager.close(conn,ptmt);
		}		
		
	}
	//添加志愿信息
	public static boolean addVS(VolunSchool vs){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		ResultSet rs=null;
		PreparedStatement ptmt=null;						
		String sql=null;
		try {
		
				sql="select vsId from volunschool where nameDuty="+vs.getNameDuty()
				       +" and rId='"+vs.getrId()+"'";
				ptmt=conn.prepareStatement(sql);
				rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
				if(rs.next())
					return false;
				else{
					if(rs!=null)
						rs.close();
					if(ptmt!=null)
						ptmt.close();
				}
				sql="insert into volunschool values(null,?,?,?)";
				int n=0;
	      		ptmt=conn.prepareStatement(sql);
	      		ptmt.setString(1,vs.getId());
	      		ptmt.setString(2,vs.getrId());
	      		ptmt.setInt(3, vs.getNameDuty());
	      		n= ptmt.executeUpdate(); 
	      		if(n!=0)return true;
	     		return false;  	         
		}
		 catch (SQLException e) {  
              e.printStackTrace();  	          
              return false;
		 }finally{
			 ConnectionManager.close(conn, ptmt);
		 } 	
		
	}

	//修改志愿信息
	public static boolean modifyVS(VolunSchool vs){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		try {	
			sql="select vsId from volunschool where nameDuty="+vs.getNameDuty()
		       +" and rId='"+vs.getrId()+"'";
			ptmt=conn.prepareStatement(sql);
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(rs.next())
				return false;
			else{
				if(rs!=null)
					rs.close();
				if(ptmt!=null)
					ptmt.close();
			}
			int n=0;
			sql="update volunschool set nameDuty=?,rId=? where vsId=?";
			ptmt=conn.prepareStatement(sql);
	      	ptmt.setInt(1,vs.getNameDuty());
	      	ptmt.setString(2, vs.getrId());
	      	ptmt.setInt(3, vs.getVsId());
	      	n= ptmt.executeUpdate(); 
      		if(n!=0)
      			return true;
	    	return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
		
	}
	
	public static int modifyPassword(String id,String op,String np){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		//处理'
		op=UserDao.changeSpecialSymbols(op);
		np=UserDao.changeSpecialSymbols(np);
		try {	
			sql="select a.id from account a where a.id='"+id
		       +"' and a.password='"+op+"'";
			ptmt=conn.prepareStatement(sql);
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(!rs.next())//找不到原账号
				return 2;//原密码错误
			else{
				if(rs!=null)
					rs.close();
				if(ptmt!=null)
					ptmt.close();
			}
			sql="update account set password= '"+np+"' where id='"+id+"'";
			if(!ConnectionManager.excuteIUD(sql, conn, ptmt))
				return 2;
			else	    	
				return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;//服务器错误
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
	}
	public static int modifySafetyInfo(String id,String op,int sp,String sa,int duty){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		op=UserDao.changeSpecialSymbols(op);
		sa=UserDao.changeSpecialSymbols(sa);
		try {	
			String[] table={"","highschoolstu","collegestu","teacher"};
			sql="select a.id from account a where a.id='"+id
				       +"' and a.password='"+op+"'";
			ptmt=conn.prepareStatement(sql);
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(!rs.next())//找不到原账号
				return 2;//原密码错误
			else{
				if(rs!=null)
					rs.close();
				if(ptmt!=null)
					ptmt.close();
			}
			sql="update "+table[duty]+" set safetyProblem= "+sp
					 +" ,safetyAnswer='"+sa+"'"
					 + " where id='"+id+"'";
			if(!ConnectionManager.excuteIUD(sql, conn, ptmt))
				return 2;
			else	    	
				return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;//服务器错误
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
	}	
	public static Double showBalance(String id,int duty){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		Double balance=0.0;
		try{
			String[] table={"","highschoolstu","collegestu","teacher"};
			String sql="select balance from "+table[duty]+" where id='"+id+"'";
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(rs.next())
				balance=rs.getDouble(1);
			return balance;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return balance;//服务器错误
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
	}
	public static int deposite(String id,int duty,double num){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		Double balance=0.0;
		
		String[] table={"","highschoolstu","collegestu","teacher"};
		String sql="insert into transrecord values(null,?,?,?,?)";
		try{
			
	      		ptmt=conn.prepareStatement(sql);
	      		ptmt.setString(1, id);
	      		ptmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
	      		ptmt.setDouble(3, num);
	      		ptmt.setInt(4, 1);
	      		int n= ptmt.executeUpdate(); 	       
	      		if(n==0)
	      			return 3;
	      		else{
	      			sql="select balance from "+table[duty]+" where id='"+id+"'";
	    			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
	    			if(rs.next())
	    				balance=rs.getDouble(1);
	    			else
	    				return 3;
	    			balance=num+balance;
	    			sql="update "+table[duty]+" set balance= "+balance+" where id='"+id+"'";
	    			if(!ConnectionManager.excuteIUD(sql, conn, ptmt)){
	    				return 3;
	    			}
	    			else{
	    				return 1;//成功
	    			}
	      		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;//服务器错误
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
	}
	
	public static Double showTodayDrawNum(String id){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		Double today_draw=0.0;
		try{
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		     String today=df.format(new Date());		
		     String sql="select sum(virtualNum) "
		    		 +"from transrecord "
		    		 +"where time like '"+today+"%' and way= 3 "
		    		 +"group by virtualNum ";			
		     rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);			
		     if(rs.next())
				today_draw=rs.getDouble(1);			
		     return today_draw;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return today_draw;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
	}


	public static int draw(String id,int duty,double num){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		Double balance=0.0;
		
		String[] table={"","highschoolstu","collegestu","teacher"};
		String sql=null;
		
		try{		
			//取余额数目
			sql="select balance from "+table[duty]+" where id='"+id+"'";
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(rs.next())
				balance=rs.getDouble(1);
			else
				return 3;
			if(balance<num)
				return 2;//余额不够
			else	    			
				balance=balance-num;
			//更新余额
			sql="update "+table[duty]+" set balance= "+balance+" where id='"+id+"'";
			if(!ConnectionManager.excuteIUD(sql, conn, ptmt)){
				return 3;
			}
			else{
				//添加交易记录
				sql="insert into transrecord values(null,?,?,?,?)";
				ptmt=conn.prepareStatement(sql);
	      		ptmt.setString(1, id);
	      		ptmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
	      		ptmt.setDouble(3, num);
	      		ptmt.setInt(4, 3);
	      		int n= ptmt.executeUpdate(); 	       
	      		if(n==0)
	      			return 3;
	      		else{
	      			return 1;//成功
	      		}
				
			}      		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;//服务器错误
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}		
	}
	
	//显示交易记录
	public static ArrayList<TransRecord> showTransRecord(String from,String to,int way,String id){
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		ArrayList<TransRecord> trList=new ArrayList<TransRecord>();
		//处理日期
		from=from.replace("年", "-").replace("月", "-").replace("日","");
		to=to.replace("年", "-").replace("月", "-").replace("日","");
		try {		
			if(from.equals(to)){
				sql="select time,virtualNum,way from transrecord"
						+ " where id='"+id+"' and time like '"+from+"%' "
								+ "and way="+way
								+" order by time desc";
			}		
			else{
				sql="select time,virtualNum,way from transrecord"
						+ " where id='"+id+"' and time>= '"+from+"' "
								+ "and time<= '"+to+"' "
										+ "and way="+way
										+" order by time desc";
			}
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
	    	while(rs.next()){
	    		TransRecord temp=new TransRecord();
	    		temp.setTime(rs.getTimestamp(1));
	    		temp.setVirtualNum(rs.getDouble(2));
	    		temp.setWay(rs.getInt(3));
	    		trList.add(temp);
	    	} 
	    	return trList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}
	

	//核对密保答案
    public static int checkSafetyInfo(String id,int sp,String sa){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String[] table={"","highschoolstu","collegestu","teacher"};
		String sql=null;
		int duty=0;
		//处理sa
		sa=UserDao.changeSpecialSymbols(sa);
		try{	
			 sql="select duty from account where id= '"+id+"'";
			 rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);	
			 if(!rs.next())
				 return 2;//找不到该用户
			 else{
				 duty=rs.getInt(1);
				 if(rs!=null)
					 rs.close();
			 }
		     sql="select id  "
		    		 +" from "+table[duty]
		    		 +" where safetyProblem = "+sp
		    		 +" and safetyAnswer ='"+sa+"' "
		    		 		+ "and id= '"+id+"'";			
		     rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);			
		     if(!rs.next())
				return 2;	
		     else{
		    	 return 1;
		     }
		     
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}	
    }
    
    //设置新密码
    public static int setNewPW(String id,String np){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		String sql=null;
		np=UserDao.changeSpecialSymbols(np);
		try{	
		     sql="update account set password ='"+np+"' where id= '"+id+"' ";			
		     if(!ConnectionManager.excuteIUD(sql, conn, ptmt))
		    	 return 3;
		     else
		    	 return 1;
		     
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 3;
		} finally{
			ConnectionManager.close(conn,ptmt);
		}	
    }
	
    public static ArrayList<Message> showMessage(String id){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		ArrayList<Message> msgList=new ArrayList<Message>();
		try{
			sql="select mId,context,time,isRead from message where id= '"+id+"' "
					+ " order by time desc";
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			while(rs.next()){
				Message temp=new Message();
				temp.setmId(rs.getInt(1));
				temp.setContext(rs.getString(2));
				temp.setTime(rs.getTimestamp(3));
				temp.setIsRead(rs.getInt(4));
				msgList.add(temp);
			}
			return msgList;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
		finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
    }

    //设置系统通知为已读
    public static void readMessage(int mId){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		String sql=null;
		try{	
		     
			sql="update message set isRead = 1 where mId= '"+mId+"'";			
		    ConnectionManager.excuteIUD(sql, conn, ptmt);
		     
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionManager.close(conn,ptmt);
		}	
    }
    
    public static int getUnReadMsgNum(String id){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		int unReadMsgNum=0;
		try{
			sql="select count(*) from message where id= '"+id+"' "
					+ "and isRead=0 "
					+ " order by time desc";
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(rs.next()){
				unReadMsgNum=rs.getInt(1);
			}
			return unReadMsgNum;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return -1;
		}
		finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
    }
    public static int getTotalMsg(String id){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		int MsgNum=0;
		try{
			sql="select count(*) from message where id= '"+id+"' ";
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			if(rs.next()){
				MsgNum=rs.getInt(1);
			}
			return MsgNum;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return -1;
		}
		finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
    }
    public static ArrayList<Message> getMsgListPaging(String id,int pageNumber, int pageSize){
    	Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		String sql=null;
		ArrayList<Message> msgList=new ArrayList<Message>();
		try{
			sql="select mId,context,time,isRead from message where id= '"+id+"' "
					+ " order by time desc"
					+" limit "+(pageNumber-1)*pageSize+","+pageSize; 
					
			rs=ConnectionManager.excuteSelect(sql, conn, ptmt, rs);
			while(rs.next()){
				Message temp=new Message();
				temp.setmId(rs.getInt(1));
				temp.setContext(rs.getString(2));
				temp.setTime(rs.getTimestamp(3));
				temp.setIsRead(rs.getInt(4));
				msgList.add(temp);
			}
			return msgList;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
		finally{
			ConnectionManager.close(conn, rs, ptmt);
		}
    }



    //得到账户状态
	public static Account getAccountInfo(String id){
		Account a=new Account();
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {	
			String sql="select * from account where id='"+id+"'";
			synchronized(sql){
	    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
	    	if(rs.next()){
	    		a.setId(rs.getString("id"));
	    		a.setDuty(rs.getInt("duty"));
	    		a.setStatus(rs.getInt("status"));
	    		a.setPassword(rs.getString("password"));
	    	} 
			}
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			ConnectionManager.close(conn,rs,ptmt);
		}
	}

}
