package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserDao;
import com.model.Account;
import com.model.CollegeStu;
import com.model.HighSchoolStu;
import com.model.Major;
import com.model.Message;
import com.model.Province;
import com.model.Teacher;
import com.model.TransRecord;
import com.model.University;
import com.model.VolunSchool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@RequestMapping(value="/showBasicInfo",method=RequestMethod.GET) 
    public ModelAndView showBasicInfo(HttpSession session,HttpServletRequest request){  
		System.out.println("进入showBasicInfo！");
		String id=(String) session.getAttribute("id");
		int duty=Integer.parseInt((String)session.getAttribute("duty"));
		HighSchoolStu hss=new HighSchoolStu();
		CollegeStu cs=new CollegeStu();
		Teacher tea=new Teacher();
		UserDao.showBasicInfo(hss,cs,tea,id,duty);
		if(duty==1)		
			request.setAttribute("hss", hss);
		else if(duty==2)		
			request.setAttribute("cs", cs);
		else if(duty==3)		
			request.setAttribute("tea", tea);
		return new ModelAndView("/jsp/userInfoIndex");
	}
	
	@RequestMapping(value="/setBasicInfo",method=RequestMethod.POST) 
    public @ResponseBody String checkLogin(@RequestBody Map<String, Object> map,
    		HttpServletRequest request,HttpSession session,String from){  
		System.out.println("进入setBasicInfo！");
		JSONArray array=new JSONArray();
		JSONObject jsonObj = new JSONObject();	
		String duty=(String) session.getAttribute("duty");
		String id=(String)session.getAttribute("id");
		int result=UserDao.setBasicInfo(duty,(String)map.get("sex"),(String)map.get("age"),(String)map.get("hId"),id);
		jsonObj.put("msg", result);
        array.add(jsonObj);   

        return array.toString();	        
	}
	
	@RequestMapping(value="/showAuditInfo",method=RequestMethod.GET) 
    public ModelAndView showAuditInfo(HttpSession session,HttpServletRequest request){  
		System.out.println("进入showAuditInfo！");
		String id=(String) session.getAttribute("id");
		int duty=Integer.parseInt((String)session.getAttribute("duty"));
		System.out.println("id,duty:"+id+" "+duty);
		CollegeStu cs=new CollegeStu();
		Teacher tea=new Teacher();
		UserDao.showAuditInfo(cs,tea,id,duty);
		request.setAttribute("duty", duty);
        if(duty==2){
			request.setAttribute("cs", cs);
			request.setAttribute("status", cs.getStatus());
        }
		else if(duty==3){
			request.setAttribute("tea", tea);
			request.setAttribute("status",tea.getStatus());
		}
			
		return new ModelAndView("/jsp/auditInfoIndex");
	}
	@RequestMapping(value="/setAuditInfo",method=RequestMethod.POST) 
    public ModelAndView setAuditInfo(CollegeStu cs,Teacher tea,
    		HttpSession session,HttpServletRequest request){  
		System.out.println("进入setAuditInfo！");
		String id=(String) session.getAttribute("id");
		String duty=(String)session.getAttribute("duty");
		System.out.println("id,duty:"+id+" "+duty);
		UserDao.setAuditInfo(cs,tea,id,duty);			
		return new ModelAndView("redirect:/userInfo/showAuditInfo");
	}
	@RequestMapping(value="/showVolunInfo",method=RequestMethod.GET) 
    public ModelAndView showVolunInfo(HttpSession session,HttpServletRequest request){  
		String id=(String) session.getAttribute("id");
		ArrayList<VolunSchool> vsList=UserDao.getVSList(id);
		request.setAttribute("vsList", vsList);
		return new ModelAndView("/jsp/volunInfoIndex");
	}
	@RequestMapping(value="/getPList",method=RequestMethod.GET) 
    public @ResponseBody String getPList(HttpSession session){  
		JSONArray array=new JSONArray();			
		ArrayList<Province> vsList=UserDao.getPList();
		Iterator itor=vsList.iterator();
		while(itor.hasNext()){
			Province temp=(Province) itor.next();
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("provinceId", temp.getProvinceId());
			jsonObj.put("provinceName", temp.getProvinceName());
			array.add(jsonObj);   
		}

        return array.toString();	  
	}
	@RequestMapping(value="/getUList",method=RequestMethod.GET) 
    public @ResponseBody String getUList(HttpSession session){  
		JSONArray array=new JSONArray();			
		ArrayList<University> vsList=UserDao.getUList();
		Iterator itor=vsList.iterator();
		while(itor.hasNext()){
			University temp=(University) itor.next();
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("universityId", temp.getUniversityId());
			jsonObj.put("universityName", temp.getUniversityName());
			array.add(jsonObj);   
		}

        return array.toString();		  
	}
	@RequestMapping(value="/getMList",method=RequestMethod.GET) 
    public @ResponseBody String getMList(HttpSession session){  
		JSONArray array=new JSONArray();			
		ArrayList<Major> vsList=UserDao.getMList();
		Iterator itor=vsList.iterator();
		while(itor.hasNext()){
			Major temp=(Major) itor.next();
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("majorId", temp.getMajorId());
			jsonObj.put("majorName", temp.getMajorName());
			array.add(jsonObj);   
		}

        return array.toString();	  
	}
	@RequestMapping(value="/deleteVolunInfo",method=RequestMethod.POST) 
    public @ResponseBody String deleteVolunInfo(@RequestBody VolunSchool vs){  
		System.out.println("dvs:"+vs.getVsId());
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		if(UserDao.deleteVS(vs.getVsId())){
			jsonObj.put("result", 1);
		}
			
		else{
			jsonObj.put("result", 2);
		}
		array.add(jsonObj);
        return array.toString();	  
	}
	@RequestMapping(value="/addVolunInfo",method=RequestMethod.POST) 
    public @ResponseBody String addVolunInfo(@RequestBody VolunSchool vs,HttpSession session){  
		String id=(String) session.getAttribute("id");
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		vs.setId(id);
		if(UserDao.addVS(vs)){
			jsonObj.put("result", 1);
		}			
		else{
			jsonObj.put("result", 2);
		}
		array.add(jsonObj);
        return array.toString();	  
	}
	@RequestMapping(value="/modifyVolunInfo",method=RequestMethod.POST) 
    public @ResponseBody String modifyVolunInfo(@RequestBody VolunSchool vs){  
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		if(UserDao.modifyVS(vs)){
			jsonObj.put("result", 1);
		}			
		else{
			jsonObj.put("result", 2);
		}
		array.add(jsonObj);
        return array.toString();	  
	}
	
	@RequestMapping(value="/modifyPW",method=RequestMethod.POST) 
    public @ResponseBody String modifyPassword(HttpSession session
    		,@RequestParam("op") String op,@RequestParam("np") String np){  
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		String id=(String)session.getAttribute("id");			
		jsonObj.put("result", UserDao.modifyPassword(id,op,np)); 
		array.add(jsonObj);
        return array.toString();	  
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET) 
    public ModelAndView mpwlogout(HttpSession session){  
		if(session!=null)
		{
			System.out.println("清除此次会话");
			session.invalidate();
		}
        return new ModelAndView("redirect:/jsp/login.jsp");	  
	}
	@RequestMapping(value="/modifySP",method=RequestMethod.POST) 
    public @ResponseBody String modifySafetyInfo(HttpSession session
    		,@RequestParam("op") String op,@RequestParam("sp") int sp
    		,@RequestParam("sa") String sa){  
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		String id=(String)session.getAttribute("id");	
		String s_duty=(String)session.getAttribute("duty");
		int duty=Integer.parseInt(s_duty);	
		jsonObj.put("result", UserDao.modifySafetyInfo(id,op,sp,sa,duty)); 
		array.add(jsonObj);
        return array.toString();	  
	}
	@RequestMapping(value="/showBalance",method=RequestMethod.GET) 
    public ModelAndView showBalance(HttpSession session,HttpServletRequest request){  
		String id=(String) session.getAttribute("id");
		String s_duty=(String) session.getAttribute("duty");
		int duty=Integer.parseInt(s_duty);
		Double balance=UserDao.showBalance(id,duty);
		request.setAttribute("balance", balance);
        return new ModelAndView("/jsp/balanceInfo");	  
	}
	@RequestMapping(value="/deposite",method=RequestMethod.POST) 
    public @ResponseBody String deposite(HttpSession session,@RequestParam("num") String num){  
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		String id=(String)session.getAttribute("id");	
		String s_duty=(String)session.getAttribute("duty");
		int duty=Integer.parseInt(s_duty);	
		double deposite_num=Double.parseDouble(num);
		jsonObj.put("result", UserDao.deposite(id,duty,deposite_num)); 
		array.add(jsonObj);
        return array.toString();	  
	}
	@RequestMapping(value="/showTodayLimit",method=RequestMethod.GET) 
    public ModelAndView showTodayLimit(HttpSession session,HttpServletRequest request){  
		String id=(String)session.getAttribute("id");	
		Double today_draw=UserDao.showTodayDrawNum(id);
		request.setAttribute("today_draw",today_draw);
        return new ModelAndView("/jsp/draw");	  
	}
	@RequestMapping(value="/draw",method=RequestMethod.POST) 
    public @ResponseBody String draw(HttpSession session,@RequestParam("num") String num){ 
		JSONArray array=new JSONArray();			
		JSONObject jsonObj=new JSONObject();
		String id=(String)session.getAttribute("id");	
		String s_duty=(String)session.getAttribute("duty");
		int duty=Integer.parseInt(s_duty);	
		double draw_num=Double.parseDouble(num);
		jsonObj.put("result", UserDao.draw(id,duty,draw_num)); 
		array.add(jsonObj);
        return array.toString();	  
	}
	@RequestMapping(value="/showTR",method=RequestMethod.POST) 
    public ModelAndView showTR(String from,String to,int way,
    		HttpSession session,HttpServletRequest request){  
		String id=(String)session.getAttribute("id");	
		ArrayList<TransRecord> trList=UserDao.showTransRecord(from,to,way,id);
		request.setAttribute("trList", trList);
		request.setAttribute("from", from);
		request.setAttribute("to", to);
		request.setAttribute("way", way);
        return new ModelAndView("/jsp/transRecoredInfo");	  
	}
	@RequestMapping(value="/showMessage",method=RequestMethod.GET) 
    public ModelAndView showMessage(HttpSession session,HttpServletRequest request){  
		String id=(String)session.getAttribute("id");	
		ArrayList<Message> msgList=UserDao.showMessage(id);
		request.setAttribute("msgList", msgList);
        return new ModelAndView("/jsp/systemMessage");	  
	}
	@RequestMapping(value="/readMessage",method=RequestMethod.POST) 
    public @ResponseBody String draw(@RequestParam("mId") String mId){ 
		int use_mId=Integer.parseInt(mId);
		UserDao.readMessage(use_mId);
        return null;	  
	}
	//分页
	@RequestMapping(value="/postPage",method=RequestMethod.GET) 
    public ModelAndView postPage(HttpServletRequest request,String pageNumberStr,HttpSession session){  
	    int pageNumber = 1;  
	    String id=(String) session.getAttribute("id");
	    if(pageNumberStr!=null && !pageNumberStr.isEmpty())  
	    {  
	        pageNumber = Integer.parseInt(pageNumberStr);  
	    }  
	      
	    int pageSize = 5; //分页大小  
	    int totalPosts = UserDao.getTotalMsg(id); //总文章数  
	    int totalPages = totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //计算得出的总页数  
	    request.setAttribute("pageSize", pageSize);  
	    request.setAttribute("totalPosts", totalPosts);  
	    request.setAttribute("pageNumber", pageNumber);  
	    request.setAttribute("totalPages", totalPages);        
	    ArrayList<Message> msgList = UserDao.getMsgListPaging(id,pageNumber, pageSize);  
	    request.setAttribute("msgList", msgList);  	      
	    return new ModelAndView("jsp/systemMessage");         
	}  
}
