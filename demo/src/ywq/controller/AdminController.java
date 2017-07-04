package ywq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ywq.model.CheckTeacher;
import ywq.model.CheckSenior;
import ywq.model.BlockedUser;
import ywq.model.Report;
import ywq.model.Topic;
import ywq.service.AdminService;


@Controller
public class AdminController {
	private static final Log logger=LogFactory.getLog(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/showTeacher_do")
	public String getTeacher(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到老师审核信息
		String message = null;
        try{
        ArrayList<CheckTeacher> ctl=adminService.getTeacherInfo();
        model.addAttribute("teacher_List", ctl);
        if(!ctl.isEmpty()){
        	message="<li>老师待审核信息如下：</li>";
         }else{
        	 message="<li>没有审核信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_CheckTeacher";
	}
	
	@RequestMapping(value="/checkTPass_do")
	public String checkTPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//老师审核通过
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		try{
		boolean flag=adminService.checkTPass(select);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/checkTNotPass_do")
	public String checkTNotPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//老师审核未通过
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		String Treason=request.getParameter("Treason");
		try{
		boolean flag=adminService.checkTNotPass(select,Treason);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/showSenior_do")
	public String getSenior(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到学长学姐审核信息
		String message = null;
        try{
        ArrayList<CheckSenior> csl=adminService.getSeniorInfo();
        model.addAttribute("senior_List", csl);
        if(!csl.isEmpty()){
        	message="<li>学长学姐待审核信息如下：</li>";
         }else{
        	 message="<li>没有审核信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_CheckSenior";
	}
	
	@RequestMapping(value="/checkSPass_do")
	public String checkSPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//学长学姐审核通过
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		try{
		boolean flag=adminService.checkSPass(select);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showSenior_do";
	}
	
	@RequestMapping(value="/checkSNotPass_do")
	public String checkSNotPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//学长学姐审核未通过
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		String Sreason=request.getParameter("Sreason");
		try{
		boolean flag=adminService.checkSNotPass(select,Sreason);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showSenior_do";
	}
	
	@RequestMapping(value="/showBlockedUser_do")
	public String getBlockedStudent(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到封禁用户信息
		String message = null;
        try{
        ArrayList<BlockedUser> bstl=adminService.getBlockedStudentInfo();
        model.addAttribute("blockedStu_List", bstl);
        ArrayList<BlockedUser> btl=adminService.getBlockedTeacherInfo();
        model.addAttribute("blockedTea_List", btl);
        ArrayList<BlockedUser> bsel=adminService.getBlockedSeniorInfo();
        model.addAttribute("blockedSen_List", bsel);
        
        if(!bstl.isEmpty()|| !btl.isEmpty() ||!bsel.isEmpty() ){
        	message="<li>封禁用户信息如下：</li>";
         }else{
        	 message="<li>没有封禁用户信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_ShowBlockedUser";
	}
	
	@RequestMapping(value="/showReportedUser_do")
	public String getReportedUser(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报用户信息
		String message = null;
        try{
        ArrayList<Report> rerStuL=adminService.getReporterStudentInfo();
        model.addAttribute("rerStuL", rerStuL);
        ArrayList<Report> rerTeaL=adminService.getReporterTeacherInfo();
        model.addAttribute("rerTeaL", rerTeaL);
        ArrayList<Report> rerSenL=adminService.getReporterSeniorInfo();
        model.addAttribute("rerSenL", rerSenL);
        
        ArrayList<Report> redStuL=adminService.getReportedStudentInfo();
        model.addAttribute("redStuL", redStuL);
        ArrayList<Report> redTeaL=adminService.getReportedTeacherInfo();
        model.addAttribute("redTeaL", redTeaL);
        ArrayList<Report> redSenL=adminService.getReportedSeniorInfo();
        model.addAttribute("redSenL", redSenL);
        
        if(!rerStuL.isEmpty()|| !rerTeaL.isEmpty() ||!rerSenL.isEmpty()|| !redStuL.isEmpty()|| !redTeaL.isEmpty() ||!redSenL.isEmpty() ){
        	message="<li>举报用户信息如下：</li>";
         }else{
        	 message="<li>没有举报用户信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedUser";
	}

	@RequestMapping(value="/blockED_do")
	public String blockED(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//封禁被举报用户
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		try{
		boolean flag=adminService.blockED(select, BTime);
		if (flag==true){
			message="<li>封禁成功！</li>";
		}else{
       	 message="<li>封禁失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/blockER_do")
	public String blockER(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//封禁举报用户
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		try{
		boolean flag=adminService.blockER(select, BTime);
		if (flag==true){
			message="<li>封禁成功！</li>";
		}else{
       	 message="<li>封禁失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/neglectU_do")
	public String neglectU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//忽略该条举报用户信息
		String message = null;
		String select[] =request.getParameterValues("reportU");
		try{
		boolean flag=adminService.neglect(select);
		if (flag==true){
			message="<li>忽略成功！</li>";
		}else{
       	 message="<li>忽略失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/blockUser_do")
	public String blockUser(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//封禁用户
				//判断用户是否已经被封禁/用户是否存在
				//account表中该用户状态置4
				//blockedusers表添加数据
				//给被封禁用户发送系统通知
				//给report表中未处理的举报该用户的举报者发送系统通知-举报成功
				//report表中举报该用户的数据状态置为1		
		String message ="";
		String privateUserId =request.getParameter("user_id");
		String closureReason =request.getParameter("Breason");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		boolean flag1=adminService.checkUexist(privateUserId);
		if (flag1==false) {message="<li>用户不存在！</li>";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==true) {message="<li>用户已封禁！</li>";
			}
			else {
				adminService.account4(privateUserId);
				adminService.addTableBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoER(privateUserId);
				adminService.setReportED1(privateUserId);
				message="<li>封禁成功！</li>";
			}
		}
		model.addAttribute("message",message);
		return "admin_BlockUser";
	}
	
	@RequestMapping(value="/unblockUser_do")
	public String unblockUser(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//解封用户
		//0.判断用户是否已经被封禁/用户是否存在
		//1.account表置0/1
		//2.blockeduser表删除数据
		//3.给解封用户发送系统通知
		String message ="";
		String privateUserId =request.getParameter("user_id");
		boolean flag1=adminService.checkUexist(privateUserId);
		if (flag1==false) {message="<li>用户不存在！</li>";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==false) {message="<li>用户未被封禁！</li>";
			}
			else {
				adminService.account01(privateUserId);
				adminService.delTableBU(privateUserId);
				adminService.sentMtoUBU(privateUserId);
				message="<li>解封成功！</li>";
			}
		}
		
		model.addAttribute("message",message);
		return "admin_UnblockUser";
	}
	
	@RequestMapping(value="/showTopic_do")
	public String getTopic(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到话题审核信息
		String message = null;
        try{
        ArrayList<Topic> tpl=adminService.getTopicInfo();
        model.addAttribute("topic_List", tpl);
        if(!tpl.isEmpty()){
        	message="<li>话题审核信息如下：</li>";
         }else{
        	 message="<li>没有审核信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_CheckTopic";
	}
	
	@RequestMapping(value="/checkTOPPass_do")
	public String checkTOPPass(Model model,HttpServletRequest request,RedirectAttributes attr,HttpServletResponse response) throws IOException{
		//话题审核通过
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		try{
		boolean flag=adminService.checkTOPPass(select);
		if (flag==true){
			message="录入成功！";
		}else{
       	 message="录入失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        } 
		//ActionContext.getContext().put("loginError",message); 
		//attr.addFlashAttribute("m",message);
        model.addAttribute(message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/checkTOPNotPass_do")
	public String checkTOPNotPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//话题审核未通过
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		String Treason=request.getParameter("Treason");
		try{
		boolean flag=adminService.checkTOPNotPass(select,Treason);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/showReportedTopic_do")
	public String getReportedTopic(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报话题信息
		String message = null;
        try{
        ArrayList<Report> TrerStuL=adminService.getTRerStudentInfo();
        model.addAttribute("TrerStuL", TrerStuL);
        ArrayList<Report> TrerTeaL=adminService.getTRerTeacherInfo();
        model.addAttribute("TrerTeaL", TrerTeaL);
        ArrayList<Report> TrerSenL=adminService.getTRerSeniorInfo();
        model.addAttribute("TrerSenL", TrerSenL);
        
        
        if(!TrerStuL.isEmpty()|| !TrerTeaL.isEmpty() ||!TrerSenL.isEmpty()){
        	message="<li>举报话题信息如下：</li>";
         }else{
        	 message="<li>没有举报话题信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedTopic";
	}
	
	@RequestMapping(value="/delTopic_do")
	public String delTopic(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//删除话题
		//1.report表置1
		//2.message发送系统通知给话题发起者
		//3.topic表删除数据
		String message = null;
		String select[] =request.getParameterValues("reportT");

		adminService.delTopic(select);
		
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/delTopicBU_do")
	public String delTopicBU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//删除话题 封禁用户
		//封禁用户
		//1.report表置1
		//2.message发送系统通知给话题发起者
		//3.topic表删除数据
		String message = null;
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.TopBU(select,closureDay);
		adminService.delTopic(select);
		
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/TopBUer_do")
	public String TopBUer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//封禁举报人
		String message = null;
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.TopBUer(select,closureDay);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/NeglectT_do")
	public String NeglectT(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//忽略该条举报话题信息
		String message = null;
		String select[] =request.getParameterValues("reportT");
		adminService.neglectT(select);
        return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/showReportedQuestion_do")
	public String getReportedQuestion(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报问题信息
		String message = null;
        try{
        ArrayList<Report> QrerStuL=adminService.getQRerStudentInfo();
        model.addAttribute("QrerStuL", QrerStuL);
        ArrayList<Report> QrerTeaL=adminService.getQRerTeacherInfo();
        model.addAttribute("QrerTeaL", QrerTeaL);
        ArrayList<Report> QrerSenL=adminService.getQRerSeniorInfo();
        model.addAttribute("QrerSenL", QrerSenL);
        
        
        if(!QrerStuL.isEmpty()|| !QrerTeaL.isEmpty() ||!QrerSenL.isEmpty()){
        	message="<li>举报问题信息如下：</li>";
         }else{
        	 message="<li>没有举报问题信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedQuestion";
	}
	
	@RequestMapping(value="/delQuestion_do")
	public String delQuestion(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//删除问题
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.question表删除数据
		String message = null;
		String select[] =request.getParameterValues("reportQ");

		adminService.delQuestion(select);
		
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/delQuestionBU_do")
	public String delQuestionBU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//删除问题 封禁用户
		//封禁用户
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.question表删除数据
		String message = null;
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.QueBU(select,closureDay);
		adminService.delQuestion(select);
		
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/QueBUer_do")
	public String QueBUer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//封禁举报人
		String message = null;
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.QueBUer(select,closureDay);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/NeglectQ_do")
	public String NeglectQ(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//忽略该条举报问题信息
		String message = null;
		String select[] =request.getParameterValues("reportQ");
		adminService.neglectQ(select);
        return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/showReportedAnswer_do")
	public String getReportedAnswer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报回答信息
		String message = null;
        try{
        ArrayList<Report> ArerStuL=adminService.getARerStudentInfo();
        model.addAttribute("ArerStuL", ArerStuL);
        ArrayList<Report> ArerTeaL=adminService.getARerTeacherInfo();
        model.addAttribute("ArerTeaL", ArerTeaL);
        ArrayList<Report> ArerSenL=adminService.getARerSeniorInfo();
        model.addAttribute("ArerSenL", ArerSenL);
        
        
        if(!ArerStuL.isEmpty()|| !ArerTeaL.isEmpty() ||!ArerSenL.isEmpty()){
        	message="<li>举报回答如下：</li>";
         }else{
        	 message="<li>没有举报回答信息！</li>";
         }
        }catch(Exception e){
            message = "<li>sql异常</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedAnswer";
	}
	
	@RequestMapping(value="/delAnswer_do")
	public String delAnswer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//删除回答
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.answer表删除数据
		String message = null;
		String select[] =request.getParameterValues("reportA");

		adminService.delAnswer(select);
		
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/delAnswerBU_do")
	public String delAnswerBU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//删除回答 封禁用户
		//封禁用户
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.answer表删除数据
		String message = null;
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.AnsBU(select,closureDay);
		adminService.delAnswer(select);
		
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/AnsBUer_do")
	public String AnsBUer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//封禁举报人
		String message = null;
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.AnsBUer(select,closureDay);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/NeglectA_do")
	public String NeglectA(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//忽略该条举报回答信息
		String message = null;
		String select[] =request.getParameterValues("reportA");
		adminService.neglectA(select);
        return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/views_do")
	public String views(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//访问量
		String message ="";
		String year =request.getParameter("time");
		String x =request.getParameter("x");
		int view=adminService.views(year);
		model.addAttribute("view",view);
		
		model.addAttribute("message",message);
		
		if(x.equals("1")) return "admin_ShowPageViewBY";
		if(x.equals("2")) return "admin_ShowPageViewBM";
		else return "admin_ShowPageViewBD";
	}
	
}
