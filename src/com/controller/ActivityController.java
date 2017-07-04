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
		
		session.setAttribute("login_status", 1);//��¼״̬
		session.setAttribute("id", "123344");
		session.setAttribute("duty", "3");
		System.out.println(session.getAttribute("duty"));
		
		//��ʾ��ʦ�����ҳ
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
	//�Լ�ֵ�Է�ʽ������Ϣ
    public @ResponseBody Map<String,Object> actAdd(@RequestBody Activity activity,HttpSession session){  
		//����
		System.out.println("==actAdd called==");
		Map<String,Object> rmap = new HashMap<String,Object>(); 
		//�˴��жϸý�ʦ�Ƿ����ͨ�����ڷ��״̬
		Account a=UserDao.getAccountInfo(session.getAttribute("id").toString());
		if(a.getStatus()!=1){
			rmap.put("msg", "wrong"); 
			System.out.println("����ʧ�ܣ��˺�״̬");
		}
		else{
		activity.setId(session.getAttribute("id").toString());
		activity.setStartTime(activity.getStartTime()+":00");
		if(ActivityDao.addActivity(activity)){
        	rmap.put("msg", "success");
        	//�˴�����ϵͳ֪ͨ
        	Message m=new Message();
        	m.setId(session.getAttribute("id").toString());
        	m.setContext(new String("���ѳɹ���������ȴ����ʽ��ʼ�� "
        			+ "<a href='/yzwish/activity/act_page-"+activity.getActivityId()+"'>���ҳ</a>"));
    		Date d=new Date();
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
        	System.out.println("����ɹ�");
		}
		else{
			rmap.put("msg", "false"); 
        	System.out.println("����ʧ��"); 
		}
		}
        return rmap;
	}
	
	@RequestMapping(value="/act_enroll",method=RequestMethod.POST) 
	public String actEnroll(Model model,HttpServletRequest request,RedirectAttributes attr,HttpSession session) throws ParseException{
		//�����
		System.out.println("==actEnroll called==");
		String activityId=request.getParameter("activityId");
		//�˴��ж��û��Ƿ����ͨ�����ڷ��״̬
		Account a=UserDao.getAccountInfo(session.getAttribute("id").toString());
		if(a.getStatus()!=1){
			System.out.println("����ʧ�ܣ��˺�״̬");
		}
		else{
		EnrollTable et=new EnrollTable();
		et.setActivityId(activityId);
		et.setId(session.getAttribute("id").toString());
		Date d=new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		et.setEnrollTime(sdf.format(d));
		if(EnrollTableDao.addEnrollTable(et)){
			//�˴�����ϵͳ֪ͨ
        	Message m=new Message();
        	m.setId(session.getAttribute("id").toString());
        	m.setContext(new String("���ѳɹ����������ȴ����ʽ��ʼ�� "
        			+ "<a href='/yzwish/activity/act_page-"+activityId+"'>���ҳ</a>"));
    		d=new Date();
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
			System.out.println("�����ɹ�");
		}
		else{
			System.out.println("����ʧ��");
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
		//ȡ�������
		System.out.println("==actUnEnroll called==");	
		String activityId=request.getParameter("activityId"),
				id=(session.getAttribute("id").toString());
		if(EnrollTableDao.delEnrollTable(activityId, id)){
			//�˴�����ϵͳ֪ͨ
        	Message m=new Message();
        	m.setId(session.getAttribute("id").toString());
        	m.setContext(new String("����ȡ������<a href='/yzwish/activity/act_page-"+activityId+"'>�</a>��"));
        	Date d=new Date();
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
			System.out.println("ȡ�������ɹ�");
		}
		else{
			System.out.println("ȡ������ʧ��");
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
		//��ʾ���ҳ
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
		//ȡ���
		System.out.println("==actCancel called==");
		String activityId=request.getParameter("activityId"),reason=request.getParameter("cancelReason");
		if(reason.equals("0"))
			reason=request.getParameter("ohterRe");
		if(ActivityDao.cancelActivity(activityId, reason)){
			/*�˴�����ϵͳ֪ͨ
        	 * ���ȡ����ȡ�����ɣ�xxxx
        	 * */
			System.out.println("ȡ����ɹ�");
		}
		else{
			System.out.println("ȡ���ʧ��");
		}
		return "redirect:act_page-"+activityId;
	}
	
	@RequestMapping(value="/act_que",method=RequestMethod.POST) 
	//�Լ�ֵ�Է�ʽ������Ϣ
    public @ResponseBody Map<String,Object> actQue(@RequestBody ActivityQuestion activityquestion,HttpSession session) throws ParseException{  
		//����
		System.out.println("==actQue called==");
		Map<String,Object> rmap = new HashMap<String,Object>(); 
		//�˴��ж��û��Ƿ����ͨ�����ڷ��״̬
		Account a=UserDao.getAccountInfo(session.getAttribute("id").toString());
		if(a.getStatus()!=1){
			rmap.put("msg", "wrong");
			System.out.println("����ʧ�ܣ��˺�״̬");
		}
		else{
			Date d=new Date();
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			activityquestion.setId(session.getAttribute("id").toString());
			activityquestion.setAcQuTime(sdf.format(d));
		if(ActivityQuestionDao.addQue(activityquestion)){
        	rmap.put("msg", "success");
        	//�˴�����ϵͳ֪ͨ
        	Message m=new Message();
        	Activity act=ActivityDao.getActInfo(Integer.toString(activityquestion.getActivityId()));		
        	m.setId(act.getId());
        	m.setContext(new String("����һ���µ�<a href='/yzwish/activity/act_page-"+act.getId()+"'>�</a>���ʴ��ش�"));
    		d=new Date();
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
        	System.out.println("���ʳɹ�");
		}
		else{
			rmap.put("msg", "false"); 
        	System.out.println("����ʧ��"); 
		}
		}
        return rmap;
	}
	
	@RequestMapping(value="/act_ans",method=RequestMethod.POST) 
	public String actAns(HttpServletRequest request,RedirectAttributes attr,HttpSession session) throws ParseException{
		//�ش�
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
			//�˴�����ϵͳ֪ͨ
        	Message m=new Message();
        	ActivityQuestion q=ActivityQuestionDao.getActInfo(qId);
        	m.setId(q.getId());
        	m.setContext(new String("����һ���µ�<a href='/yzwish/activity/act_page-"+activityId+"'>�</a>�ش���鿴��"));
        	d=new Date();
        	m.setTime(Timestamp.valueOf(sdf.format(d)));
        	MessageDao.addMessage(m);
			System.out.println("�ش�ɹ�");
		}
		else{
			System.out.println("�ش�ʧ��");
		}
		return "redirect:act_page-"+activityId;
	}
	
}
