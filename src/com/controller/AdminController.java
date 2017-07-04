package com.controller;

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

import com.model.BlockedUser;
import com.model.CheckSenior;
import com.model.CheckTeacher;
import com.model.CheckReport;
import com.model.CheckTopic;
import com.service.AdminService;


@Controller
public class AdminController {
	private static final Log logger=LogFactory.getLog(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/showTeacher_do")
	public String getTeacher(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到老师审核信息
		String message = null;
        try{
        ArrayList<CheckTeacher> ctl=adminService.getTeacherInfo();
        model.addAttribute("teacher_List", ctl);
        if(!ctl.isEmpty()){
        	message="老师待审核信息如下：";
         }else{
        	 message="没有审核信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_CheckTeacher";
	}
	
	@RequestMapping(value="/checkTPass_do")
	public String checkTPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//老师审核通过
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.checkTPass(select);
		if (flag==true){
			message="录入成功！";
		}else{
       	 message="录入失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/checkTNotPass_do")
	public String checkTNotPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//老师审核未通过
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		String Treason=request.getParameter("Treason");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.checkTNotPass(select,Treason);
		if (flag==true){
			message="录入成功！";
		}else{
       	 message="录入失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/showSenior_do")
	public String getSenior(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到学长学姐审核信息
		String message = null;
        try{
        ArrayList<CheckSenior> csl=adminService.getSeniorInfo();
        model.addAttribute("senior_List", csl);
        if(!csl.isEmpty()){
        	message="学长学姐待审核信息如下：";
         }else{
        	 message="没有审核信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_CheckSenior";
	}
	
	@RequestMapping(value="/checkSPass_do")
	public String checkSPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//学长学姐审核通过
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.checkSPass(select);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showSenior_do";
	}
	
	@RequestMapping(value="/checkSNotPass_do")
	public String checkSNotPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//学长学姐审核未通过
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		String Sreason=request.getParameter("Sreason");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.checkSNotPass(select,Sreason);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }}
		redirectattributes.addFlashAttribute("message2",message);
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
        	message="封禁用户信息如下";
         }else{
        	 message="没有封禁用户信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message",message);
        return "admin_ShowBlockedUser";
	}
	
