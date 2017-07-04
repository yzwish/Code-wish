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
		//�õ���ʦ�����Ϣ
		String message = null;
        try{
        ArrayList<CheckTeacher> ctl=adminService.getTeacherInfo();
        model.addAttribute("teacher_List", ctl);
        if(!ctl.isEmpty()){
        	message="<li>��ʦ�������Ϣ���£�</li>";
         }else{
        	 message="<li>û�������Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_CheckTeacher";
	}
	
	@RequestMapping(value="/checkTPass_do")
	public String checkTPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//��ʦ���ͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		try{
		boolean flag=adminService.checkTPass(select);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/checkTNotPass_do")
	public String checkTNotPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//��ʦ���δͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		String Treason=request.getParameter("Treason");
		try{
		boolean flag=adminService.checkTNotPass(select,Treason);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/showSenior_do")
	public String getSenior(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ�ѧ��ѧ�������Ϣ
		String message = null;
        try{
        ArrayList<CheckSenior> csl=adminService.getSeniorInfo();
        model.addAttribute("senior_List", csl);
        if(!csl.isEmpty()){
        	message="<li>ѧ��ѧ��������Ϣ���£�</li>";
         }else{
        	 message="<li>û�������Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_CheckSenior";
	}
	
	@RequestMapping(value="/checkSPass_do")
	public String checkSPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ѧ��ѧ�����ͨ��
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		try{
		boolean flag=adminService.checkSPass(select);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showSenior_do";
	}
	
	@RequestMapping(value="/checkSNotPass_do")
	public String checkSNotPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ѧ��ѧ�����δͨ��
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		String Sreason=request.getParameter("Sreason");
		try{
		boolean flag=adminService.checkSNotPass(select,Sreason);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showSenior_do";
	}
	
	@RequestMapping(value="/showBlockedUser_do")
	public String getBlockedStudent(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ�����û���Ϣ
		String message = null;
        try{
        ArrayList<BlockedUser> bstl=adminService.getBlockedStudentInfo();
        model.addAttribute("blockedStu_List", bstl);
        ArrayList<BlockedUser> btl=adminService.getBlockedTeacherInfo();
        model.addAttribute("blockedTea_List", btl);
        ArrayList<BlockedUser> bsel=adminService.getBlockedSeniorInfo();
        model.addAttribute("blockedSen_List", bsel);
        
        if(!bstl.isEmpty()|| !btl.isEmpty() ||!bsel.isEmpty() ){
        	message="<li>����û���Ϣ���£�</li>";
         }else{
        	 message="<li>û�з���û���Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_ShowBlockedUser";
	}
	
	@RequestMapping(value="/showReportedUser_do")
	public String getReportedUser(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ��û���Ϣ
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
        	message="<li>�ٱ��û���Ϣ���£�</li>";
         }else{
        	 message="<li>û�оٱ��û���Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedUser";
	}

	@RequestMapping(value="/blockED_do")
	public String blockED(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//������ٱ��û�
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		try{
		boolean flag=adminService.blockED(select, BTime);
		if (flag==true){
			message="<li>����ɹ���</li>";
		}else{
       	 message="<li>���ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/blockER_do")
	public String blockER(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//����ٱ��û�
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		try{
		boolean flag=adminService.blockER(select, BTime);
		if (flag==true){
			message="<li>����ɹ���</li>";
		}else{
       	 message="<li>���ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/neglectU_do")
	public String neglectU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//���Ը����ٱ��û���Ϣ
		String message = null;
		String select[] =request.getParameterValues("reportU");
		try{
		boolean flag=adminService.neglect(select);
		if (flag==true){
			message="<li>���Գɹ���</li>";
		}else{
       	 message="<li>����ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/blockUser_do")
	public String blockUser(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//����û�
				//�ж��û��Ƿ��Ѿ������/�û��Ƿ����
				//account���и��û�״̬��4
				//blockedusers���������
				//��������û�����ϵͳ֪ͨ
				//��report����δ����ľٱ����û��ľٱ��߷���ϵͳ֪ͨ-�ٱ��ɹ�
				//report���оٱ����û�������״̬��Ϊ1		
		String message ="";
		String privateUserId =request.getParameter("user_id");
		String closureReason =request.getParameter("Breason");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		boolean flag1=adminService.checkUexist(privateUserId);
		if (flag1==false) {message="<li>�û������ڣ�</li>";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==true) {message="<li>�û��ѷ����</li>";
			}
			else {
				adminService.account4(privateUserId);
				adminService.addTableBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoER(privateUserId);
				adminService.setReportED1(privateUserId);
				message="<li>����ɹ���</li>";
			}
		}
		model.addAttribute("message",message);
		return "admin_BlockUser";
	}
	
	@RequestMapping(value="/unblockUser_do")
	public String unblockUser(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//����û�
		//0.�ж��û��Ƿ��Ѿ������/�û��Ƿ����
		//1.account����0/1
		//2.blockeduser��ɾ������
		//3.������û�����ϵͳ֪ͨ
		String message ="";
		String privateUserId =request.getParameter("user_id");
		boolean flag1=adminService.checkUexist(privateUserId);
		if (flag1==false) {message="<li>�û������ڣ�</li>";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==false) {message="<li>�û�δ�������</li>";
			}
			else {
				adminService.account01(privateUserId);
				adminService.delTableBU(privateUserId);
				adminService.sentMtoUBU(privateUserId);
				message="<li>���ɹ���</li>";
			}
		}
		
		model.addAttribute("message",message);
		return "admin_UnblockUser";
	}
	
	@RequestMapping(value="/showTopic_do")
	public String getTopic(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ����������Ϣ
		String message = null;
        try{
        ArrayList<Topic> tpl=adminService.getTopicInfo();
        model.addAttribute("topic_List", tpl);
        if(!tpl.isEmpty()){
        	message="<li>���������Ϣ���£�</li>";
         }else{
        	 message="<li>û�������Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_CheckTopic";
	}
	
	@RequestMapping(value="/checkTOPPass_do")
	public String checkTOPPass(Model model,HttpServletRequest request,RedirectAttributes attr,HttpServletResponse response) throws IOException{
		//�������ͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		try{
		boolean flag=adminService.checkTOPPass(select);
		if (flag==true){
			message="¼��ɹ���";
		}else{
       	 message="¼��ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        } 
		//ActionContext.getContext().put("loginError",message); 
		//attr.addFlashAttribute("m",message);
        model.addAttribute(message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/checkTOPNotPass_do")
	public String checkTOPNotPass(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�������δͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		String Treason=request.getParameter("Treason");
		try{
		boolean flag=adminService.checkTOPNotPass(select,Treason);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/showReportedTopic_do")
	public String getReportedTopic(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ�������Ϣ
		String message = null;
        try{
        ArrayList<Report> TrerStuL=adminService.getTRerStudentInfo();
        model.addAttribute("TrerStuL", TrerStuL);
        ArrayList<Report> TrerTeaL=adminService.getTRerTeacherInfo();
        model.addAttribute("TrerTeaL", TrerTeaL);
        ArrayList<Report> TrerSenL=adminService.getTRerSeniorInfo();
        model.addAttribute("TrerSenL", TrerSenL);
        
        
        if(!TrerStuL.isEmpty()|| !TrerTeaL.isEmpty() ||!TrerSenL.isEmpty()){
        	message="<li>�ٱ�������Ϣ���£�</li>";
         }else{
        	 message="<li>û�оٱ�������Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedTopic";
	}
	
	@RequestMapping(value="/delTopic_do")
	public String delTopic(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ɾ������
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.topic��ɾ������
		String message = null;
		String select[] =request.getParameterValues("reportT");

		adminService.delTopic(select);
		
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/delTopicBU_do")
	public String delTopicBU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ɾ������ ����û�
		//����û�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.topic��ɾ������
		String message = null;
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.TopBU(select,closureDay);
		adminService.delTopic(select);
		
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/TopBUer_do")
	public String TopBUer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//����ٱ���
		String message = null;
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.TopBUer(select,closureDay);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/NeglectT_do")
	public String NeglectT(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//���Ը����ٱ�������Ϣ
		String message = null;
		String select[] =request.getParameterValues("reportT");
		adminService.neglectT(select);
        return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/showReportedQuestion_do")
	public String getReportedQuestion(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ�������Ϣ
		String message = null;
        try{
        ArrayList<Report> QrerStuL=adminService.getQRerStudentInfo();
        model.addAttribute("QrerStuL", QrerStuL);
        ArrayList<Report> QrerTeaL=adminService.getQRerTeacherInfo();
        model.addAttribute("QrerTeaL", QrerTeaL);
        ArrayList<Report> QrerSenL=adminService.getQRerSeniorInfo();
        model.addAttribute("QrerSenL", QrerSenL);
        
        
        if(!QrerStuL.isEmpty()|| !QrerTeaL.isEmpty() ||!QrerSenL.isEmpty()){
        	message="<li>�ٱ�������Ϣ���£�</li>";
         }else{
        	 message="<li>û�оٱ�������Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedQuestion";
	}
	
	@RequestMapping(value="/delQuestion_do")
	public String delQuestion(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ɾ������
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.question��ɾ������
		String message = null;
		String select[] =request.getParameterValues("reportQ");

		adminService.delQuestion(select);
		
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/delQuestionBU_do")
	public String delQuestionBU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ɾ������ ����û�
		//����û�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.question��ɾ������
		String message = null;
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.QueBU(select,closureDay);
		adminService.delQuestion(select);
		
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/QueBUer_do")
	public String QueBUer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//����ٱ���
		String message = null;
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.QueBUer(select,closureDay);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/NeglectQ_do")
	public String NeglectQ(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//���Ը����ٱ�������Ϣ
		String message = null;
		String select[] =request.getParameterValues("reportQ");
		adminService.neglectQ(select);
        return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/showReportedAnswer_do")
	public String getReportedAnswer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ��ش���Ϣ
		String message = null;
        try{
        ArrayList<Report> ArerStuL=adminService.getARerStudentInfo();
        model.addAttribute("ArerStuL", ArerStuL);
        ArrayList<Report> ArerTeaL=adminService.getARerTeacherInfo();
        model.addAttribute("ArerTeaL", ArerTeaL);
        ArrayList<Report> ArerSenL=adminService.getARerSeniorInfo();
        model.addAttribute("ArerSenL", ArerSenL);
        
        
        if(!ArerStuL.isEmpty()|| !ArerTeaL.isEmpty() ||!ArerSenL.isEmpty()){
        	message="<li>�ٱ��ش����£�</li>";
         }else{
        	 message="<li>û�оٱ��ش���Ϣ��</li>";
         }
        }catch(Exception e){
            message = "<li>sql�쳣</li>";
        }
        model.addAttribute(message);
        return "admin_ShowReportedAnswer";
	}
	
	@RequestMapping(value="/delAnswer_do")
	public String delAnswer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ɾ���ش�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.answer��ɾ������
		String message = null;
		String select[] =request.getParameterValues("reportA");

		adminService.delAnswer(select);
		
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/delAnswerBU_do")
	public String delAnswerBU(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ɾ���ش� ����û�
		//����û�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.answer��ɾ������
		String message = null;
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.AnsBU(select,closureDay);
		adminService.delAnswer(select);
		
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/AnsBUer_do")
	public String AnsBUer(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//����ٱ���
		String message = null;
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		adminService.AnsBUer(select,closureDay);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/NeglectA_do")
	public String NeglectA(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//���Ը����ٱ��ش���Ϣ
		String message = null;
		String select[] =request.getParameterValues("reportA");
		adminService.neglectA(select);
        return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/views_do")
	public String views(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//������
		String time =request.getParameter("time");
		String x =request.getParameter("x");
		
		if(time!=""){
		int view=adminService.views(time);
		model.addAttribute("time",time);
		model.addAttribute("view",view);
		}
		else {String m="��ѡ��ʱ���";
		model.addAttribute("m",m);}
		
		
		if(x.equals("1")) return "admin_ShowPageViewBY";
		if(x.equals("2")) return "admin_ShowPageViewBM";
		else return "admin_ShowPageViewBD";
	}
	
}
