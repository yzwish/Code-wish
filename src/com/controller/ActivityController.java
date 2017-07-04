package com.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dao.ActivityDao;
import com.dao.ActivityQuestionDao;
import com.dao.EnrollTableDao;
import com.dao.MessageDao;
import com.dao.UserDao;
import com.model.Account;
import com.model.Activity;
import com.model.ActivityAnswer;
import com.model.ActivityQuestion;
import com.model.EnrollTable;
import com.model.Message;
import com.model.Teacher;


@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@RequestMapping(value="/act_index")
	public String actIndex(Model model,HttpServletRequest request,HttpSession session) throws ParseException{
		
		session.setAttribute("login_status", 1);//登录状态
		session.setAttribute("id", "123344");
		session.setAttribute("duty", "3");
		System.out.println(session.getAttribute("duty"));
		
		//显示名师解答主页
		System.out.println("==actIndex called==");
		String startTime=null;
		int duration=0,status=0,choice=1;
		if(request.getParameter("duration")==null&&request.getParameter("sta")==null)
			choice=0;
		else{
			if(request.getParameter("duration")!=null){
				duration=Integer.parseInt(request.getParameter("duration"));
			}
			if(request.getParameter("sta")!=null){
				status=Integer.parseInt(request.getParameter("sta"));
			}
		}
		startTime=new String("0");
		ArrayList<Activity> al=ActivityDao.getActList(startTime, duration, status, choice);
		if(al.size()!=0){
			model.addAttribute("actList", al);
			if(session.getAttribute("id")!=null&&
					(session.getAttribute("duty").equals("1")||session.getAttribute("duty").equals("2"))){
				ArrayList<EnrollTable> el=EnrollTableDao.getActListOfId(session.getAttribute("id").toString());
				if(el.size()!=0)
					model.addAttribute("el", el);
				else
					model.addAttribute("el", null);
			}
		}
		else
			model.addAttribute("actList", null);
		model.addAttribute("duration", duration);model.addAttribute("sta", status);
		return "/jsp/ActivityIndex";
	}
	
	@RequestMapping(value="/act_add",method=RequestMethod.POST) 
	//以键值对方式返回消息
    public @ResponseBody Map<String,Object> actAdd(@RequestBody Activity activity,HttpSession session){  
		//发起活动
		System.out.println("==actAdd called==");
		Map<String,Object> rmap = new HashMap<String,Object>(); 
		//此处判断该教师是否审核通过或处于封禁状态
		Account a=UserDao.getAccountInfo(session.getAttribute("id").toString());
		if(a.getStatus()!=1){
			rmap.put("msg", "wrong"); 
			System.out.println("发起失败，账号状态");
		}
		else{
		activity.setId(session.getAttribute("id").toString());
		activity.setStartTime(activity.getStartTime()+":00");
		if(ActivityDao.addActivity(activity)){
        	rmap.put("msg", "success");
        	//此处发送系统通知
        	Message m=new Message();
        	m.setId(session.getAttribute("id").toString());
        	m.setContext(new String("您已成功发起活动，请等待活动正式开始！ "
        			+ "<a href='/yzwish/activity/act_page-"+activity.getActivityId()+"'>活动主页</a>"));
    		Date d=new Date();
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
        	System.out.println("发起成功");
		}
		else{
			rmap.put("msg", "false"); 
        	System.out.println("发起失败"); 
		}
		}
        return rmap;
	}
	
	@RequestMapping(value="/act_enroll",method=RequestMethod.POST) 
	public String actEnroll(Model model,HttpServletRequest request,RedirectAttributes attr,HttpSession session) throws ParseException{
		//报名活动
		System.out.println("==actEnroll called==");
		String activityId=request.getParameter("activityId");
		//此处判断用户是否审核通过或处于封禁状态
		Account a=UserDao.getAccountInfo(session.getAttribute("id").toString());
		if(a.getStatus()!=1){
			System.out.println("报名失败，账号状态");
		}
		else{
		EnrollTable et=new EnrollTable();
		et.setActivityId(activityId);
		et.setId(session.getAttribute("id").toString());
		Date d=new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		et.setEnrollTime(sdf.format(d));
		if(EnrollTableDao.addEnrollTable(et)){
			//此处发送系统通知
        	Message m=new Message();
        	m.setId(session.getAttribute("id").toString());
        	m.setContext(new String("您已成功报名活动，请等待活动正式开始！ "
        			+ "<a href='/yzwish/activity/act_page-"+activityId+"'>活动主页</a>"));
    		d=new Date();
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
			System.out.println("报名成功");
		}
		else{
			System.out.println("报名失败");
		}
		}
		String where=request.getParameter("where");
		if(where.equals("1")){
			if(request.getParameter("duration")!=null){
				attr.addAttribute("duration", request.getParameter("duration"));
			}
			if(request.getParameter("st")!=null){
				attr.addAttribute("sta", request.getParameter("st"));
			}
			return "redirect:act_index";
		}
		else{
			return "redirect:act_page-"+activityId;
		}
	}
	
	@RequestMapping(value="/act_unEnroll",method=RequestMethod.POST) 
	public String actUnEnroll(Model model,HttpServletRequest request,RedirectAttributes attr,HttpSession session){
		//取消报名活动
		System.out.println("==actUnEnroll called==");	
		String activityId=request.getParameter("activityId"),
				id=(session.getAttribute("id").toString());
		if(EnrollTableDao.delEnrollTable(activityId, id)){
			//此处发送系统通知
        	Message m=new Message();
        	m.setId(session.getAttribute("id").toString());
        	m.setContext(new String("您已取消报名<a href='/yzwish/activity/act_page-"+activityId+"'>活动</a>！"));
        	Date d=new Date();
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
			System.out.println("取消报名成功");
		}
		else{
			System.out.println("取消报名失败");
		}
		String where=request.getParameter("where");
		if(where.equals("1")){
			if(request.getParameter("duration")!=null){
				attr.addAttribute("duration", request.getParameter("duration"));
			}
			if(request.getParameter("st")!=null){
				attr.addAttribute("sta", request.getParameter("st"));
			}
			return "redirect:act_index";
		}
		else{
			return "redirect:act_page-"+activityId;
		}
	}
	

	@RequestMapping(value="/act_page-{aId}")
	public String actPage(@PathVariable String aId,Model model,HttpServletRequest request,HttpSession session) throws ParseException{
		//显示活动主页
		System.out.println("==actPage "+aId+" called==");
		Activity a=ActivityDao.getActInfo(aId);
		model.addAttribute("activity",a);
		ArrayList<ActivityQuestion> ql=ActivityQuestionDao.getQuList(aId);
		if(ql.size()!=0)
			model.addAttribute("ql", ql);
		else
			model.addAttribute("ql", null);
		if(session.getAttribute("id")!=null&&
				(session.getAttribute("duty").equals("1")||session.getAttribute("duty").equals("2"))){
			ArrayList<EnrollTable> el=EnrollTableDao.getActListOfId(session.getAttribute("id").toString());
			if(el.size()!=0)
				model.addAttribute("el", el);
			else
				model.addAttribute("el", null);
		}
		return "/jsp/ActivityPage";
	}
	
	@RequestMapping(value="/act_cancel",method=RequestMethod.POST) 
	public String actCancel(Model model,HttpServletRequest request,RedirectAttributes attr,HttpSession session){
		//取消活动
		System.out.println("==actCancel called==");
		String activityId=request.getParameter("activityId"),reason=request.getParameter("cancelReason");
		if(reason.equals("0"))
			reason=request.getParameter("ohterRe");
		if(ActivityDao.cancelActivity(activityId, reason)){
			/*此处发送系统通知
        	 * 活动已取消！取消理由：xxxx
        	 * */
			System.out.println("取消活动成功");
		}
		else{
			System.out.println("取消活动失败");
		}
		return "redirect:act_page-"+activityId;
	}
	
	@RequestMapping(value="/act_que",method=RequestMethod.POST) 
	//以键值对方式返回消息
    public @ResponseBody Map<String,Object> actQue(@RequestBody ActivityQuestion activityquestion,HttpSession session) throws ParseException{  
		//提问
		System.out.println("==actQue called==");
		Map<String,Object> rmap = new HashMap<String,Object>(); 
		//此处判断用户是否审核通过或处于封禁状态
		Account a=UserDao.getAccountInfo(session.getAttribute("id").toString());
		if(a.getStatus()!=1){
			rmap.put("msg", "wrong");
			System.out.println("提问失败，账号状态");
		}
		else{
			Date d=new Date();
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			activityquestion.setId(session.getAttribute("id").toString());
			activityquestion.setAcQuTime(sdf.format(d));
		if(ActivityQuestionDao.addQue(activityquestion)){
        	rmap.put("msg", "success");
        	//此处发送系统通知
        	Message m=new Message();
        	Activity act=ActivityDao.getActInfo(Integer.toString(activityquestion.getActivityId()));		
        	m.setId(act.getId());
        	m.setContext(new String("您有一条新的<a href='/yzwish/activity/act_page-"+act.getId()+"'>活动</a>提问待回答！"));
    		d=new Date();
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
        	System.out.println("提问成功");
		}
		else{
			rmap.put("msg", "false"); 
        	System.out.println("提问失败"); 
		}
		}
        return rmap;
	}
	
	@RequestMapping(value="/act_ans",method=RequestMethod.POST) 
	public String actAns(HttpServletRequest request,RedirectAttributes attr,HttpSession session) throws ParseException{
		//回答
		System.out.println("==actAns called==");	
		String context=request.getParameter("context"),
				qId=request.getParameter("qId"),
				 activityId=request.getParameter("activityId");
		ActivityAnswer an=new ActivityAnswer();
		an.setAcAnContext(context);
		an.setAcQuestionId(Integer.parseInt(qId));
		an.setActivityId(Integer.parseInt(activityId));
		Date d=new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		an.setAcAnTime(sdf.format(d));
		if(ActivityQuestionDao.addAns(an)){
			//此处发送系统通知
        	Message m=new Message();
        	ActivityQuestion q=ActivityQuestionDao.getActInfo(qId);
        	m.setId(q.getId());
        	m.setContext(new String("您有一条新的<a href='/yzwish/activity/act_page-"+activityId+"'>活动</a>回答待查看！"));
        	d=new Date();
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
			System.out.println("回答成功");
		}
		else{
			System.out.println("回答失败");
		}
		return "redirect:act_page-"+activityId;
	}
	
}
