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
		//�õ���ʦ�����Ϣ
		String message = null;
        try{
        ArrayList<CheckTeacher> ctl=adminService.getTeacherInfo();
        model.addAttribute("teacher_List", ctl);
        if(!ctl.isEmpty()){
        	message="��ʦ�������Ϣ���£�";
         }else{
        	 message="û�������Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_CheckTeacher";
	}
	
	@RequestMapping(value="/checkTPass_do")
	public String checkTPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//��ʦ���ͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.checkTPass(select);
		if (flag==true){
			message="¼��ɹ���";
		}else{
       	 message="¼��ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/checkTNotPass_do")
	public String checkTNotPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//��ʦ���δͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTea");
		String Treason=request.getParameter("Treason");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.checkTNotPass(select,Treason);
		if (flag==true){
			message="¼��ɹ���";
		}else{
       	 message="¼��ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTeacher_do";
	}
	
	@RequestMapping(value="/showSenior_do")
	public String getSenior(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ�ѧ��ѧ�������Ϣ
		String message = null;
        try{
        ArrayList<CheckSenior> csl=adminService.getSeniorInfo();
        model.addAttribute("senior_List", csl);
        if(!csl.isEmpty()){
        	message="ѧ��ѧ��������Ϣ���£�";
         }else{
        	 message="û�������Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_CheckSenior";
	}
	
	@RequestMapping(value="/checkSPass_do")
	public String checkSPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ѧ��ѧ�����ͨ��
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.checkSPass(select);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showSenior_do";
	}
	
	@RequestMapping(value="/checkSNotPass_do")
	public String checkSNotPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ѧ��ѧ�����δͨ��
		String message = null;
		String select[] =request.getParameterValues("checkSen");
		String Sreason=request.getParameter("Sreason");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.checkSNotPass(select,Sreason);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }}
		redirectattributes.addFlashAttribute("message2",message);
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
        	message="����û���Ϣ����";
         }else{
        	 message="û�з���û���Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message",message);
        return "admin_ShowBlockedUser";
	}
	
	@RequestMapping(value="/showReportedUser_do")
	public String getReportedUser(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ��û���Ϣ
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
        	message="�ٱ��û���Ϣ���£�";
         }else{
        	 message="û�оٱ��û���Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedUser";
	}

	@RequestMapping(value="/blockED_do")
	public String blockED(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//������ٱ��û�
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.blockED(select, BTime);
		if (flag==true){
			message="����ɹ���";
		}else{
       	 message="���ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/blockER_do")
	public String blockER(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//����ٱ��û�
		String message = null;
		String select[] =request.getParameterValues("reportU");
		String BTime=request.getParameter("BTime");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.blockER(select, BTime);
		if (flag==true){
			message="����ɹ���";
		}else{
       	 message="���ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedUser_do";
	}
	
	@RequestMapping(value="/neglectU_do")
	public String neglectU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//���Ը����ٱ��û���Ϣ
		String message = null;
		String select[] =request.getParameterValues("reportU");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.neglect(select);
		if (flag==true){
			message="���Գɹ���";
		}else{
       	 message="����ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        }}
		redirectattributes.addFlashAttribute("message2",message);
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
		if (flag1==false) {message="�û������ڣ�";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==true) {message="�û��ѷ����";
			}
			else {
				adminService.account4(privateUserId);
				adminService.addTableBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoBU(privateUserId, closureReason, closureDay);
				adminService.sentMtoER(privateUserId);
				adminService.setReportED1(privateUserId);
				message="����ɹ���";
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
		if (flag1==false) {message="�û������ڣ�";
		}
		else{
			boolean flag2=adminService.checkUB(privateUserId);
			if (flag2==false) {message="�û�δ�������";
			}
			else {
				adminService.account01(privateUserId);
				adminService.delTableBU(privateUserId);
				adminService.sentMtoUBU(privateUserId);
				message="���ɹ���";
			}
		}
		
		model.addAttribute("message",message);
		return "admin_UnblockUser";
	}
	
	@RequestMapping(value="/showTopic_do")
	public String getTopic(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ����������Ϣ
		String message = null;
        try{
        ArrayList<CheckTopic> tpl=adminService.getTopicInfo();
        model.addAttribute("topic_List", tpl);
        if(!tpl.isEmpty()){
        	message="���������Ϣ���£�";
         }else{
        	 message="û�������Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_CheckTopic";
	}
	
	@RequestMapping(value="/checkTOPPass_do")
	public String checkTOPPass(Model model,HttpServletRequest request,RedirectAttributes attr,HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//�������ͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.checkTOPPass(select);
		if (flag==true){
			message="¼��ɹ���";
		}else{
       	 message="¼��ʧ�ܣ�";
        }
		}catch(Exception e){
            message = "sql�쳣";
        } }
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/checkTOPNotPass_do")
	public String checkTOPNotPass(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//�������δͨ��
		String message = null;
		String select[] =request.getParameterValues("checkTop");
		String Treason=request.getParameter("Treason");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		try{
		boolean flag=adminService.checkTOPNotPass(select,Treason);
		if (flag==true){
			message="<li>¼��ɹ���</li>";
		}else{
       	 message="<li>¼��ʧ�ܣ�</li>";
        }
		}catch(Exception e){
            message = "<li>sql�쳣</li>";
        }}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showTopic_do";
	}
	
	@RequestMapping(value="/showReportedTopic_do")
	public String getReportedTopic(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ�������Ϣ
		String message = null;
        try{
        ArrayList<CheckReport> TrerStuL=adminService.getTRerStudentInfo();
        model.addAttribute("TrerStuL", TrerStuL);
        ArrayList<CheckReport> TrerTeaL=adminService.getTRerTeacherInfo();
        model.addAttribute("TrerTeaL", TrerTeaL);
        ArrayList<CheckReport> TrerSenL=adminService.getTRerSeniorInfo();
        model.addAttribute("TrerSenL", TrerSenL);
        
        
        if(!TrerStuL.isEmpty()|| !TrerTeaL.isEmpty() ||!TrerSenL.isEmpty()){
        	message="�ٱ�������Ϣ���£�";
         }else{
        	 message="û�оٱ�������Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedTopic";
	}
	
	@RequestMapping(value="/delTopic_do")
	public String delTopic(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ɾ������
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.topic��ɾ������
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportT");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.delTopic(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/delTopicBU_do")
	public String delTopicBU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ɾ������ ����û�
		//����û�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.topic��ɾ������
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.TopBU(select,closureDay);
		adminService.delTopic(select);
		}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/TopBUer_do")
	public String TopBUer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//����ٱ���
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportT");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.TopBUer(select,closureDay);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/NeglectT_do")
	public String NeglectT(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//���Ը����ٱ�������Ϣ
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportT");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.neglectT(select);}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedTopic_do";
	}
	
	@RequestMapping(value="/showReportedQuestion_do")
	public String getReportedQuestion(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ�������Ϣ
		String message = null;
        try{
        ArrayList<CheckReport> QrerStuL=adminService.getQRerStudentInfo();
        model.addAttribute("QrerStuL", QrerStuL);
        ArrayList<CheckReport> QrerTeaL=adminService.getQRerTeacherInfo();
        model.addAttribute("QrerTeaL", QrerTeaL);
        ArrayList<CheckReport> QrerSenL=adminService.getQRerSeniorInfo();
        model.addAttribute("QrerSenL", QrerSenL);
        
        
        if(!QrerStuL.isEmpty()|| !QrerTeaL.isEmpty() ||!QrerSenL.isEmpty()){
        	message="�ٱ�������Ϣ���£�";
         }else{
        	 message="û�оٱ�������Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedQuestion";
	}
	
	@RequestMapping(value="/delQuestion_do")
	public String delQuestion(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ɾ������
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.question��ɾ������
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportQ");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.delQuestion(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/delQuestionBU_do")
	public String delQuestionBU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ɾ������ ����û�
		//����û�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.question��ɾ������
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.QueBU(select,closureDay);
		adminService.delQuestion(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/QueBUer_do")
	public String QueBUer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//����ٱ���
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportQ");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.QueBUer(select,closureDay);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/NeglectQ_do")
	public String NeglectQ(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//���Ը����ٱ�������Ϣ
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportQ");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.neglectQ(select);}
		redirectattributes.addFlashAttribute("message2",message);
        return "redirect:/showReportedQuestion_do";
	}
	
	@RequestMapping(value="/showReportedAnswer_do")
	public String getReportedAnswer(@ModelAttribute("message2") String message2,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�õ��ٱ��ش���Ϣ
		String message = null;
        try{
        ArrayList<CheckReport> ArerStuL=adminService.getARerStudentInfo();
        model.addAttribute("ArerStuL", ArerStuL);
        ArrayList<CheckReport> ArerTeaL=adminService.getARerTeacherInfo();
        model.addAttribute("ArerTeaL", ArerTeaL);
        ArrayList<CheckReport> ArerSenL=adminService.getARerSeniorInfo();
        model.addAttribute("ArerSenL", ArerSenL);
        
        
        if(!ArerStuL.isEmpty()|| !ArerTeaL.isEmpty() ||!ArerSenL.isEmpty()){
        	message="�ٱ��ش����£�";
         }else{
        	 message="û�оٱ��ش���Ϣ��";
         }
        }catch(Exception e){
            message = "sql�쳣";
        }
        model.addAttribute("message1",message);
        model.addAttribute("message2",message2);
        return "admin_ShowReportedAnswer";
	}
	
	@RequestMapping(value="/delAnswer_do")
	public String delAnswer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ɾ���ش�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.answer��ɾ������
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportA");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.delAnswer(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/delAnswerBU_do")
	public String delAnswerBU(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//ɾ���ش� ����û�
		//����û�
		//1.report����1
		//2.message����ϵͳ֪ͨ�����ⷢ����
		//3.answer��ɾ������
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.AnsBU(select,closureDay);
		adminService.delAnswer(select);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/AnsBUer_do")
	public String AnsBUer(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//����ٱ���
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportA");
		int closureDay =Integer.parseInt(request.getParameter("BTime"));
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.AnsBUer(select,closureDay);}
		redirectattributes.addFlashAttribute("message2",message);
		return "redirect:/showReportedAnswer_do";
	}
	
	@RequestMapping(value="/NeglectA_do")
	public String NeglectA(Model model,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectattributes) throws IOException{
		//���Ը����ٱ��ش���Ϣ
		String message = "�����ɹ�";
		String select[] =request.getParameterValues("reportA");
		if(select==null){message="��ѡ�񣡣���";}
		else{
		adminService.neglectA(select);}
		redirectattributes.addFlashAttribute("message2",message);
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