	@RequestMapping(value="/showReportedUser_do")
	public String getReportedUser(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报用户信息
		String message = null;
        try{
        ArrayList<CheckReport> rerStuL=adminService.getReporterStudentInfo();
        model.addAttribute("rerStuL", rerStuL);
        ArrayList<CheckReport> rerTeaL=adminService.getReporterTeacherInfo();
        model.addAttribute("rerTeaL", rerTeaL);
        ArrayList<CheckReport> rerSenL=adminService.getReporterSeniorInfo();
        model.addAttribute("rerSenL", rerSenL);
        
        ArrayList<CheckReport> redStuL=adminService.getReportedStudentInfo();
        model.addAttribute("redStuL", redStuL);
        ArrayList<CheckReport> redTeaL=adminService.getReportedTeacherInfo();
        model.addAttribute("redTeaL", redTeaL);
        ArrayList<CheckReport> redSenL=adminService.getReportedSeniorInfo();
        model.addAttribute("redSenL", redSenL);
        
        if(!rerStuL.isEmpty()|| !rerTeaL.isEmpty() ||!rerSenL.isEmpty()|| !redStuL.isEmpty()|| !redTeaL.isEmpty() ||!redSenL.isEmpty() ){
        	message="举报用户信息如下：";
         }else{
        	 message="没有举报用户信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedUser";
	}

	@RequestMapping(value="/blockED_do")
	public String blockED(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//封禁被举报用户
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.blockED(select, BTime);
		if (flag==true){
			message="封禁成功！";
		}else{
       	 message="封禁失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/blockER_do")
	public String blockER(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//封禁举报用户
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.blockER(select, BTime);
		if (flag==true){
			message="封禁成功！";
		}else{
       	 message="封禁失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/neglectU_do")
	public String neglectU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//忽略该条举报用户信息
		String message = null;
		String select[] =request.getParameterValues("reportU");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.neglect(select);
		if (flag==true){
			message="忽略成功！";
		}else{
       	 message="忽略失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        }}
		redirectattributes.addFlashAttribute("message2",message);
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
		if (flag1==false) {message="用户不存在！";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==true) {message="用户已封禁！";
			}
			else {
				adminService.account4(privateUserId);
				adminService.addTableBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoER(privateUserId);
				adminService.setReportED1(privateUserId);
				message="封禁成功！";
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
		if (flag1==false) {message="用户不存在！";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==false) {message="用户未被封禁！";
			}
			else {
				adminService.account01(privateUserId);
				adminService.delTableBU(privateUserId);
				adminService.sentMtoUBU(privateUserId);
				message="解封成功！";
			}
		}
		
		model.addAttribute("message",message);
		return "admin_UnblockUser";
	}
	
	@RequestMapping(value="/showTopic_do")
	public String getTopic(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到话题审核信息
		String message = null;
        try{
        ArrayList<CheckTopic> tpl=adminService.getTopicInfo();
        model.addAttribute("topic_List", tpl);
        if(!tpl.isEmpty()){
        	message="话题审核信息如下：";
         }else{
        	 message="没有审核信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_CheckTopic";
	}
	
	@RequestMapping(value="/checkTOPPass_do")
	public String checkTOPPass(Model model,HttpServletRequest request,RedirectAttributes attr,HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//话题审核通过
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.checkTOPPass(select);
		if (flag==true){
			message="录入成功！";
		}else{
       	 message="录入失败！";
        }
		}catch(Exception e){
            message = "sql异常";
        } }
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/checkTOPNotPass_do")
	public String checkTOPNotPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//话题审核未通过
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		String Treason=request.getParameter("Treason");
		if(select==null){message="请选择！！！";}
		else{
		try{
		boolean flag=adminService.checkTOPNotPass(select,Treason);
		if (flag==true){
			message="<li>录入成功！</li>";
		}else{
       	 message="<li>录入失败！</li>";
        }
		}catch(Exception e){
            message = "<li>sql异常</li>";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/showReportedTopic_do")
	public String getReportedTopic(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报话题信息
		String message = null;
        try{
        ArrayList<CheckReport> TrerStuL=adminService.getTRerStudentInfo();
        model.addAttribute("TrerStuL", TrerStuL);
        ArrayList<CheckReport> TrerTeaL=adminService.getTRerTeacherInfo();
        model.addAttribute("TrerTeaL", TrerTeaL);
        ArrayList<CheckReport> TrerSenL=adminService.getTRerSeniorInfo();
        model.addAttribute("TrerSenL", TrerSenL);
        
        
        if(!TrerStuL.isEmpty()|| !TrerTeaL.isEmpty() ||!TrerSenL.isEmpty()){
        	message="举报话题信息如下：";
         }else{
        	 message="没有举报话题信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedTopic";
	}
	
	@RequestMapping(value="/delTopic_do")
	public String delTopic(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//删除话题
		//1.report表置1
		//2.message发送系统通知给话题发起者
		//3.topic表删除数据
		String message = "操作成功";
		String select[] =request.getParameterValues("reportT");
		if(select==null){message="请选择！！！";}
		else{
		adminService.delTopic(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/delTopicBU_do")
	public String delTopicBU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//删除话题 封禁用户
		//封禁用户
		//1.report表置1
		//2.message发送系统通知给话题发起者
		//3.topic表删除数据
		String message = "操作成功";
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="请选择！！！";}
		else{
		adminService.TopBU(select,closureDay);
		adminService.delTopic(select);
		}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/TopBUer_do")
	public String TopBUer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//封禁举报人
		String message = "操作成功";
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="请选择！！！";}
		else{
		adminService.TopBUer(select,closureDay);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/NeglectT_do")
	public String NeglectT(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//忽略该条举报话题信息
		String message = "操作成功";
		String select[] =request.getParameterValues("reportT");
		if(select==null){message="请选择！！！";}
		else{
		adminService.neglectT(select);}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/showReportedQuestion_do")
	public String getReportedQuestion(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报问题信息
		String message = null;
        try{
        ArrayList<CheckReport> QrerStuL=adminService.getQRerStudentInfo();
        model.addAttribute("QrerStuL", QrerStuL);
        ArrayList<CheckReport> QrerTeaL=adminService.getQRerTeacherInfo();
        model.addAttribute("QrerTeaL", QrerTeaL);
        ArrayList<CheckReport> QrerSenL=adminService.getQRerSeniorInfo();
        model.addAttribute("QrerSenL", QrerSenL);
        
        
        if(!QrerStuL.isEmpty()|| !QrerTeaL.isEmpty() ||!QrerSenL.isEmpty()){
        	message="举报问题信息如下：";
         }else{
        	 message="没有举报问题信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedQuestion";
	}
	
	@RequestMapping(value="/delQuestion_do")
	public String delQuestion(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//删除问题
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.question表删除数据
		String message = "操作成功";
		String select[] =request.getParameterValues("reportQ");
		if(select==null){message="请选择！！！";}
		else{
		adminService.delQuestion(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/delQuestionBU_do")
	public String delQuestionBU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//删除问题 封禁用户
		//封禁用户
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.question表删除数据
		String message = "操作成功";
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="请选择！！！";}
		else{
		adminService.QueBU(select,closureDay);
		adminService.delQuestion(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/QueBUer_do")
	public String QueBUer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//封禁举报人
		String message = "操作成功";
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="请选择！！！";}
		else{
		adminService.QueBUer(select,closureDay);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/NeglectQ_do")
	public String NeglectQ(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//忽略该条举报问题信息
		String message = "操作成功";
		String select[] =request.getParameterValues("reportQ");
		if(select==null){message="请选择！！！";}
		else{
		adminService.neglectQ(select);}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/showReportedAnswer_do")
	public String getReportedAnswer(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//得到举报回答信息
		String message = null;
        try{
        ArrayList<CheckReport> ArerStuL=adminService.getARerStudentInfo();
        model.addAttribute("ArerStuL", ArerStuL);
        ArrayList<CheckReport> ArerTeaL=adminService.getARerTeacherInfo();
        model.addAttribute("ArerTeaL", ArerTeaL);
        ArrayList<CheckReport> ArerSenL=adminService.getARerSeniorInfo();
        model.addAttribute("ArerSenL", ArerSenL);
        
        
        if(!ArerStuL.isEmpty()|| !ArerTeaL.isEmpty() ||!ArerSenL.isEmpty()){
        	message="举报回答如下：";
         }else{
        	 message="没有举报回答信息！";
         }
        }catch(Exception e){
            message = "sql异常";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedAnswer";
	}
	
	@RequestMapping(value="/delAnswer_do")
	public String delAnswer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//删除回答
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.answer表删除数据
		String message = "操作成功";
		String select[] =request.getParameterValues("reportA");
		if(select==null){message="请选择！！！";}
		else{
		adminService.delAnswer(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/delAnswerBU_do")
	public String delAnswerBU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//删除回答 封禁用户
		//封禁用户
		//1.report表置1
		//2.message发送系统通知给问题发起者
		//3.answer表删除数据
		String message = "操作成功";
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="请选择！！！";}
		else{
		adminService.AnsBU(select,closureDay);
		adminService.delAnswer(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/AnsBUer_do")
	public String AnsBUer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//封禁举报人
		String message = "操作成功";
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="请选择！！！";}
		else{
		adminService.AnsBUer(select,closureDay);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/NeglectA_do")
	public String NeglectA(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//忽略该条举报回答信息
		String message = "操作成功";
		String select[] =request.getParameterValues("reportA");
		if(select==null){message="请选择！！！";}
		else{
		adminService.neglectA(select);}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/views_do")
	public String views(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//访问量
		String time =request.getParameter("time");
		String x =request.getParameter("x");
		
		if(time!=""){
		int view=adminService.views(time);
		model.addAttribute("time",time);
		model.addAttribute("view",view);
		}
		else {String m="请选择时间段";
		model.addAttribute("m",m);}
		
		if(x.equals("1")) return "admin_ShowPageViewBY";
		if(x.equals("2")) return "admin_ShowPageViewBM";
		else return "admin_ShowPageViewBD";
	}
	
}
