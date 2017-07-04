package com.controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.UserPageDao;
import com.model.*;

@Controller
@RequestMapping("/comment")
public class UserPageController {
	
	@RequestMapping("/getUser.do")
	protected String getUser(HttpSession session,Model model,String visitId ) throws SQLException
	{
		
		System.out.println("进入UserPageController");
		
		visitId="AprilTea";
		//-----------------
		String duty=(String) session.getAttribute("duty");
		String id=(String) session.getAttribute("id");
		String visitDuty="";
		
		UserPageDao upd=new UserPageDao();
		visitDuty=upd.findDuty(visitId)+"";
		
		System.out.println("获取visitDuty："+visitDuty);
		System.out.println("获取duty："+duty);
		System.out.println("获取ID："+id);
		System.out.println("获取visitID："+visitId);
		model.addAttribute("visitDuty",visitDuty);
		
		if(visitDuty.equals("1"))
		{//获取学生信息
			
			//基本信息
			HighSchoolStu h=new HighSchoolStu();
			h=upd.findHighschoolstu(visitId);
			if(h!=null)
			{
			System.out.println("设置session:visitUser");
			model.addAttribute("visitUser",h);	
			}
			//隐私设置
			Hprivates hp = new Hprivates();
			hp=upd.findHprivates(visitId);
			if(hp!=null)
			{
		    System.out.println("设置session:visitStuPrivate");
			model.addAttribute("visitStuPrivate",hp);
			}
			//志愿信息
			Map<String, String> vs=new HashMap<String, String> ();
			vs=upd.findVolunSchool(visitId);
			if(vs!=null)	
			{
				System.out.println("设置session:visitVolunSchool");
				model.addAttribute("visitVolunSchool",vs);
			}
			//话题信息
			List<Topic> t=new ArrayList<Topic>();
			t=upd.findTopic(visitId);
			if(t!=null)	
			{
				System.out.println("设置session:visitTopic");
				model.addAttribute("visitTopic",t);
			}
			//问题
			List<Question> q=new ArrayList<Question>();
			q=upd.findQuestion(visitId);
			if(q!=null)	
			{
				System.out.println("设置session:visitQuestion");
				model.addAttribute("visitQuestion",q);
			}
			//回答
			List<Answer> a=new ArrayList<Answer>();
			a=upd.findAnswer(visitId);
			if(a!=null)	
			{
				System.out.println("设置session:visitAnswer");
				model.addAttribute("visitAnswer",a);
			}
			//关注
		    Map<String, String> f=new HashMap<String, String> ();
			f=upd.findFollow(visitId);
			if(f!=null)	
			{
				System.out.println("设置session:visitFollow");
				model.addAttribute("visitFollow",f);
			}
			
			
			//参加活动
			List<EnrollTable> et=new ArrayList<EnrollTable>();
			et=upd.findenrollEnrollTable(visitId);
			if(t!=null)	
			{
				System.out.println("设置session:visitEnrollTable");
				model.addAttribute("visitEnrollTable",et);
			}
			
			
		}
		else if(visitDuty.equals("2"))
		{//获取学长学姐信息
			
			
           //基本信息
			CollegeStu h=new CollegeStu();
			h=upd.findCollegeStu(visitId);
			if(h!=null)
			{
			System.out.println("设置session:visitUser");
			model.addAttribute("visitUser",h);	
			}
			//隐私
			Cprivates hp = new Cprivates();
			hp=upd.findUprivates(visitId);
			if(hp!=null)
			{
		    System.out.println("设置session:visitUniPrivate");
			model.addAttribute("visitUniPrivate",hp);
			}
			
			//话题
			List<Topic> t=new ArrayList<Topic>();
			t=upd.findTopic(visitId);
			if(t!=null)	
			{
				System.out.println("设置session:visitTopic");
				model.addAttribute("visitTopic",t);
			}
			
			
			//回答
			List<Answer> a=new ArrayList<Answer>();
			a=upd.findAnswer(visitId);
			if(a!=null)	
			{
				System.out.println("设置session:visitAnswer");
				model.addAttribute("visitAnswer",a);
			}
			
			//关注
		    Map<String, String> f=new HashMap<String, String> ();
			f=upd.findFollow(visitId);
			if(f!=null)	
			{
				System.out.println("设置session:visitFollow");
				model.addAttribute("visitFollow",f);
			}
			
			
			
		}
		else if(visitDuty.equals("3"))
		{//获取老师信息
			
			
			//基本信息
			Teacher h=new Teacher();
			h=upd.findTeacher(visitId);
			if(h!=null)
			{
			System.out.println("设置session:visitUser");
			model.addAttribute("visitUser",h);	
			}
			//隐私
			Tprivates hp = new Tprivates();
			hp=upd.findTprivates(visitId);
			if(hp!=null)
			{
		    System.out.println("设置session:visitTeaPrivate");
			model.addAttribute("visitTeaPrivate",hp);
			}
			
			//话题
			List<Topic> t=new ArrayList<Topic>();
			t=upd.findTopic(visitId);
			if(t!=null)	
			{
				System.out.println("设置session:visitTopic");
				model.addAttribute("visitTopic",t);
			}
			
			
			//回答
			List<Answer> a=new ArrayList<Answer>();
			a=upd.findAnswer(visitId);
			if(a!=null)	
			{
				System.out.println("设置session:visitAnswer");
				model.addAttribute("visitAnswer",a);
			}
			
			//关注
		    Map<String, String> f=new HashMap<String, String> ();
			f=upd.findFollow(visitId);
			if(f!=null)	
			{
				System.out.println("设置session:visitFollow");
				model.addAttribute("visitFollow",f);
			}
			//举办活动
			List<Activity> ac=new ArrayList<Activity>();
			ac=upd.findActivity(visitId);
			if(ac!=null)	
			{
				System.out.println("设置session:visitActivity");
				model.addAttribute("visitActivity",ac);
			}
		}
		
		if(duty.equals("1"))
		{//获取学生隐私设置
			
			/*
			HighSchoolStu h=new HighSchoolStu();
			h=upd.findHighschoolstu(id);
			if(h!=null)
			{
			System.out.println("设置session:visitUser");
			model.addAttribute("visitUser",h);	
			}
			*/
			Hprivates hp = new Hprivates();
			hp=upd.findHprivates(id);	
			if(hp!=null)
			{
			System.out.println("设置session:userStuPrivate");
			model.addAttribute("userStuPrivate",hp);
			}
			
		}
		else if(duty.equals("2"))
		{//获取学长学姐隐私设置
			Cprivates hp = new Cprivates();
			hp=upd.findUprivates(id);	
			if(hp!=null)
			{
			System.out.println("设置session:userUniPrivate");
			model.addAttribute("userUniPrivate",hp);
			}
		}
		else if(duty.equals("3"))
		{//获取老师隐私设置
			
			Tprivates hp = new Tprivates();
			hp=upd.findTprivates(id);	
			if(hp!=null)
			{
			System.out.println("设置session:userTeaPrivate");
			model.addAttribute("userTeaPrivate",hp);
			}
			
		}
			
		return "jsp/userPage";
		
	}
	
	@RequestMapping("/changePrivate.do")
	protected String changePrivate( @RequestBody List<String> check_val,HttpSession session,Model model) throws SQLException
	{   System.out.println("----changePrivate.do");
	     for(String s:check_val)
		System.out.println(s);
	     
	     
	     String duty=(String) session.getAttribute("duty");
		 String id=(String) session.getAttribute("id");
		 UserPageDao upd=new  UserPageDao();
		 Boolean b=upd.changePrivate(id, duty, check_val);
		 if(b==true)
		 model.addAttribute("message","修改成功");
		 else
		 model.addAttribute("message","修改失败");
		 System.out.println("修改结果："+b);
		 return "comment/getUser.do";
	}
	
	
	

}
