package ywq.service;

import java.util.ArrayList;

import ywq.model.CheckSenior;
import ywq.model.CheckTeacher;
import ywq.model.Report;
import ywq.model.BlockedUser;
import ywq.model.Topic;

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

	ArrayList<Report> getReporterStudentInfo();

	ArrayList<Report> getReporterTeacherInfo();

	ArrayList<Report> getReporterSeniorInfo();

	ArrayList<Report> getReportedStudentInfo();

	ArrayList<Report> getReportedTeacherInfo();

	ArrayList<Report> getReportedSeniorInfo();

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

	ArrayList<Topic> getTopicInfo();

	boolean checkTOPPass(String[] list);

	boolean checkTOPNotPass(String[] list, String Treason);

	ArrayList<Report> getTRerStudentInfo();

	ArrayList<Report> getTRerTeacherInfo();

	ArrayList<Report> getTRerSeniorInfo();

	boolean delTopic(String[] list);

	boolean TopBU(String[] list, int bday);

	boolean TopBUer(String[] list, int bday);

	boolean neglectT(String[] list);

	boolean delQuestion(String[] list);
	
	ArrayList<Report> getQRerStudentInfo();

	ArrayList<Report> getQRerSeniorInfo();

	ArrayList<Report> getQRerTeacherInfo();

	boolean QueBU(String[] list, int bday);

	boolean QueBUer(String[] list, int bday);

	boolean neglectQ(String[] list);

	ArrayList<Report> getARerTeacherInfo();

	ArrayList<Report> getARerSeniorInfo();
	
	ArrayList<Report> getARerStudentInfo();

	boolean delAnswer(String[] list);

	boolean AnsBU(String[] list, int bday);

	boolean AnsBUer(String[] list, int bday);

	boolean neglectA(String[] list);

	int views(String time);
}
