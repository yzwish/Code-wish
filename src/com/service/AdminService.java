package com.service;

import java.util.ArrayList;

import com.model.BlockedUser;
import com.model.CheckSenior;
import com.model.CheckTeacher;
import com.model.CheckReport;
import com.model.CheckTopic;

public interface AdminService {

	ArrayList<CheckTeacher> getTeacherInfo();

	boolean checkTPass(String[] select);

	boolean checkTNotPass(String[] select, String treason);

	ArrayList<CheckSenior> getSeniorInfo();

	boolean checkSPass(String[] list);

	boolean checkSNotPass(String[] list, String Treason);

	ArrayList<BlockedUser> getBlockedStudentInfo();

	ArrayList<BlockedUser> getBlockedTeacherInfo();

	ArrayList<BlockedUser> getBlockedSeniorInfo();

	ArrayList<CheckReport> getReporterStudentInfo();

	ArrayList<CheckReport> getReporterTeacherInfo();

	ArrayList<CheckReport> getReporterSeniorInfo();

	ArrayList<CheckReport> getReportedStudentInfo();

	ArrayList<CheckReport> getReportedTeacherInfo();

	ArrayList<CheckReport> getReportedSeniorInfo();

	boolean blockED(String[] list, String Bday);

	boolean blockER(String[] list, String Bday);

	boolean neglect(String[] list);

	boolean checkUexist(String privateUserId);

	boolean checkUB(String privateUserId);

	boolean account4(String privateUserId);

	boolean addTableBU(String privateUserId, String closureReason, int closureDay);

	boolean setReportED1(String privateUserId);

	boolean sentMtoER(String privateUserId);

	boolean sentMtoBU(String privateUserId, String closureReason, int closureDay);

	boolean account01(String privateUserId);

	boolean delTableBU(String privateUserId);

	boolean sentMtoUBU(String privateUserId);

	ArrayList<CheckTopic> getTopicInfo();

	boolean checkTOPPass(String[] list);

	boolean checkTOPNotPass(String[] list, String Treason);

	ArrayList<CheckReport> getTRerStudentInfo();

	ArrayList<CheckReport> getTRerTeacherInfo();

	ArrayList<CheckReport> getTRerSeniorInfo();

	boolean delTopic(String[] list);

	boolean TopBU(String[] list, int bday);

	boolean TopBUer(String[] list, int bday);

	boolean neglectT(String[] list);

	boolean delQuestion(String[] list);
	
	ArrayList<CheckReport> getQRerStudentInfo();

	ArrayList<CheckReport> getQRerSeniorInfo();

	ArrayList<CheckReport> getQRerTeacherInfo();

	boolean QueBU(String[] list, int bday);

	boolean QueBUer(String[] list, int bday);

	boolean neglectQ(String[] list);

	ArrayList<CheckReport> getARerTeacherInfo();

	ArrayList<CheckReport> getARerSeniorInfo();
	
	ArrayList<CheckReport> getARerStudentInfo();

	boolean delAnswer(String[] list);

	boolean AnsBU(String[] list, int bday);

	boolean AnsBUer(String[] list, int bday);

	boolean neglectA(String[] list);

	int views(String time);
}
